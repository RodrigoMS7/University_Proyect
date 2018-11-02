/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2.presentation.registrador.solicitudeslistado;

import java.awt.Point;
import java.util.List;
import org.hibernate.Session;
import proyecto2.SessionUsuario;
import proyecto2.logic.Solicitud;

/**
 *
 * @author oscar
 */
public class RegistradorController {
    Session session;
    RegistradorView view;
    RegistradorModel model;
    SessionUsuario sessionUsu;

    public RegistradorController(RegistradorView view, RegistradorModel model, Session session, SessionUsuario sessionUsu) {
        this.session = session;
        this.view = view;
        this.model = model;
        this.sessionUsu = sessionUsu;
        view.setController(this);
        view.setModel(model);
    }
    public void buscar(List<String> lista) throws Exception{       
        this.refrescarBusqueda(lista);
    }
    
    public void refrescarBusqueda(List<String> lista) throws Exception{

        //List<Solicitud> rows = proyecto2.logic.ModelGeneral.instance().searchByEstado("verificado",model.getFilter());
       List<Solicitud> rows = proyecto2.logic.ModelGeneral.instance().findSolicitudByEstado(lista, model.getFilter());
        model.setSolicitudes(rows);
        model.commit();
        if (rows.isEmpty()) throw new Exception("Ningún dato coincide");

    }
    
    public void reset(){
        model.reset();
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
    
    
}
