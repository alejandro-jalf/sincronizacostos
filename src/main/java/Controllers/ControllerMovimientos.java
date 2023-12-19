/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controllers;

import com.alex96.sincronizacostos.Models.Articulo;
import com.alex96.sincronizacostos.Models.Movimiento;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author alefl
 */
public class ControllerMovimientos {
    private Movimiento movimiento = null;
    private ArrayList<Movimiento> listMovimientos = null;
    private ArrayList<Articulo> listArticulos = null;

    public ControllerMovimientos() {
        this.listMovimientos = new ArrayList<>();
    }
    
    public void addMovimiento(
        String Documento, String Referencia, String NombreTercero, int CountArticulos, String Almacen, Date Fecha, Date Hora, ArrayList<Articulo> ListArticulos,
        String Caja, String NombreCajero, String Observaciones
    ) {
        movimiento = new Movimiento();
        movimiento.setDocumento(Documento);
        movimiento.setReferencia(Referencia);
        movimiento.setNombreTercero(NombreTercero);
        movimiento.setCountArticulos(CountArticulos);
        movimiento.setAlmacen(Almacen);
        movimiento.setFecha(Fecha);
        movimiento.setHora(Hora);
        movimiento.setListArticulos(ListArticulos);
        movimiento.setCaja(Caja);
        movimiento.setNombreCajero(NombreCajero);
        movimiento.setObservaciones(Observaciones);
        listMovimientos.add(movimiento);
    }
    
    public Movimiento getMovimiento(String Documento) {
        this.movimiento = null;
        for (Movimiento movimiento : listMovimientos) {
            if (movimiento.getDocumento().equals(Documento)){
                this.movimiento = movimiento;
                break;
            }
        }
        return this.movimiento;
    }
    
    public Movimiento getMovimiento(int position) {
        return listMovimientos.get(position);
    }
    
    public void resetMovimientos() {
        this.listMovimientos = new ArrayList<>();
    }
    
    public boolean exist(String Documento) {
        for (Movimiento movimiento : listMovimientos)
            if (movimiento.getDocumento().equals(Documento))return true;
        return false;
    }
    
    public ArrayList<Articulo> getListArticulos(String Documento) {
        this.listArticulos = null;
        for (Movimiento movimiento : listMovimientos) {
            if (movimiento.getDocumento().equals(Documento)){
                this.listArticulos = movimiento.getListArticulos();
                break;
            }
        }
        return this.listArticulos;
    }
    
    public ArrayList<Movimiento> getList() {
        return listMovimientos;
    }
    
    public int getSize() {
        return listMovimientos.size();
    }
    
    public Movimiento get(int position) {
        return listMovimientos.get(position);
    }
}
