/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2.presentation.application;

import java.awt.Point;
import org.hibernate.Session;
import proyecto2.Application;
import proyecto2.SessionUsuario;
import proyecto2.logic.Usuario;

/**
 *
 * @author oscar
 */
public class ApplicationController {
    ApplicationView view;
    ApplicationModel model;
    Session session;
    SessionUsuario sessUsu;
    
    public ApplicationController(ApplicationView view, ApplicationModel model, Session session, SessionUsuario sessUsu) {
        this.session=session;
        this.sessUsu = sessUsu;
        this.view = view;
        this.model = model;
        view.setController(this);
        view.setModel(model);
    }
    
    public void dependenciasShow(){
        Application.DEPENDENCIAS_CONTROLLER.show();
    }
    public void funcionariosShow(){
        Application.FUNCIONARIOS_CONTROLLER.show();
    }
    public void solicitudesShow(){
        Application.SOLICITUDES_CONTROLLER.show();
    }
    public void solicitudesJefeShow(){
         Application.JEFE_CONTROLLER.show();
    }
    public void solicitudesSecretariaShow(){
         Application.SECRETARIA_CONTROLLER.show();
    }
    public void solicitudEdicionShow(){
         Application.SOLICITUD_CONTROLLER.show();
    }
    public void categoriaShow() {
        Application.CATEGORIAS_CONTROLLER.show();
    }
    
    public void enter(){
        this.reset();
        this.show();
    }
    
    public void reset(){
        //Application.FUNCIONARIOS_CONTROLLER.reset();
       // Application.ESTADOS_CONTROLLER.reset();
        model.reset((Usuario) sessUsu.getAttribute(Application.USER_ATTRIBUTE));
    }
    
    public void show(){
        view.setVisible(true);
    }
    public void show(Point position){
        view.setLocation(position);
        this.show();
    }   
    public void hide(){
        Application.DEPENDENCIAS_CONTROLLER.hide();
        //Application.ESTADOS_CONTROLLER.hide();        
        view.setVisible(false);
    }   

    
}
