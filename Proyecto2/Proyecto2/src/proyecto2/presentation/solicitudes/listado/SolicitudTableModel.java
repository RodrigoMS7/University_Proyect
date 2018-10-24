/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2.presentation.solicitudes.listado;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import proyecto2.logic.Solicitud;

/**
 *
 * @author oscar
 */
public class SolicitudTableModel extends AbstractTableModel{
    List<Solicitud> rows;
    int[] cols;

    public  SolicitudTableModel(int[] cols, List<Solicitud> rows){
        this.cols=cols;
        this.rows=rows;
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
    public String getColumnName(int col){
        return colNames[cols[col]]; //colNames => array
    }    
    


    public Object getValueAt(int row, int col) {
        //rows => lista
        //rows.get => obtiene el objeto en esa fila
        Solicitud solicitud = rows.get(row); 
        switch (cols[col]){
            case COMPROBANTE:
                return solicitud.getComprobante();
            case CODIGO:
                return solicitud.getCodigo();
            case TIPO:
                return solicitud.getTipoAdquisicion();
            case CANTIDAD:
                return solicitud.getCantidad();
            case MONTO:
                return solicitud.getMonto();
            case ESTADO:
                return solicitud.getEstado();
            case FECHA:
                return solicitud.getFecha();     
            default: return "";
        }
    }    

    public Solicitud getRowAt(int row) {
        return rows.get(row);
    }
    
    public static final int COMPROBANTE=0;
    public static final int CODIGO=1;
    public static final int TIPO=2;
    public static final int MONTO=3;
    public static final int ESTADO=4;
    public static final int FECHA=5;
    public static final int CANTIDAD=6;
    
    String[] colNames = new String[9];
    private void initColNames(){
        colNames[COMPROBANTE] = "Comprobante";
        colNames[CODIGO] = "Codigo";
        colNames[TIPO] = "Tipo adquisicion";
        colNames[MONTO] = "Precio";
        colNames[ESTADO] = "Estado";
        colNames[FECHA] = "Fecha";
        colNames[CANTIDAD] = "Cantidad";
    }
}
