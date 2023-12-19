/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.alex96.sincronizacostos.Models;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author alefl
 */
public class Movimiento {
    private String Documento = null;
    private String Referencia = null;
    private String NombreTercero = null;
    private int CountArticulos = 0;
    private String Almacen = null;
    private Date Fecha = null;
    private Date Hora = null;
    private ArrayList <Articulo> ListArticulos = null;

    public String getDocumento() {
        return Documento;
    }

    public void setDocumento(String Documento) {
        this.Documento = Documento;
    }

    public String getReferencia() {
        return Referencia;
    }

    public void setReferencia(String Referencia) {
        this.Referencia = Referencia;
    }

    public String getNombreTercero() {
        return NombreTercero;
    }

    public void setNombreTercero(String NombreTercero) {
        this.NombreTercero = NombreTercero;
    }

    public int getCountArticulos() {
        return CountArticulos;
    }

    public void setCountArticulos(int CountArticulos) {
        this.CountArticulos = CountArticulos;
    }

    public String getAlmacen() {
        return Almacen;
    }

    public void setAlmacen(String Almacen) {
        this.Almacen = Almacen;
    }

    public Date getFecha() {
        return Fecha;
    }

    public void setFecha(Date Fecha) {
        this.Fecha = Fecha;
    }

    public Date getHora() {
        return Hora;
    }

    public void setHora(Date Hora) {
        this.Hora = Hora;
    }

    public ArrayList<Articulo> getListArticulos() {
        return ListArticulos;
    }

    public void setListArticulos(ArrayList<Articulo> ListArticulos) {
        this.ListArticulos = ListArticulos;
    }
}
