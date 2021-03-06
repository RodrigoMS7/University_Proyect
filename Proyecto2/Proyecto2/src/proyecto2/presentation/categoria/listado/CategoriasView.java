/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2.presentation.categoria.listado;

import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import proyecto2.logic.Categoria;
import proyecto2.presentation.categoria.edicion.CategoriaController;
import proyecto2.presentation.categoria.edicion.CategoriaModel;

/**
 *
 * @author Dani
 */
public class CategoriasView extends javax.swing.JInternalFrame implements java.util.Observer {

    CategoriasModel model;
    CategoriasController controller;
    
    public void ocultaBotonAsignaCat(){
        this.asignaCat.setVisible(false);
    }
    public void muestraBotonAsignaCat(){
        this.asignaCat.setVisible(true);
    }
    public CategoriasModel getModel() {
        return model;
    }

    public void setModel(CategoriasModel model) {
        this.model = model;
        model.addObserver(this);
    }

    public CategoriasController getController() {
        return controller;
    }

    public void setController(CategoriasController controller) {
        this.controller = controller;
    }

    public CategoriasView() {
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

        jScrollPane1 = new javax.swing.JScrollPane();
        categoriaTable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        agregarButton = new javax.swing.JButton();
        tipoBuscar = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        asignaCat = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);

        categoriaTable.setModel(new javax.swing.table.DefaultTableModel(
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
        categoriaTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                categoriaTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(categoriaTable);

        jLabel1.setText("Tipo");

        agregarButton.setText("Agregar");
        agregarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarButtonActionPerformed(evt);
            }
        });

        jButton1.setText("buscar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        asignaCat.setText("Asignar");
        asignaCat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                asignaCatActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(asignaCat))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tipoBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(agregarButton)))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(tipoBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGap(18, 18, 18)
                .addComponent(agregarButton)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(54, 54, 54))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(asignaCat)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void agregarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarButtonActionPerformed
        agregarActividad();
    }//GEN-LAST:event_agregarButtonActionPerformed

    private void categoriaTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_categoriaTableMouseClicked
        if (evt.getClickCount() == 2) {
            int row = this.categoriaTable.getSelectedRow();
            JTextField tipo = new JTextField();
            Object[] message = {"Tipo: ", tipo};
            int Option = JOptionPane.showConfirmDialog(null, message, "Categoria", JOptionPane.OK_CANCEL_OPTION);
            if (Option == JOptionPane.OK_OPTION) {
                controller.editar(row, tipo.getText());
            }
        }
    }//GEN-LAST:event_categoriaTableMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            controller.buscar(this.toCategoria());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void asignaCatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_asignaCatActionPerformed
        int row = this.categoriaTable.getSelectedRow();
        try {
            controller.agregaActivos(row);
            JOptionPane.showMessageDialog(this, "Asignacion exitosa", "OK", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Bien ya tiene categoria asignada", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(CategoriasView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_asignaCatActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton agregarButton;
    private javax.swing.JButton asignaCat;
    private javax.swing.JTable categoriaTable;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField tipoBuscar;
    // End of variables declaration//GEN-END:variables

    @Override
    public void update(Observable o, Object arg) {
        Categoria filtro = model.getFilter();
//        this.fromFuncionario(filtro);
        this.categoriaTable.setModel(model.getFuncionarios());
    }

    public void agregarActividad() {
        JTextField tipo = new JTextField();
        Object[] message = {"Tipo: ", tipo};
        int Option = JOptionPane.showConfirmDialog(null, message, "Categoria", JOptionPane.OK_CANCEL_OPTION);
        if (Option == JOptionPane.OK_OPTION) {
            JOptionPane.showMessageDialog(this, "Datos registrados", "OK", JOptionPane.INFORMATION_MESSAGE);
            controller.agregaCategoria(tipo.getText());
        }
    }

    Categoria toCategoria() {
        Categoria result = new Categoria();
        result.setTipo(tipoBuscar.getText());
        return result;
    }
}
