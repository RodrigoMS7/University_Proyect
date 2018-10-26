/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2;

import java.awt.Color;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import proyecto2.logic.Funcionario;
import proyecto2.logic.HibernateUtil;
import proyecto2.logic.Labor;
import proyecto2.logic.Puesto;
import proyecto2.logic.Usuario;
import proyecto2.presentation.application.ApplicationController;
import proyecto2.presentation.application.ApplicationModel;
import proyecto2.presentation.application.ApplicationView;
import proyecto2.presentation.bien.edicion.BienController;
import proyecto2.presentation.dependencias.edicion.DependenciaController;
import proyecto2.presentation.dependencias.listado.DependenciasController;
import proyecto2.presentation.funcionarios.edicion.FuncionarioController;
import proyecto2.presentation.funcionarios.edicion.FuncionarioModel;
import proyecto2.presentation.funcionarios.edicion.FuncionarioView;
import proyecto2.presentation.funcionarios.listado.FuncionariosController;
import proyecto2.presentation.funcionarios.listado.FuncionariosModel;
import proyecto2.presentation.funcionarios.listado.FuncionariosView;
import proyecto2.presentation.login_usuario.LoginController;
import proyecto2.presentation.login_usuario.LoginModel;
import proyecto2.presentation.login_usuario.LoginView;

/**
 *
 * @author Dani
 */
public class Application {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
          String sql = "select puesto,funcionario from labor";
        Session session = HibernateUtil.getSessionFactory().openSession();
        SessionUsuario sessionUsu = new SessionUsuario();
//           Usuario u = proyecto2.logic.ModelGeneral.instance().getUsuario(1, "001");
//           System.out.println(u.getUsername());
        ApplicationModel applicationModel = new ApplicationModel();
        ApplicationView applicationView = new ApplicationView();
        ApplicationController applicationController = new ApplicationController(applicationView, applicationModel, session,sessionUsu);
        APPLICATION_CONTROLLER = applicationController;
        applicationView.setVisible(true);
        
        FuncionarioModel funcionarioModel = new FuncionarioModel();
        FuncionarioView funcionarioView = new FuncionarioView(applicationView, true);
        FuncionarioController funcionarioController = new FuncionarioController(funcionarioView, funcionarioModel, session);

        
        FuncionariosModel funcionariosModel = new FuncionariosModel();
        FuncionariosView funcionariosView = new FuncionariosView();
        applicationView.addInternalFrame(funcionariosView);
        FuncionariosController funcionariosController = new FuncionariosController(funcionariosView, funcionariosModel, session,sessionUsu);
        FUNCIONARIOS_CONTROLLER = funcionariosController;
//        applicationView.setVisible(true);

        LoginModel loginModel = new LoginModel();
        LoginView loginView = new LoginView();
        LoginController logincontroller = new LoginController(loginView, loginModel, session,sessionUsu);
        LOGIN_CONTROLLER = logincontroller;
        loginView.setVisible(true);
    }
    
    
   

    public static FuncionarioController FUNCIONARIO_CONTROLLER;
    public static FuncionariosController FUNCIONARIOS_CONTROLLER;
    public static DependenciaController DEPENDENCIA_CONTROLLER;
    public static DependenciasController DEPENDENCIAS_CONTROLLER;
    public static BienController BIEN_CONTROLLER;
    public static ApplicationController APPLICATION_CONTROLLER;
    public static LoginController LOGIN_CONTROLLER; 
    
    public static final int MODO_AGREGAR = 0;
    public static final int MODO_EDITAR = 1;
    public static final int MODO_CONSULTAR = 2;

    public static final Color COLOR_ERROR = Color.red;
    public static final Color COLOR_OK = Color.black;

    public static  final String  ROL_NOTAUTHORIZED="No Autorizado!";
    public static  final String  USER_ATTRIBUTE="User";

}
