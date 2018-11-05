/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2.presentation.registrador.labor.listado;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;
import proyecto2.logic.Labor;

/**
 *
 * @author oscar
 */
public class LaborsModel extends java.util.Observable{
    
    LaborTableModel labors;
    Labor seleccionado;
    String codigoActivo;
    
    public LaborsModel(){
        this.reset();
    }

    public String getCodigoActivo() {
        return codigoActivo;
    }

    public void setCodigoActivo(String codigoActivo) {
        this.codigoActivo = codigoActivo;
        this.commit();
    }
    
    
    
    public void reset(){
       
        List<Labor> rows=new ArrayList<>();
        seleccionado=null; 
        this.setLabors(rows);
        this.commit();  
    }
    
    

    public LaborTableModel getLabors() {
        return labors;
    }

    public void setLabors(List<Labor> labors){
        int[] cols={LaborTableModel.ID,LaborTableModel.DEPENDENCIA,LaborTableModel.FUNCIONARIO};
        this.labors= new LaborTableModel(cols,labors);
    }

    public Labor getSeleccionado() {
        return seleccionado;
    }

    public void setSeleccionado(Labor seleccionado) {
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
