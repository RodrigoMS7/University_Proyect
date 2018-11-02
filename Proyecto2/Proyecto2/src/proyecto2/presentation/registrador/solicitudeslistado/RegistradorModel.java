/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2.presentation.registrador.solicitudeslistado;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;
import proyecto2.logic.Solicitud;
import proyecto2.presentation.SolicitudTableModel;
/**
 *
 * @author oscar
 */
public class RegistradorModel extends java.util.Observable{
    Solicitud filter;
    SolicitudTableModel solicitudes;
    Solicitud seleccionada;

    public RegistradorModel() {
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
        int[] cols={SolicitudTableModel.CODIGO,SolicitudTableModel.COMPROBANTE,SolicitudTableModel.DEPENDENCIA,
        SolicitudTableModel.FECHA,SolicitudTableModel.TIPO,SolicitudTableModel.CANTIDAD,SolicitudTableModel.MONTO,
        SolicitudTableModel.ESTADO};
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
    public void setEstado(Solicitud s, String estado){
        s.setEstado(estado);
    }
}
