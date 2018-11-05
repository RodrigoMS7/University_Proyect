/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2.presentation.activosGeneral;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;
import proyecto2.logic.Activo;
import proyecto2.logic.Solicitud;
import proyecto2.presentation.SolicitudTableModel;

/**
 *
 * @author Rodrigo Meléndez
 */
public class ActivoGeneralModel extends java.util.Observable{
    
    Activo filter;
    ActivoGeneralTableModel activos;
    Activo seleccionada;

    public ActivoGeneralModel() {
        this.reset();
    }

    public void reset(){ 
        filter = new Activo();
        List<Activo> rows = new ArrayList<>();
        seleccionada = null;
        this.setActivos(rows);
        this.commit();
    }

    public Activo getFilter() {
        return filter;
    }

    public void setFilter(Activo filter) {
        this.filter = filter;
    }

    public ActivoGeneralTableModel getActivos() {
        return activos;
    }
    
    public void setActivos(List<Activo> activos) {
        int[] cols={ActivoGeneralTableModel.CODIGO, ActivoGeneralTableModel.CATEGORIA,ActivoGeneralTableModel.DESCRIPCION,ActivoGeneralTableModel.DEPENDENCIA,ActivoGeneralTableModel.FUNCIONARIO_RESPONSABLE};
        this.activos = new ActivoGeneralTableModel(cols, activos);
    }

    public Activo getSeleccionada() {
        return seleccionada;
    }

    public void setSeleccionada(Activo seleccionada) {
        this.seleccionada = seleccionada;
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
