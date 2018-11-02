/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2.presentation.solicitudes.edicion;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Observer;
import java.util.Set;
import proyecto2.logic.Bien;
import proyecto2.logic.Solicitud;

/**
 *
 * @author Rodrigo Meléndez
 */
public class SolicitudModel extends java.util.Observable { 
    SolicitudBienTableModel bienes;
    Bien seleccionado;
    Bien currentB;
    int modoB;
    Solicitud filter;

    public Solicitud getFilter() {
        return filter;
    }

    public void setFilter(Solicitud filter) {
        this.filter = filter;
    }

    public Set getBiens() {
        return biens;
    }

    public void setBiens(Set biens) {
        this.biens = biens;
    }
    Solicitud currentS;
    int modoS;  
    List<Bien> listaBienes;
    Set biens = new HashSet(0);

    
    public SolicitudModel() {
        listaBienes = new ArrayList();
        this.reset();
    }
    
    public void agregaBien(Bien bien){
        listaBienes.add(bien);
        biens.add(bien);
    }
    
    public int sumaCantidad(){
        int result = 0;
        for(Bien bien: listaBienes) {
            result += bien.getCantidad();
        }
        return result;
    }
    
    public double sumaMontoTotal(){
        double result = 0;
        for(Bien bien: listaBienes) {
            result += bien.getPrecio();
        }
        return result;
    }

    public SolicitudBienTableModel getBienes() {
        return bienes;
    }

    public void setBienes(SolicitudBienTableModel bienes) {
        this.bienes = bienes;
    }

    public List<Bien> getListaBienes() {
        return listaBienes;
    }

    public void setListaBienes(List<Bien> listaBienes) {
        this.listaBienes = listaBienes;
    }
    
    public void setSolicituaABien(Solicitud solicitud){
        for(Bien bien: listaBienes){
            bien.setSolicitud(solicitud);    
        }
    }

    public void reset(){ //PUEDE QUE NO FUNCIONE (PROBAR)
        setCurrentS(new Solicitud());
        List<Bien> rows = new ArrayList<>();        
        seleccionado = null;  
        this.setBien(rows);
        this.commit();  
    }
    
      void reset(int modo, Solicitud current) {
        this.setModoS(modo);
        this.setCurrentS(current);
        this.commit();
    }
    
    public void setBien(List<Bien> bienes){
        int[] cols = {SolicitudBienTableModel.DESCRIPCION,SolicitudBienTableModel.MARCA,SolicitudBienTableModel.MODELO,SolicitudBienTableModel.CANTIDAD,SolicitudBienTableModel.PRECIO};
        this.bienes = new SolicitudBienTableModel(cols, bienes);
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
