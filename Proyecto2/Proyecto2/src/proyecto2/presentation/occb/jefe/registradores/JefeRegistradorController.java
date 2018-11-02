/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2.presentation.occb.jefe.registradores;

import java.awt.Point;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import proyecto2.Application;
import proyecto2.SessionUsuario;
import proyecto2.logic.Funcionario;
import proyecto2.logic.Solicitud;

/**
 *
 * @author Dani
 */
public class JefeRegistradorController {
    Session session;
    JefeRegistradorView view;
    JefeRegistradorModel model;
    SessionUsuario sessionUsuario;
    
    public JefeRegistradorController(JefeRegistradorView view, JefeRegistradorModel model, Session session, SessionUsuario u) {
        this.session=session;
        sessionUsuario = u;
        this.view = view;
        this.model = model;
        view.setController(this);
        view.setModel(model);
    }
     public void buscar() throws Exception{
        this.refrescarBusqueda();
    }
    
     public void setCodigoSolicitud(int codigo){
         model.setCodigoSolicitud(codigo);
     }
    public void refrescarBusqueda() throws Exception{
       List<Funcionario> rows = proyecto2.logic.ModelGeneral.instance().getRegistradores();
        model.setFuncionarios(rows);
        model.commit();
        if (rows.isEmpty()) throw new Exception("Ningún dato coincide");
    } 

    public void reset(){
        model.reset();
    }
    
    public void show(){
        view.setVisible(true);
    }

    public void show(Point position) throws Exception{
        buscar();
        view.setLocation(position);
        this.show();
    }   
    
    public void hide(){
        view.setVisible(false);
    }  
    
    public void asignaRegistradorAsolicitud(int row) throws Exception {
        Funcionario seleccionada = model.getFuncionarios().getRowAt(row);
        Transaction t = session.beginTransaction();
        System.out.println(model.getCodigoSolicitud());
        Solicitud solicitud = proyecto2.logic.ModelGeneral.instance().getSolicitud(model.getCodigoSolicitud());
        solicitud.setFuncionario(seleccionada);
        session.merge(solicitud);
        t.commit();
    }
}
