/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2.presentation.activosGeneral;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import proyecto2.logic.Activo;

/**
 *
 * @author Rodrigo Meléndez
 */
public class ActivoGeneralTableModel extends AbstractTableModel {
    
    List<Activo> rows;
    int[] cols;

    public ActivoGeneralTableModel(int[] cols,List<Activo> rows){
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
    //codigo 
    //dependencia y funcionario si tienen
    //si no tienen entonces sin asignar
    @Override
    public Object getValueAt(int row, int col) {
        Activo activo = rows.get(row);
        switch(cols[col]){
            case CODIGO : return activo.getCodigo();
            case CATEGORIA:
            {
                if(activo.getBien().getCategoria() != null)
                    return activo.getBien().getCategoria().getTipo();
                else return "Sin asignar";
            }
            case DESCRIPCION:
            {
                if(activo.getBien() != null)
                    return activo.getBien().getDescripcion();
                else return "Sin asignar";
            }
            case DEPENDENCIA: 
            {
                if(activo.getLabor().getDependencia()!=null)
                    return activo.getLabor().getDependencia().getNombre();
                else return "Sin asignar";
            }
            case FUNCIONARIO_RESPONSABLE:
            {
                if(activo.getLabor().getFuncionario()!=null)
                    return activo.getLabor().getFuncionario().getNombre();
                else return "Sin asignar";
            }
            default: return "";
        }
    }
    public Activo getRowAt(int row){
        return rows.get(row);
    }

    String[] colNames=new String[6];
    public static final int CODIGO = 0;
    public static final int CATEGORIA = 1;
    public static final int DESCRIPCION = 2;
    public static final int DEPENDENCIA = 3;
    public static final int FUNCIONARIO_RESPONSABLE = 4;
    //public static final int  = 5;

    private void initColNames(){
        colNames[CODIGO]="Codigo";
        colNames[CATEGORIA]="Categoría";
        colNames[DESCRIPCION]="Descripción";
        colNames[DEPENDENCIA]="Dependencia";
        colNames[FUNCIONARIO_RESPONSABLE]="Funcionario responsable";
    }
}
