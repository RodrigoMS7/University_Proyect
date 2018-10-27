/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2.presentation.solicitudes.listado;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;
import proyecto2.logic.Solicitud;

/**
 *
 * @author oscar
 */
public class SolicitudesModel extends java.util.Observable{

    Solicitud filter;
    SolicitudTableModel solicitudes;
    Solicitud seleccionada;

    public SolicitudesModel() {
        this.reset();
    }

    public void reset(){ 
        filter = new Solicitud();
        List<Solicitud> rows = new ArrayList<>();
        seleccionada=null;
        this.setSolicitudes(rows);
        this.commit();
    }

    public Solicitud getFilter() {
        return filter;
    }

    public void setFilter(Solicitud filter) {
        this.filter = filter;
    }

    public SolicitudTableModel getSolicitudes() {
        return solicitudes;
    }
    
    public void setSolicitudes(List<Solicitud> solicitudes) {
        int[] cols={SolicitudTableModel.CODIGO,SolicitudTableModel.FECHA,SolicitudTableModel.CANTIDAD,SolicitudTableModel.TIPO,SolicitudTableModel.ESTADO,SolicitudTableModel.MONTO,SolicitudTableModel.COMPROBANTE};
        this.solicitudes= new SolicitudTableModel(cols,solicitudes);
    }

    public Solicitud getSeleccionada() {
        return seleccionada;
    }

    public void setSeleccionada(Solicitud seleccionada) {
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
