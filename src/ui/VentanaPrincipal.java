/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ui;
//hola

import archivos.ServicioAbrirImagen;
import archivos.ServicioGuardarImagen;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JColorChooser;

//import javax.swing.JButton;
//import javax.swing.ButtonGroup;

import archivos.ServicioNuevoDocumento;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.ButtonGroup;
import javax.swing.JToggleButton;

/**
 *
 * @author josearielpereyra
 */
public class VentanaPrincipal extends JFrame {

    private static final java.util.logging.Logger logger
            = java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName());

    private final JToggleButton btnOvalo = new JToggleButton("Óvalo");
    private final JToggleButton btnCirculo = new JToggleButton("Círculo");
    private final JToggleButton btnRombo = new JToggleButton("Rombo");
    private final JToggleButton btnPentagono = new JToggleButton("Pentagono");
    private final JToggleButton btnCorazon = new JToggleButton("Corazon");
    private final JToggleButton btnFlecha = new JToggleButton("Flecha");
    private final JToggleButton btnHexagono = new JToggleButton("Hexágono");
    private final JToggleButton btnEstrella = new JToggleButton("Estrella");
    //
    private ContenedorLienzo contenedorLienzo;
    //

    private ServicioNuevoDocumento servicioNuevoDocumento;
    private final ButtonGroup grupoHerramientas = new ButtonGroup();
    private final PanelHerramientas herramientas = new PanelHerramientas();

    PanelDeDibujo panelDeDibujo = new PanelDeDibujo();

    
    private Color colorActual = Color.WHITE;
    private Color colorAnterior = Color.WHITE;
    
    
    private void cambiarColor(Color nuevoColor) {

    colorAnterior = colorActual;
    colorActual = nuevoColor;

    panelColorAnterior.setBackground(colorAnterior);
    panelColorActual.setBackground(colorActual);
    //para conectar la paleta de colores al lienzo, sustituir setColor por el método que se requiere
    //panelDeDibujo.setColor(colorActual);
}
    

    /**
     * Creates new form VentanaPrincipal
     */
    public VentanaPrincipal() {
        initComponents();
        
        
     
        jButton8.setBackground(Color.WHITE);
      
        
        // Botones de la paleta de colores
        jButton1.addActionListener(e -> cambiarColor(jButton1.getBackground()));
        btnRojo.addActionListener(e -> cambiarColor(btnRojo.getBackground()));
        jButton3.addActionListener(e -> cambiarColor(jButton3.getBackground()));
        jButton4.addActionListener(e -> cambiarColor(jButton4.getBackground()));
        jButton5.addActionListener(e -> cambiarColor(jButton5.getBackground()));
        jButton6.addActionListener(e -> cambiarColor(jButton6.getBackground()));
        jButton7.addActionListener(e -> cambiarColor(jButton7.getBackground()));
        jButton8.addActionListener(e -> cambiarColor(jButton8.getBackground()));
        jButton9.addActionListener(e -> cambiarColor(jButton9.getBackground()));
        jButton10.addActionListener(e -> cambiarColor(jButton10.getBackground()));
        jButton11.addActionListener(e -> cambiarColor(jButton11.getBackground()));
        jButton12.addActionListener(e -> cambiarColor(jButton12.getBackground()));
        jButton13.addActionListener(e -> cambiarColor(jButton13.getBackground()));
        jButton14.addActionListener(e -> cambiarColor(jButton14.getBackground()));
        jButton15.addActionListener(e -> cambiarColor(jButton15.getBackground()));
        jButton16.addActionListener(e -> cambiarColor(jButton16.getBackground()));
        jButton17.addActionListener(e -> cambiarColor(jButton17.getBackground()));
        jButton18.addActionListener(e -> cambiarColor(jButton18.getBackground()));


        //Delimitar cada clase en su zona
//       zonaLienzo.add(new ContenedorLienzo(panelDeDibujo), BorderLayout.CENTER);
//        zonaHerramientas.add(herramientas, BorderLayout.CENTER);

        jMenuBar1.add(herramientas.btnGuardar, 3);
        jMenuBar1.add(herramientas.btnDeshacer, 4);
        jMenuBar1.add(herramientas.btnRehacer, 5);
        jMenuBar1.add(herramientas.btnLimpiar, 6);

        zonaHerramientas.add(herramientas, BorderLayout.CENTER);
        zonaGrosor.setLayout(new BorderLayout());          // slider de grosor a un lado del lienzo
        zonaGrosor.setPreferredSize(new Dimension(110, 100));
        zonaGrosor.add(herramientas.getPanelGrosor(), BorderLayout.CENTER);

        herramientas.conectar(panelDeDibujo);
        panelDeDibujo.setGrosorActual(herramientas.sliderGrosor.getValue());

        //int posicionRelleno = jPanel1.getComponentZOrder(chkRelleno);
//        jPanel1.add(btnOvalo, posicionRelleno);
//        jPanel1.add(btnCirculo, posicionRelleno + 1);
//        jPanel1.add(btnRombo, posicionRelleno + 2);
//        jPanel1.add(btnPentagono, posicionRelleno + 3);
//        jPanel1.add(btnCorazon, posicionRelleno + 4);
//        jPanel1.add(btnFlecha, posicionRelleno + 5);
//        jPanel1.add(btnHexagono, posicionRelleno + 6);
//        jPanel1.add(btnEstrella, posicionRelleno + 7);
        servicioNuevoDocumento
                = new ServicioNuevoDocumento(panelDeDibujo);

        btnLapiz.addActionListener(e -> herramientas.activarLapiz());
        btnBorrador.addActionListener(e -> herramientas.activarBorrador());

        //btnTrazo.addActionListener(e -> panelDeDibujo.setTipoActual(PanelDeDibujo.TipoFigura.TRAZO));
        //btnLinea.addActionListener(e -> panelDeDibujo.setTipoActual(PanelDeDibujo.TipoFigura.LINEA));
        //btnRectangulo.addActionListener(e -> panelDeDibujo.setTipoActual(PanelDeDibujo.TipoFigura.RECTANGULO));
        btnOvalo.addActionListener(e -> panelDeDibujo.setTipoActual(PanelDeDibujo.TipoFigura.OVALO));
        btnCirculo.addActionListener(e -> panelDeDibujo.setTipoActual(PanelDeDibujo.TipoFigura.CIRCULO));
        btnRombo.addActionListener(e -> panelDeDibujo.setTipoActual(PanelDeDibujo.TipoFigura.ROMBO));
        btnPentagono.addActionListener(e -> panelDeDibujo.setTipoActual(PanelDeDibujo.TipoFigura.PENTAGONO));
        btnCorazon.addActionListener(e -> panelDeDibujo.setTipoActual(PanelDeDibujo.TipoFigura.CORAZON));
        btnFlecha.addActionListener(e -> panelDeDibujo.setTipoActual(PanelDeDibujo.TipoFigura.FLECHA));
        btnHexagono.addActionListener(e -> panelDeDibujo.setTipoActual(PanelDeDibujo.TipoFigura.HEXAGONO));
        btnEstrella.addActionListener(e -> panelDeDibujo.setTipoActual(PanelDeDibujo.TipoFigura.ESTRELLA));
        //chkRelleno.addActionListener(e -> panelDeDibujo.setRellenarFigura(chkRelleno.isSelected()));
        //btnLimpiar.addActionListener(e -> { panelDeDibujo.Limpiar(); 
        //btnLimpiar.setSelected(false);});


//        zonaFiguras.add(figuras, BorderLayout.CENTER);
//        zonaOpciones.add(opciones, BorderLayout.CENTER);
//        zonaColores.add(colores, BorderLayout.CENTER);
//        zonaEstado.add(estado, BorderLayout.CENTER);
        setSize(1000, 700);
        contenedorLienzo = new ContenedorLienzo(panelDeDibujo);
        ZonaLienzo.add(contenedorLienzo, BorderLayout.CENTER);

        herramientas.instalarMenuArchivo(jMenu1, jMenuItem2, jMenuItem1,
                panelDeDibujo, contenedorLienzo, this);

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
        btnLapiz = new javax.swing.JButton();
        btnBorrador = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        PanelCentral = new javax.swing.JPanel();
        zonaFiguras = new javax.swing.JPanel();
        btnLinea = new javax.swing.JButton();
        btnTrazo = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jButton17 = new javax.swing.JButton();
        panelDerecho = new javax.swing.JPanel();
        zonaEstadoColor = new javax.swing.JPanel();
        panelColorActual = new javax.swing.JPanel();
        panelColorAnterior = new javax.swing.JPanel();
        zonaColores = new javax.swing.JPanel();
        panelBotones = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton19 = new javax.swing.JButton();
        btnRojo = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton20 = new javax.swing.JButton();
        jButton21 = new javax.swing.JButton();
        jButton22 = new javax.swing.JButton();
        jButton23 = new javax.swing.JButton();
        jButton24 = new javax.swing.JButton();
        jButton25 = new javax.swing.JButton();
        jButton26 = new javax.swing.JButton();
        jButton27 = new javax.swing.JButton();
        jButton28 = new javax.swing.JButton();
        jButton29 = new javax.swing.JButton();
        jButton30 = new javax.swing.JButton();
        jButton18 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btnElegirColor = new javax.swing.JButton();
        ZonaLienzo = new javax.swing.JPanel();
        zonaGrosor = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        btnDeshacer1 = new javax.swing.JMenuItem();
        btnRehacer1 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sin Nombre - Paint");
        setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        setMinimumSize(new java.awt.Dimension(915, 420));
        setPreferredSize(new java.awt.Dimension(910, 420));
        setSize(new java.awt.Dimension(0, 0));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        PanelCinta.setLayout(new java.awt.BorderLayout());

        zonaHerramientas.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        zonaHerramientas.setPreferredSize(new java.awt.Dimension(250, 100));

        btnLapiz.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/lapiz.png"))); // NOI18N
        btnLapiz.setToolTipText("Lapiz");

        btnBorrador.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/borrador.png"))); // NOI18N
        btnBorrador.setToolTipText("Borrador");
        btnBorrador.addActionListener(this::btnBorradorActionPerformed);

        jLabel3.setText("Herramientas");

        javax.swing.GroupLayout zonaHerramientasLayout = new javax.swing.GroupLayout(zonaHerramientas);
        zonaHerramientas.setLayout(zonaHerramientasLayout);
        zonaHerramientasLayout.setHorizontalGroup(
            zonaHerramientasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(zonaHerramientasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnLapiz)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBorrador)
                .addContainerGap(192, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, zonaHerramientasLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55))
        );
        zonaHerramientasLayout.setVerticalGroup(
            zonaHerramientasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(zonaHerramientasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(zonaHerramientasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLapiz)
                    .addComponent(btnBorrador))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addContainerGap())
        );

        PanelCinta.add(zonaHerramientas, java.awt.BorderLayout.WEST);

        PanelCentral.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        PanelCentral.setLayout(new java.awt.BorderLayout());

        zonaFiguras.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        zonaFiguras.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        zonaFiguras.setMaximumSize(new java.awt.Dimension(257, 256));
        zonaFiguras.setMinimumSize(new java.awt.Dimension(2, 2));

        btnLinea.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/linea-diagonal_1.png"))); // NOI18N
        btnLinea.setToolTipText("Linea");

        btnTrazo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/acento-circunflejo.png"))); // NOI18N
        btnTrazo.setToolTipText("Trazo");

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/rectangulo.png"))); // NOI18N
        jButton6.setToolTipText("Rectangulo");

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cuadrado.png"))); // NOI18N
        jButton7.setToolTipText("Cuadrado");

        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/circulo.png"))); // NOI18N
        jButton8.setToolTipText("Circulo");

        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/ovalo.png"))); // NOI18N
        jButton9.setToolTipText("Ovalo");

        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/corazon.png"))); // NOI18N
        jButton10.setToolTipText("Corazon");

        jButton11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/estrella-vacia.png"))); // NOI18N
        jButton11.setToolTipText("Estrella");

        jButton12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/hexagono.png"))); // NOI18N
        jButton12.setToolTipText("Hexagono");

        jButton13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/pentagono.png"))); // NOI18N
        jButton13.setToolTipText("Pentagono");

        jButton14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/rombo.png"))); // NOI18N
        jButton14.setToolTipText("Rombo");

        jButton15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/triangulo.png"))); // NOI18N
        jButton15.setToolTipText("Triangulo");

        jButton16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/flecha-correcta.png"))); // NOI18N
        jButton16.setToolTipText("Flecha");

        jLabel2.setText("Formas");

        jButton17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/paralelogramo (1).png"))); // NOI18N
        jButton17.setToolTipText("Paralelogramo");

        javax.swing.GroupLayout zonaFigurasLayout = new javax.swing.GroupLayout(zonaFiguras);
        zonaFiguras.setLayout(zonaFigurasLayout);
        zonaFigurasLayout.setHorizontalGroup(
            zonaFigurasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(zonaFigurasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(zonaFigurasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(zonaFigurasLayout.createSequentialGroup()
                        .addComponent(btnLinea, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnTrazo, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(zonaFigurasLayout.createSequentialGroup()
                        .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(zonaFigurasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(zonaFigurasLayout.createSequentialGroup()
                                .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(8, Short.MAX_VALUE))
        );
        zonaFigurasLayout.setVerticalGroup(
            zonaFigurasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(zonaFigurasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(zonaFigurasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTrazo, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLinea, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(zonaFigurasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addContainerGap())
        );

        PanelCentral.add(zonaFiguras, java.awt.BorderLayout.LINE_START);

        PanelCinta.add(PanelCentral, java.awt.BorderLayout.CENTER);

        panelDerecho.setLayout(new java.awt.BorderLayout());

        zonaEstadoColor.setMinimumSize(new java.awt.Dimension(20, 22));
        zonaEstadoColor.setPreferredSize(new java.awt.Dimension(100, 90));

        panelColorActual.setBackground(new java.awt.Color(0, 0, 0));
        panelColorActual.setPreferredSize(new java.awt.Dimension(35, 35));

        panelColorAnterior.setBackground(new java.awt.Color(255, 255, 255));
        panelColorAnterior.setPreferredSize(new java.awt.Dimension(35, 35));

        javax.swing.GroupLayout zonaEstadoColorLayout = new javax.swing.GroupLayout(zonaEstadoColor);
        zonaEstadoColor.setLayout(zonaEstadoColorLayout);
        zonaEstadoColorLayout.setHorizontalGroup(
            zonaEstadoColorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(zonaEstadoColorLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(zonaEstadoColorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelColorActual, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelColorAnterior, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(31, Short.MAX_VALUE))
        );
        zonaEstadoColorLayout.setVerticalGroup(
            zonaEstadoColorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(zonaEstadoColorLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelColorActual, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelColorAnterior, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelDerecho.add(zonaEstadoColor, java.awt.BorderLayout.CENTER);

        zonaColores.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        zonaColores.setPreferredSize(new java.awt.Dimension(290, 100));

        panelBotones.setName("PanelBotones"); // NOI18N
        panelBotones.setPreferredSize(new java.awt.Dimension(280, 63));
        panelBotones.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jButton1.setBackground(new java.awt.Color(0, 0, 0));
        jButton1.setToolTipText("Negro");
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setPreferredSize(new java.awt.Dimension(25, 25));
        panelBotones.add(jButton1);

        jButton19.setBackground(new java.awt.Color(102, 102, 102));
        jButton19.setToolTipText("Gris");
        jButton19.setPreferredSize(new java.awt.Dimension(25, 25));
        panelBotones.add(jButton19);

        btnRojo.setBackground(new java.awt.Color(204, 0, 0));
        btnRojo.setToolTipText("Rojo");
        btnRojo.setPreferredSize(new java.awt.Dimension(25, 25));
        panelBotones.add(btnRojo);

        jButton3.setBackground(new java.awt.Color(255, 153, 0));
        jButton3.setToolTipText("Naranja");
        jButton3.setPreferredSize(new java.awt.Dimension(25, 25));
        panelBotones.add(jButton3);

        jButton4.setBackground(new java.awt.Color(255, 255, 0));
        jButton4.setToolTipText("Amarillo");
        jButton4.setPreferredSize(new java.awt.Dimension(25, 25));
        panelBotones.add(jButton4);

        jButton5.setBackground(new java.awt.Color(0, 153, 0));
        jButton5.setToolTipText("Verde");
        jButton5.setPreferredSize(new java.awt.Dimension(25, 25));
        panelBotones.add(jButton5);

        jButton20.setBackground(new java.awt.Color(51, 51, 255));
        jButton20.setToolTipText("Azul");
        jButton20.setPreferredSize(new java.awt.Dimension(25, 25));
        panelBotones.add(jButton20);

        jButton21.setBackground(new java.awt.Color(204, 0, 204));
        jButton21.setToolTipText("Púrpura");
        jButton21.setPreferredSize(new java.awt.Dimension(25, 25));
        panelBotones.add(jButton21);

        jButton22.setBackground(new java.awt.Color(153, 102, 0));
        jButton22.setToolTipText("Marrón");
        jButton22.setPreferredSize(new java.awt.Dimension(25, 25));
        panelBotones.add(jButton22);

        jButton23.setToolTipText("Blanco");
        jButton23.setPreferredSize(new java.awt.Dimension(25, 25));
        panelBotones.add(jButton23);

        jButton24.setBackground(new java.awt.Color(153, 153, 153));
        jButton24.setToolTipText("Gris Claro");
        jButton24.setPreferredSize(new java.awt.Dimension(25, 25));
        panelBotones.add(jButton24);

        jButton25.setBackground(new java.awt.Color(255, 204, 204));
        jButton25.setToolTipText("Rosa");
        jButton25.setPreferredSize(new java.awt.Dimension(25, 25));
        panelBotones.add(jButton25);

        jButton26.setBackground(new java.awt.Color(255, 222, 0));
        jButton26.setToolTipText("Dorado");
        jButton26.setPreferredSize(new java.awt.Dimension(25, 25));
        panelBotones.add(jButton26);

        jButton27.setBackground(new java.awt.Color(255, 255, 157));
        jButton27.setToolTipText("Amarillo Claro");
        jButton27.setPreferredSize(new java.awt.Dimension(25, 25));
        panelBotones.add(jButton27);

        jButton28.setBackground(new java.awt.Color(51, 255, 51));
        jButton28.setToolTipText("Lima");
        jButton28.setPreferredSize(new java.awt.Dimension(25, 25));
        panelBotones.add(jButton28);

        jButton29.setBackground(new java.awt.Color(115, 251, 253));
        jButton29.setToolTipText("Azul Cielo");
        jButton29.setPreferredSize(new java.awt.Dimension(25, 25));
        panelBotones.add(jButton29);

        jButton30.setBackground(new java.awt.Color(238, 138, 248));
        jButton30.setToolTipText("Lavanda");
        jButton30.setPreferredSize(new java.awt.Dimension(25, 25));
        panelBotones.add(jButton30);

        jButton18.setBackground(new java.awt.Color(196, 164, 132));
        jButton18.setToolTipText("Marrón Claro");
        jButton18.setPreferredSize(new java.awt.Dimension(25, 25));
        panelBotones.add(jButton18);

        zonaColores.add(panelBotones);
        panelBotones.getAccessibleContext().setAccessibleDescription("");

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Colores");
        jLabel1.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel1.setMaximumSize(new java.awt.Dimension(50, 16));
        jLabel1.setMinimumSize(new java.awt.Dimension(50, 16));
        jLabel1.setPreferredSize(new java.awt.Dimension(50, 16));
        zonaColores.add(jLabel1);

        btnElegirColor.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        btnElegirColor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/colores.png"))); // NOI18N
        btnElegirColor.setToolTipText("Editar Colores");
        btnElegirColor.setFocusPainted(false);
        btnElegirColor.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnElegirColor.setIconTextGap(8);
        btnElegirColor.setName(""); // NOI18N
        btnElegirColor.setPreferredSize(new java.awt.Dimension(25, 25));
        btnElegirColor.addActionListener(this::btnElegirColorActionPerformed);
        zonaColores.add(btnElegirColor);

        panelDerecho.add(zonaColores, java.awt.BorderLayout.CENTER);

        PanelCinta.add(panelDerecho, java.awt.BorderLayout.EAST);

        getContentPane().add(PanelCinta, java.awt.BorderLayout.PAGE_START);
        PanelCinta.getAccessibleContext().setAccessibleDescription("");

        ZonaLienzo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        ZonaLienzo.setLayout(new java.awt.BorderLayout());
        ZonaLienzo.add(zonaGrosor, java.awt.BorderLayout.LINE_START);

        getContentPane().add(ZonaLienzo, java.awt.BorderLayout.CENTER);

        jLabel4.setText("jLabel2");
        jPanel1.add(jLabel4);

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_END);

        jMenu1.setText("Archivo");

        jMenuItem1.setText("Salir");
        jMenuItem1.addActionListener(this::jMenuItem1ActionPerformed);
        jMenu1.add(jMenuItem1);

        jMenuItem2.setText("Nuevo");
        jMenuItem2.addActionListener(this::jMenuItem2ActionPerformed);
        jMenu1.add(jMenuItem2);

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

        setBounds(0, 0, 906, 425);
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        System.exit(0x0);
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowClosing


    private void btnElegirColorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnElegirColorActionPerformed
         Color nuevoColor = JColorChooser.showDialog(
        this,
        "Selecciona un color",
        colorActual
    );

    if (nuevoColor != null) {
        cambiarColor(nuevoColor);
  
    }
    }//GEN-LAST:event_btnElegirColorActionPerformed

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

    private void btnBorradorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorradorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBorradorActionPerformed

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
    private javax.swing.JButton btnBorrador;
    private javax.swing.JMenuItem btnDeshacer1;
    private javax.swing.JButton btnElegirColor;
    private javax.swing.JButton btnLapiz;
    private javax.swing.JButton btnLinea;
    private javax.swing.JMenuItem btnRehacer1;
    private javax.swing.JButton btnRojo;
    private javax.swing.JButton btnTrazo;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton22;
    private javax.swing.JButton jButton23;
    private javax.swing.JButton jButton24;
    private javax.swing.JButton jButton25;
    private javax.swing.JButton jButton26;
    private javax.swing.JButton jButton27;
    private javax.swing.JButton jButton28;
    private javax.swing.JButton jButton29;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton30;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel panelBotones;
    private javax.swing.JPanel panelColorActual;
    private javax.swing.JPanel panelColorAnterior;
    private javax.swing.JPanel panelDerecho;
    private javax.swing.JPanel zonaColores;
    private javax.swing.JPanel zonaEstadoColor;
    private javax.swing.JPanel zonaFiguras;
    private javax.swing.JPanel zonaGrosor;
    private javax.swing.JPanel zonaHerramientas;
    // End of variables declaration//GEN-END:variables
   

}
