/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2.presentation.categoria.listado;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import proyecto2.logic.Categoria;

/**
 *
 * @author Dani
 */
public class CategoriaTableModel extends AbstractTableModel {
    List<Categoria> rows;
    int[] cols;

    public CategoriaTableModel(int[] cols, List<Categoria> rows){
        this.cols=cols;
        this.rows=rows;
        initColNames();
    }

    public int getColumnCount() {
        return cols.length;
    }

    public int getRowCount() {
        return rows.size();
    }

    public String getColumnName(int col){
        return colNames[cols[col]]; //colNames => array
    }    
    


    public Object getValueAt(int row, int col) {
        Categoria categoria = rows.get(row); 
        switch (cols[col]){
            //hacer system out del return
            case CONSECUTIVO: return categoria.getConsecutivo();
            case TIPO: return categoria.getTipo();
            default: return "";
        }
    }    

    public Categoria getRowAt(int row) {
        return rows.get(row);
    }
    
    public static final int CONSECUTIVO=0;
    public static final int TIPO=1;
    
    String[] colNames = new String[2];
    private void initColNames(){
        colNames[CONSECUTIVO]= "Consecutivo";
        colNames[TIPO]= "Tipo"; 
    }
    
}
