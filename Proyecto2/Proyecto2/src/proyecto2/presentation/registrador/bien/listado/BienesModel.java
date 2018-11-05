/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2.presentation.registrador.bien.listado;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;
import proyecto2.logic.Bien;
import proyecto2.logic.Solicitud;

/**
 *
 * @author Dani
 */
public class BienesModel extends java.util.Observable {
    Bien filter; 
    BienTableModel bienes;
    Bien seleccionado;
    Solicitud solicitud;

    public BienTableModel getBienes() {
        return bienes;
    }

    public void setBienes(BienTableModel bienes) {
        this.bienes = bienes;
    }

    public Solicitud getsolicitud() {
        return solicitud;
    }

    public void setsolicitud(Solicitud sol) {
        this.solicitud = sol;
        commit();
    }

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
        int[] cols = {BienTableModel.CODIGO,BienTableModel.DESCRIPCION,BienTableModel.MARCA,BienTableModel.MODELO,BienTableModel.CANTIDAD,BienTableModel.PRECIO, BienTableModel.CATEGORIA};
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
