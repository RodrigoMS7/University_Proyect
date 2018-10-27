/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2.presentation.dependencias.listado;

import java.awt.Point;
import java.util.Arrays;
import java.util.List;
import org.hibernate.Session;
import proyecto2.logic.Dependencia;

/**
 *
 * @author oscar
 */
public class DependenciasController {
    //Falta agregar domainmodel
    Session session;
    DependenciasView view;
    DependenciasModel model;
    
    public DependenciasController(DependenciasView view, DependenciasModel model, Session session){
        this.session=session;
        this.view = view;
        this.model = model;
        view.setController(this);
        view.setModel(model);
    }
    
    public void buscar(Dependencia filter) throws Exception{       
        model.setFilter(filter);
        this.refrescarBusqueda();
    }
    public void refrescarBusqueda() throws Exception{
       
        List<Dependencia> rows=proyecto2.logic.ModelGeneral.instance().searchDependencias(model.getFilter());
        model.setDependencias(rows);
        model.commit();
        if(rows.isEmpty()) throw new Exception("Ningun dato coincide");
    }

    public void borrar(int row){  
        Dependencia seleccionada = model.getDependencias().getRowAt(row); 
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
