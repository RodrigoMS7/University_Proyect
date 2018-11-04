/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2.presentation.registrador.bien.listado;

import java.awt.Point;
import java.util.List;
import org.hibernate.Session;
import proyecto2.SessionUsuario;
import proyecto2.logic.Bien;
import proyecto2.logic.Solicitud;

/**
 *
 * @author Rodrigo Meléndez
 */
public class BienesController {
    Session session;
    BienesView view;
    BienesModel model;
    SessionUsuario ses;

    public BienesController(BienesView view, BienesModel model, Session session, SessionUsuario ses){
        this.session=session;
        this.view = view;
        this.model = model;
        this.ses = ses;
        view.setController(this);
        view.setModel(model);
    }

    public void buscar(){
        this.refrescarBusqueda();
    }
    public void refrescarBusqueda() {
        List<Bien> rows = proyecto2.logic.ModelGeneral.instance().getAllBienesCategoria(model.getsolicitud());
        model.setBien(rows);
        model.commit();
//        if(rows.isEmpty()) throw new Exception("Ningun dato coincide");
    }

    public void borrar(int row){
        Bien seleccionada = model.getBien().getRowAt(row); 
        try {
            //domainModel.deletePersona(seleccionada);
        } catch (Exception ex) { }
        //List<Dependencia> rowsMod = domainModel.searchPersonas(model.getFilter());
      //  model.setDependencias(rowsMod);
        model.commit();
    }

    public void reset(){
        model.reset();
    }

    public void show(Solicitud sol){
        model.setsolicitud(sol);
        buscar();
        view.setVisible(true);
    }

  

    public void hide(){
        view.setVisible(false);
    }
}
