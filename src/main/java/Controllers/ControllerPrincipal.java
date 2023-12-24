/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controllers;

import com.alex96.sincronizacostos.Models.Articulo;
import com.alex96.sincronizacostos.Models.Movimiento;
import com.alex96.sincronizacostos.Models.Movimientos;
import com.alex96.sincronizacostos.utils.Conexion;
import com.alex96.sincronizacostos.utils.MessagesResult;
import com.alex96.sincronizacostos.views.Details;
import com.alex96.sincronizacostos.views.Principal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author alefl
 */
public class ControllerPrincipal {
    private Principal principal;
    private ControllerMovimientos controllerMovimientos;
    private ControllerArticulos controllerArticulos;
    private MessagesResult messagesResult;
    private Movimientos movimientos;
    private Movimiento movimiento;
    private Conexion conexion;
    private JSONArray data;
    private JSONObject response;
    private JSONObject row;
    private Object[] rowObject = new Object[9];
    private DefaultTableModel model;
    private JTable tableMovimientos;
    private ArrayList<ControllerArticulos> dataArticles;
    private ControllerArticulos distinctArticles;
    private JButton btnSincroniza;

    public ControllerPrincipal(DefaultTableModel model, JTable tableMovimientos, Principal principal, JButton btnSincroniza) {
        this.btnSincroniza = btnSincroniza;
        this.principal = principal;
        conexion = new Conexion();
        controllerArticulos = new ControllerArticulos();
        controllerMovimientos = new ControllerMovimientos();
        messagesResult = new MessagesResult();
        movimientos = new Movimientos(conexion, messagesResult);
        this.model = model;
        this.tableMovimientos = tableMovimientos;
    }
    
    public void loadTable(String fecha) {
        if (loadMoves(fecha)) {
            fillTable();
            btnSincroniza.setEnabled(true);
        } else {
            cleanTable();
            btnSincroniza.setEnabled(false);
        }
    }
    
    private void fillTable() {
        cleanTable();
        SimpleDateFormat formatFecha = new SimpleDateFormat("dd/MM/YYYY");
        SimpleDateFormat formatHora = new SimpleDateFormat("hh:mm:ss a");
        for (int fila = 0; fila < controllerMovimientos.getSize(); fila++) {
            movimiento = controllerMovimientos.getMovimiento(fila);
            rowObject[0] = movimiento.getDocumento();
            rowObject[1] = movimiento.getReferencia();
            rowObject[2] = movimiento.getNombreTercero();
            rowObject[3] = String.valueOf(movimiento.getCountArticulos());
            rowObject[4] = movimiento.getAlmacen();
            rowObject[5] = formatFecha.format(movimiento.getFecha());
            rowObject[6] = formatHora.format(movimiento.getHora());
            rowObject[7] = getButtonDelete(movimiento.getDocumento());
            rowObject[8] = getButtonOpen(movimiento);
            model.addRow(rowObject);
        }
        tableMovimientos.setModel(model);
    }
    
    private JButton getButtonDelete(String Documento) {
        JButton btnDelete = new JButton();
        btnDelete.setIcon(new ImageIcon(getClass().getResource("/contents/discard25x25.png")));
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                discardMove(Documento);
            }
        });
        return btnDelete;
    }
    
    public boolean discardMove(String Documento) {
        int option = JOptionPane.showConfirmDialog(principal, "¿Quiere eliminar el movimiento " + Documento + " de la lista?", "Descartando Movimiento", JOptionPane.YES_OPTION, JOptionPane.WARNING_MESSAGE);
        if (option == JOptionPane.OK_OPTION) {
            if (!controllerMovimientos.removeMovimiento(Documento)) {
                JOptionPane.showMessageDialog(principal, "No se pudo eliminar el documento", "Fallo", JOptionPane.WARNING_MESSAGE);
                return false;
            }
            fillTable();
            return true;
        }
        return false;
    }
    
    private JButton getButtonOpen(Movimiento movimiento) {
        JButton btnDelete = new JButton();
        btnDelete.setIcon(new ImageIcon(getClass().getResource("/contents/openfolderb25x25.png")));
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openDetails(movimiento);
            }
        });
        return btnDelete;
    }
    
    private void openDetails(Movimiento movimiento) {
        Details details = new Details(movimiento, principal, this);
        details.setTitle(movimiento.getDocumento());
        details.setSize(1500, 700);
        details.setVisible(true);
        details.setLocationRelativeTo(null);
        principal.setEnabled(false);
    }
    
    private void cleanTable() {
        model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        model.addColumn("Documento");
        model.addColumn("Referencia");
        model.addColumn("Nombre Tercero");
        model.addColumn("Articulos");
        model.addColumn("Almacen");
        model.addColumn("Fecha");
        model.addColumn("Hora");
        model.addColumn(" ");
        model.addColumn(" ");
        
        tableMovimientos.setModel(model);
        
        tableMovimientos.setRowHeight(30);
        tableMovimientos.getColumnModel().getColumn(0).setPreferredWidth(60); // Documento
        tableMovimientos.getColumnModel().getColumn(1).setPreferredWidth(55); // Referencia
        tableMovimientos.getColumnModel().getColumn(2).setPreferredWidth(300); // Nombre Tercero
        tableMovimientos.getColumnModel().getColumn(3).setPreferredWidth(40); // Articulos
        tableMovimientos.getColumnModel().getColumn(4).setPreferredWidth(200); // Almacen
        tableMovimientos.getColumnModel().getColumn(5).setPreferredWidth(60); // Fecha
        tableMovimientos.getColumnModel().getColumn(6).setPreferredWidth(60); // Hora
        tableMovimientos.getColumnModel().getColumn(7).setPreferredWidth(20); // Eliminar
        tableMovimientos.getColumnModel().getColumn(8).setPreferredWidth(20); // Abrir
    }
    
    private boolean loadMoves(String fecha) {
        response = movimientos.getMovimientos(fecha);
        if (!response.getBoolean("success") && response.getString("message").equals("La lista de movimientos esta vacia")) {
            JOptionPane.showMessageDialog(null, response.getString("message"), "Sin Movimientos", JOptionPane.WARNING_MESSAGE);
            return false;
        } else if (!response.getBoolean("success")) {
            JOptionPane.showMessageDialog(null, response.getString("message"), "Fallo", JOptionPane.WARNING_MESSAGE);
            return false;
        } else {
            data = response.getJSONArray("data");
            controllerMovimientos.resetMovimientos();
            dataArticles = new ArrayList<>();
            joinCosts(data);
            
            for (int position = 0; position < data.length(); position++) {
                row = data.getJSONObject(position);
                loadListMoves(row);
                loadListArticles(row);
            }
            insertListArticlesIntoMovimiento();
           return true;
        }
    }
    
    private boolean joinCosts(JSONArray data) {
        JSONArray newData = new JSONArray();
        JSONObject row, newRow;
        boolean exist = false;
        for (int position = 0; position < data.length(); position++) {
            row = data.getJSONObject(position);
            exist = false;
            for (int newPosition = 0; newPosition < newData.length(); newPosition++) {
                newRow = newData.getJSONObject(newPosition);
                if (
                    row.getString("Documento").equals(newRow.getString("Documento")) &&
                    row.getString("Articulo").equals(newRow.getString("Articulo")) &&
                    row.getDouble("CostoUnitario") == newRow.getDouble("CostoUnitario") &&
                    row.getDouble("CantidadRegular") == newRow.getDouble("CantidadRegular")
                ) {
                    exist = true;
                    if (row.getString("AlmacenArticulo").equals("12")) {
                        newRow.put("CostoAlmacenSuper", row.getDouble("UltimoCosto"));
                    } else if (row.getString("AlmacenArticulo").equals("11")) {
                        newRow.put("CostoAlmacenBodega", row.getDouble("UltimoCosto"));
                    }
                    break;
                }
            }
            if (!exist) {
                if (row.getString("AlmacenArticulo").equals("12"))
                    row.put("CostoAlmacenSuper", row.getDouble("UltimoCosto"));
                else if (row.getString("AlmacenArticulo").equals("11"))
                    row.put("CostoAlmacenBodega", row.getDouble("UltimoCosto"));
                newData.put(row);
            }
        }
        
        this.data = newData;
        return false;
    }
    
    private void loadListMoves(JSONObject rowData) {
        if (!controllerMovimientos.exist(rowData.getString("Documento"))) {
            controllerMovimientos.addMovimiento(
                rowData.getString("Documento"),
                rowData.getString("Referencia"),
                rowData.getString("NombreTercero"),
                0,
                rowData.getString("DescripcionAlmacen"),
                (Date) rowData.get("Fecha"),
                (Timestamp) rowData.get("Hora"),
                null,
                rowData.getString("Caja"),
                rowData.getString("NombreCajero"),
                rowData.getString("Observaciones")
            );
        }
    }
    
    private void loadListArticles(JSONObject rowData) {
        ControllerArticulos controllerTemp = new ControllerArticulos();
        boolean existDocument = false;
        for (ControllerArticulos dataArticle : dataArticles) {
            existDocument = dataArticle.exist(rowData.getString("Documento"));
            if (existDocument) {
                controllerTemp = dataArticle;
                break;
            }
        }
        controllerTemp.addArticulo(
            rowData.getString("Documento"),
            rowData.getString("Articulo"),
            rowData.getString("Nombre"),
            rowData.getDouble("CostoUnitario"),
            rowData.getDouble("CantidadRegular"),
            rowData.getDouble("UltimoCosto"),
            rowData.getInt("Almacen"),
            (Date) rowData.get("Fecha"),
            (Timestamp) rowData.get("Hora"),
            rowData.getString("Relacion"),
            rowData.getString("UnidadCompra"),
            rowData.getString("UnidadVenta"),
            rowData.getDouble("CantidadRegularUC"),
            rowData.getDouble("CostoUnitarioNetoUC"),
            rowData.getDouble("CostoValorNeto"),
            rowData.getDouble("CostoAlmacenSuper"),
            rowData.getDouble("CostoAlmacenBodega"),
            rowData.getDouble("CostoAlmacenSuper") - rowData.getDouble("CostoAlmacenBodega")
        );
        if (!existDocument) {
            dataArticles.add(controllerTemp);
        }
    }
    
    private void insertListArticlesIntoMovimiento() {
        if (controllerMovimientos.getSize() != 0) {
            for (int pos = 0; pos < controllerMovimientos.getSize(); pos++) {
                ControllerArticulos controllerTemp = null;
                for (ControllerArticulos dataArticle : dataArticles) {
                    if (dataArticle.exist(controllerMovimientos.get(pos).getDocumento()))
                        controllerTemp = dataArticle;
                }
                if (controllerTemp != null) {
                    controllerMovimientos.get(pos).setListArticulos(controllerTemp.getList());
                    controllerMovimientos.get(pos).setCountArticulos(controllerTemp.getSize());
                }
            }
        }
    }
    
    public void igualaCostos() {
        if (controllerMovimientos.getSize() != 0) {
            SimpleDateFormat sd = new SimpleDateFormat("dd/MM/YYYY");
            String Fecha = sd.format(controllerMovimientos.get(0).getFecha());
            
            int result = JOptionPane.showOptionDialog(
                principal,
                "¿Quiere sincronizar los costos de la fecha \"" + Fecha + "\"?",
                "Sincronizando Costos",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                new Object[] { "Si", "No"},
                "NO"
            );
            if (result == JOptionPane.YES_OPTION) {
                loadDistintcArticles();
                ControllerArticulos caDiferences = getArticlesWithDiference();
                if (caDiferences.getSize() > 0)
                    JOptionPane.showMessageDialog(principal, "Hay diferencia de costo en " + caDiferences.getSize() + " articulos", "Difrencias", JOptionPane.WARNING_MESSAGE);
                else
                    JOptionPane.showMessageDialog(principal, "No se encontraron diferencias en los costos del almacen de Bodega y de Super", "Sincronizado", JOptionPane.INFORMATION_MESSAGE);
            }
        } else
            JOptionPane.showMessageDialog(principal, "No se encontraron documentos cargados", "Lista vacia", JOptionPane.WARNING_MESSAGE);
    }
    
    private void loadDistintcArticles() {
        if (controllerMovimientos.getSize() != 0) {
            distinctArticles = new ControllerArticulos();
            ArrayList<Articulo> listArticlesTemp = null;
            boolean exist = false;
            
            for (Movimiento movimiento : controllerMovimientos.getList()) {
                listArticlesTemp = movimiento.getListArticulos();
                for (Articulo articulo : listArticlesTemp) {
                    exist = false;
                    for (Articulo articleDistintc : distinctArticles.getList()) {
                        if (articleDistintc.getArticulo().equals(articulo.getArticulo())){
                            exist = true;
                            break;
                        }
                    }
                    if (!exist) distinctArticles.addArticle(articulo);
                }
            }
        } else
            JOptionPane.showMessageDialog(principal, "No se encontraron documentos cargados", "Lista vacia", JOptionPane.WARNING_MESSAGE);
    }
    
    private ControllerArticulos getArticlesWithDiference() {
        ControllerArticulos cnTemp = new ControllerArticulos();
        for (Articulo articulo : distinctArticles.getList()) {
            if (Math.abs(articulo.getDiferencia()) >= 0.1)
                cnTemp.addArticle(articulo);
        }
        return cnTemp;
    }
}
