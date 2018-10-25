/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2.presentation.bien.listado;

import java.awt.Point;
import java.util.List;
import org.hibernate.Session;
import proyecto2.logic.Bien;

/**
 *
 * @author Rodrigo Meléndez
 */
public class BienesController {
    Session session;
    BienesView view;
    BienesModel model;

    public BienesController(BienesView view, BienesModel model, Session session){
        this.session=session;
        this.view = view;
        this.model = model;
        view.setController(this);
        view.setModel(model);
    }

    public void buscar(Bien filter) throws Exception{
        model.setFilter(filter);
        this.refrescarBusqueda();
    }
    public void refrescarBusqueda() throws Exception{
        List<Bien> rows = proyecto2.logic.ModelGeneral.instance().searchBien(model.getFilter());
        model.setBien(rows);
        model.commit();
        if(rows.isEmpty()) throw new Exception("Ningun dato coincide");
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
