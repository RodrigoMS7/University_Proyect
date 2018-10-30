/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2.presentation.occb.jefe;

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
 * @author Dani
 */
public class JefeController {

    Session session;
    JefeView view;
    JefeModel model;
    SessionUsuario sessionUsuario;

    public JefeController(JefeView view, JefeModel model, Session session, SessionUsuario u) {
        this.session = session;
        sessionUsuario = u;
        this.view = view;
        this.model = model;
        view.setController(this);
        view.setModel(model);
    }

    public void buscar(Solicitud filter) throws Exception {
        model.setFilter(filter);
        this.refrescarBusqueda();
    }

    public void buscar() {
        List<Solicitud> rows = proyecto2.logic.ModelGeneral.instance().searchByEstado();
        if (!rows.isEmpty()) {
            model.setSolicitudes(rows);
            model.commit();
        }
    }

    public void refrescarBusqueda() throws Exception {
        System.out.println(model.getFilter().getEstado());
        List<Solicitud> rows = proyecto2.logic.ModelGeneral.instance().searchByEstado("recibido", model.getFilter());
        model.setSolicitudes(rows);
        model.commit();
        if (rows.isEmpty()) {
            throw new Exception("Ningún dato coincide");
        }
    }

    public void editar(int row, Point at) {
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
    }

    public void reset() {
        model.reset();
    }

    public void show() {
//        buscar();
        view.setVisible(true);
    }

    public void show(Point position) {
        view.setLocation(position);
        this.show();
    }

    public void hide() {
        view.setVisible(false);
    }
}
