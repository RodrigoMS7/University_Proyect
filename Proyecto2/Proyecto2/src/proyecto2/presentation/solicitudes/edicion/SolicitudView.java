/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2.presentation.solicitudes.edicion;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Observable;
import javax.swing.JOptionPane;
import proyecto2.Application;
import proyecto2.logic.Bien;
import proyecto2.logic.Dependencia;
import proyecto2.logic.Labor;
import proyecto2.logic.Solicitud;

/**
 *
 * @author Rodrigo Meléndez
 */
public class SolicitudView extends javax.swing.JDialog implements java.util.Observer {

    SolicitudModel model;
    SolicitudController controller;

    public SolicitudModel getModel() {
        return model;
    }

    public void setModel(SolicitudModel model) {
        this.model = model;
        model.addObserver(this);
    }

    public SolicitudController getController() {
        return controller;
    }

    public void setController(SolicitudController controller) {
        this.controller = controller;
    }

    /**
     * Creates new form SolicitudView
     */
    public SolicitudView() {
        super(/*parent, modal*/);
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

        label_Solicitud = new javax.swing.JLabel();
        label_Bien = new javax.swing.JLabel();
        label_Descripcion = new javax.swing.JLabel();
        text_Descripcion = new javax.swing.JTextField();
        label_Marca = new javax.swing.JLabel();
        label_Modelo = new javax.swing.JLabel();
        text_Marca = new javax.swing.JTextField();
        text_Modelo = new javax.swing.JTextField();
        label_Cantidad = new javax.swing.JLabel();
        label_Precio = new javax.swing.JLabel();
        text_Cantidad = new javax.swing.JTextField();
        text_Precio = new javax.swing.JTextField();
        button_EliminaBien = new javax.swing.JButton();
        button_Guardar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_bien = new javax.swing.JTable();
        button_AgregaBien = new javax.swing.JButton();
        button_Salir = new javax.swing.JButton();
        text_Comprobante = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        comboBox_Tipo = new javax.swing.JComboBox<>();
        label_Tipo = new javax.swing.JLabel();
        text_Fecha = new javax.swing.JTextField();
        labelComprobante = new javax.swing.JLabel();
        label_Fecha = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        label_Solicitud.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        label_Solicitud.setText("SOLICITUD");
        getContentPane().add(label_Solicitud, new org.netbeans.lib.awtextra.AbsoluteConstraints(51, 29, -1, -1));

        label_Bien.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        label_Bien.setText("BIENES");
        getContentPane().add(label_Bien, new org.netbeans.lib.awtextra.AbsoluteConstraints(51, 143, -1, -1));

        label_Descripcion.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        label_Descripcion.setText("Descripción");
        getContentPane().add(label_Descripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(51, 172, -1, -1));
        getContentPane().add(text_Descripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(51, 193, 118, -1));

        label_Marca.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        label_Marca.setText("Marca");
        getContentPane().add(label_Marca, new org.netbeans.lib.awtextra.AbsoluteConstraints(183, 171, -1, -1));

        label_Modelo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        label_Modelo.setText("Modelo");
        getContentPane().add(label_Modelo, new org.netbeans.lib.awtextra.AbsoluteConstraints(328, 172, -1, -1));
        getContentPane().add(text_Marca, new org.netbeans.lib.awtextra.AbsoluteConstraints(181, 193, 135, -1));
        getContentPane().add(text_Modelo, new org.netbeans.lib.awtextra.AbsoluteConstraints(328, 193, 137, -1));

        label_Cantidad.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        label_Cantidad.setText("Cantidad");
        getContentPane().add(label_Cantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(483, 172, -1, -1));

        label_Precio.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        label_Precio.setText("Precio");
        getContentPane().add(label_Precio, new org.netbeans.lib.awtextra.AbsoluteConstraints(613, 172, -1, -1));

        text_Cantidad.setText("0");
        getContentPane().add(text_Cantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(483, 193, 118, -1));

        text_Precio.setText("0.0");
        getContentPane().add(text_Precio, new org.netbeans.lib.awtextra.AbsoluteConstraints(613, 193, 126, -1));

        button_EliminaBien.setText("Eliminar");
        button_EliminaBien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_EliminaBienActionPerformed(evt);
            }
        });
        getContentPane().add(button_EliminaBien, new org.netbeans.lib.awtextra.AbsoluteConstraints(745, 225, -1, -1));

        button_Guardar.setText("Guardar");
        button_Guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_GuardarActionPerformed(evt);
            }
        });
        getContentPane().add(button_Guardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 420, -1, -1));

        table_bien.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(table_bien);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(51, 225, 688, 166));

        button_AgregaBien.setText("Agregar");
        button_AgregaBien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_AgregaBienActionPerformed(evt);
            }
        });
        getContentPane().add(button_AgregaBien, new org.netbeans.lib.awtextra.AbsoluteConstraints(745, 187, -1, -1));

        button_Salir.setText("Salir");
        button_Salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_SalirActionPerformed(evt);
            }
        });
        getContentPane().add(button_Salir, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 420, -1, -1));
        getContentPane().add(text_Comprobante, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 60, 89, -1));

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 808, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 268, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, 810, 270));

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        comboBox_Tipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Compra", "Donación", "Producción" }));
        jPanel1.add(comboBox_Tipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(513, 39, -1, -1));

        label_Tipo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        label_Tipo.setText("Tipo");
        jPanel1.add(label_Tipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(469, 41, -1, -1));
        jPanel1.add(text_Fecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(378, 39, 81, -1));

        labelComprobante.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        labelComprobante.setText("Comprobante");
        jPanel1.add(labelComprobante, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 42, -1, -1));

        label_Fecha.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        label_Fecha.setText("Fecha dd/mm/yyyy");
        jPanel1.add(label_Fecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(242, 41, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 810, 100));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void button_EliminaBienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_EliminaBienActionPerformed
        try {
            int row = this.table_bien.getSelectedRow();
            int col = this.table_bien.getSelectedColumn();
            this.controller.borrarBien(row); //Mandar una excepcion si agrega Dependencia es null
            JOptionPane.showMessageDialog(this, "Solicitud Eliminada Exitosamente", "OK", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_button_EliminaBienActionPerformed

    private void button_GuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_GuardarActionPerformed
//        if (this.validarSolicitud()) {
            try {
                this.controller.guardarSolicitud(this.agregaSolicitud()); //Mandar una excepcion si agrega Dependencia es null
                JOptionPane.showMessageDialog(this, "Solicitud Agregada Exitosamente", "OK", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            }
//        } else {
//            JOptionPane.showMessageDialog(this, "Datos invalidos", "ERROR", JOptionPane.ERROR_MESSAGE);
//        }
    }//GEN-LAST:event_button_GuardarActionPerformed

    private void button_SalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_SalirActionPerformed
        this.controller.hide();
    }//GEN-LAST:event_button_SalirActionPerformed

    private void button_AgregaBienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_AgregaBienActionPerformed
        try {
            this.controller.refrescarTablaBien();
            this.controller.guardarBien(this.toBien());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_button_AgregaBienActionPerformed

    private boolean validarSolicitud() {
        boolean error = true;
        this.labelComprobante.setForeground(Application.COLOR_OK);
        this.label_Fecha.setForeground(Application.COLOR_OK);
        if (this.text_Comprobante.getText().isEmpty()) {
            this.labelComprobante.setForeground(Application.COLOR_ERROR);
            error = false;
        }
        if (this.text_Fecha.getText().isEmpty()) {
            this.label_Fecha.setForeground(Application.COLOR_ERROR);
            error = false;
        }
        if (!validarBien()) {
            error = false;
        }
        return error;
    }

    private boolean validarBien() {
        boolean error = true;
        this.label_Descripcion.setForeground(Application.COLOR_OK);
        this.label_Marca.setForeground(Application.COLOR_OK);
        this.label_Modelo.setForeground(Application.COLOR_OK);
        this.label_Cantidad.setForeground(Application.COLOR_OK);
        this.label_Precio.setForeground(Application.COLOR_OK);
        if (this.text_Descripcion.getText().isEmpty()) {
            this.label_Descripcion.setForeground(Application.COLOR_ERROR);
            error = false;
        }
        if (this.text_Marca.getText().isEmpty()) {
            this.label_Marca.setForeground(Application.COLOR_ERROR);
            error = false;
        }
        if (this.text_Modelo.getText().isEmpty()) {
            this.label_Modelo.setForeground(Application.COLOR_ERROR);
            error = false;
        }
        if (Integer.parseInt(this.text_Cantidad.getText()) == 0) {
            this.label_Cantidad.setForeground(Application.COLOR_ERROR);
            error = false;
        }
        if (Double.parseDouble(this.text_Precio.getText()) == 0.0) {
            this.label_Precio.setForeground(Application.COLOR_ERROR);
            error = false;
        }
        return error;
    }

    Solicitud agregaSolicitud() throws ParseException {
        Solicitud s = new Solicitud();
        s.setComprobante(this.text_Comprobante.getText());
        SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
        try {
            s.setFecha(sdf.parse(this.text_Fecha.getText())); 
        } catch (ParseException ex) {
            this.label_Fecha.setForeground(Application.COLOR_ERROR);
            JOptionPane.showMessageDialog(this, "Mal formato de la fecha (D/M/Y)", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        s.setTipoAdquisicion((String) this.comboBox_Tipo.getSelectedItem());
        s.setEstado("recibida");
//        s.setBiens(new HashSet<Bien>(proyecto2.logic.ModelGeneral.instance().searchBien(this.controller.model.getFilter()))); //Como agregar los bienes
        return s;
    }

      Bien toBien(){
        Bien b=new Bien();
        b.setDescripcion(text_Descripcion.getText());
        b.setMarca(text_Marca.getText());
        b.setModelo(text_Modelo.getText());
        b.setCantidad(Integer.parseInt(text_Cantidad.getText()));
        b.setPrecio(Double.parseDouble(text_Precio.getText()));
        return b;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton button_AgregaBien;
    private javax.swing.JButton button_EliminaBien;
    private javax.swing.JButton button_Guardar;
    private javax.swing.JButton button_Salir;
    private javax.swing.JComboBox<String> comboBox_Tipo;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelComprobante;
    private javax.swing.JLabel label_Bien;
    private javax.swing.JLabel label_Cantidad;
    private javax.swing.JLabel label_Descripcion;
    private javax.swing.JLabel label_Fecha;
    private javax.swing.JLabel label_Marca;
    private javax.swing.JLabel label_Modelo;
    private javax.swing.JLabel label_Precio;
    private javax.swing.JLabel label_Solicitud;
    private javax.swing.JLabel label_Tipo;
    private javax.swing.JTable table_bien;
    private javax.swing.JTextField text_Cantidad;
    private javax.swing.JTextField text_Comprobante;
    private javax.swing.JTextField text_Descripcion;
    private javax.swing.JTextField text_Fecha;
    private javax.swing.JTextField text_Marca;
    private javax.swing.JTextField text_Modelo;
    private javax.swing.JTextField text_Precio;
    // End of variables declaration//GEN-END:variables

    @Override
    public void update(Observable o, Object arg) {
        this.limpiarErrores();
        Solicitud actual = model.getCurrentS();
        this.fromSolicitud(actual);
        this.table_bien.setModel(model.getBien());
    }
    
    
  
    private boolean validarBien2() {
        //probando 
        boolean error = false;
        Bien actual = model.getCurrentB();

        this.label_Descripcion.setForeground(Application.COLOR_OK);
        if (this.text_Descripcion.getText().isEmpty()) {
            this.label_Descripcion.setForeground(Application.COLOR_ERROR);
            error = true;
        }

        this.label_Marca.setForeground(Application.COLOR_OK);
        if (this.text_Marca.getText().isEmpty()) {
            this.label_Marca.setForeground(Application.COLOR_ERROR);
            error = true;
        }

        this.label_Marca.setForeground(Application.COLOR_OK);
        if (this.text_Modelo.getText().isEmpty()) {
            this.label_Modelo.setForeground(Application.COLOR_ERROR);
            error = true;
        }

        this.label_Cantidad.setForeground(Application.COLOR_OK);
        if (Integer.parseInt(this.text_Cantidad.getText()) == 0) {
            this.label_Cantidad.setForeground(Application.COLOR_ERROR);
            error = true;
        }

        this.label_Precio.setForeground(Application.COLOR_OK);
        if (Double.parseDouble(this.text_Precio.getText()) == 0.0) {
            this.label_Precio.setForeground(Application.COLOR_ERROR);
            error = true;
        }
        return !error;
    }
    
    public void limpiarErrores() {
        this.labelComprobante.setForeground(Application.COLOR_OK);
        this.label_Fecha.setForeground(Application.COLOR_OK);
    }

    public void fromSolicitud(Solicitud actual) { //NO SE SI FUNCIONE, TENGO DUDAS DE SU FUNCIONAMIENTO
        try {
//            this.label_Comprobante.setEnabled(model.getModoS() == Application.MODO_AGREGAR);
//            if (actual.getEstado() == "solicitud recibida") {
//                this.label_Comprobante.setEnabled(model.getModoS() == Application.MODO_EDITAR);
//            }
//            this.label_Comprobante.setText(actual.getComprobante());
            Boolean editable = Arrays.asList(Application.MODO_AGREGAR, Application.MODO_EDITAR).contains(model.getModoS());

            this.label_Fecha.setEnabled(editable);
//            SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy");
//            this.text_Fecha.setText(sdf.format(actual.getFecha()));
            this.comboBox_Tipo.setSelectedItem(actual.getTipoAdquisicion());
//            this.controller.refrescarTablaBien();
            this.button_Guardar.setVisible(editable);
            this.validate();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

}
