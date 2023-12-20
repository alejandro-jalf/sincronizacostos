/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controllers;

import com.alex96.sincronizacostos.Models.Articulo;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author alefl
 */
public class ControllerArticulos {
    private Articulo articulo = null;
    private ArrayList<Articulo> listArticulos = null;

    public ControllerArticulos() {
        this.listArticulos = new ArrayList<>();
    }
    
    public void setListArticulos(ArrayList<Articulo> listArticulos) {
        this.listArticulos = listArticulos;
    }
    
    public void addArticulo(
        String Documento, String Articulo, String Nombre, double Costo, double CantidadRegular, double UltimoCosto, int Almacen, Date Fecha, Date Hora,
        String Relacion, String UnidadCompra, String UnidadVenta, double CantidadRegularUC, double CostoUnitarioNetoUC, double CostoValorNeto
    ) {
        articulo = new Articulo();
        articulo.setDocumento(Documento);
        articulo.setArticulo(Articulo);
        articulo.setNombre(Nombre);
        articulo.setCosto(Costo);
        articulo.setCantidadRegular(CantidadRegular);
        articulo.setUltimoCosto(UltimoCosto);
        articulo.setAlmacen(Almacen);
        articulo.setFecha(Fecha);
        articulo.setHora(Hora);
        articulo.setRelacion(Relacion);
        articulo.setUnidadCompra(UnidadCompra);
        articulo.setUnidadVenta(UnidadVenta);
        articulo.setCantidadRegularUC(CantidadRegularUC);
        articulo.setCostoUnitarioNetoUC(CostoUnitarioNetoUC);
        articulo.setCostoValorNeto(CostoValorNeto);
        this.listArticulos.add(articulo);
    }
    
    public Articulo getArticulo(String Articulo) {
        this.articulo = null;
        for (Articulo articulo : listArticulos) {
            if (articulo.getArticulo().equals(Articulo)){
                this.articulo = articulo;
                break;
            }
        }
        return this.articulo;
    }
    
    public ArrayList<Articulo> getList(String Documento) {
        for (Articulo articulo : listArticulos)
            if (articulo.getDocumento().equals(Documento))return this.listArticulos;
        return null;
    }
    
    public boolean exist(String Documento) {
        for (Articulo articulo : listArticulos)
            if (articulo.getDocumento().equals(Documento))return true;
        return false;
    }
    
    public ArrayList<Articulo> getList() {
        return listArticulos;
    }
    
    public int getSize() {
        return listArticulos.size();
    }
}
