/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2.presentation.rrhh.listado;

import java.awt.Point;
import java.util.Arrays;
import java.util.List;
import org.hibernate.Session;
import proyecto2.Application;
import proyecto2.SessionUsuario;
import proyecto2.logic.Funcionario;
import proyecto2.logic.Usuario;

/**
 *
 * @author oscar
 */
public class FuncionariosDependenciaController {
    Session session;
    FuncionariosDependenciaView view;
    FuncionariosDependenciaModel model;
    SessionUsuario sessionUsuario;
    public FuncionariosDependenciaController(FuncionariosDependenciaView view, FuncionariosDependenciaModel model, Session session, SessionUsuario u) {
        this.session=session;
        sessionUsuario = u;
        this.view = view;
        this.model = model;
        view.setController(this);
        view.setModel(model);
    }
     public void buscar(Funcionario filter) throws Exception{
//          Usuario principal = (Usuario) sessionUsuario.getAttribute("User");
//               System.out.println("jpa");
//           System.out.println(proyecto2.logic.ModelGeneral.instance().getRolUsuario(principal.getFuncionario().getId()));
//
//        if (!proyecto2.logic.ModelGeneral.instance().getRolUsuario(principal.getFuncionario().getId()).equals( "Administrador") ){
//           throw new Exception(Application.ROL_NOTAUTHORIZED);
//        }
          
           
        model.setFilter(filter);
        this.refrescarBusqueda();
    }
    public void buscar(){
        List<Funcionario> rows = proyecto2.logic.ModelGeneral.instance().searchAllFuncionariosFromDependencia(model.getCodigoDependencia());
        model.setFuncionarios(rows);
        model.commit();
    }
    public void refrescarBusqueda() throws Exception{
       List<Funcionario> rows = proyecto2.logic.ModelGeneral.instance().searchAllFuncionariosFromDependencia(model.getCodigoDependencia(), model.getFilter());
        model.setFuncionarios(rows);
        model.commit();
        if (rows.isEmpty()) throw new Exception("Ningún dato coincide");
    }    

    public void preAgregar(Point at)throws Exception{      
        Application.FUNCIONARIO_CONTROLLER.reset(Application.MODO_AGREGAR, new Funcionario()); //modo agregar se setea aqui
        Application.FUNCIONARIO_CONTROLLER.show(at);
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
    public void setCodigoDependencia(String codigo){
        model.setCodigoDependencia(codigo);
    }
    public void hide(){
        view.setVisible(false);
    }       
}
