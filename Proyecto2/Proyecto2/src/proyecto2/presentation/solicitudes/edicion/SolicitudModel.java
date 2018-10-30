/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2.presentation.solicitudes.edicion;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;
import proyecto2.logic.Bien;
import proyecto2.logic.Solicitud;

/**
 *
 * @author Rodrigo Meléndez
 */
public class SolicitudModel extends java.util.Observable {
    Bien filter; 
    SolicitudBienTableModel bienes;
    Bien seleccionado;
    Bien currentB;
    int modoB;
    Solicitud currentS;
    int modoS;  

    public SolicitudModel() {
        this.reset();
    }

    public void reset(){ //PUEDE QUE NO FUNCIONE (PROBAR)
        setCurrentB(new Bien());
        setCurrentS(new Solicitud());
        filter = new Bien();
        List<Bien> rows = new ArrayList<>();        
        seleccionado = null;  
        this.setBien(rows);
        this.commit();  
    }
    
    public void setBien(List<Bien> bienes){
        int[] cols = {SolicitudBienTableModel.DESCRIPCION,SolicitudBienTableModel.MARCA,SolicitudBienTableModel.MODELO,SolicitudBienTableModel.CANTIDAD,SolicitudBienTableModel.PRECIO};
        this.bienes = new SolicitudBienTableModel(cols, bienes);    
    }
    
    public Bien getFilter() {
        return filter;
    }
    
    public void setFilter(Bien filter) {
        this.filter = filter;
    }
    
     public SolicitudBienTableModel getBien() {
        return bienes;
    }

    public Bien getSeleccionado() {
        return seleccionado;
    }

    public void setSeleccionado(Bien seleccionado) {
        this.seleccionado = seleccionado;
    }
    
    public Solicitud getCurrentS() {
        return currentS;
    }

    public void setCurrentS(Solicitud current) {
        this.currentS = current;   
    }   
    
     public int getModoS() {
        return modoS;
    }

    public void setModoS(int modo) {
        this.modoS = modo;
    }
    
    public Bien getCurrentB() {
        return currentB;
    }

    public void setCurrentB(Bien current) {
        this.currentB = current;   
    }   
    
     public int getModoB() {
        return modoB;
    }

    public void setModoB(int modo) {
        this.modoB = modo;
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
