/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2;

import java.awt.Color;
import proyecto2.presentation.application.ApplicationController;
import proyecto2.presentation.bien.edicion.BienController;
import proyecto2.presentation.dependencias.edicion.DependenciaController;
import proyecto2.presentation.dependencias.listado.DependenciasController;
import proyecto2.presentation.funcionarios.edicion.FuncionarioController;
import proyecto2.presentation.funcionarios.listado.FuncionariosController;
import proyecto2.presentation.login_usuario.LoginController;
import proyecto2.presentation.solicitudes.edicion.SolicitudController;
import proyecto2.presentation.solicitudes.listado.SolicitudesController;

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
    }
    
    public static FuncionarioController FUNCIONARIO_CONTROLLER;
    public static FuncionariosController FUNCIONARIOS_CONTROLLER;
    public static DependenciaController DEPENDENCIA_CONTROLLER;
    public static DependenciasController DEPENDENCIAS_CONTROLLER;
    public static BienController BIEN_CONTROLLER;
    public static ApplicationController APPLICATION_CONTROLLER;
    public static LoginController LOGIN_CONTROLLER; 
    public static SolicitudesController SOLICITUDES_CONTROLLER;

    public static SolicitudController SOLICITUD_CONTROLLER;
    
    public static final int MODO_AGREGAR = 0;
    public static final int MODO_EDITAR = 1;
    public static final int MODO_CONSULTAR = 2;
    
    public static final Color COLOR_ERROR = Color.red;
    public static final Color COLOR_OK = Color.black;

    public static  final String  ROL_NOTAUTHORIZED="No Autorizado!";
    public static  final String  USER_ATTRIBUTE="User";

}
