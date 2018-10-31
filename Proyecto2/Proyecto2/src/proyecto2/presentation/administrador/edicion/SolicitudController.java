/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2.presentation.administrador.edicion;

import proyecto2.presentation.solicitudes.edicion.*;
import java.awt.Point;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import proyecto2.Application;
import proyecto2.logic.Bien;
import proyecto2.logic.ModelGeneral;
import proyecto2.logic.Solicitud;

/**
 *
 * @author Rodrigo Meléndez
 */
public class SolicitudController {
    Session session;
    SolicitudView view;
    SolicitudModel model;

    public SolicitudController(SolicitudView view, SolicitudModel model, Session session){
        this.session=session;
        this.view = view;
        this.model = model;
        view.setController(this);
        view.setModel(model);
    }

//    public void buscar(Bien filter) throws Exception{
//        model.setFilter(filter);
//        this.refrescarBusqueda();
//    }
    
    public void refrescarTablaBien() throws Exception{
        List<Bien> rows = proyecto2.logic.ModelGeneral.instance().searchBien(model.getFilter());
        model.setBien(rows);
        model.commit();
        if(rows.isEmpty()) throw new Exception("Ningun dato coincide");
    }

    public void guardarBien(Bien bien) throws Exception{ 
        Transaction t = session.beginTransaction();
        switch(model.getModoB()){
            case Application.MODO_AGREGAR:
                session.save(bien);
                t.commit();
                //Application.FUNCIONARIOS_CONTROLLER.refrescarBusqueda();                   
                model.setCurrentB(new Bien());
                model.commit();
                this.refrescarTablaBien();
                break;
            case Application.MODO_EDITAR:
                session.update(bien);
                 t.commit();
                 this.refrescarTablaBien();
                //Application.FUNCIONARIOS_CONTROLLER.refrescarBusqueda();               
                break;
        } 
    }
    
    public void borrarBien(int row)throws Exception {  
        Bien seleccionada = model.getBien().getRowAt(row); 
        try {
            modelGeneral.borraBien(seleccionada);
        } catch (Exception ex) { }
        List<Bien> rowsMod = modelGeneral.searchBien(model.getFilter());
        model.setBien(rowsMod);
        model.commit();
        this.refrescarTablaBien();
    }
    
    public void guardarSolicitud(Solicitud solicitud) throws Exception{ 
        Transaction t = session.beginTransaction();
        switch(model.getModoS()){
            case Application.MODO_AGREGAR:
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
