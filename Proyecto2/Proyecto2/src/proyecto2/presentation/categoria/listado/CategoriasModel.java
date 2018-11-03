/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2.presentation.categoria.listado;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;
import proyecto2.logic.Bien;
import proyecto2.logic.Categoria;

/**
 *
 * @author Dani
 */
public class CategoriasModel extends java.util.Observable{

    Categoria filter;
    CategoriaTableModel categorias;
    Categoria seleccionada;
    int codBien;

    public int getCodBien() {
        return codBien;
    }

    public void setCodBien(int codBien) {
        this.codBien = codBien;
    }

    public CategoriaTableModel getCategorias() {
        return categorias;
    }

    public void setCategorias(List<Categoria> categorias) {
        int[] cols={CategoriaTableModel.TIPO,CategoriaTableModel.CONSECUTIVO};
        this.categorias= new CategoriaTableModel(cols,categorias);
    }

    public Categoria getSeleccionada() {
        return seleccionada;
    }

    public void setSeleccionada(Categoria seleccionada) {
        this.seleccionada = seleccionada;
    }

    
    public CategoriasModel() {
        this.reset();
    }

    public void reset(){ 
        filter = new Categoria();
        List<Categoria> rows = new ArrayList<>();
        seleccionada=null;
        this.setCategorias(rows);
        this.commit();
    }

    public Categoria getFilter() {
        return filter;
    }

    public void setFilter(Categoria filter) {
        this.filter = filter;
    }

    public CategoriaTableModel getFuncionarios() {
        return categorias;
    }


    @Override
    public void addObserver(Observer o) {
        super.addObserver(o);
        this.commit();
    }

    public void commit(){
        setChanged();
        notifyObservers();
    }
}
