/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2.presentation.occb.secretaria.listado;

import java.awt.Point;
import java.util.List;
import org.hibernate.Session;
import proyecto2.SessionUsuario;
import proyecto2.logic.Solicitud;

/**
 *
 * @author oscar
 */
public class SolicitudesController {
    
    Session session;
    SolicitudesView view;
    SolicitudesModel model;
    SessionUsuario sessionUsu;
    
    public SolicitudesController(SolicitudesView view, SolicitudesModel model, Session session) {
        this.session=session;
        this.view = view;
        this.model = model;
        view.setController(this);
        view.setModel(model);
    }
     public void buscar(Solicitud filter) throws Exception{       
        model.setFilter(filter);
        this.refrescarBusqueda();
    }
    
    public void refrescarBusqueda() throws Exception{

       List<Solicitud> rows = proyecto2.logic.ModelGeneral.instance().searchSolicitudes(model.getFilter());
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
