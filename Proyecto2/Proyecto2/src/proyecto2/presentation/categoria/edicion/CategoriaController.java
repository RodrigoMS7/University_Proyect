/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2.presentation.categoria.edicion;

import java.awt.Point;
import org.hibernate.Session;
import org.hibernate.Transaction;
import proyecto2.Application;
import proyecto2.SessionUsuario;
import proyecto2.logic.Categoria;

/**
 *
 * @author Dani
 */
public class CategoriaController {
    CategoriaView view;
    CategoriaModel model;
    Session session; 
    SessionUsuario ses;
    
    public CategoriaController(CategoriaView view, CategoriaModel model, Session session, SessionUsuario ses) {
        this.view = view;
        this.model = model;
        this.session = session;
        this.ses = ses;
        view.setController(this);
        view.setModel(model);
    }

     public void reset(){
        model.reset();
    }
    
    public void reset(int modo, Categoria current){
        model.reset(modo, current);
    }    
    
    public void show(){
        view.setVisible(true);
    }

    public void show(Point position){
        view.setLocation(position);
        this.show();
    }   
    
    public void hide(){
        view.setVisible(false);
    }  
    
     public void guardar(Categoria categoria) throws Exception{ 
           Transaction t = session.beginTransaction();
        switch(model.getModo()){
            case Application.MODO_AGREGAR:
                session.save(categoria);
                t.commit();
                Application.CATEGORIAS_CONTROLLER.refrescarBusqueda();                   
                model.setCurrent(new Categoria());
                model.commit();   
                break;
            case Application.MODO_EDITAR:
                session.merge(categoria);
                 t.commit();
                Application.CATEGORIAS_CONTROLLER.refrescarBusqueda();               
                break;
        }   
    } 
}
