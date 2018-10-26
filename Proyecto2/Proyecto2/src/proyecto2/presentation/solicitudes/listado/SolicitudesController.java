/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2.presentation.solicitudes.listado;

import java.awt.Point;
import java.util.Arrays;
import java.util.List;
import org.hibernate.Session;
import proyecto2.Application;
import proyecto2.SessionUsuario;
import proyecto2.logic.Solicitud;
import proyecto2.logic.Usuario;

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
//    public void borrar(int row){  
//        Funcionario seleccionada = model.getFuncionarios().getRowAt(row); 
//        try {
//            proyecto2.logic.ModelGeneral.instance().eliminaFuncionario(seleccionada);
//        } catch (Exception ex) { }
//        List<Funcionario> rowsMod = proyecto2.logic.ModelGeneral.instance().searchFuncionarios(model.getFilter());
//        model.setFuncionarios(rowsMod);
//        model.commit();
//    }

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
