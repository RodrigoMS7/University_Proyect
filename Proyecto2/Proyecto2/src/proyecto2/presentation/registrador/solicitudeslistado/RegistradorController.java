/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2.presentation.registrador.solicitudeslistado;

import java.awt.Point;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import proyecto2.Application;
import proyecto2.SessionUsuario;
import proyecto2.logic.Solicitud;
import proyecto2.logic.Usuario;

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
    public void buscar(String estado) throws Exception{       
        this.refrescarBusqueda(estado);
    }
    public void buscarInicio() {
        Usuario usuario = (Usuario) sessionUsu.getAttribute("User");
        List<Solicitud> rows = proyecto2.logic.ModelGeneral.instance().solicitudesDeRegistrador(usuario.getFuncionario().getId());
        if (!rows.isEmpty()) {
            model.setSolicitudes(rows);
            model.commit();
        }
    }
    public void refrescarBusqueda(String estado) throws Exception{
        Usuario usuario = (Usuario) sessionUsu.getAttribute("User");
        //List<Solicitud> rows = proyecto2.logic.ModelGeneral.instance().searchByEstado("verificado",model.getFilter());
        List<Solicitud> rows = proyecto2.logic.ModelGeneral.instance().solicitudesRegistradorSearch(estado,usuario.getFuncionario().getId(),model.getFilter());
        model.setSolicitudes(rows);
        model.commit();
        if (rows.isEmpty()) throw new Exception("Ningún dato coincide");

    }
    
    public void muestraBien(int row){
        Solicitud seleccionada = model.getSolicitudes().getRowAt(row);
        Application.BIENES_CONTROLLER.show(seleccionada);
    }
    
    public void reset(){
        model.reset();
    }
    
    public void show(){
        view.setVisible(true);
        buscarInicio();
    }

    public void show(Point position) {
        view.setLocation(position);
        this.show();
    }   
    
    public void hide(){
        view.setVisible(false);
    }  
    
    public void cambiaEstadoSolicitud(String estado, int row) throws Exception{
        Transaction t=session.beginTransaction();
        Solicitud seleccionada = model.getSolicitudes().getRowAt(row);
        Solicitud solicitud = proyecto2.logic.ModelGeneral.instance().getSolicitud(seleccionada.getCodigo());
        solicitud.setEstado(estado);
        session.merge(solicitud);
        t.commit();
        buscarInicio();
    }
    
}
