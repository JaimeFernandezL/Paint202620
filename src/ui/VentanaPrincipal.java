/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ui;
//hola

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JToggleButton;

/**
 *
 * @author josearielpereyra
 */
public class VentanaPrincipal extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName());

    PanelDeDibujo panelDeDibujo = new PanelDeDibujo() {
        @Override
        protected void processMouseMotionEvent(java.awt.event.MouseEvent e) {
            if (e.getID() == java.awt.event.MouseEvent.MOUSE_DRAGGED) {
                int x = Math.max(0, Math.min(getWidth() - 1, e.getX()));
                int y = Math.max(0, Math.min(getHeight() - 1, e.getY()));
                e = new java.awt.event.MouseEvent(this, e.getID(), e.getWhen(),
                        e.getModifiersEx(), x, y, e.getClickCount(),
                        e.isPopupTrigger(), e.getButton());
            }
            super.processMouseMotionEvent(e);
        }
    };

    private final ButtonGroup grupoHerramientas = new ButtonGroup();
    private final PanelHerramientas herramientas = new PanelHerramientas();

// TODO clases por crear, por quien le corresponda:
//    private final PanelFiguras figuras = new PanelFiguras(panelDeDibujo, grupoHerramientas);
//    private final PanelColores colores = new PanelColores(panelDeDibujo);
//    private final BarraEstado estado = new BarraEstado();
    /**
     * Creates new form VentanaPrincipal
     */
    public VentanaPrincipal() {
        initComponents();

        jMenuBar1.add(herramientas.btnDeshacer, 3);
        jMenuBar1.add(herramientas.btnRehacer, 4);
        jMenuBar1.add(herramientas.btnLimpiar, 5);
        
        zonaHerramientas.add(herramientas, BorderLayout.CENTER);
        zonaGrosor.setLayout(new BorderLayout());          // slider de grosor a un lado del lienzo
        zonaGrosor.setPreferredSize(new Dimension(56, 100));
        zonaGrosor.add(herramientas.getPanelGrosor(), BorderLayout.CENTER);

        grupoHerramientas.add(herramientas.btnLapiz);
        grupoHerramientas.add(herramientas.btnBorrador);
        herramientas.btnLapiz.setSelected(true);
        herramientas.conectar(panelDeDibujo);

//        zonaFiguras.add(figuras, BorderLayout.CENTER);
//        zonaOpciones.add(opciones, BorderLayout.CENTER);
//        zonaColores.add(colores, BorderLayout.CENTER);
//        zonaEstado.add(estado, BorderLayout.CENTER);
        setSize(1000, 700);
        ZonaLienzo.add(new ContenedorLienzo(panelDeDibujo), BorderLayout.CENTER);
        panelDeDibujo.setBackground(java.awt.Color.WHITE);

//        btnTrazo.setFocusPainted(false);
//        btnLinea.setFocusPainted(false);
//        btnRectangulo.setFocusPainted(false);
//        chkRelleno.setFocusPainted(false);
//        btnLimpiar.setFocusPainted(false);
//        //Agrupacion de botones
//        javax.swing.ButtonGroup grupoHerramientas = new javax.swing.ButtonGroup();
//        grupoHerramientas.add(btnTrazo);
//        grupoHerramientas.add(btnLinea);
//        grupoHerramientas.add(btnRectangulo);
//
//        // Trazo seleccionado por defecto al abrir
//        btnTrazo.setSelected(true);
//
//        // lógica (Listeners)
//        btnTrazo.addActionListener(e -> panelDeDibujo.setTipoActual(PanelDeDibujo.TipoFigura.TRAZO));
//        btnLinea.addActionListener(e -> panelDeDibujo.setTipoActual(PanelDeDibujo.TipoFigura.LINEA));
//        btnRectangulo.addActionListener(e -> panelDeDibujo.setTipoActual(PanelDeDibujo.TipoFigura.RECTANGULO));
//
//        chkRelleno.addActionListener(e -> panelDeDibujo.setRellenarFigura(chkRelleno.isSelected()));
//
//        btnLimpiar.addActionListener(e -> panelDeDibujo.Limpiar());
//        JToolBar barra = new JToolBar();
//        barra.setFloatable(false);
//
//        JButton btnTrazo = new JButton("Trazo Libre");
//        btnTrazo.addActionListener(e -> panelDeDibujo.setTipoActual(PanelDeDibujo.TipoFigura.TRAZO));
//
//        JButton btnLinea = new JButton("Línea");
//        btnLinea.addActionListener(e -> panelDeDibujo.setTipoActual(PanelDeDibujo.TipoFigura.LINEA));
//
//        JButton btnRectangulo = new JButton("Rectángulo");
//        btnRectangulo.addActionListener(e -> panelDeDibujo.setTipoActual(PanelDeDibujo.TipoFigura.RECTANGULO));
//
//        JCheckBox chkRelleno = new JCheckBox("Relleno");
//        chkRelleno.addActionListener(e -> panelDeDibujo.setRellenarFigura(chkRelleno.isSelected()));
//
//        barra.add(btnTrazo);
//        barra.add(btnLinea);
//        barra.add(btnRectangulo);
//        barra.addSeparator();
//        barra.add(chkRelleno);
        //add(barra, BorderLayout.NORTH);
//        sliderGrosor.addChangeListener(e -> {
//            int grosorSeleccionado = sliderGrosor.getValue();
//            panelDeDibujo.setGrosorActual(grosorSeleccionado);
//        });
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

        PanelCinta = new javax.swing.JPanel();
        zonaHerramientas = new javax.swing.JPanel();
        PanelCentral = new javax.swing.JPanel();
        zonaFiguras = new javax.swing.JPanel();
        zonaEstadoFiguras = new javax.swing.JPanel();
        panelDerecho = new javax.swing.JPanel();
        zonaEstadoColor = new javax.swing.JPanel();
        zonaColores = new javax.swing.JPanel();
        ZonaLienzo = new javax.swing.JPanel();
        zonaGrosor = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        btnDeshacer1 = new javax.swing.JMenuItem();
        btnRehacer1 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sin Nombre - Paint");
        setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        setMinimumSize(new java.awt.Dimension(800, 600));
        setSize(new java.awt.Dimension(0, 0));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        PanelCinta.setLayout(new java.awt.BorderLayout());

        zonaHerramientas.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        zonaHerramientas.setPreferredSize(new java.awt.Dimension(250, 100));
        zonaHerramientas.setLayout(new java.awt.BorderLayout());
        PanelCinta.add(zonaHerramientas, java.awt.BorderLayout.WEST);

        PanelCentral.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        PanelCentral.setLayout(new java.awt.BorderLayout());

        zonaFiguras.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        PanelCentral.add(zonaFiguras, java.awt.BorderLayout.CENTER);

        zonaEstadoFiguras.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        zonaEstadoFiguras.setPreferredSize(new java.awt.Dimension(100, 90));
        PanelCentral.add(zonaEstadoFiguras, java.awt.BorderLayout.LINE_END);

        PanelCinta.add(PanelCentral, java.awt.BorderLayout.CENTER);

        panelDerecho.setLayout(new java.awt.BorderLayout());

        zonaEstadoColor.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        zonaEstadoColor.setPreferredSize(new java.awt.Dimension(100, 90));
        zonaEstadoColor.setLayout(new java.awt.BorderLayout());
        panelDerecho.add(zonaEstadoColor, java.awt.BorderLayout.LINE_START);

        zonaColores.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        zonaColores.setPreferredSize(new java.awt.Dimension(290, 100));
        zonaColores.setLayout(new java.awt.BorderLayout());
        panelDerecho.add(zonaColores, java.awt.BorderLayout.LINE_END);

        PanelCinta.add(panelDerecho, java.awt.BorderLayout.EAST);

        getContentPane().add(PanelCinta, java.awt.BorderLayout.PAGE_START);
        PanelCinta.getAccessibleContext().setAccessibleDescription("");

        ZonaLienzo.setLayout(new java.awt.BorderLayout());
        ZonaLienzo.add(zonaGrosor, java.awt.BorderLayout.LINE_START);

        getContentPane().add(ZonaLienzo, java.awt.BorderLayout.CENTER);

        jLabel1.setText("jLabel2");
        jPanel1.add(jLabel1);

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_END);

        jMenu1.setText("Archivo");

        jMenuItem1.setText("Salir");
        jMenuItem1.addActionListener(this::jMenuItem1ActionPerformed);
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edición");

        btnDeshacer1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Z, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        btnDeshacer1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/deshacer16x16.png"))); // NOI18N
        btnDeshacer1.setText("Deshacer");
        btnDeshacer1.addActionListener(this::btnDeshacer1ActionPerformed);
        jMenu2.add(btnDeshacer1);

        btnRehacer1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Y, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        btnRehacer1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/rehacer16x16.png"))); // NOI18N
        btnRehacer1.setText("Rehacer");
        btnRehacer1.addActionListener(this::btnRehacer1ActionPerformed);
        jMenu2.add(btnRehacer1);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Ayuda");
        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        setBounds(0, 0, 879, 428);
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        System.exit(0x0);
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowClosing

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
    private javax.swing.JPanel PanelCentral;
    private javax.swing.JPanel PanelCinta;
    private javax.swing.JPanel ZonaLienzo;
    private javax.swing.JMenuItem btnDeshacer1;
    private javax.swing.JMenuItem btnRehacer1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel panelDerecho;
    private javax.swing.JPanel zonaColores;
    private javax.swing.JPanel zonaEstadoColor;
    private javax.swing.JPanel zonaEstadoFiguras;
    private javax.swing.JPanel zonaFiguras;
    private javax.swing.JPanel zonaGrosor;
    private javax.swing.JPanel zonaHerramientas;
    // End of variables declaration//GEN-END:variables

    private void btnRehacer1ActionPerformed(ActionEvent e) {
        panelDeDibujo.rehacer();
    }

    private void btnDeshacer1ActionPerformed(ActionEvent e) {
        panelDeDibujo.deshacer();
    }
}
