/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2.presentation.registrador.labor.listado;

import java.awt.Point;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import proyecto2.SessionUsuario;
import proyecto2.logic.Activo;
import proyecto2.logic.Labor;

/**
 *
 * @author oscar
 */
public class LaborsController {
    Session session;
    LaborsView view;
    LaborsModel model;
    SessionUsuario sessionUsuario;
   
    
    public LaborsController(LaborsView view, LaborsModel model,Session session,  SessionUsuario u) {
        this.session = session;
        this.view = view;
        this.model = model;
        this.sessionUsuario = u;
        view.setController(this);
        view.setModel(model);
    }

    public void setCodigoActivo(String codigoActivo) {
        model.setCodigoActivo(codigoActivo);
    }
    
    public void buscar()throws Exception{
        List<Labor> rows = proyecto2.logic.ModelGeneral.instance().searchLabors();
        model.setLabors(rows);
        model.commit();
        if (rows.isEmpty()) throw new Exception("Ningún dato coincide");
    }
    
    public void reset(){
        model.reset();
    }
    
    public void show() throws Exception{
        this.buscar();
        view.setVisible(true);
    }

    public void show(Point position)throws Exception{
        
        view.setLocation(position);
        this.show();
    }   
    
    public void hide(){
        view.setVisible(false);
    }    
    public void asignaLaboraActivo(int row)throws Exception{
        Labor seleccionada = model.getLabors().getRowAt(row);
        Transaction t = session.beginTransaction();
        Activo a = proyecto2.logic.ModelGeneral.instance().getActivo(model.getCodigoActivo());
        a.setLabor(seleccionada);
        session.merge(a);
        t.commit();
    }
}
