/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2.presentation.administrador.listado;

import proyecto2.presentation.solicitudes.listado.*;
import java.awt.Point;
import java.text.ParseException;
import java.util.Arrays;
import java.util.List;
import org.hibernate.Session;
import proyecto2.Application;
import proyecto2.SessionUsuario;
import proyecto2.logic.Solicitud;
import proyecto2.logic.Usuario;

/**
 *
 * @author Rodrigo Meléndez
 */
public class AdministradorSolicitudController {
    Session session;
    AdministradorSolicitudView view;
    AdministradorSolicitudModel model;
    SessionUsuario sessionUsu;
    
    public AdministradorSolicitudController(AdministradorSolicitudView view, AdministradorSolicitudModel model, Session session, SessionUsuario sessionUsu) {
        this.session=session;
        this.view = view;
        this.model = model;
        this.sessionUsu = sessionUsu;
        view.setController(this);
        view.setModel(model);
    }
    
    public void buscar(Solicitud filter) throws Exception {       
        model.setFilter(filter);
        this.refrescarBusqueda();
    }
    
    public void refrescarBusqueda() throws Exception {
       List<Solicitud> rows = proyecto2.logic.ModelGeneral.instance().searchSolicitudes(model.getFilter());
       model.setSolicitudes(rows);
       model.commit();
       if (rows.isEmpty()) throw new Exception("Ningún dato coincide");

    }

    public void buscarFromDependencia() throws Exception {
        this.refrescarTable();
    }
    
    private void refrescarTable() throws Exception {
       Usuario usuario = (Usuario) sessionUsu.getAttribute("User");
       //String nombre = proyecto2.logic.ModelGeneral.instance().getCodigoDependenciaDesdeLabor(usuario.getFuncionario().getId());
       List<Solicitud> rows = proyecto2.logic.ModelGeneral.instance().searchSolicitudesFromDependencia(usuario.getFuncionario().getId());
       model.setSolicitudes(rows);
       model.commit();
       if (rows.isEmpty()) throw new Exception("Ningún dato coincide");
    }
//
//    public void preAgregar(Point at)throws Exception{      
////        Usuario principal = (Usuario) session.getAttribute(Application.USER_ATTRIBUTE);
////        if ( !Arrays.asList(Application.ROL_MANAGER).contains(principal.getRol())){
////           throw new Exception(Application.ROL_NOTAUTHORIZED);
////        }
//        Application.FUNCIONARIO_CONTROLLER.reset(Application.MODO_AGREGAR, new Funcionario()); //modo agregar se setea aqui
//        Application.FUNCIONARIO_CONTROLLER.show(at);
//    }
//    
//    public void editar(int row, Point at){       
//        Funcionario seleccionada = model.getFuncionarios().getRowAt(row); 
////        Usuario principal = (Usuario) session.getAttribute(Application.USER_ATTRIBUTE);
//        int modo;
////        if ( Arrays.asList(Application.ROL_MANAGER, Application.ROL_SUPERVISOR).contains(principal.getRol())){
////            modo=Application.MODO_EDITAR;
////        }
////        else{
////            modo=Application.MODO_CONSULTAR;            
////        }
//        Application.FUNCIONARIO_CONTROLLER.reset(Application.MODO_EDITAR, seleccionada);
//        Application.FUNCIONARIO_CONTROLLER.show(at);
//    }
//
    public void borrar(int row) throws ParseException{  
        Solicitud seleccionada = model.getSolicitudes().getRowAt(row); 
        try {
            proyecto2.logic.ModelGeneral.instance().borrarSolicitud(seleccionada);
        } catch (Exception ex) { }
        List<Solicitud> rowsMod = proyecto2.logic.ModelGeneral.instance().searchSolicitudes(model.getFilter());
        model.setSolicitudes(rowsMod);
        model.commit();
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

    public AdministradorSolicitudView getView() {
        return view;
    }

    Solicitud getSeleccionada(int row) {
        return model.getSolicitudes().getRowAt(row); 
    }
}
