/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2.presentation.rrhh.labor;

import java.awt.Point;
import java.util.Arrays;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import proyecto2.Application;
import proyecto2.SessionUsuario;
import proyecto2.logic.Dependencia;
import proyecto2.logic.Funcionario;
import proyecto2.logic.Labor;
import proyecto2.logic.Puesto;
import proyecto2.logic.Usuario;

/**
 *
 * @author oscar
 */
public class LaborController {
    Session session;
    LaborView view;
    LaborModel model;
    SessionUsuario sessionUsuario;
    public LaborController(LaborView view, LaborModel model, Session session, SessionUsuario u) {
        model.reset(proyecto2.logic.ModelGeneral.instance().findAllPuestos());
        this.session=session;
        sessionUsuario = u;
        this.view = view;
        this.model = model;
        view.setController(this);
        view.setModel(model);
    }
     public void buscar(Funcionario filter) throws Exception{
        model.setFilter(filter);
        this.refrescarBusqueda();
    }
    public void buscar(){
        List<Funcionario> rows = proyecto2.logic.ModelGeneral.instance().searchAllFuncionarios();
        model.setFuncionarios(rows);
        model.commit();
    }
    public void refrescarBusqueda() throws Exception{
       List<Funcionario> rows = proyecto2.logic.ModelGeneral.instance().searchFuncionarios(model.getFilter());
        model.setFuncionarios(rows);
        model.commit();
        if (rows.isEmpty()) throw new Exception("Ningún dato coincide");
    }    

    public void preAgregar(Point at)throws Exception{      
        Application.FUNCIONARIO_CONTROLLER.reset(Application.MODO_AGREGAR, new Funcionario()); //modo agregar se setea aqui
        Application.FUNCIONARIO_CONTROLLER.show(at);
    }
    
    public void editar(int row, Point at){       
        Funcionario seleccionada = model.getFuncionarios().getRowAt(row); 
        Application.FUNCIONARIO_CONTROLLER.reset(Application.MODO_EDITAR, seleccionada);
        Application.FUNCIONARIO_CONTROLLER.show(at);
    }

    public void borrar(int row){  
        Funcionario seleccionada = model.getFuncionarios().getRowAt(row); 
        try {
            proyecto2.logic.ModelGeneral.instance().eliminaFuncionario(seleccionada);
        } catch (Exception ex) { 
        }
        List<Funcionario> rowsMod = proyecto2.logic.ModelGeneral.instance().searchFuncionarios(model.getFilter());
        model.setFuncionarios(rowsMod);
        model.commit();
        buscar();
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
    
    public void hide() {
        view.setVisible(false);
    }

    public void setCodigoDependencia(String codigo) {
        model.setCodigoDependencia(codigo);
    }

    void agregaLabor(int row, Puesto puesto) throws Exception {
       Funcionario func = model.getFuncionarios().getRowAt(row);
       Labor labor = new Labor();
       labor.setFuncionario(func);
       labor.setPuesto(puesto);
       Dependencia depdendencia = proyecto2.logic.ModelGeneral.instance().getDependencia(model.getCodigoDependencia());
       labor.setDependencia(depdendencia);
       Transaction t = session.beginTransaction();
       session.save(labor);
       t.commit();
    }
}
