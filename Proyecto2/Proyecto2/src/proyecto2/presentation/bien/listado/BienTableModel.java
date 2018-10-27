/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2.presentation.bien.listado;

import java.util.List;
import proyecto2.logic.Bien;

/**
 *
 * @author Rodrigo Meléndez
 */
public class BienTableModel {
    List<Bien> rows;
    int[] cols;

    public  BienTableModel(int[] cols, List<Bien> rows){
        this.cols = cols;
        this.rows = rows;
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
        //rows => lista
        //rows.get => obtenga el objeto en esa fila
        Bien bien = rows.get(row); 
        switch (cols[col]){
            //hacer system out del return
            case DESCRIPCION: return bien.getDescripcion();
            case MARCA: return bien.getMarca();
            case MODELO: return bien.getModelo();
            case CANTIDAD: return bien.getCantidad();
            case PRECIO: return bien.getPrecio();
            default: return "";
        }
    }    

    public Bien getRowAt(int row) {
        return rows.get(row);
    }
    
    public static final int DESCRIPCION = 0;
    public static final int MARCA = 1;
    public static final int MODELO = 2;
    public static final int CANTIDAD = 3;
    public static final int PRECIO = 4;
    
    String[] colNames = new String[10];
    private void initColNames(){
        colNames[DESCRIPCION]= "Descripción";
        colNames[MARCA]= "Marca";
        colNames[MODELO]= "Modelo";
        colNames[CANTIDAD]= "Cantidad";
        colNames[PRECIO]= "Precio";
    }
}
