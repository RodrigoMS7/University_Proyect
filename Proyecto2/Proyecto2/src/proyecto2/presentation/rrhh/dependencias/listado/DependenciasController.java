/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2.presentation.rrhh.dependencias.listado;

import java.awt.Point;
import java.util.Arrays;
import java.util.List;
import org.hibernate.Session;
import proyecto2.Application;
import proyecto2.SessionUsuario;
import proyecto2.logic.Dependencia;
import proyecto2.logic.Funcionario;

/**
 *
 * @author oscar
 */
public class DependenciasController {
    
    Session session;
    DependenciasView view;
    DependenciasModel model;
    SessionUsuario sessionUsuario;
    public DependenciasController(DependenciasView view, DependenciasModel model, Session session,SessionUsuario u){
        this.session=session;
        sessionUsuario = u;
        this.view = view;
        this.model = model;
        view.setController(this);
        view.setModel(model);
    }
    
    public void buscar(Dependencia filter) throws Exception{       
        model.setFilter(filter);
        this.refrescarBusqueda();
    }
    
     public void buscar() {
        List<Dependencia> rows = proyecto2.logic.ModelGeneral.instance().searchAllDependencias();
        model.setDependencias(rows);
        model.commit();
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
            proyecto2.logic.ModelGeneral.instance().eliminaDependencia(seleccionada);
        } catch (Exception ex) { 
        }
        List<Dependencia> rowsMod=proyecto2.logic.ModelGeneral.instance().searchDependencias(model.getFilter());
        
        model.setDependencias(rowsMod);
        model.commit();
        
    }
    public void preAgregar(Point at)throws Exception{
        Application.DEPENDENCIA_CONTROLLER.reset(Application.MODO_AGREGAR, new Dependencia()); //modo agregar se setea aqui
        Application.DEPENDENCIA_CONTROLLER.show(at);
    }
    public void editar(int row, Point at){
        Dependencia seleccionada = model.getDependencias().getRowAt(row);
        int modo;
        Application.DEPENDENCIA_CONTROLLER.reset(Application.MODO_EDITAR, seleccionada);
        Application.DEPENDENCIA_CONTROLLER.show(at);
    }
    public void reset(){
        model.reset();
    }
    
    public void show(){
        buscar();
        view.setVisible(true);
    }

    public void show(Point position){
        view.setLocation(position);
        this.show();
    }   
    
    public void hide(){
        view.setVisible(false);
    }     
    
    public void listaFuncionariosDependencia(int row){
        Dependencia dep= model.getDependencias().getRowAt(row);
        Application.FUNCIONARIOS_DEPENDENCIA_CONTROLLER.setCodigoDependencia(dep.getCodigo());
        Application.FUNCIONARIOS_DEPENDENCIA_CONTROLLER.show();
    }

    void asignaFuncionario(int row) {
        Dependencia dep= model.getDependencias().getRowAt(row);
        Application.LABOR_CONTOLLER.setCodigoDependencia(dep.getCodigo());
        Application.LABOR_CONTOLLER.show();     
    }
}
