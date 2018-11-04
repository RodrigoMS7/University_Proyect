/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2.presentation.rrhh.labor;

import java.util.Observable;
import javax.swing.JOptionPane;
import javax.swing.table.TableModel;
import proyecto2.Application;
import proyecto2.logic.Funcionario;

/**
 *
 * @author Dani
 */
public class FuncionariosDependenciaView extends javax.swing.JInternalFrame implements java.util.Observer {

    FuncionariosDependenciaController controller;
    FuncionariosDependenciaModel model;
    public FuncionariosDependenciaView() throws Exception {
        super("", false, true);
        initComponents();
    }

    public FuncionariosDependenciaController getController() {
        return controller;
    }

    public void setController(FuncionariosDependenciaController controller) {
        this.controller = controller;
    }

    public FuncionariosDependenciaModel getModel() {
        return model;
    }

    public void setModel(FuncionariosDependenciaModel model) {
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
        jComboBox1 = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);

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

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Registrador", "Secretaria", "Jefe OCCB", "Jefe RRHH", "Administrador" }));

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
                        .addGap(0, 32, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(IDlabl)
                    .addComponent(idTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buscar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(54, Short.MAX_VALUE))
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

    private void funcionariosTabMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_funcionariosTabMouseClicked

    }//GEN-LAST:event_funcionariosTabMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel IDlabl;
    private javax.swing.JButton buscar;
    private javax.swing.JTable funcionariosTab;
    private javax.swing.JTextField idTxtField;
    private javax.swing.JComboBox jComboBox1;
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
