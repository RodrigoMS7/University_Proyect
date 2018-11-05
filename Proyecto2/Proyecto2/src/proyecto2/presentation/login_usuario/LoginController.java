/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2.presentation.login_usuario;

import org.hibernate.Session;
import proyecto2.Application;
import proyecto2.SessionUsuario;
import proyecto2.logic.Usuario;

/**
 *
 * @author Dani
 */
public class LoginController {
    Session session;    
    LoginView view;
    LoginModel model;
    SessionUsuario sessUsu;
    
    public LoginController(LoginView view, LoginModel model, Session session, SessionUsuario sesUs) {
        this.session=session;
        this.sessUsu = sesUs;
        this.view = view;
        this.model = model;
        view.setController(this);
        view.setModel(model);
    }
    
    public LoginModel getLoginModel(){
        return model;
    }

    public void login(Usuario typed) throws Exception{
        model.setCurrent(typed);
        Usuario real = proyecto2.logic.ModelGeneral.instance().getUsuario(typed.getUsername(), typed.getPassword());
        sessUsu.setAttibute(Application.USER_ATTRIBUTE, real);
        view.setVisible(false);
        Application.APPLICATION_CONTROLLER.setUsuario(real);
        Application.APPLICATION_CONTROLLER.habilitaBotones();
        Application.APPLICATION_CONTROLLER.enter();
       
    }   

    public void logout(){
        sessUsu.removeAttribute(Application.USER_ATTRIBUTE);
        model.setCurrent(new Usuario());
        view.setVisible(true);
    }
    
    public void exit(){
        System.exit(0);
    }

}
