/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.alex96.sincronizacostos.Models;

import java.util.Date;

/**
 *
 * @author alefl
 */
public class Articulo { //
    private String Documento = null;
    private String Articulo = null;
    private String Nombre = null;
    private double Costo = 0;
    private double CantidadRegular = 0;
    private double UltimoCosto = 0;
    private int Almacen = 0;
    private Date Fecha = null;
    private Date Hora = null;

    public String getArticulo() {
        return Articulo;
    }

    public void setArticulo(String Articulo) {
        this.Articulo = Articulo;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public double getCosto() {
        return Costo;
    }

    public void setCosto(double Costo) {
        this.Costo = Costo;
    }

    public double getCantidadRegular() {
        return CantidadRegular;
    }

    public void setCantidadRegular(double CantidadRegular) {
        this.CantidadRegular = CantidadRegular;
    }

    public double getUltimoCosto() {
        return UltimoCosto;
    }

    public void setUltimoCosto(double UltimoCosto) {
        this.UltimoCosto = UltimoCosto;
    }

    public int getAlmacen() {
        return Almacen;
    }

    public void setAlmacen(int Almacen) {
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

    public String getDocumento() {
        return Documento;
    }

    public void setDocumento(String Documento) {
        this.Documento = Documento;
    }
}
