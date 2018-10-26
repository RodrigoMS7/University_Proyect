/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2.presentation.login_usuario;

import javax.swing.JOptionPane;
import proyecto2.logic.Usuario;

/**
 *
 * @author Dani
 */
public class LoginView extends javax.swing.JFrame implements java.util.Observer {

    LoginController controller;
    LoginModel model;
    
    public void setController(LoginController controller){
        this.controller=controller;
    }

    public LoginController getController() {
        return controller;
    }
    
    public void setModel(LoginModel model){
        this.model=model;
         model.addObserver(this);
    }

    public LoginModel getModel() {
        return model;
    }
    
   public void update(java.util.Observable updatedModel,Object parametros){
//       this.limpiarErrores();
       Usuario current = model.getCurrent();
       this.fromUsuario(current);
    } 
   
    public LoginView() {
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

        userLlabl = new javax.swing.JLabel();
        passwordLabl = new javax.swing.JLabel();
        userText = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        passwordText = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        userLlabl.setText("username");

        passwordLabl.setText("Password");

        jButton1.setText("entrar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(userLlabl, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(passwordLabl))
                        .addGap(9, 9, 9)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(passwordText, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
                            .addComponent(userText)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(111, 111, 111)
                        .addComponent(jButton1)))
                .addContainerGap(166, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(userLlabl)
                    .addComponent(userText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(passwordLabl)
                    .addComponent(passwordText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 96, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(75, 75, 75))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
         try {
                this.controller.login(this.toUsuario());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE); 
            }
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
   Usuario toUsuario(){
        Usuario result = new Usuario();
        result.setUsername(this.userText.getText());
        result.setPassword(new String(this.passwordText.getPassword()));
        return result;
   }
    
   public void fromUsuario(Usuario current){ 
       userText.setText(current.getUsername());
       passwordText.setText(current.getPassword());
   }
   
//   public void limpiarErrores(){
//       this.idLbl.setForeground(Application.COLOR_OK); 
//       this.claveLbl.setForeground(Application.COLOR_OK); 
//   }
//   
//    boolean validar(){
//        boolean error=false;
//        
//        this.idLbl.setForeground(Application.COLOR_OK); 
//        if (this.idFld.getText().isEmpty()){
//            this.idLbl.setForeground(Application.COLOR_ERROR);
//             error=true;
//        }
//        
//        this.claveLbl.setForeground(Application.COLOR_OK); 
//        if ( (new String(this.claveFld.getPassword())).isEmpty()){
//            this.claveLbl.setForeground(Application.COLOR_ERROR);
//             error=true;
//        }
//        
//        return !error;
//    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel passwordLabl;
    private javax.swing.JPasswordField passwordText;
    private javax.swing.JLabel userLlabl;
    private javax.swing.JTextField userText;
    // End of variables declaration//GEN-END:variables
}