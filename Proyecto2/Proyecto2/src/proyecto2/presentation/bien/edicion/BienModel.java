/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2.presentation.bien.edicion;

import proyecto2.logic.Bien;

/**
 *
 * @author Rodrigo Meléndez
 */
public class BienModel extends java.util.Observable {
    Bien current;
    int modo;    

    public BienModel() {
        this.reset();
    }

    public void reset(){      
        setCurrent(new Bien());
    }   

    public Bien getCurrent() {
        return current;
    }

    public void setCurrent(Bien current) {
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