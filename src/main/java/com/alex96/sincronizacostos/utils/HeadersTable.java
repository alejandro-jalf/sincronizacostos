/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.alex96.sincronizacostos.utils;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Jose Alejandro Lopez Flores
 */
public class HeadersTable implements TableCellRenderer {
    JComponent jcomponent = null;
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        
        if( value instanceof String ) {
            jcomponent = new JLabel((String) value);
            ((JLabel)jcomponent).setHorizontalAlignment( SwingConstants.LEFT );
            ((JLabel)jcomponent).setSize( 30, jcomponent.getWidth() );   
            ((JLabel)jcomponent).setPreferredSize( new Dimension(6, jcomponent.getWidth())  );
        }         
   
        jcomponent.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 1, new java.awt.Color(255, 255, 255)));
        jcomponent.setBorder(new EmptyBorder(0,10,0,0));
        jcomponent.setOpaque(true);
        jcomponent.setBackground( new Color(65,65,65) );
        jcomponent.setForeground(Color.WHITE);
//        jcomponent.setBackground(new Color(7, 8, 19));
        jcomponent.setToolTipText("Lista de articulos");
        jcomponent.setFont(new Font("Arial", Font.PLAIN, 15));
        
        return jcomponent;
    }
    
}
