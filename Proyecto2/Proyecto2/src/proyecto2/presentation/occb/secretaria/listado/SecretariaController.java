/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2.presentation.occb.secretaria.listado;

import java.awt.Point;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import proyecto2.Application;
import proyecto2.SessionUsuario;
import proyecto2.logic.Solicitud;

/**
 *
 * @author oscar
 */
public class SecretariaController {
    
    Session session;
    SecretariaView view;
    SecretariaModel model;
    SessionUsuario sessionUsu;
    
    public SecretariaController(SecretariaView view, SecretariaModel model, Session session) {
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
        List<Solicitud> rows = proyecto2.logic.ModelGeneral.instance().searchByEstado("Solicitud recibida",model.getFilter());
        model.setSolicitudes(rows);
        model.commit();
        if (rows.isEmpty()) throw new Exception("Ningún dato coincide");
    }
    
    public void actualizar(Solicitud s, String estado)throws Exception{
        Transaction t=session.beginTransaction();
        Solicitud sol = proyecto2.logic.ModelGeneral.instance().getSolicitud(s.getCodigo());
        model.setEstado(sol, estado);
        session.merge(sol);
        t.commit();
        Application.SECRETARIA_CONTROLLER.refrescarBusqueda();
    }
    public void actualizarMotivo(Solicitud s, String estado,String motivo)throws Exception{
        Transaction t=session.beginTransaction();
        Solicitud sol = proyecto2.logic.ModelGeneral.instance().getSolicitud(s.getCodigo());
        model.setEstado(sol, estado);
        model.setMotivo(s, motivo);
        session.merge(sol);
        t.commit();
        Application.SECRETARIA_CONTROLLER.refrescarBusqueda();
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
