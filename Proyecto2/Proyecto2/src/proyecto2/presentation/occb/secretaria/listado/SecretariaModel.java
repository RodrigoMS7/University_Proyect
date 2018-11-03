/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2.presentation.occb.secretaria.listado;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;
import proyecto2.logic.Solicitud;

/**
 *
 * @author oscar
 */
public class SecretariaModel extends java.util.Observable{
    Solicitud filter;
    SecretariaTableModel solicitudes;
    Solicitud seleccionada;

    public SecretariaModel() {
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

    public SecretariaTableModel getSolicitudes() {
        return solicitudes;
    }
    
    public void setSolicitudes(List<Solicitud> solicitudes) {
        int[] cols={SecretariaTableModel.CODIGO,SecretariaTableModel.COMPROBANTE,SecretariaTableModel.DEPENDENCIA,
        SecretariaTableModel.FECHA,SecretariaTableModel.TIPO,SecretariaTableModel.CANTIDAD,SecretariaTableModel.MONTO,
        SecretariaTableModel.ESTADO};
        this.solicitudes= new SecretariaTableModel(cols,solicitudes);
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
    public void setMotivo(Solicitud s, String motivo){
        s.setMotivioRechazo(motivo);
    }
}
