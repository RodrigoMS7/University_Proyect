/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2.presentation.categoria.listado;

import java.awt.Point;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.hibernate.Session;
import org.hibernate.Transaction;
import proyecto2.Application;
import proyecto2.SessionUsuario;
import proyecto2.logic.Activo;
import proyecto2.logic.Bien;
import proyecto2.logic.Categoria;

/**
 *
 * @author Dani
 */
public class CategoriasController {
    Session session;
    CategoriasView view;
    CategoriasModel model;
    SessionUsuario sessionUsuario;
    
    public CategoriasController(CategoriasView view, CategoriasModel model, Session session, SessionUsuario u) {
        this.session=session;
        sessionUsuario = u;
        this.view = view;
        this.model = model;
        view.setController(this);
        view.setModel(model);
    }
     public void buscar(Categoria filter) throws Exception{           
        model.setFilter(filter);
        this.refrescarBusqueda();
    }
    public void muestraBoton() {
        view.muestraBotonAsignaCat();
    }
     public void ocultaBoton() {
        view.ocultaBotonAsignaCat();
    }
    public void buscar(){
        List<Categoria> rows = proyecto2.logic.ModelGeneral.instance().searchAllCategoria();
        model.setCategorias(rows);
        model.commit();
    }
    public void refrescarBusqueda() throws Exception{
       List<Categoria> rows = proyecto2.logic.ModelGeneral.instance().searchCategoria(model.getFilter());
        model.setCategorias(rows);
        model.commit();
        if (rows.isEmpty()) throw new Exception("Ningún dato coincide");
    }    

//    public void preAgregar(Point at)throws Exception{      
//        Application.FUNCIONARIO_CONTROLLER.reset(Application.MODO_AGREGAR, new Funcionario()); //modo agregar se setea aqui
//        Application.FUNCIONARIO_CONTROLLER.show(at);
//    }
    
    public void editar(int row,String text){ 
        Categoria seleccionada = model.getCategorias().getRowAt(row); 
        seleccionada.setTipo(text);
        Transaction t = session.beginTransaction();
        session.merge(seleccionada);
        t.commit();
    }

 
    public void reset(){
        model.reset();
    }
    
    public void show(){
        buscar();
        view.setVisible(true);
    }

//    public void show(Point position){
//        view.setLocation(position);
//        this.show();
//    }   
    
    public void hide(){
        view.setVisible(false);
    }       

    void agregaCategoria(String text) {
        Categoria categoria = new Categoria();
        categoria.setTipo(text);
        categoria.setConsecutivo(0);
        Transaction t = session.beginTransaction();
        session.save(categoria);
        t.commit();
        model.commit();
        buscar();
    }
    
    public void agregaActivos(int row) throws Exception{
        Transaction t = session.beginTransaction();
        Bien bien = proyecto2.logic.ModelGeneral.instance().getBien(model.getCodBien());
        Categoria categoria = model.getCategorias().getRowAt(row);
        Categoria catBD = proyecto2.logic.ModelGeneral.instance().getCategoria(categoria.getIdCategoria());
        if(bien.getCategoria() != null) throw new Exception("Al bien ya se le asigno categoria");
        for(int i =0; i<bien.getCantidad(); i++){
            Activo activo = new Activo();
            
            StringBuilder sb = new StringBuilder();
            sb.append(categoria.getTipo().subSequence(0, 2));
            sb.append(categoria.getConsecutivo());
            activo.setCodigo(sb.toString());
            
            activo.setBien(bien);
            categoria.setConsecutivo(categoria.getConsecutivo()+1);
            session.save(activo);
        }
        catBD.setConsecutivo(catBD.getConsecutivo()+bien.getCantidad());
        bien.setCategoria(catBD);
        session.merge(bien);
        session.merge(catBD);
        t.commit();
        Application.BIENES_CONTROLLER.refrescarBusqueda();
    }
    
    public void setCodBbien(int codBien){
        model.setCodBien(codBien);
    }
}
