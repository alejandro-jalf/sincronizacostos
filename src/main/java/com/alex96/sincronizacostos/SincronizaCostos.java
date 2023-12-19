/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.alex96.sincronizacostos;

import com.alex96.sincronizacostos.utils.Conexion;
import com.alex96.sincronizacostos.views.Principal;
import javax.swing.JFrame;

/**
 *
 * @author alefl
 */
public class SincronizaCostos {

    public static void main(String[] args) {
//        Conexion conexion = new Conexion();
//        conexion.getConexion();

        Principal principal = new Principal();
        principal.setVisible(true);
    }
}
