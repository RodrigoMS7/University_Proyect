/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2.presentation.solicitudes.edicion;

import java.awt.Point;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import proyecto2.Application;
import proyecto2.SessionUsuario;
import proyecto2.logic.Bien;
import proyecto2.logic.Dependencia;
import proyecto2.logic.ModelGeneral;
import proyecto2.logic.Solicitud;
import proyecto2.logic.Usuario;

/**
 *
 * @author Rodrigo Meléndez
 */
public class SolicitudController {
    Session session;
    SolicitudView view;
    SolicitudModel model;
    SessionUsuario sessU;

    public SolicitudController(SolicitudView view, SolicitudModel model, Session session,SessionUsuario sess){
        this.session=session;
        this.view = view;
        this.model = model;
        this.sessU = sess;
        view.setController(this);
        view.setModel(model);
    }

//    public void buscar(Bien filter) throws Exception{
//        model.setFilter(filter);
//        this.refrescarBusqueda();
//    }
    
    public void refrescarTablaBien() throws Exception{
        List<Bien> rows = proyecto2.logic.ModelGeneral.instance().getAllBienes();
        model.setBien(rows);
        model.commit();
        if(rows.isEmpty()) throw new Exception("Ningun dato coincide");
    }

    public void guardarBien(Bien bien, Solicitud solicitud) throws Exception{ 
        Transaction t = session.beginTransaction();
//        bien.setSolicitud(solicitud);
        switch(model.getModoB()){
            case Application.MODO_AGREGAR:
                session.save(bien);
                t.commit();
                //Application.FUNCIONARIOS_CONTROLLER.refrescarBusqueda();                   
                model.setCurrentB(new Bien());
                model.commit();
////                this.refrescarTablaBien();
                break;
            case Application.MODO_EDITAR:
                session.update(bien);
                 t.commit();
//                 this.refrescarTablaBien();
                //Application.FUNCIONARIOS_CONTROLLER.refrescarBusqueda();               
                break;
        } 
    }
    
    public void borrarBien(int row)throws Exception {  
//        Bien seleccionada = model.getBien().getRowAt(row); 
//        try {
//            proyecto2.logic.ModelGeneral.instance().borraBien(seleccionada);
//        } catch (Exception ex) { }
//        List<Bien> rowsMod = proyecto2.logic.ModelGeneral.instance().searchBien(model.getFilter());
//        model.setBien(rowsMod);
//        model.commit();
//        this.refrescarTablaBien();
    }
    
    public void guardarSolicitud(Solicitud solicitud) throws Exception{ 
        Transaction t = session.beginTransaction();
        Usuario principal = (Usuario) sessU.getAttribute("User");
        System.out.println(solicitud.getComprobante());
        String cod = proyecto2.logic.ModelGeneral.instance().getCodigoDependenciaDesdeLabor(principal.getFuncionario().getId());
        Dependencia dep = proyecto2.logic.ModelGeneral.instance().getDependencia(cod);
        switch(model.getModoS()){
            case Application.MODO_AGREGAR:
                solicitud.setDependencia(dep);
                session.save(solicitud);
                t.commit();
                //Application.FUNCIONARIOS_CONTROLLER.refrescarBusqueda();                   
                model.setCurrentS(new Solicitud());
                model.commit();
                break;
            case Application.MODO_EDITAR:
                session.update(solicitud);
                t.commit();
                //Application.FUNCIONARIOS_CONTROLLER.refrescarBusqueda();               
                break;
        } 
    }

    public void reset(){
        model.reset();
    }

    public void show(Point position){
        view.setLocation(position);
        this.show();
    }
    
     public void show(){
        view.setVisible(true);
    }

    public void hide(){
        view.setVisible(false);
    }
}
