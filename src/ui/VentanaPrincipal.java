/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ui;

import java.awt.BorderLayout;
import herramientaborrador.Borrador;
import archivos.ServicioNuevoDocumento;

import javax.swing.*;
//import javax.swing.JButton;

/**
 *
 * @author josearielpereyra
 */
public class VentanaPrincipal extends JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName());
    private final PanelDeDibujo panelDeDibujo = new PanelDeDibujo();
    private final JToggleButton btnOvalo = new JToggleButton("Óvalo");
    private final JToggleButton btnCirculo = new JToggleButton("Círculo");
    private final JToggleButton btnRombo = new JToggleButton("Rombo");
    private final JToggleButton btnPentagono = new JToggleButton("Pentagono");
    private final JToggleButton btnCorazon = new JToggleButton("Corazon");
    private final JToggleButton btnFlecha = new JToggleButton("Flecha");
    private final JToggleButton btnHexagono = new JToggleButton("Hexágono");
    private final JToggleButton btnEstrella = new JToggleButton("Estrella");
    private final JToggleButton btnTriangulo = new JToggleButton("Triángulo");
    private final JToggleButton btnParalelogramo = new JToggleButton("Paralelogramo");
    private ServicioNuevoDocumento servicioNuevoDocumento;
    
    /**
     * Creates new form VentanaPrincipal
     */
    public VentanaPrincipal() {
        initComponents();

        add(panelDeDibujo, BorderLayout.CENTER);
        servicioNuevoDocumento =
        new ServicioNuevoDocumento(panelDeDibujo);

        int posicionRelleno = jPanel1.getComponentZOrder(chkRelleno);
        jPanel1.add(btnOvalo, posicionRelleno);
        jPanel1.add(btnCirculo, posicionRelleno + 1);
        jPanel1.add(btnRombo, posicionRelleno + 2);
        jPanel1.add(btnPentagono, posicionRelleno + 3);
        jPanel1.add(btnCorazon, posicionRelleno + 4);
        jPanel1.add(btnFlecha, posicionRelleno + 5);
        jPanel1.add(btnHexagono, posicionRelleno + 6);
        jPanel1.add(btnEstrella, posicionRelleno + 7);
        jPanel1.add(btnTriangulo, posicionRelleno + 8);
        jPanel1.add(btnParalelogramo, posicionRelleno + 9);
        
        //Agrupacion de botones
        ButtonGroup grupoHerramientas = new ButtonGroup();
        JToggleButton[] botones = {btnTrazo, btnLinea, btnRectangulo, btnOvalo, btnCirculo, btnRombo, btnPentagono,btnCorazon,btnFlecha,  btnHexagono, btnEstrella, btnTriangulo, btnParalelogramo};
        for (JToggleButton boton : botones) {
            boton.setFocusPainted(false);
            grupoHerramientas.add(boton);
        }
        chkRelleno.setFocusPainted(false);
        btnLimpiar.setFocusPainted(false);
        
        //Borrador
        javax.swing.JToggleButton btnBorrador = new javax.swing.JToggleButton("Borrador");
        btnBorrador.setFocusPainted(false);
        jPanel1.add(btnBorrador);
        grupoHerramientas.add(btnBorrador);

        Borrador borrador = new Borrador(panelDeDibujo);
        borrador.vincularBoton(btnBorrador);
        borrador.vincularLimpiar(btnLimpiar);
        
        // Opacidad
        javax.swing.JLabel lblOpacidad = new javax.swing.JLabel("Opacidad:");
        javax.swing.JSlider sliderOpacidad = new javax.swing.JSlider(0, 100, 100);
        sliderOpacidad.setToolTipText("Opacidad de las figuras: 0-100 %");
        sliderOpacidad.addChangeListener(e -> panelDeDibujo.setOpacidad(sliderOpacidad.getValue()));
        jPanel1.add(lblOpacidad);
        jPanel1.add(sliderOpacidad);

        // Trazo seleccionado por defecto al abrir
        btnTrazo.setSelected(true);

        btnTrazo.addActionListener(e -> panelDeDibujo.setTipoActual(PanelDeDibujo.TipoFigura.TRAZO));
        btnLinea.addActionListener(e -> panelDeDibujo.setTipoActual(PanelDeDibujo.TipoFigura.LINEA));
        btnRectangulo.addActionListener(e -> panelDeDibujo.setTipoActual(PanelDeDibujo.TipoFigura.RECTANGULO));
        btnOvalo.addActionListener(e -> panelDeDibujo.setTipoActual(PanelDeDibujo.TipoFigura.OVALO));
        btnCirculo.addActionListener(e -> panelDeDibujo.setTipoActual(PanelDeDibujo.TipoFigura.CIRCULO));
        btnRombo.addActionListener(e -> panelDeDibujo.setTipoActual(PanelDeDibujo.TipoFigura.ROMBO));
        btnPentagono.addActionListener(e -> panelDeDibujo.setTipoActual(PanelDeDibujo.TipoFigura.PENTAGONO));
        btnCorazon.addActionListener(e -> panelDeDibujo.setTipoActual(PanelDeDibujo.TipoFigura.CORAZON));
        btnFlecha.addActionListener(e -> panelDeDibujo.setTipoActual(PanelDeDibujo.TipoFigura.FLECHA));
        btnHexagono.addActionListener(e -> panelDeDibujo.setTipoActual(PanelDeDibujo.TipoFigura.HEXAGONO));
        btnEstrella.addActionListener(e -> panelDeDibujo.setTipoActual(PanelDeDibujo.TipoFigura.ESTRELLA));
        btnTriangulo.addActionListener(e -> panelDeDibujo.setTipoActual(PanelDeDibujo.TipoFigura.TRIANGULO));
        btnParalelogramo.addActionListener(e -> panelDeDibujo.setTipoActual(PanelDeDibujo.TipoFigura.PARALELOGRAMO));
        chkRelleno.addActionListener(e -> panelDeDibujo.setRellenarFigura(chkRelleno.isSelected()));
        btnLimpiar.addActionListener(e -> { panelDeDibujo.Limpiar(); 
        btnLimpiar.setSelected(false);
        });

        btnLimpiar.addActionListener(e -> panelDeDibujo.Limpiar());
        
        sliderGrosor.addChangeListener(e -> {
            int grosorSeleccionado = sliderGrosor.getValue();
            panelDeDibujo.setGrosorActual(grosorSeleccionado);
        });
        
        setSize(1000, 500);
        setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        btnTrazo = new javax.swing.JToggleButton();
        jSeparator5 = new javax.swing.JSeparator();
        jSeparator1 = new javax.swing.JSeparator();
        btnLinea = new javax.swing.JToggleButton();
        jSeparator2 = new javax.swing.JSeparator();
        btnRectangulo = new javax.swing.JToggleButton();
        jSeparator3 = new javax.swing.JSeparator();
        chkRelleno = new javax.swing.JCheckBox();
        jSeparator4 = new javax.swing.JSeparator();
        btnLimpiar = new javax.swing.JToggleButton();
        jSeparator6 = new javax.swing.JSeparator();
        jSeparator7 = new javax.swing.JSeparator();
        jSeparator8 = new javax.swing.JSeparator();
        jSeparator9 = new javax.swing.JSeparator();
        jSeparator10 = new javax.swing.JSeparator();
        jSeparator11 = new javax.swing.JSeparator();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jPanel2 = new javax.swing.JPanel();
        sliderGrosor = new javax.swing.JSlider();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sin Nombre - Paint");
        setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        setSize(new java.awt.Dimension(0, 0));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.add(filler1);

        btnTrazo.setText("Trazo");
        btnTrazo.addActionListener(this::btnTrazoActionPerformed);
        jPanel1.add(btnTrazo);
        jPanel1.add(jSeparator5);
        jPanel1.add(jSeparator1);

        btnLinea.setText("Linea");
        jPanel1.add(btnLinea);
        jPanel1.add(jSeparator2);

        btnRectangulo.setText("Rectangulo");
        jPanel1.add(btnRectangulo);
        jPanel1.add(jSeparator3);

        chkRelleno.setText("Relleno");
        jPanel1.add(chkRelleno);
        jPanel1.add(jSeparator4);

        btnLimpiar.setText("Limpiar");
        jPanel1.add(btnLimpiar);
        jPanel1.add(jSeparator6);
        jPanel1.add(jSeparator7);
        jPanel1.add(jSeparator8);
        jPanel1.add(jSeparator9);
        jPanel1.add(jSeparator10);
        jPanel1.add(jSeparator11);

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_START);

        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel2.setLayout(new java.awt.BorderLayout());

        sliderGrosor.setMaximum(50);
        sliderGrosor.setMinimum(1);
        sliderGrosor.setOrientation(javax.swing.JSlider.VERTICAL);
        sliderGrosor.setToolTipText("");
        sliderGrosor.setValue(3);
        sliderGrosor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sliderGrosorMouseClicked(evt);
            }
        });
        jPanel2.add(sliderGrosor, java.awt.BorderLayout.LINE_START);

        getContentPane().add(jPanel2, java.awt.BorderLayout.LINE_START);

        jMenu1.setText("Archivo");

        jMenuItem1.setText("Salir");
        jMenuItem1.addActionListener(this::jMenuItem1ActionPerformed);
        jMenu1.add(jMenuItem1);

        jMenuItem2.setText("Nuevo");
        jMenuItem2.addActionListener(this::jMenuItem2ActionPerformed);
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edición");
        jMenuBar1.add(jMenu2);

        jMenu3.setText("Ayuda");
        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        setBounds(0, 0, 573, 428);
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        System.exit(0x0);
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowClosing

    private void btnTrazoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTrazoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnTrazoActionPerformed

    private void btnDeshacer1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeshacer1ActionPerformed
        if (panelDeDibujo != null) {
            panelDeDibujo.deshacer();// TODO add your handling code here:
    }//GEN-LAST:event_btnDeshacer1ActionPerformed
    }
    
    private void btnDeshacer2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeshacer2ActionPerformed
        if (panelDeDibujo != null) {
            panelDeDibujo.deshacer();// TODO add your handling code here:
        }
    }//GEN-LAST:event_btnDeshacer2ActionPerformed

    private void btnRehacer1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRehacer1ActionPerformed
        if (panelDeDibujo != null) {
            panelDeDibujo.rehacer();
        }
    }//GEN-LAST:event_btnRehacer1ActionPerformed

    private void btnRehacer2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRehacer2ActionPerformed
        if (panelDeDibujo != null) {
            panelDeDibujo.rehacer();
        }
    }//GEN-LAST:event_btnRehacer2ActionPerformed

    private void sliderGrosorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sliderGrosorMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_sliderGrosorMouseClicked

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        servicioNuevoDocumento.nuevoDocumento();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

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
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new VentanaPrincipal().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton btnLimpiar;
    private javax.swing.JToggleButton btnLinea;
    private javax.swing.JToggleButton btnRectangulo;
    private javax.swing.JToggleButton btnTrazo;
    private javax.swing.JCheckBox chkRelleno;
    private javax.swing.Box.Filler filler1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSlider sliderGrosor;
    // End of variables declaration//GEN-END:variables
}
