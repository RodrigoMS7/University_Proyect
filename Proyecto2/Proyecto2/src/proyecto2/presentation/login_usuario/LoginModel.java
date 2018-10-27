/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2.presentation.login_usuario;

import proyecto2.logic.Usuario;

/**
 *
 * @author Dani
 */
public class LoginModel extends java.util.Observable {
    Usuario current;

    public LoginModel() {
        this.reset();
    }
    
    public void reset(){
        setCurrent(new Usuario());        
    }
    
    public Usuario getCurrent() {
        return current;
    }

    public void setCurrent(Usuario current) {
        this.current = current;
        this.commit();
    }

    @Override
    public void addObserver(java.util.Observer o) {
        super.addObserver(o);
        setChanged();
        notifyObservers();
    }

    public void commit(){
        setChanged();
        notifyObservers();       
    }     
    
}
