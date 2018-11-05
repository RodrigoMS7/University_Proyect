/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2.presentation.registrador.labor.listado;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import proyecto2.logic.Labor;


/**
 *
 * @author oscar
 */
public class LaborTableModel extends AbstractTableModel{

    List<Labor> rows;
    int[] cols;
    
    public LaborTableModel(int[] cols,List<Labor> rows){
        this.cols=cols;
        this.rows = rows;
        initColNames();
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
   
    @Override
    public Object getValueAt(int row, int col) {
        Labor labor = rows.get(row);
        switch(cols[col]){
            case ID : return labor.getIdLabor();
            case DEPENDENCIA: 
            {
                if(labor.getDependencia()!=null)
                    return labor.getDependencia().getCodigo();
                else return "Sin asignar";
            }
            case FUNCIONARIO:
            {
                if(labor.getFuncionario()!=null)
                    return labor.getFuncionario().getId();
                else return "Sin asignar";
            }
//            case DEPENDENCIA: return labor.getDependencia().getCodigo();
//            case FUNCIONARIO: return labor.getFuncionario().getId();
            default: return "";
        }
    }
    public Labor getRowAt(int row){
        return rows.get(row);
    }
    
    String[] colNames=new String[3];
    public static final int ID=0;
    public static final int DEPENDENCIA = 1;
    public static final int FUNCIONARIO =2;
    
    private void initColNames(){
        colNames[ID]="Id";
        colNames[DEPENDENCIA]="Dependencia";   
        colNames[FUNCIONARIO]="Funcionario";   
    }
    
}
