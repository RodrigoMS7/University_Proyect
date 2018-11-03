/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2.presentation.categoria.edicion;

import proyecto2.logic.Categoria;

/**
 *
 * @author Dani
 */
public class CategoriaModel extends java.util.Observable {
    Categoria current;
    int modo;    

    public CategoriaModel() {
        this.reset();
    }

    public void reset(){      
        setCurrent(new Categoria());
    }
    
    public void reset(int modo, Categoria current){
        this.setModo(modo);
        this.setCurrent(current);
        this.commit();
    }
    
    public Categoria getCurrent() {
        return current;
    }

    public void setCurrent(Categoria current) {
        this.current = current;   
    }

    @Override
    public void addObserver(java.util.Observer o) {
        super.addObserver(o);
        this.commit();
    }

    public void commit(){
        setChanged();
        notifyObservers();       
    }    
    
     public int getModo() {
        return modo;
    }

    public void setModo(int modo) {
        this.modo = modo;
    }
    
}
