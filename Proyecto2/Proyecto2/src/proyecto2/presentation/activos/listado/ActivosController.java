/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2.presentation.activos.listado;

import java.awt.Point;
import java.util.List;
import org.hibernate.Session;
import proyecto2.Application;
import proyecto2.SessionUsuario;
import proyecto2.logic.Activo;

/**
 *
 * @author Dani
 */
public class ActivosController {
    Session session;
    ActivosView view;
    ActivosModel model;
    SessionUsuario sessionUsuario;

    public ActivosController(ActivosView view, ActivosModel model,Session session,  SessionUsuario u) {
        this.session = session;
        this.view = view;
        this.model = model;
        this.sessionUsuario = u;
        view.setController(this);
        view.setModel(model);
    }
    
    public void buscar(Activo filter) throws Exception{       
        model.setFilter(filter);
        this.refrescarBusqueda();
    }
    
    public void refrescarBusqueda() throws Exception{
       
        List<Activo> rows= proyecto2.logic.ModelGeneral.instance().searchActivos(model.getFilter());
        model.setActivos(rows);
        model.commit();
        if(rows.isEmpty()) throw new Exception("Ningun dato coincide");
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
    
    public void getLabores(Point at, int row) throws Exception {
        Activo seleccionado = model.getActivos().getRowAt(row);
        Application.LABORS_CONTROLLER.setCodigoActivo(seleccionado.getCodigo());
        Application.LABORS_CONTROLLER.show(at);
        
    }
    
}
