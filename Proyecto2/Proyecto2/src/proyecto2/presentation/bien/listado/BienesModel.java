/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2.presentation.bien.listado;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;
import proyecto2.logic.Bien;

/**
 *
 * @author Dani
 */
public class BienesModel extends java.util.Observable {
    Bien filter; 
    BienTableModel bienes;
    Bien seleccionado;

    public BienesModel() {
        this.reset();
    }

    public void reset(){ 
        filter = new Bien();
        List<Bien> rows = new ArrayList<>();        
        seleccionado = null;  
        this.setBien(rows);
        this.commit();  
    }
    
    public void setBien(List<Bien> bienes){
        int[] cols = {BienTableModel.DESCRIPCION,BienTableModel.MARCA,BienTableModel.MODELO,BienTableModel.CANTIDAD,BienTableModel.PRECIO};
        this.bienes = new BienTableModel(cols, bienes);    
    }
    
    public Bien getFilter() {
        return filter;
    }
    
    public void setFilter(Bien filter) {
        this.filter = filter;
    }
    
     public BienTableModel getBien() {
        return bienes;
    }

    public Bien getSeleccionado() {
        return seleccionado;
    }

    public void setSeleccionado(Bien seleccionado) {
        this.seleccionado = seleccionado;
    }
  
    @Override
    public void addObserver(Observer o) {
        super.addObserver(o);
        this.commit();   
    }

    public void commit(){
        setChanged();
        notifyObservers();       
    }
}
