/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2.presentation.rrhh.funcionarios.listado;

import java.util.Observable;
import javax.swing.JOptionPane;
import javax.swing.table.TableModel;
import proyecto2.Application;
import proyecto2.logic.Funcionario;

/**
 *
 * @author Dani
 */
public class FuncionariosView extends javax.swing.JInternalFrame implements java.util.Observer {

    FuncionariosController controller;
    FuncionariosModel model;
    public FuncionariosView() throws Exception {
        super("", false, true);
        initComponents();
    }

    public FuncionariosController getController() {
        return controller;
    }

    public void setController(FuncionariosController controller) {
        this.controller = controller;
    }

    public FuncionariosModel getModel() {
        return model;
    }

    public void setModel(FuncionariosModel model) {
        this.model = model;
        model.addObserver(this);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        funcionariosTab = new javax.swing.JTable();
        IDlabl = new javax.swing.JLabel();
        idTxtField = new javax.swing.JTextField();
        buscar = new javax.swing.JButton();
        agregar = new javax.swing.JButton();
        eliminar = new javax.swing.JButton();

        funcionariosTab.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        funcionariosTab.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                funcionariosTabMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(funcionariosTab);

        IDlabl.setText("ID");

        buscar.setText("buscar");
        buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarActionPerformed(evt);
            }
        });

        agregar.setText("agregar");
        agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarActionPerformed(evt);
            }
        });

        eliminar.setText("eliminar");
        eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(IDlabl)
                        .addGap(18, 18, 18)
                        .addComponent(idTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(buscar)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 20, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(eliminar, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(agregar, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(IDlabl)
                    .addComponent(idTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buscar))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(agregar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(eliminar))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(39, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarActionPerformed
        try {
                controller.buscar(this.toFuncionario());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE); 
            }
    }//GEN-LAST:event_buscarActionPerformed

    private void agregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarActionPerformed
          try {
            controller.preAgregar(this.agregar.getLocationOnScreen());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE); 
        }
    }//GEN-LAST:event_agregarActionPerformed

    private void eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarActionPerformed
         int row = this.funcionariosTab.getSelectedRow();
        controller.borrar(row);
    }//GEN-LAST:event_eliminarActionPerformed

    private void funcionariosTabMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_funcionariosTabMouseClicked
           if (evt.getClickCount() == 2) {
            int row = this.funcionariosTab.getSelectedRow();
            int col = this.funcionariosTab.getSelectedColumn();
            controller.editar(row, evt.getLocationOnScreen());
        }
    }//GEN-LAST:event_funcionariosTabMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel IDlabl;
    private javax.swing.JButton agregar;
    private javax.swing.JButton buscar;
    private javax.swing.JButton eliminar;
    private javax.swing.JTable funcionariosTab;
    private javax.swing.JTextField idTxtField;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

    @Override
    public void update(Observable o, Object arg) {
        this.limpiarErrores();
        Funcionario filtro = model.getFilter();
        this.fromFuncionario(filtro);
        this.funcionariosTab.setModel(model.getFuncionarios());
    }
    
      boolean validar(){
        boolean error=false;
       
        this.IDlabl.setForeground(Application.COLOR_OK);
        if (this.IDlabl.getText().isEmpty()) {//***********cambiar
            this.IDlabl.setForeground(Application.COLOR_ERROR);
            error = true;
        }
        return !error;
    }

    public void fromFuncionario(Funcionario filtro) {
        this.idTxtField.setText(filtro.getId());
    }

    Funcionario toFuncionario() {
        Funcionario result = new Funcionario();
        result.setId(idTxtField.getText());
        return result;
    }
    
      public void limpiarErrores(){
       this.IDlabl.setForeground(Application.COLOR_OK); 
   }
}
