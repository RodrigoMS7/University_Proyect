/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2;

import java.awt.Color;
import org.hibernate.Session;
import proyecto2.logic.HibernateUtil;
import proyecto2.presentation.application.ApplicationController;
import proyecto2.presentation.application.ApplicationModel;
import proyecto2.presentation.application.ApplicationView;
import proyecto2.presentation.bien.edicion.BienController;
import proyecto2.presentation.dependencias.edicion.DependenciaController;
import proyecto2.presentation.dependencias.listado.DependenciasController;
import proyecto2.presentation.dependencias.listado.DependenciasModel;
import proyecto2.presentation.dependencias.listado.DependenciasView;
import proyecto2.presentation.funcionarios.edicion.FuncionarioController;
import proyecto2.presentation.funcionarios.listado.FuncionariosController;
import proyecto2.presentation.solicitudes.listado.SolicitudesController;
import proyecto2.presentation.solicitudes.listado.SolicitudesModel;
import proyecto2.presentation.solicitudes.listado.SolicitudesView;

/**
 *
 * @author Dani
 */
public class Application {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Session session = HibernateUtil.getSessionFactory().openSession();
        ApplicationModel applicationModel = new ApplicationModel();
        ApplicationView applicationView= new ApplicationView();
        ApplicationController applicationController = new ApplicationController(applicationView,applicationModel,session);
        APPLICATION_CONTROLLER = applicationController;
        
        DependenciasModel dependenciasModel=new DependenciasModel();
        DependenciasView dependenciasView=new DependenciasView();
        applicationView.addInternalFrame(dependenciasView);
        DependenciasController dependenciasController=new DependenciasController(dependenciasView,dependenciasModel,session);
        DEPENDENCIAS_CONTROLLER=dependenciasController;
        
        SolicitudesModel solicitudesModel=new SolicitudesModel();
        SolicitudesView solicitudesView=new SolicitudesView();
        applicationView.addInternalFrame(solicitudesView);
        SolicitudesController solicitudesController=new SolicitudesController(solicitudesView,solicitudesModel,session);
        SOLICITUDES_CONTROLLER=solicitudesController;
        
        applicationView.setVisible(true);
    }
    
    
    public static FuncionarioController FUNCIONARIO_CONTROLLER;
    public static FuncionariosController FUNCIONARIOS_CONTROLLER;
    public static DependenciaController DEPENDENCIA_CONTROLLER;
    public static DependenciasController DEPENDENCIAS_CONTROLLER;
    public static BienController BIEN_CONTROLLER;
    public static SolicitudesController SOLICITUDES_CONTROLLER;
    public static ApplicationController APPLICATION_CONTROLLER; 
    
    public static final int MODO_AGREGAR = 0;
    public static final int MODO_EDITAR = 1;
    public static final int MODO_CONSULTAR = 2;
    
    public static final Color COLOR_ERROR = Color.red;
    public static final Color COLOR_OK = Color.black;


}
