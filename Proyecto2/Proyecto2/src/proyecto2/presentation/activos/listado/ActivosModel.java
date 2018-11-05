/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2.presentation.activos.listado;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;
import proyecto2.logic.Activo;

/**
 *
 * @author Dani
 */
public class ActivosModel extends java.util.Observable{
    Activo filter;
    ActivoTableModel activos;
    Activo seleccionado;

    public ActivosModel(){
        this.reset();
    }
    
    public void reset(){
        filter=new Activo();
        List<Activo> rows=new ArrayList<>();
        seleccionado=null; 
        this.setActivos(rows);
        this.commit();  
    }
    
    public Activo getFilter() {
        return filter;
    }

    public void setFilter(Activo filter) {
        this.filter = filter;
    }

    public ActivoTableModel getActivos() {
        return activos;
    }

    public void setActivos(List<Activo> activos){
        int[] cols={ActivoTableModel.CODIGO/*,ActivoTableModel.DEPENDENCIA,ActivoTableModel.FUNCIONARIO*/};
        this.activos= new ActivoTableModel(cols,activos);
    }

    public Activo getSeleccionado() {
        return seleccionado;
    }

    public void setSeleccionado(Activo seleccionado) {
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
