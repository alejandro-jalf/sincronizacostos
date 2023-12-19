/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.alex96.sincronizacostos.views;

import Controllers.ControllerArticulos;
import Controllers.ControllerMovimientos;
import com.alex96.sincronizacostos.Models.Articulo;
import com.alex96.sincronizacostos.Models.Movimiento;
import com.alex96.sincronizacostos.utils.HeadersTable;
import com.alex96.sincronizacostos.utils.PaintRowTable;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author alefl
 */
public class Details extends javax.swing.JFrame {
    private DefaultTableModel modelTable;
    private Details details;
    private Principal principal;
    private Movimiento movimiento = null;
    private Articulo articulo;
    private ControllerMovimientos controllerMovimientos;
    private ControllerArticulos controllerArticulos;
    private String[] row = new String[8];
    
    /**
     * Creates new form Details
     * @param movimiento Instancia de la clase de Movimiento
     * @param principal Instancia de la clase de Principal
     */
    public Details(Movimiento movimiento, Principal principal) {
        initComponents();
        this.principal = principal;
        this.movimiento = movimiento;
        this.details = this;
        setConfigWindow();
        setTable();
        showData();
    }
    
    private void setConfigWindow() {
        this.getContentPane().setBackground(new Color(247, 247, 247));
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    principal.setEnabled(true);
                    principal.toFront();
                    details.dispose();
                }
        });
    }
    
    private void setTable() {
        // tableMovimientos.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tableArticulos.setDefaultRenderer(Object.class, new PaintRowTable());
        modelTable = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        modelTable.addColumn("Articulo");
        modelTable.addColumn("Nombre");
        modelTable.addColumn("Relacion");
        modelTable.addColumn("Cantidad");
        modelTable.addColumn("Unidad");
        modelTable.addColumn("Cajas");
        modelTable.addColumn("Unidad");
        modelTable.addColumn("Costo");
        
        tableArticulos.setModel(modelTable);
        
        JTableHeader headerTable = tableArticulos.getTableHeader();
        headerTable.setDefaultRenderer(new HeadersTable());
        tableArticulos.setTableHeader(headerTable);
        
        tableArticulos.setRowHeight(30);
        tableArticulos.getColumnModel().getColumn(0).setPreferredWidth(60); // Articulo
        tableArticulos.getColumnModel().getColumn(1).setPreferredWidth(250); // Nombre
        tableArticulos.getColumnModel().getColumn(2).setPreferredWidth(80); // Relacion
        tableArticulos.getColumnModel().getColumn(3).setPreferredWidth(60); // Cantidad
        tableArticulos.getColumnModel().getColumn(4).setPreferredWidth(40); // Unidad
        tableArticulos.getColumnModel().getColumn(5).setPreferredWidth(60); // Cajas
        tableArticulos.getColumnModel().getColumn(6).setPreferredWidth(40); // Unidad
        tableArticulos.getColumnModel().getColumn(7).setPreferredWidth(80); // Costo
    }
    
    private void showData() {
        SimpleDateFormat formatFecha = new SimpleDateFormat("dd/MM/YYYY");
        SimpleDateFormat formatHora = new SimpleDateFormat("hh:mm:ss a");
        
        textDocumento.setText(movimiento.getDocumento());
        textReferencia.setText(movimiento.getReferencia());
        textAlmacen.setText(movimiento.getAlmacen());
        textCajero.setText(movimiento.getNombreCajero());
        textObservaciones.setText(movimiento.getObservaciones());
        textFecha.setText(formatFecha.format(movimiento.getFecha()));
        textHora.setText(formatHora.format(movimiento.getHora()));
        textCaja.setText(movimiento.getCaja());
        textArticulos.setText(String.valueOf(movimiento.getListArticulos().size()));
        textProveedor.setText(movimiento.getNombreTercero());
        
        
        ArrayList<Articulo> listaArticulos = movimiento.getListArticulos();
        loadTable(listaArticulos);
    }
    
    private void loadTable(ArrayList<Articulo> listaArticulos) {
        cleanTable();
        for (int fila = 0; fila < listaArticulos.size(); fila++) {
            articulo = listaArticulos.get(fila);
            row[0] = articulo.getArticulo();
            row[1] = articulo.getNombre();
            row[2] = articulo.getRelacion();
            row[3] = String.valueOf(articulo.getCantidadRegular());
            row[4] = articulo.getUnidadVenta();
            row[5] = String.valueOf(articulo.getCantidadRegularUC());
            row[6] = articulo.getUnidadCompra();
            row[7] = String.valueOf(articulo.getCosto());
            modelTable.addRow(row);
        }
        tableArticulos.setModel(modelTable);
    }
    
    private void cleanTable() {
        modelTable = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        modelTable.addColumn("Articulo");
        modelTable.addColumn("Nombre");
        modelTable.addColumn("Relacion");
        modelTable.addColumn("Cantidad");
        modelTable.addColumn("Unidad");
        modelTable.addColumn("Cajas");
        modelTable.addColumn("Unidad");
        modelTable.addColumn("Costo");
        
        tableArticulos.setModel(modelTable);
        
        tableArticulos.setRowHeight(30);
        tableArticulos.getColumnModel().getColumn(0).setPreferredWidth(60); // Documento
        tableArticulos.getColumnModel().getColumn(1).setPreferredWidth(250); // Referencia
        tableArticulos.getColumnModel().getColumn(2).setPreferredWidth(80); // Nombre Tercero
        tableArticulos.getColumnModel().getColumn(3).setPreferredWidth(60); // Articulos
        tableArticulos.getColumnModel().getColumn(4).setPreferredWidth(40); // Almacen
        tableArticulos.getColumnModel().getColumn(5).setPreferredWidth(60); // Fecha
        tableArticulos.getColumnModel().getColumn(6).setPreferredWidth(40); // Hora
        tableArticulos.getColumnModel().getColumn(7).setPreferredWidth(80); // Acciones
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        textDocumento = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        textReferencia = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        textAlmacen = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        textCajero = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        textObservaciones = new javax.swing.JTextArea();
        jLabel6 = new javax.swing.JLabel();
        textFecha = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        textHora = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        textCaja = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        textArticulos = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        textProveedor = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableArticulos = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel1.setText("Documento:");

        textDocumento.setEditable(false);
        textDocumento.setBackground(new java.awt.Color(230, 230, 230));
        textDocumento.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel2.setText("Referencia");

        textReferencia.setEditable(false);
        textReferencia.setBackground(new java.awt.Color(230, 230, 230));
        textReferencia.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel3.setText("Almacen:");

        textAlmacen.setEditable(false);
        textAlmacen.setBackground(new java.awt.Color(230, 230, 230));
        textAlmacen.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel4.setText("Cajero:");

        textCajero.setEditable(false);
        textCajero.setBackground(new java.awt.Color(230, 230, 230));
        textCajero.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel5.setText("Observaciones");

        jScrollPane1.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N

        textObservaciones.setEditable(false);
        textObservaciones.setBackground(new java.awt.Color(230, 230, 230));
        textObservaciones.setColumns(20);
        textObservaciones.setRows(3);
        jScrollPane1.setViewportView(textObservaciones);

        jLabel6.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel6.setText("Fecha:");

        textFecha.setEditable(false);
        textFecha.setBackground(new java.awt.Color(230, 230, 230));
        textFecha.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel7.setText("Hora:");

        textHora.setEditable(false);
        textHora.setBackground(new java.awt.Color(230, 230, 230));
        textHora.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel8.setText("Caja:");

        textCaja.setEditable(false);
        textCaja.setBackground(new java.awt.Color(230, 230, 230));
        textCaja.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel9.setText("Articulos:");

        textArticulos.setEditable(false);
        textArticulos.setBackground(new java.awt.Color(230, 230, 230));
        textArticulos.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N

        jLabel10.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel10.setText("Proveedor:");

        textProveedor.setEditable(false);
        textProveedor.setBackground(new java.awt.Color(230, 230, 230));
        textProveedor.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N

        tableArticulos.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        tableArticulos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Articulo", "Nombre", "Relacion", "Cantidad", "Unidad", "Caja", "Unidad", "Costo"
            }
        ));
        jScrollPane2.setViewportView(tableArticulos);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(textDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(textCajero))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel8)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(textCaja, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(textFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(textHora))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel9)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(textArticulos)))))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(textProveedor))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(textReferencia, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(textAlmacen, javax.swing.GroupLayout.PREFERRED_SIZE, 354, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 647, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 55, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(textReferencia, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(textAlmacen, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(textDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(textCajero, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(textFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)
                            .addComponent(textHora, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(textCaja, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(textArticulos, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(textProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 498, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Details.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Details.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Details.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Details.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Details(new Movimiento(), new Principal()).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tableArticulos;
    private javax.swing.JTextField textAlmacen;
    private javax.swing.JTextField textArticulos;
    private javax.swing.JTextField textCaja;
    private javax.swing.JTextField textCajero;
    private javax.swing.JTextField textDocumento;
    private javax.swing.JTextField textFecha;
    private javax.swing.JTextField textHora;
    private javax.swing.JTextArea textObservaciones;
    private javax.swing.JTextField textProveedor;
    private javax.swing.JTextField textReferencia;
    // End of variables declaration//GEN-END:variables
}
