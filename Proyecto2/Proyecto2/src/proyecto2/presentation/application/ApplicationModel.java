/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2.presentation.application;

import proyecto2.logic.Usuario;

/**
 *
 * @author oscar
 */
public class ApplicationModel extends java.util.Observable{
    String mensaje;
    Usuario current;
    
    public void reset(Usuario current){ 
        clearErrors();
        this.setCurrent(current);
    }   
    
     public ApplicationModel() { 
        this.reset(new Usuario());
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Usuario getCurrent() {
        return current;
    }

    public void setCurrent(Usuario current) {
        this.current = current;
        this.commit();
    }
    
    public void clearErrors(){
        setMensaje("");
        
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
}
