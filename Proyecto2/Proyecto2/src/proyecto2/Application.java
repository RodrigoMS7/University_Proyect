/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import proyecto2.data.HibernateUtil;
import proyecto2.logic.Solicitud;
import proyecto2.presentation.application.ApplicationController;
import proyecto2.presentation.application.ApplicationModel;
import proyecto2.presentation.application.ApplicationView;
import proyecto2.presentation.bien.edicion.BienController;
import proyecto2.presentation.login_usuario.LoginController;
import proyecto2.presentation.occb.jefe.JefeController;
import proyecto2.presentation.occb.jefe.JefeModel;
import proyecto2.presentation.occb.jefe.JefeView;
import proyecto2.presentation.rrhh.dependencias.edicion.DependenciaController;
import proyecto2.presentation.rrhh.dependencias.listado.DependenciasController;
import proyecto2.presentation.rrhh.funcionarios.edicion.FuncionarioController;
import proyecto2.presentation.rrhh.funcionarios.listado.FuncionariosController;
import proyecto2.presentation.solicitudes.edicion.SolicitudController;
import proyecto2.presentation.solicitudes.listado.SolicitudesController;

/**
 *
 * @author Rodrigo Meléndez
 */
public class Application {
    
    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        SessionUsuario ses = new SessionUsuario();
        
        ApplicationModel applicationModel = new ApplicationModel();
        ApplicationView applicationView= new ApplicationView();
        ApplicationController applicationController = new ApplicationController(applicationView,applicationModel,session,ses);
        APPLICATION_CONTROLLER = applicationController;
       
        applicationView.setVisible(true);
        
        
        JefeModel personasModel = new JefeModel();
        JefeView personasView= new JefeView();
        //applicationView.addInternalFrame(personasView);
        JefeController personascontroller = new JefeController(personasView,personasModel,session,ses);
        JEFE_CONTROLLER=personascontroller;
         applicationView.addInternalFrame(personasView);
        
        
    }
   
    public static FuncionarioController FUNCIONARIO_CONTROLLER;
    public static FuncionariosController FUNCIONARIOS_CONTROLLER;
    public static DependenciaController DEPENDENCIA_CONTROLLER;
    public static DependenciasController DEPENDENCIAS_CONTROLLER;
    public static BienController BIEN_CONTROLLER;
    public static ApplicationController APPLICATION_CONTROLLER;
    public static LoginController LOGIN_CONTROLLER; 
    public static SolicitudesController SOLICITUDES_CONTROLLER;
    public static JefeController JEFE_CONTROLLER;
    public static SolicitudController SOLICITUD_CONTROLLER;
    
    public static final int MODO_AGREGAR = 0;
    public static final int MODO_EDITAR = 1;
    public static final int MODO_CONSULTAR = 2;
    
    public static final Color COLOR_ERROR = Color.red;
    public static final Color COLOR_OK = Color.black;

    public static  final String  ROL_NOTAUTHORIZED="No Autorizado!";
    public static  final String  USER_ATTRIBUTE="User";

}
