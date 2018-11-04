/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2.presentation.solicitudes.edicion;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
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

    public SolicitudModel getModel() {
        return model;
    }

    public void setModel(SolicitudModel model) {
        this.model = model;
    }

    public SolicitudView getView() {
        return view;
    }

    public void setView(SolicitudView view) {
        this.view = view;
    }

//    public void buscar(Bien filter) throws Exception{
//        model.setFilter(filter);
//        this.refrescarBusqueda();
//    }
    
    public void refrescarTablaBien() throws Exception {
        List<Bien> rows;
        switch (model.getModoS()) {
            case Application.MODO_AGREGAR:
                rows = model.getListaBienes();
                model.setBien(rows);
                model.commit();
                break;
            case Application.MODO_EDITAR:
                rows = proyecto2.logic.ModelGeneral.instance().getBienesFromSolicitud(model.getCurrentS().getCodigo());
                //rows = proyecto2.logic.ModelGeneral.instance().getAllBienesSolicitud(model.getFilter());
                model.setListaBienes(rows);
                model.setBien(rows);
                model.commit();
                if (rows.isEmpty()) {
                    throw new Exception("Ningun dato coincide");
                }
                break;
        }
    }

    public void guardarBien(Bien bien )  { 
        model.agregaBien(bien);
    }
    
    public void borrarBien(int row)throws Exception { 
        //Bien seleccionada = model.getBien().getRowAt(row);
        //switch (model.getModoS()) {
            //case Application.MODO_AGREGAR:
                // seleccionada = model.getBien().getRowAt(row);
                 //Falta
                //break;
            //case Application.MODO_EDITAR:
                 //seleccionada = model.getBien().getRowAt(row);
                //try {
                    //proyecto2.logic.ModelGeneral.instance().borraBien(seleccionada);
                //} catch (Exception ex) {
                //}
                //List<Bien> rowsMod = proyecto2.logic.ModelGeneral.instance().getAllBienesSolicitud(model.getFilter());
               // model.setBien(rowsMod);
                //model.commit();
                //this.refrescarTablaBien();
                //break;
        //}
    }
    

    public void guardarSolicitud(Solicitud solicitud, int codigoSolicitud) throws Exception{ 
        Transaction t = session.beginTransaction();
        Usuario principal = (Usuario) sessU.getAttribute("User");
        String cod = proyecto2.logic.ModelGeneral.instance().getCodigoDependenciaDesdeLabor(principal.getFuncionario().getId());
        Dependencia dep = proyecto2.logic.ModelGeneral.instance().getDependencia(cod);
        model.setSolicituaABien(solicitud);
        solicitud.setBiens(model.biens);
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
                solicitud.setCodigo(codigoSolicitud);
                solicitud.setDependencia(dep);
                session.merge(solicitud);
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

    int getCantidad() {
        return model.sumaCantidad();
    }

    double getMonto() {
        return model.sumaMontoTotal();
    }

//    void agregaBien2(int codigo) throws Exception {
//        List<Bien> bien = proyecto2.logic.ModelGeneral.instance().getBienesFromSolicitud(codigo);
//        for (Bien bienes: bien){
//            model.agregaBien(bienes);
//        }
//        this.refrescarTablaBien();
//    }

    //void guardarBien(Bien agregaBien) {
    //    model.agregaBien(agregaBien);
    //}
}
