/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alex96.sincronizacostos.utils;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Jose Alejandro Lopez Flores
 */
public class PaintRowTable extends DefaultTableCellRenderer {
    private double pedido, cajas;
    private double diferencia;
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
 
//        pedido = (double) table.getValueAt(row, 5);
//        cajas = (double) table.getValueAt(row, 11);
//        diferencia = (double) table.getValueAt(row, 17);
        
        if (row % 2 != 0) setBackground(new Color(243, 243, 243));
        else setBackground(Color.WHITE);
        setForeground(Color.BLACK);
        
//        if ((column == 5 || column == 11) && pedido != cajas) setBackground(new Color(255, 239, 100));
//        if (column == 17 && diferencia < 0) setBackground(new Color(255, 100, 100));
        if (column == 7) setBackground(new Color(144, 255, 100));
        
        if (value instanceof JButton) {
            JButton btn =  (JButton) value;
            return btn;
        }
        
        
        setBorder(new LineBorder(Color.GRAY));
        setOpaque(true);
        
        return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
    }
}
