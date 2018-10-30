/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2.presentation.application;

import java.util.Observable;
import javax.swing.JInternalFrame;

/**
 *
 * @author oscar
 */
public class ApplicationView extends javax.swing.JFrame implements java.util.Observer{

    ApplicationController controller;
    ApplicationModel model;
    
    public void setController(ApplicationController controller){
        this.controller=controller;
    }

    public ApplicationController getController() {
        return controller;
    }
    
    public void setModel(ApplicationModel model){
        this.model=model;
        model.addObserver(this);
    }

    public ApplicationModel getModel() {
        return model;
    }
    public ApplicationView() {
        initComponents();
        this.setSize(this.getMaximumSize());
    }
    @Override
    public void update(java.util.Observable updatedModel,Object parametros) {
//        if (model.getCurrent()!=null){
//           this.setTitle("DEPENDENCIAS ("+model.getCurrent().getIdUsuario()+")");
//       }
//       else{
//           this.setTitle("PERSONAS");
//       }     
    }
    
    public void addInternalFrame(JInternalFrame f){
       this.desktopPane.add(f);
   }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        desktopPane = new javax.swing.JDesktopPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        DEPENDENCIAS = new javax.swing.JMenu();
        dependenciasListadoMenuItem = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        Funcionarios = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        solicitudesMenu = new javax.swing.JMenu();
        solicitudesMenuItem = new javax.swing.JMenuItem();
        secretariaMenuItem = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout desktopPaneLayout = new javax.swing.GroupLayout(desktopPane);
        desktopPane.setLayout(desktopPaneLayout);
        desktopPaneLayout.setHorizontalGroup(
            desktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 456, Short.MAX_VALUE)
        );
        desktopPaneLayout.setVerticalGroup(
            desktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 284, Short.MAX_VALUE)
        );

        DEPENDENCIAS.setText("Dependencias");

        dependenciasListadoMenuItem.setText("Listado");
        dependenciasListadoMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dependenciasListadoMenuItemActionPerformed(evt);
            }
        });
        DEPENDENCIAS.add(dependenciasListadoMenuItem);

        jMenuBar1.add(DEPENDENCIAS);

        jMenu2.setText("Exit");
        jMenuBar1.add(jMenu2);

        Funcionarios.setText("Funcionarios");

        jMenuItem1.setText("jMenuItem1");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        Funcionarios.add(jMenuItem1);

        jMenuBar1.add(Funcionarios);

        solicitudesMenu.setText("Solicitudes");

        solicitudesMenuItem.setText("Listado");
        solicitudesMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                solicitudesMenuItemActionPerformed(evt);
            }
        });
        solicitudesMenu.add(solicitudesMenuItem);

        secretariaMenuItem.setText("secretaria");
        secretariaMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                secretariaMenuItemActionPerformed(evt);
            }
        });
        solicitudesMenu.add(secretariaMenuItem);

        jMenuItem2.setText("jefe");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        solicitudesMenu.add(jMenuItem2);

        jMenuItem3.setText("Administrador");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        solicitudesMenu.add(jMenuItem3);

        jMenuBar1.add(solicitudesMenu);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopPane)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopPane)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void dependenciasListadoMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dependenciasListadoMenuItemActionPerformed
        controller.dependenciasShow();
    }//GEN-LAST:event_dependenciasListadoMenuItemActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
       controller.funcionariosShow();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void solicitudesMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_solicitudesMenuItemActionPerformed
        controller.solicitudesShow();
    }//GEN-LAST:event_solicitudesMenuItemActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
       controller.solicitudesJefeShow();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void secretariaMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_secretariaMenuItemActionPerformed
        controller.solicitudesSecretariaShow();
    }//GEN-LAST:event_secretariaMenuItemActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        controller.solicitudEdicionShow();
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ApplicationView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ApplicationView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ApplicationView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ApplicationView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ApplicationView().setVisible(true);
            }
        });
    }

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu DEPENDENCIAS;
    private javax.swing.JMenu Funcionarios;
    private javax.swing.JMenuItem dependenciasListadoMenuItem;
    private javax.swing.JDesktopPane desktopPane;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem secretariaMenuItem;
    private javax.swing.JMenu solicitudesMenu;
    private javax.swing.JMenuItem solicitudesMenuItem;
    // End of variables declaration//GEN-END:variables
}
