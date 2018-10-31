/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2.presentation.rrhh.funcionarios.edicion;

import java.util.Arrays;
import java.util.Observable;
import javax.swing.JOptionPane;
import proyecto2.Application;
import proyecto2.logic.Funcionario;

/**
 *
 * @author Dani
 */
public class FuncionarioView extends javax.swing.JInternalFrame implements java.util.Observer {

    FuncionarioController controller;
    FuncionarioModel model;

    public FuncionarioController getController() {
        return controller;
    }

    public void setController(FuncionarioController Controller) {
        this.controller = Controller;
    }

    public FuncionarioModel getModel() {
        return model;
    }

    public void setModel(FuncionarioModel model) {
        this.model = model;
        model.addObserver(this); 
    }

    public FuncionarioView() {
        super("", false, true);
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ID = new javax.swing.JTextField();
        Nombre = new javax.swing.JTextField();
        idlabel = new javax.swing.JLabel();
        nombreLab = new javax.swing.JLabel();
        guardar = new java.awt.Button();
        cancelar = new java.awt.Button();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        idlabel.setText("ID");

        nombreLab.setText("Nombre");

        guardar.setActionCommand("guardar");
        guardar.setLabel("guardar");
        guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarActionPerformed(evt);
            }
        });

        cancelar.setLabel("cancelar");
        cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(idlabel)
                        .addGap(18, 18, 18)
                        .addComponent(ID, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(nombreLab)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addComponent(guardar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(29, 29, 29)
                                .addComponent(cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(Nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(147, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(idlabel))
                .addGap(44, 44, 44)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nombreLab))
                .addGap(56, 56, 56)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(guardar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(88, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarActionPerformed
        if (this.validar()) {
            try {
                this.controller.guardar(this.creaFuncionario());
                JOptionPane.showMessageDialog(this, "Datos registrados", "OK", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Error en datos", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_guardarActionPerformed

    private void cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cancelarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField ID;
    private javax.swing.JTextField Nombre;
    private java.awt.Button cancelar;
    private java.awt.Button guardar;
    private javax.swing.JLabel idlabel;
    private javax.swing.JLabel nombreLab;
    // End of variables declaration//GEN-END:variables

    @Override
    public void update(Observable o, Object arg) {
        //this.limpiarErrores();
        Funcionario actual = model.getCurrent(); //ver quien es actual??
        this.fromPersona(actual);
    }

    public boolean validar() { //Retorna true si no hay errores
        boolean error = false;

        this.idlabel.setForeground(Application.COLOR_OK);
        if (this.ID.getText().isEmpty()) {
            this.idlabel.setForeground(Application.COLOR_ERROR);
            error = true;
        }
        this.nombreLab.setForeground(Application.COLOR_OK);
        if (this.Nombre.getText().isEmpty()) {
            this.nombreLab.setForeground(Application.COLOR_ERROR);
            error = true;
        }
        return !error;
    }

    Funcionario creaFuncionario() {
        Funcionario func = new Funcionario();
        func.setId(ID.getText());
        func.setNombre(Nombre.getText());
        System.out.println(Nombre.getName());
        return func;
    }

    public void limpiarErrores() {
        this.idlabel.setForeground(Application.COLOR_OK);
        this.nombreLab.setForeground(Application.COLOR_OK);
    }

    public void fromPersona(Funcionario actual) {
        //si el modo agrega, se pone disponible(enabled)
        this.ID.setEnabled(model.getModo() == Application.MODO_AGREGAR);
        ID.setText(actual.getId());
        Boolean editable = Arrays.asList(Application.MODO_AGREGAR, Application.MODO_EDITAR).contains(model.getModo());

        Nombre.setEnabled(editable);
        Nombre.setText(actual.getNombre());
        
        this.guardar.setVisible(editable);
        this.validate();
    }
}
