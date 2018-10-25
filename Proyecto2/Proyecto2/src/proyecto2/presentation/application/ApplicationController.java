/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2.presentation.application;

import java.awt.Point;
import org.hibernate.Session;
import proyecto2.Application;

/**
 *
 * @author oscar
 */
public class ApplicationController {
    ApplicationView view;
    ApplicationModel model;
    Session session;
    
    public ApplicationController(ApplicationView view, ApplicationModel model, Session session) {
        this.session=session;
        
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
    public void enter(){
        this.reset();
        this.show();
    }
    
    public void reset(){
        Application.DEPENDENCIAS_CONTROLLER.reset();
       // Application.ESTADOS_CONTROLLER.reset();
       // model.reset((Usuario) session.getAttribute(Application.USER_ATTRIBUTE));
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
