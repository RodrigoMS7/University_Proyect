/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2.presentation.dependencias.listado;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import proyecto2.logic.Dependencia;
/**
 *
 * @author oscar
 */
public class DependenciaTableModel extends AbstractTableModel{
    List<Dependencia> rows;
    int[] cols;
    
    public DependenciaTableModel(int[] cols,List<Dependencia> rows) {
        this.cols=cols;
        this.rows = rows;
    }
    
    @Override
    public int getRowCount() {
        return rows.size();
    }

    @Override
    public int getColumnCount() {
        return cols.length;
    }
    
    public String getColumnName(int col){
        return colNames[cols[col]];
    }
    
    public static final int CODIGO=0;
    public static final int NOMBRE=1;
    
    @Override
    public Object getValueAt(int row, int col) {
        Dependencia dependencia=rows.get(row);
        switch(cols[col]){
            case CODIGO: return dependencia.getCodigo();
            case NOMBRE: return dependencia.getNombre();
            default: return "";
        }
    }
    public Dependencia getRowAt(int row) {
        return rows.get(row);
    }
    String[] colNames=new String[2];
    private void initColNames(){
        colNames[CODIGO]="Codigo";
        colNames[NOMBRE]="Nombre";    
    }
}
