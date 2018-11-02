/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2.presentation;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import proyecto2.logic.Solicitud;

/**
 *
 * @author oscar
 */
public class SolicitudTableModel extends AbstractTableModel {

    List<Solicitud> rows;
    int[] cols;

    public SolicitudTableModel(int[] cols, List<Solicitud> rows) {
        this.cols = cols;
        this.rows = rows;
        initColNames();
    }

    @Override
    public int getColumnCount() {
        return cols.length;
    }

    @Override
    public int getRowCount() {
        return rows.size();
    }

    @Override
    public String getColumnName(int col) {
        return colNames[cols[col]]; //colNames => array
    }

    @Override
    public Object getValueAt(int row, int col) {
        //rows => lista
        //rows.get => obtiene el objeto en esa fila
        Solicitud solicitud = rows.get(row);
//        if()
       // System.out.println(solicitud.getDependencia().getNombre());
        switch (cols[col]) {
            case CODIGO:
                return solicitud.getCodigo();
            case FECHA:
                return solicitud.getFecha();
            case CANTIDAD:
                return solicitud.getCantidad();
            case TIPO:
                return solicitud.getTipoAdquisicion();
            case ESTADO:
                return solicitud.getEstado();
            case MONTO:
                return solicitud.getMonto();
            case COMPROBANTE:
                return solicitud.getComprobante();
            case DEPENDENCIA:
                return solicitud.getDependencia().getNombre();
            default:
                return "";
        }
    }

    public Solicitud getRowAt(int row) {
        return rows.get(row);
    }
    

    public static final int COMPROBANTE = 6;
    public static final int CODIGO = 0;
    public static final int TIPO = 3;
    public static final int MONTO = 5;
    public static final int ESTADO = 4;
    public static final int FECHA = 1;
    public static final int CANTIDAD = 2;
    public static final int DEPENDENCIA =7;

    String[] colNames = new String[9];

    private void initColNames() {

        colNames[CODIGO] = "Codigo";
        colNames[COMPROBANTE] = "Comprobante";
        colNames[FECHA] = "Fecha";
        colNames[CANTIDAD] = "Cantidad";
        colNames[TIPO] = "Tipo";
        colNames[ESTADO] = "Estado";
        colNames[MONTO] = "Precio";
        colNames[DEPENDENCIA] = "Dependencia";
    }
}
