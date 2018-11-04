/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2.presentation.rrhh.labor;

import proyecto2.presentation.rrhh.FuncionarioTableModel;
import java.util.ArrayList;
import java.util.List;
import java.util.Observer;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import proyecto2.logic.Funcionario;
import proyecto2.logic.Puesto;

/**
 *
 * @author oscar
 */
public class LaborModel extends java.util.Observable{

    Funcionario filter;
    FuncionarioTableModel funcionarios;
    Funcionario seleccionada;
    String codigoDependencia;
    ComboBoxModel<Puesto> puestos;
    
    
    public ComboBoxModel<Puesto> getPuestos() {
        return puestos;
    }

    public void reset(List<Puesto> puestos){      
        setPuestos(puestos);
    }
    
    public void setPuestos(List<Puesto> puestos) {
        List<Puesto> es;
        this.puestos = new DefaultComboBoxModel(puestos.toArray());
        this.commit();    
    }
    public LaborModel() {
        this.reset();
    }

    public void reset(){ 
        filter = new Funcionario();
        List<Funcionario> rows = new ArrayList<>();
        seleccionada=null;
        this.setFuncionarios(rows);
        this.commit();
    }

    public String getCodigoDependencia() {
        return codigoDependencia;
    }

    public void setCodigoDependencia(String codigoDependencia) {
        this.codigoDependencia = codigoDependencia;
        commit();
    }
    
    public Funcionario getFilter() {
        return filter;
    }

    public void setFilter(Funcionario filter) {
        this.filter = filter;
    }

    public FuncionarioTableModel getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(List<Funcionario> funcionarios) {
        int[] cols={FuncionarioTableModel.ID,FuncionarioTableModel.NOMBRE};
        this.funcionarios= new FuncionarioTableModel(cols,funcionarios);
    }

    public Funcionario getSeleccionada() {
        return seleccionada;
    }

    public void setSeleccionada(Funcionario seleccionada) {
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
