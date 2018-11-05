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
import proyecto2.logic.Categoria;
import proyecto2.logic.Solicitud;
import proyecto2.presentation.activos.listado.ActivosController;
import proyecto2.presentation.activos.listado.ActivosModel;
import proyecto2.presentation.activos.listado.ActivosView;
import proyecto2.presentation.administrador.listado.AdministradorSolicitudController;
import proyecto2.presentation.administrador.listado.AdministradorSolicitudModel;
import proyecto2.presentation.administrador.listado.AdministradorSolicitudView;
import proyecto2.presentation.application.ApplicationController;
import proyecto2.presentation.application.ApplicationModel;
import proyecto2.presentation.application.ApplicationView;
import proyecto2.presentation.registrador.bien.edicion.BienController;
import proyecto2.presentation.registrador.bien.listado.BienesController;
import proyecto2.presentation.registrador.bien.listado.BienesModel;
import proyecto2.presentation.registrador.bien.listado.BienesView;
import proyecto2.presentation.categoria.edicion.CategoriaController;
import proyecto2.presentation.categoria.listado.CategoriasController;
import proyecto2.presentation.categoria.listado.CategoriasModel;
import proyecto2.presentation.categoria.listado.CategoriasView;
import proyecto2.presentation.registrador.labor.listado.LaborsController;
import proyecto2.presentation.registrador.labor.listado.LaborsModel;
import proyecto2.presentation.registrador.labor.listado.LaborsView;
import proyecto2.presentation.login_usuario.LoginController;
import proyecto2.presentation.login_usuario.LoginModel;
import proyecto2.presentation.login_usuario.LoginView;
import proyecto2.presentation.occb.jefe.JefeController;
import proyecto2.presentation.occb.jefe.JefeModel;
import proyecto2.presentation.occb.jefe.JefeView;
import proyecto2.presentation.occb.jefe.registradores.JefeRegistradorController;
import proyecto2.presentation.occb.jefe.registradores.JefeRegistradorModel;
import proyecto2.presentation.occb.jefe.registradores.JefeRegistradorView;

import proyecto2.presentation.occb.secretaria.listado.SecretariaController;
import proyecto2.presentation.occb.secretaria.listado.SecretariaModel;
import proyecto2.presentation.occb.secretaria.listado.SecretariaView;
import proyecto2.presentation.registrador.solicitudeslistado.RegistradorController;
import proyecto2.presentation.registrador.solicitudeslistado.RegistradorModel;
import proyecto2.presentation.registrador.solicitudeslistado.RegistradorView;
import proyecto2.presentation.rrhh.dependencias.edicion.DependenciaController;
import proyecto2.presentation.rrhh.dependencias.edicion.DependenciaModel;
import proyecto2.presentation.rrhh.dependencias.edicion.DependenciaView;
import proyecto2.presentation.rrhh.dependencias.listado.DependenciasController;
import proyecto2.presentation.rrhh.dependencias.listado.DependenciasModel;
import proyecto2.presentation.rrhh.dependencias.listado.DependenciasView;
import proyecto2.presentation.rrhh.funcionarios.edicion.FuncionarioController;
import proyecto2.presentation.rrhh.funcionarios.edicion.FuncionarioModel;
import proyecto2.presentation.rrhh.funcionarios.edicion.FuncionarioView;
import proyecto2.presentation.rrhh.funcionarios.listado.FuncionariosController;
import proyecto2.presentation.rrhh.funcionarios.listado.FuncionariosModel;
import proyecto2.presentation.rrhh.funcionarios.listado.FuncionariosView;
import proyecto2.presentation.rrhh.labor.LaborController;
import proyecto2.presentation.rrhh.labor.LaborModel;
import proyecto2.presentation.rrhh.labor.LaborView;
import proyecto2.presentation.rrhh.listado.FuncionariosDependenciaController;
import proyecto2.presentation.rrhh.listado.FuncionariosDependenciaModel;
import proyecto2.presentation.rrhh.listado.FuncionariosDependenciaView;
import proyecto2.presentation.solicitudes.edicion.SolicitudController;
import proyecto2.presentation.solicitudes.edicion.SolicitudModel;
import proyecto2.presentation.solicitudes.edicion.SolicitudView;
import proyecto2.presentation.solicitudes.listado.SolicitudesController;

/**
 *
 * @author Rodrigo Meléndez
 */
public class Application {

    public static void main(String[] args) throws Exception {
//        Categoria catBD = proyecto2.logic.ModelGeneral.instance().getCategoria(1);
//        System.out.println(catBD.getConsecutivo());
        Session session = HibernateUtil.getSessionFactory().openSession();
        SessionUsuario ses = new SessionUsuario();

        LoginModel loginModel = new LoginModel();
        LoginView loginView = new LoginView();
        LoginController logincontroller = new LoginController(loginView, loginModel, session, ses);
        LOGIN_CONTROLLER = logincontroller;

        loginView.setVisible(true);

        ApplicationModel applicationModel = new ApplicationModel();
        ApplicationView applicationView = new ApplicationView();
        ApplicationController applicationController = new ApplicationController(applicationView, applicationModel, session, ses);
        APPLICATION_CONTROLLER = applicationController;

        SolicitudModel solicitudModel = new SolicitudModel();
        SolicitudView solicitudView = new SolicitudView();
        SolicitudController solicitudController = new SolicitudController(solicitudView, solicitudModel, session, ses);
        applicationView.addInternalFrame(solicitudView);
        SOLICITUD_CONTROLLER = solicitudController;
        //solicitudView.setVisible(true);

        JefeModel personasModel = new JefeModel();
        JefeView personasView = new JefeView();
        //applicationView.addInternalFrame(personasView);
        JefeController personascontroller = new JefeController(personasView, personasModel, session, ses);
        JEFE_CONTROLLER = personascontroller;

        JefeRegistradorModel jefRegModel = new JefeRegistradorModel();
        JefeRegistradorView jefRegView = new JefeRegistradorView();
        JefeRegistradorController jefRegController = new JefeRegistradorController(jefRegView, jefRegModel, session, ses);
        JEFE_REGISTRADOR_CONTROLLER = jefRegController;
        applicationView.addInternalFrame(jefRegView);

        applicationView.addInternalFrame(personasView);

        RegistradorModel registradorModel = new RegistradorModel();
        RegistradorView registradorView = new RegistradorView();
        RegistradorController registradorController = new RegistradorController(registradorView, registradorModel, session, ses);
        applicationView.addInternalFrame(registradorView);
        REGISTRADOR_CONTROLLER = registradorController;

        SecretariaModel secretariaModel = new SecretariaModel();
        SecretariaView secretariaView = new SecretariaView();
        SecretariaController secretariaController = new SecretariaController(secretariaView, secretariaModel, session);
        applicationView.addInternalFrame(secretariaView);
        SECRETARIA_CONTROLLER = secretariaController;
//        
        DependenciaModel dependenciaModel = new DependenciaModel();
        DependenciaView dependenciaView = new DependenciaView(applicationView, true);
        DependenciaController dependenciaController = new DependenciaController(dependenciaView, dependenciaModel, session);
        DEPENDENCIA_CONTROLLER = dependenciaController;
        applicationView.addInternalFrame(dependenciaView);

        DependenciasModel dependenciasModel = new DependenciasModel();
        DependenciasView dependenciasView = new DependenciasView();
        applicationView.addInternalFrame(dependenciasView);
        DependenciasController dependenciasController = new DependenciasController(dependenciasView, dependenciasModel, session, ses);
        DEPENDENCIAS_CONTROLLER = dependenciasController;

        FuncionarioModel funcionarioModel = new FuncionarioModel();
        FuncionarioView funcionarioView = new FuncionarioView();
        FuncionarioController funcionarioController = new FuncionarioController(funcionarioView, funcionarioModel, session);
        FUNCIONARIO_CONTROLLER = funcionarioController;
        applicationView.addInternalFrame(funcionarioView);

        FuncionariosModel funcionariosModel = new FuncionariosModel();
        FuncionariosView funcionariosView = new FuncionariosView();
        applicationView.addInternalFrame(funcionariosView);
        FuncionariosController funcionariosController = new FuncionariosController(funcionariosView, funcionariosModel, session, ses);
        FUNCIONARIOS_CONTROLLER = funcionariosController;

        AdministradorSolicitudView administradorSolicitudView = new AdministradorSolicitudView();
        AdministradorSolicitudModel administradorSolicitudModel = new AdministradorSolicitudModel();
        AdministradorSolicitudController administradorSolicitudController = new AdministradorSolicitudController(administradorSolicitudView, administradorSolicitudModel, session, ses);
        applicationView.addInternalFrame(administradorSolicitudView);
        ADMINISTRADOR_SOLICITUD_CONTROLLER = administradorSolicitudController;
          //administradorSolicitudView.setVisible(true);
        //administradorSolicitudController.show();
        //ADMINISTRADOR_SOLICITUD_CONTROLLER.show();

       // applicationView.setVisible(true);
        CategoriasModel categoriasModel = new CategoriasModel();
        CategoriasView categoriaView = new CategoriasView();
        CategoriasController categoriasController = new CategoriasController(categoriaView, categoriasModel, session, ses);
        CATEGORIAS_CONTROLLER = categoriasController;
        applicationView.addInternalFrame(categoriaView);
        
        ActivosModel activosModel = new ActivosModel();
        ActivosView activosView= new ActivosView();
        ActivosController activosController = new ActivosController(activosView,activosModel,session,ses);
        ACTIVOS_CONTROLLER = activosController;
        applicationView.addInternalFrame(activosView);


        LaborsModel laborsModel = new LaborsModel();
        LaborsView laborsView= new LaborsView();
        LaborsController laborsController = new LaborsController(laborsView,laborsModel,session,ses);
        LABORS_CONTROLLER = laborsController;
        applicationView.addInternalFrame(laborsView);


        BienesModel bienesModel = new BienesModel();
        BienesView bienesView = new BienesView();
        BienesController bienesController = new BienesController(bienesView, bienesModel, session, ses);
        BIENES_CONTROLLER = bienesController;
        applicationView.addInternalFrame(bienesView);

        
        FuncionariosDependenciaModel funcDepModel = new FuncionariosDependenciaModel();
        FuncionariosDependenciaView funcDepView = new FuncionariosDependenciaView();
        FuncionariosDependenciaController funcDepController = new FuncionariosDependenciaController(funcDepView,funcDepModel, session, ses);
        FUNCIONARIOS_DEPENDENCIA_CONTROLLER = funcDepController;
        applicationView.addInternalFrame(funcDepView);
        
        LaborModel laborModel = new LaborModel();
        LaborView laborView = new LaborView();
        LaborController laborController = new LaborController(laborView, laborModel, session,ses);
        applicationView.addInternalFrame(laborView);
        LABOR_CONTOLLER = laborController; 
        
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
    public static SecretariaController SECRETARIA_CONTROLLER;
    public static JefeRegistradorController JEFE_REGISTRADOR_CONTROLLER;
    public static CategoriaController CATEGORIA_CONTROLLER;
    public static CategoriasController CATEGORIAS_CONTROLLER;
    public static AdministradorSolicitudController ADMINISTRADOR_SOLICITUD_CONTROLLER;
    public static RegistradorController REGISTRADOR_CONTROLLER;
    public static ActivosController ACTIVOS_CONTROLLER;
    public static BienesController BIENES_CONTROLLER;
    public static LaborsController LABORS_CONTROLLER;
    public static FuncionariosDependenciaController FUNCIONARIOS_DEPENDENCIA_CONTROLLER;
    public static LaborController LABOR_CONTOLLER;
 
    public static final int MODO_AGREGAR = 0;
    public static final int MODO_EDITAR = 1;
    public static final int MODO_CONSULTAR = 2;

    public static final Color COLOR_ERROR = Color.red;
    public static final Color COLOR_OK = Color.black;

    public static final String ROL_NOTAUTHORIZED = "No Autorizado!";
    public static final String USER_ATTRIBUTE = "User";

}