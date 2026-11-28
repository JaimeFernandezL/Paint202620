package ui;
//hola

import archivos.ServicioGuardarImagen;
import java.awt.BorderLayout;
import archivos.ServicioNuevoDocumento;
import javax.swing.*;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.ButtonGroup;
import javax.swing.JToggleButton;

public class VentanaPrincipal extends JFrame {

    //Atributos y metodos para conectar toda la interfaz con BarraEstado
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName());
    private final PanelDeDibujo panelDeDibujo = new PanelDeDibujo();
    private final ContenedorLienzo contenedorLienzo = new ContenedorLienzo(panelDeDibujo);
    private final JToggleButton btnOvalo = new JToggleButton("Óvalo");
    private final JToggleButton btnCirculo = new JToggleButton("Círculo");
    private final JToggleButton btnRombo = new JToggleButton("Rombo");
    private final JToggleButton btnPentagono = new JToggleButton("Pentagono");
    private final JToggleButton btnCorazon = new JToggleButton("Corazon");
    private final JToggleButton btnFlecha = new JToggleButton("Flecha");
    private final JToggleButton btnHexagono = new JToggleButton("Hexágono");
    private final JToggleButton btnEstrella = new JToggleButton("Estrella");

    private ServicioNuevoDocumento servicioNuevoDocumento;
    private final ButtonGroup grupoHerramientas = new ButtonGroup();
    private final PanelHerramientas herramientas = new PanelHerramientas();
    private final BarraEstado estado = new BarraEstado();

    private Color colorActual = Color.WHITE;
    private Color colorAnterior = Color.WHITE;

    private final GestorColor gestorColor = new GestorColor(panelDeDibujo);
    private JButton jButton1;
    private JButton jButton2;
    private JButton jButton3;
    private JButton jButton4;
    private JButton jButton5;

    private void cambiarColor(Color nuevoColor) {
        gestorColor.cambiarColor(nuevoColor);

        colorActual = gestorColor.getColorActual();
        colorAnterior = gestorColor.getColorAnterior();

        panelColorActual1.setBackground(colorActual);
        panelColorAnterior1.setBackground(colorAnterior);

        estado.setColorActivo(colorActual);
    }

    public VentanaPrincipal() {
        initComponents();

        // Agregamos el contenedor del lienzo al centro de la ventana principal en lugar del panel directo
        setIconImage(new javax.swing.ImageIcon(
                getClass().getResource("/imagenes/logo.png")
        ).getImage());

        servicioNuevoDocumento
                = new ServicioNuevoDocumento(panelDeDibujo);

        // Botones de la paleta de colores
        btnNegro.addActionListener(e -> cambiarColor(btnNegro.getBackground()));
        btnGris.addActionListener(e -> cambiarColor(btnGris.getBackground()));
        btnRojo.addActionListener(e -> cambiarColor(btnRojo.getBackground()));
        btnNaranja.addActionListener(e -> cambiarColor(btnNaranja.getBackground()));
        btnAmarillo.addActionListener(e -> cambiarColor(btnAmarillo.getBackground()));
        btnVerde.addActionListener(e -> cambiarColor(btnVerde.getBackground()));
        btnAzul.addActionListener(e -> cambiarColor(btnAzul.getBackground()));
        btnPurpura.addActionListener(e -> cambiarColor(btnPurpura.getBackground()));
        btnMarron.addActionListener(e -> cambiarColor(btnMarron.getBackground()));
        btnBlanco.addActionListener(e -> cambiarColor(btnBlanco.getBackground()));
        btnGrisClaro.addActionListener(e -> cambiarColor(btnGrisClaro.getBackground()));
        btnRosa.addActionListener(e -> cambiarColor(btnRosa.getBackground()));
        btnSelectorColor.addActionListener(e -> cambiarColor(btnSelectorColor.getBackground()));
        btnAmarilloClaro.addActionListener(e -> cambiarColor(btnAmarilloClaro.getBackground()));
        btnLima.addActionListener(e -> cambiarColor(btnLima.getBackground()));
        btnCeleste.addActionListener(e -> cambiarColor(btnCeleste.getBackground()));
        btnLavanda.addActionListener(e -> cambiarColor(btnLavanda.getBackground()));
        btnMarronClaro.addActionListener(e -> cambiarColor(btnMarronClaro.getBackground()));
        gestorColor.conectarCuadros(panelColorActual1, panelColorAnterior1);
        panelColorActual1.setBackground(colorActual);
        panelColorAnterior1.setBackground(colorAnterior);

        btnSelectorColores.addActionListener(e -> {
            if (gestorColor.abrirSelector(this)) {

                // Sincronizar los colores
                colorActual = gestorColor.getColorActual();
                colorAnterior = gestorColor.getColorAnterior();

                // Actualizar los dos cuadros
                panelColorActual1.setBackground(colorActual);
                panelColorAnterior1.setBackground(colorAnterior);

                // Actualizar el botón del selector
                btnSelectorColor.setBackground(colorActual);

                // Actualizar la barra de estado
                estado.setColorActivo(colorActual);
            }
        });

        // Relleno: las figuras NUEVAS se dibujan con o sin relleno
        jCheckBox1.setFocusPainted(false);
        jCheckBox1.addActionListener(e -> panelDeDibujo.setRellenarFigura(jCheckBox1.isSelected()));
        panelDeDibujo.setRellenarFigura(jCheckBox1.isSelected());   // que empiece igual que el checkbox (sin marcar)

        grupoHerramientas.add(herramientas.btnLapiz);
        grupoHerramientas.add(herramientas.btnBorrador);

        jMenuBar1.add(herramientas.btnGuardar, 3);
        jMenuBar1.add(herramientas.btnDeshacer, 4);
        jMenuBar1.add(herramientas.btnRehacer, 5);
        jMenuBar1.add(herramientas.btnLimpiar, 6);

        ZonaLienzo.setLayout(new BorderLayout());

        zonaGrosor.setPreferredSize(new Dimension(75, 100));
        zonaGrosor.add(herramientas.getPanelGrosor(), BorderLayout.CENTER);

        ZonaLienzo.add(zonaGrosor, BorderLayout.WEST);
        ZonaLienzo.add(contenedorLienzo, BorderLayout.CENTER);
        herramientas.conectar(panelDeDibujo);
        panelDeDibujo.setGrosorActual(herramientas.sliderGrosor.getValue());

        PanelFiguras figuras = new PanelFiguras(panelDeDibujo, grupoHerramientas);
        zonaFiguras.setLayout(new java.awt.BorderLayout());
        zonaFiguras.add(figuras, java.awt.BorderLayout.CENTER);

        servicioNuevoDocumento
                = new ServicioNuevoDocumento(panelDeDibujo);

        btnLapiz.addActionListener(e -> herramientas.activarLapiz());
        btnBorra.addActionListener(e -> herramientas.activarBorrador());

        btnOvalo.addActionListener(e -> panelDeDibujo.setTipoActual(PanelDeDibujo.TipoFigura.OVALO));
        btnCirculo.addActionListener(e -> panelDeDibujo.setTipoActual(PanelDeDibujo.TipoFigura.CIRCULO));
        btnRombo.addActionListener(e -> panelDeDibujo.setTipoActual(PanelDeDibujo.TipoFigura.ROMBO));
        btnPentagono.addActionListener(e -> panelDeDibujo.setTipoActual(PanelDeDibujo.TipoFigura.PENTAGONO));
        btnCorazon.addActionListener(e -> panelDeDibujo.setTipoActual(PanelDeDibujo.TipoFigura.CORAZON));
        btnFlecha.addActionListener(e -> panelDeDibujo.setTipoActual(PanelDeDibujo.TipoFigura.FLECHA));
        btnHexagono.addActionListener(e -> panelDeDibujo.setTipoActual(PanelDeDibujo.TipoFigura.HEXAGONO));
        btnEstrella.addActionListener(e -> panelDeDibujo.setTipoActual(PanelDeDibujo.TipoFigura.ESTRELLA));

        herramientas.btnLapiz.setSelected(true);

        // Establecemos un tamaño base de respaldo en caso de que el usuario restaure la ventana
        setMinimumSize(new Dimension(900, 600));
        setSize(1000, 700);

        herramientas.instalarMenuArchivo(jMenu1, jMenuItem2, jMenuItem1,
                panelDeDibujo, contenedorLienzo, this);

        panelDeDibujo.setBackground(java.awt.Color.WHITE);

        getContentPane().remove(jPanel1);
        getContentPane().add(estado, BorderLayout.PAGE_END);

        // Sincronizar herramienta activa con BarraEstado (Lápiz, Borrador y Figuras)
        estado.setHerramientaActiva("Lápiz");
        btnLapiz.addActionListener(e -> estado.setHerramientaActiva("Lápiz"));
        btnBorra.addActionListener(e -> estado.setHerramientaActiva("Borrador"));
        estado.setHerramientaActiva("Lápiz");

        btnLapiz.addActionListener(e -> estado.setHerramientaActiva("Lápiz"));
        btnBorra.addActionListener(e -> estado.setHerramientaActiva("Borrador"));
        herramientas.btnLapiz.addActionListener(e -> estado.setHerramientaActiva("Lápiz"));
        herramientas.btnBorrador.addActionListener(e -> estado.setHerramientaActiva("Borrador"));

        for (java.awt.Component comp : figuras.getComponents()) {
            if (comp instanceof JToggleButton) {
                JToggleButton btn = (JToggleButton) comp;
                btn.addActionListener(e -> estado.setHerramientaActiva(btn.getText()));
            }
        }

        setLocationRelativeTo(null);
        // Maximizamos la ventana al tamaño completo de la pantalla del dispositivo al iniciar
        setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu4 = new javax.swing.JMenu();
        jMenu5 = new javax.swing.JMenu();
        PanelCinta = new javax.swing.JPanel();
        zonaHerramientas = new javax.swing.JPanel();
        btnBorra = new javax.swing.JButton();
        btnLapiz = new javax.swing.JButton();
        jSeparador1 = new javax.swing.JSeparator();
        PanelCentral = new javax.swing.JPanel();
        zonaFiguras = new javax.swing.JPanel();
        jSeparador2 = new javax.swing.JSeparator();
        zonaEstadoFiguras = new javax.swing.JPanel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jSeparador3 = new javax.swing.JSeparator();
        jSeparador4 = new javax.swing.JSeparator();
        panelDerecho = new javax.swing.JPanel();
        zonaEstadoColor = new javax.swing.JPanel();
        panelColorActual = new javax.swing.JPanel();
        panelColorAnterior = new javax.swing.JPanel();
        zonaColores = new javax.swing.JPanel();
        panelBotones = new javax.swing.JPanel();
        btnNegro = new javax.swing.JButton();
        btnGris = new javax.swing.JButton();
        btnRojo = new javax.swing.JButton();
        btnNaranja = new javax.swing.JButton();
        btnAmarillo = new javax.swing.JButton();
        btnVerde = new javax.swing.JButton();
        btnAzul = new javax.swing.JButton();
        btnPurpura = new javax.swing.JButton();
        btnMarron = new javax.swing.JButton();
        btnBlanco = new javax.swing.JButton();
        btnGrisClaro = new javax.swing.JButton();
        btnRosa = new javax.swing.JButton();
        btnDorado1 = new javax.swing.JButton();
        btnAmarilloClaro = new javax.swing.JButton();
        btnLima = new javax.swing.JButton();
        btnCeleste = new javax.swing.JButton();
        btnLavanda = new javax.swing.JButton();
        btnMarronClaro = new javax.swing.JButton();
        jButton21 = new javax.swing.JButton();
        jSeparador5 = new javax.swing.JSeparator();
        jPanel2 = new javax.swing.JPanel();
        btnSelectorColores = new javax.swing.JButton();
        btnSelectorColor = new javax.swing.JButton();
        zonaEstadoColor1 = new javax.swing.JPanel();
        panelColorActual1 = new javax.swing.JPanel();
        panelColorAnterior1 = new javax.swing.JPanel();
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

        jMenu4.setText("File");
        jMenuBar2.add(jMenu4);

        jMenu5.setText("Edit");
        jMenuBar2.add(jMenu5);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Paint");
        setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        setMinimumSize(new java.awt.Dimension(915, 420));
        setPreferredSize(new java.awt.Dimension(910, 420));
        setSize(new java.awt.Dimension(0, 0));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        PanelCinta.setBackground(new java.awt.Color(255, 255, 255));
        PanelCinta.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        PanelCinta.setLayout(new java.awt.BorderLayout());

        zonaHerramientas.setBackground(new java.awt.Color(255, 255, 255));
        zonaHerramientas.setPreferredSize(new java.awt.Dimension(250, 100));

        btnBorra.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/borrador.png"))); // NOI18N
        btnBorra.setToolTipText("Borrador");
        btnBorra.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        btnLapiz.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/lapiz.png"))); // NOI18N
        btnLapiz.setToolTipText("Lapiz");
        btnLapiz.setAutoscrolls(true);
        btnLapiz.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnLapiz.addActionListener(this::btnLapizActionPerformed);

        jSeparador1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparador1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout zonaHerramientasLayout = new javax.swing.GroupLayout(zonaHerramientas);
        zonaHerramientas.setLayout(zonaHerramientasLayout);
        zonaHerramientasLayout.setHorizontalGroup(
            zonaHerramientasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(zonaHerramientasLayout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(btnLapiz, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 66, Short.MAX_VALUE)
                .addComponent(btnBorra, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48)
                .addComponent(jSeparador1, javax.swing.GroupLayout.PREFERRED_SIZE, 4, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        zonaHerramientasLayout.setVerticalGroup(
            zonaHerramientasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparador1)
            .addGroup(zonaHerramientasLayout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(zonaHerramientasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnLapiz, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                    .addComponent(btnBorra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(37, Short.MAX_VALUE))
        );

        PanelCinta.add(zonaHerramientas, java.awt.BorderLayout.WEST);

        PanelCentral.setLayout(new java.awt.BorderLayout());

        zonaFiguras.setBackground(new java.awt.Color(255, 255, 255));
        zonaFiguras.setAutoscrolls(true);
        zonaFiguras.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        zonaFiguras.setMaximumSize(new java.awt.Dimension(257, 256));
        zonaFiguras.setMinimumSize(new java.awt.Dimension(2, 2));
        zonaFiguras.setLayout(new java.awt.BorderLayout());
        PanelCentral.add(zonaFiguras, java.awt.BorderLayout.CENTER);

        jSeparador2.setOrientation(javax.swing.SwingConstants.VERTICAL);
        PanelCentral.add(jSeparador2, java.awt.BorderLayout.LINE_END);

        zonaEstadoFiguras.setBackground(new java.awt.Color(255, 255, 255));

        jCheckBox1.setText("Relleno");

        jSeparador3.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparador3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jSeparador4.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparador4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout zonaEstadoFigurasLayout = new javax.swing.GroupLayout(zonaEstadoFiguras);
        zonaEstadoFiguras.setLayout(zonaEstadoFigurasLayout);
        zonaEstadoFigurasLayout.setHorizontalGroup(
            zonaEstadoFigurasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, zonaEstadoFigurasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparador3, javax.swing.GroupLayout.PREFERRED_SIZE, 4, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addComponent(jCheckBox1)
                .addGap(7, 7, 7)
                .addComponent(jSeparador4, javax.swing.GroupLayout.PREFERRED_SIZE, 4, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        zonaEstadoFigurasLayout.setVerticalGroup(
            zonaEstadoFigurasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(zonaEstadoFigurasLayout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jCheckBox1)
                .addContainerGap(53, Short.MAX_VALUE))
            .addComponent(jSeparador4, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jSeparador3)
        );

        PanelCentral.add(zonaEstadoFiguras, java.awt.BorderLayout.LINE_END);

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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

        zonaColores.setBackground(new java.awt.Color(255, 255, 255));
        zonaColores.setPreferredSize(new java.awt.Dimension(290, 100));

        panelBotones.setBackground(new java.awt.Color(255, 255, 255));
        panelBotones.setName("PanelBotones"); // NOI18N
        panelBotones.setPreferredSize(new java.awt.Dimension(280, 63));
        panelBotones.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        btnNegro.setBackground(new java.awt.Color(0, 0, 0));
        btnNegro.setToolTipText("Negro");
        btnNegro.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnNegro.setPreferredSize(new java.awt.Dimension(25, 25));
        panelBotones.add(btnNegro);

        btnGris.setBackground(new java.awt.Color(102, 102, 102));
        btnGris.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        btnGris.setToolTipText("Gris");
        btnGris.setFocusPainted(false);
        btnGris.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnGris.setIconTextGap(8);
        btnGris.setName(""); // NOI18N
        btnGris.setPreferredSize(new java.awt.Dimension(25, 25));
        panelBotones.add(btnGris);

        btnRojo.setBackground(new java.awt.Color(204, 0, 0));
        btnRojo.setToolTipText("Rojo");
        btnRojo.setPreferredSize(new java.awt.Dimension(25, 25));
        panelBotones.add(btnRojo);

        btnNaranja.setBackground(new java.awt.Color(255, 153, 0));
        btnNaranja.setToolTipText("Naranja");
        btnNaranja.setPreferredSize(new java.awt.Dimension(25, 25));
        panelBotones.add(btnNaranja);

        btnAmarillo.setBackground(new java.awt.Color(255, 255, 0));
        btnAmarillo.setToolTipText("Amarillo");
        btnAmarillo.setPreferredSize(new java.awt.Dimension(25, 25));
        panelBotones.add(btnAmarillo);

        btnVerde.setBackground(new java.awt.Color(0, 153, 0));
        btnVerde.setToolTipText("Verde");
        btnVerde.setPreferredSize(new java.awt.Dimension(25, 25));
        panelBotones.add(btnVerde);

        btnAzul.setBackground(new java.awt.Color(51, 51, 255));
        btnAzul.setToolTipText("Azul");
        btnAzul.setPreferredSize(new java.awt.Dimension(25, 25));
        panelBotones.add(btnAzul);

        btnPurpura.setBackground(new java.awt.Color(204, 0, 204));
        btnPurpura.setToolTipText("Púrpura");
        btnPurpura.setPreferredSize(new java.awt.Dimension(25, 25));
        panelBotones.add(btnPurpura);

        btnMarron.setBackground(new java.awt.Color(153, 102, 0));
        btnMarron.setToolTipText("Marrón");
        btnMarron.setPreferredSize(new java.awt.Dimension(25, 25));
        panelBotones.add(btnMarron);

        btnBlanco.setBackground(null);
        btnBlanco.setToolTipText("Blanco");
        btnBlanco.setPreferredSize(new java.awt.Dimension(25, 25));
        panelBotones.add(btnBlanco);

        btnGrisClaro.setBackground(new java.awt.Color(153, 153, 153));
        btnGrisClaro.setToolTipText("Gris Claro");
        btnGrisClaro.setPreferredSize(new java.awt.Dimension(25, 25));
        panelBotones.add(btnGrisClaro);

        btnRosa.setBackground(new java.awt.Color(255, 204, 204));
        btnRosa.setToolTipText("Rosa");
        btnRosa.setPreferredSize(new java.awt.Dimension(25, 25));
        panelBotones.add(btnRosa);

        btnDorado1.setBackground(new java.awt.Color(255, 222, 0));
        btnDorado1.setToolTipText("Dorado");
        btnDorado1.setPreferredSize(new java.awt.Dimension(25, 25));
        panelBotones.add(btnDorado1);

        btnAmarilloClaro.setBackground(new java.awt.Color(255, 255, 157));
        btnAmarilloClaro.setToolTipText("Amarillo Claro");
        btnAmarilloClaro.setPreferredSize(new java.awt.Dimension(25, 25));
        panelBotones.add(btnAmarilloClaro);

        btnLima.setBackground(new java.awt.Color(51, 255, 51));
        btnLima.setToolTipText("Lima");
        btnLima.setPreferredSize(new java.awt.Dimension(25, 25));
        panelBotones.add(btnLima);

        btnCeleste.setBackground(new java.awt.Color(115, 251, 253));
        btnCeleste.setToolTipText("Celeste");
        btnCeleste.setPreferredSize(new java.awt.Dimension(25, 25));
        panelBotones.add(btnCeleste);

        btnLavanda.setBackground(new java.awt.Color(238, 138, 248));
        btnLavanda.setToolTipText("Lavanda");
        btnLavanda.setPreferredSize(new java.awt.Dimension(25, 25));
        panelBotones.add(btnLavanda);

        btnMarronClaro.setBackground(new java.awt.Color(196, 164, 132));
        btnMarronClaro.setToolTipText("Marrón Claro");
        btnMarronClaro.setPreferredSize(new java.awt.Dimension(25, 25));
        panelBotones.add(btnMarronClaro);

        jButton21.setToolTipText("Marrón Claro");
        jButton21.setPreferredSize(new java.awt.Dimension(20, 24));

        jSeparador5.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparador5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel2.setBackground(null);
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setForeground(null);
        jPanel2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        btnSelectorColores.setBackground(null);
        btnSelectorColores.setForeground(null);
        btnSelectorColores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/selector-de-color_1.png"))); // NOI18N
        btnSelectorColores.setToolTipText("Selector de colores");
        btnSelectorColores.setBorder(null);
        btnSelectorColores.setBorderPainted(false);
        btnSelectorColores.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnSelectorColores.setFocusTraversalPolicyProvider(true);
        btnSelectorColores.setMaximumSize(new java.awt.Dimension(25, 25));
        btnSelectorColores.setMinimumSize(new java.awt.Dimension(25, 25));
        btnSelectorColores.setPreferredSize(new java.awt.Dimension(25, 25));

        btnSelectorColor.setToolTipText("Color Seleccionado");
        btnSelectorColor.setPreferredSize(new java.awt.Dimension(25, 25));
        btnSelectorColor.addActionListener(this::btnSelectorColorActionPerformed);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnSelectorColores, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addComponent(btnSelectorColor, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnSelectorColor, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
            .addComponent(btnSelectorColores, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout zonaColoresLayout = new javax.swing.GroupLayout(zonaColores);
        zonaColores.setLayout(zonaColoresLayout);
        zonaColoresLayout.setHorizontalGroup(
            zonaColoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(zonaColoresLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparador5, javax.swing.GroupLayout.PREFERRED_SIZE, 4, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addGroup(zonaColoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(zonaColoresLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(panelBotones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15))
        );
        zonaColoresLayout.setVerticalGroup(
            zonaColoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(zonaColoresLayout.createSequentialGroup()
                .addComponent(panelBotones, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(jSeparador5)
        );

        panelBotones.getAccessibleContext().setAccessibleDescription("");

        panelDerecho.add(zonaColores, java.awt.BorderLayout.CENTER);

        zonaEstadoColor1.setBackground(new java.awt.Color(255, 255, 255));
        zonaEstadoColor1.setMinimumSize(new java.awt.Dimension(20, 22));
        zonaEstadoColor1.setPreferredSize(new java.awt.Dimension(100, 90));

        panelColorActual1.setBackground(new java.awt.Color(0, 0, 0));
        panelColorActual1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panelColorActual1.setPreferredSize(new java.awt.Dimension(35, 35));

        panelColorAnterior1.setBackground(null);
        panelColorAnterior1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panelColorAnterior1.setPreferredSize(new java.awt.Dimension(35, 35));

        javax.swing.GroupLayout zonaEstadoColor1Layout = new javax.swing.GroupLayout(zonaEstadoColor1);
        zonaEstadoColor1.setLayout(zonaEstadoColor1Layout);
        zonaEstadoColor1Layout.setHorizontalGroup(
            zonaEstadoColor1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(zonaEstadoColor1Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(zonaEstadoColor1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelColorActual1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelColorAnterior1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(31, Short.MAX_VALUE))
        );
        zonaEstadoColor1Layout.setVerticalGroup(
            zonaEstadoColor1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(zonaEstadoColor1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelColorActual1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelColorAnterior1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        panelDerecho.add(zonaEstadoColor1, java.awt.BorderLayout.LINE_START);

        PanelCinta.add(panelDerecho, java.awt.BorderLayout.EAST);

        getContentPane().add(PanelCinta, java.awt.BorderLayout.PAGE_START);
        PanelCinta.getAccessibleContext().setAccessibleDescription("");

        ZonaLienzo.setLayout(new java.awt.BorderLayout());
        ZonaLienzo.add(zonaGrosor, java.awt.BorderLayout.LINE_START);

        getContentPane().add(ZonaLienzo, java.awt.BorderLayout.CENTER);

        jLabel4.setText("jLabel2");
        jPanel1.add(jLabel4);

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_END);

        jMenuBar1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

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

        setBounds(0, 0, 887, 393);
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        System.exit(0x0);
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        int respuesta = JOptionPane.showConfirmDialog(
                this,
                "¿Deseas guardar antes de cerrar?",
                "Cerrar Paint",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.WARNING_MESSAGE
        );

        if (respuesta == JOptionPane.CANCEL_OPTION
                || respuesta == JOptionPane.CLOSED_OPTION) {
            setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            return;
        }

        if (respuesta == JOptionPane.YES_OPTION) {
            boolean guardado = new ServicioGuardarImagen()
                    .guardarImagen(panelDeDibujo, this);

            if (!guardado) {
                setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                return;
            }
        }

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

    private void btnSelectorColorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSelectorColorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSelectorColorActionPerformed

    private void btnLapizActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLapizActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLapizActionPerformed

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
    private javax.swing.JButton btnAmarillo;
    private javax.swing.JButton btnAmarilloClaro;
    private javax.swing.JButton btnAzul;
    private javax.swing.JButton btnBlanco;
    private javax.swing.JButton btnBorra;
    private javax.swing.JButton btnCeleste;
    private javax.swing.JMenuItem btnDeshacer1;
    private javax.swing.JButton btnDorado1;
    private javax.swing.JButton btnGris;
    private javax.swing.JButton btnGrisClaro;
    private javax.swing.JButton btnLapiz;
    private javax.swing.JButton btnLavanda;
    private javax.swing.JButton btnLima;
    private javax.swing.JButton btnMarron;
    private javax.swing.JButton btnMarronClaro;
    private javax.swing.JButton btnNaranja;
    private javax.swing.JButton btnNegro;
    private javax.swing.JButton btnPurpura;
    private javax.swing.JMenuItem btnRehacer1;
    private javax.swing.JButton btnRojo;
    private javax.swing.JButton btnRosa;
    private javax.swing.JButton btnSelectorColor;
    private javax.swing.JButton btnSelectorColores;
    private javax.swing.JButton btnVerde;
    private javax.swing.JButton jButton21;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparador1;
    private javax.swing.JSeparator jSeparador2;
    private javax.swing.JSeparator jSeparador3;
    private javax.swing.JSeparator jSeparador4;
    private javax.swing.JSeparator jSeparador5;
    private javax.swing.JPanel panelBotones;
    private javax.swing.JPanel panelColorActual;
    private javax.swing.JPanel panelColorActual1;
    private javax.swing.JPanel panelColorAnterior;
    private javax.swing.JPanel panelColorAnterior1;
    private javax.swing.JPanel panelDerecho;
    private javax.swing.JPanel zonaColores;
    private javax.swing.JPanel zonaEstadoColor;
    private javax.swing.JPanel zonaEstadoColor1;
    private javax.swing.JPanel zonaEstadoFiguras;
    private javax.swing.JPanel zonaFiguras;
    private javax.swing.JPanel zonaGrosor;
    private javax.swing.JPanel zonaHerramientas;
    // End of variables declaration//GEN-END:variables

}
