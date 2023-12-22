/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.alex96.sincronizacostos.Models;

import Controllers.ControllerArticulos;
import Controllers.ControllerMovimientos;
import com.alex96.sincronizacostos.utils.Conexion;
import com.alex96.sincronizacostos.utils.MessagesResult;
import com.alex96.sincronizacostos.utils.Results;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author alefl
 */
public class Movimientos {
    private Conexion conexion;
    private Connection con;
//    private PreparedStatement pstmt;
    private Statement statement;
    private ResultSet resultSet;
    private MessagesResult mr;
    private JSONArray data;
    private JSONObject response;
    private JSONObject row;

    public Movimientos(Conexion conexion, MessagesResult mr) {
        this.conexion = conexion;
        this.mr = mr;
    }
    
    /**
    * @param fecha Cadena con la fecha del dia de consulta en formato  YYYYMMdd
    * @return Retorna un Objeto JSON con los movimientos de ese dia
    */
    public JSONObject getMovimientos(String fecha) {
        try {
            con = conexion.getConexion();
            statement = con.createStatement();
            resultSet = statement.executeQuery(
                "DECLARE @Fecha datetime = CAST('" + fecha + "' AS datetime);" +
                """
                DECLARE @Tienda NVARCHAR(1) = '7';
                DECLARE @Almacen2 NVARCHAR(2) = '11';
                DECLARE @Almacen1 NVARCHAR(2) = '12';
                
                SELECT
                    Documento, Referencia, NombreTercero, D.Articulo, D.Nombre, DescripcionAlmacen, Fecha, Hora, CostoUnitarioNeto, L.UltimoCosto, D.Almacen,
                    Diferencia = ABS(D.CostoUnitario-L.UltimoCosto), D.CantidadRegularUC, L.UnidadCompra, CantidadRegular, L.UnidadVenta,
                    Relacion = CAST(CAST(L.FactorCompra AS INT) AS NVARCHAR) + '/' + L.UnidadCompra + ' - ' + CAST(CAST(L.FactorVenta AS INT) AS NVARCHAR) + '/' + L.UnidadVenta,
                    D.NombreCajero, D.Observaciones, D.Caja, D.CostoUnitarioNetoUC, D.CostoValorNeto, D.CostoUnitario, L.DescAlmacen, AlmacenArticulo = L.Almacen
                FROM QVDEMovAlmacen D
                LEFT JOIN QVExistencias L ON D.Articulo = L.Articulo AND D.Tienda = L.Tienda AND (D.Almacen = @Almacen1 OR D.Almacen = @Almacen2)
                WHERE D.Fecha = @Fecha AND D.TipoDocumento IN ('C', 'E') AND (D.Almacen = @Almacen1 OR D.Almacen = @Almacen2)
                ORDER BY Documento DESC, Fecha, Hora; """
            );
            data = new JSONArray();
            while (resultSet.next()) {
                row = new JSONObject();
                row.put("Documento", resultSet.getString("Documento"));
                row.put("Referencia", resultSet.getString("Referencia"));
                row.put("NombreTercero", resultSet.getString("NombreTercero"));
                row.put("Articulo", resultSet.getString("Articulo"));
                row.put("Nombre", resultSet.getString("Nombre"));
                row.put("DescripcionAlmacen", resultSet.getString("DescripcionAlmacen"));
                row.put("Fecha", resultSet.getDate("Fecha"));
                row.put("Hora", resultSet.getTimestamp("Hora"));
                row.put("CostoUnitario", resultSet.getDouble("CostoUnitarioNeto"));
                row.put("UltimoCosto", resultSet.getDouble("UltimoCosto"));
                row.put("Almacen", resultSet.getDouble("Almacen"));
                row.put("Diferencia", resultSet.getDouble("Diferencia"));
                row.put("CantidadRegularUC", resultSet.getDouble("CantidadRegularUC"));
                row.put("UnidadCompra", resultSet.getString("UnidadCompra"));
                row.put("CantidadRegular", resultSet.getDouble("CantidadRegular"));
                row.put("UnidadVenta", resultSet.getString("UnidadVenta"));
                row.put("Relacion", resultSet.getString("Relacion"));
                row.put("NombreCajero", resultSet.getString("NombreCajero"));
                row.put("Observaciones", resultSet.getString("Observaciones"));
                row.put("Caja", resultSet.getString("Caja"));
                row.put("CostoUnitarioNetoUC", resultSet.getDouble("CostoUnitarioNetoUC"));
                row.put("CostoValorNeto", resultSet.getDouble("CostoValorNeto"));
                row.put("CostoUnitario", resultSet.getDouble("CostoUnitario"));
                row.put("DescAlmacen", resultSet.getString("DescAlmacen"));
                row.put("AlmacenArticulo", resultSet.getString("AlmacenArticulo"));
                
                data.put(row);
            }
            conexion.closeConexion();
            if (data.isEmpty()) response = mr.createContentError("La lista de movimientos esta vacia", null);
            else response = mr.createContentAssert("Movimientos encontrados", data);
            return response;
        } catch (SQLException sqle) {
            System.out.println("Error al connectar con la base de datos al obtener los movimientos: " + sqle.getMessage());
            JOptionPane.showMessageDialog(null, "error" + sqle.getMessage());
            return mr.createContentError("Error al obtener los movimientos: " + sqle.getMessage(), null);
        } catch(JSONException e) {
            JOptionPane.showMessageDialog(null, "error" + e.getMessage());
            return mr.createContentError("Error al convertir los datos al obtener los movimientos: " + e.getMessage(), null);
        } catch(Exception e) {
            JOptionPane.showMessageDialog(null, "error" + e.getMessage());
            return mr.createContentError("Error al convertir los datos al obtener los movimientos: " + e.getMessage(), null);
        }
    }
}
