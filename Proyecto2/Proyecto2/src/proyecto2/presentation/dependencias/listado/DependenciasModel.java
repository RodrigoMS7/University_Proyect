/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2.presentation.dependencias.listado;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;
import proyecto2.logic.Dependencia;

/**
 *
 * @author oscar
 */
public class DependenciasModel extends java.util.Observable{
    Dependencia filter;
    DependenciaTableModel dependencias;
    Dependencia seleccionada;

    public DependenciasModel() {
        this.reset();
    }

    public void reset(){ 
        filter = new Dependencia();
        List<Dependencia> rows = new ArrayList<>();        
        seleccionada=null;  
        this.setDependencias(rows);
        this.commit();  
    }
    
    public Dependencia getFilter() {
        return filter;
    }

    public void setFilter(Dependencia filter) {
        this.filter = filter;
    }

    public DependenciaTableModel getDependencias() {
        return dependencias;
    }

    public void setDependencias(List<Dependencia> dependencias) {
        int[] cols={DependenciaTableModel.CODIGO,DependenciaTableModel.NOMBRE};
        this.dependencias= new DependenciaTableModel(cols,dependencias);
    }

    public Dependencia getSeleccionada() {
        return seleccionada;
    }

    public void setSeleccionada(Dependencia seleccionada) {
        this.seleccionada = seleccionada;
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
