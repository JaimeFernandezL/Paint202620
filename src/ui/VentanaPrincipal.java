
package ui;
//hola


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
        estado.setColorActivo(nuevoColor);
    }
    
    public VentanaPrincipal() {
        initComponents();

        // Agregamos el contenedor del lienzo al centro de la ventana principal en lugar del panel directo
        servicioNuevoDocumento
                = new ServicioNuevoDocumento(panelDeDibujo);

        jButton8.setBackground(Color.WHITE);

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
        btnDorado.addActionListener(e -> cambiarColor(btnDorado.getBackground()));
        btnAmarilloClaro.addActionListener(e -> cambiarColor(btnAmarilloClaro.getBackground()));
        btnLima.addActionListener(e -> cambiarColor(btnLima.getBackground()));
        btnCeleste.addActionListener(e -> cambiarColor(btnCeleste.getBackground()));
        btnLavanda.addActionListener(e -> cambiarColor(btnLavanda.getBackground()));
        btnMarronClaro.addActionListener(e -> cambiarColor(btnMarronClaro.getBackground()));
        btnSelectorColores.addActionListener(e -> {
            if (gestorColor.abrirSelector(this)) {
                // Si eligen un color nuevo, el botón 18 (el que marcaste) se pinta de ese color
                btnSelectorColores.setBackground(gestorColor.getColorActual());
                //Actualiza el color elegido en BarraEstado
                estado.setColorActivo(gestorColor.getColorActual());
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

        PanelCinta = new javax.swing.JPanel();
        zonaHerramientas = new javax.swing.JPanel();
        btnBorra = new javax.swing.JButton();
        btnLapiz = new javax.swing.JButton();
        jSeparador1 = new javax.swing.JSeparator();
        PanelCentral = new javax.swing.JPanel();
        zonaFiguras = new javax.swing.JPanel();
        btnLinea = new javax.swing.JButton();
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
        jSeparador2 = new javax.swing.JSeparator();
        zonaEstadoFiguras = new javax.swing.JPanel();
        jCheckBox1 = new javax.swing.JCheckBox();
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
        btnDorado = new javax.swing.JButton();
        btnAmarilloClaro = new javax.swing.JButton();
        btnLima = new javax.swing.JButton();
        btnCeleste = new javax.swing.JButton();
        btnLavanda = new javax.swing.JButton();
        btnMarronClaro = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btnSelectorColores = new javax.swing.JButton();
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

        zonaHerramientas.setPreferredSize(new java.awt.Dimension(250, 100));

        btnBorra.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/borrador.png"))); // NOI18N
        btnBorra.setToolTipText("Borrador");

        btnLapiz.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/lapiz.png"))); // NOI18N
        btnLapiz.setToolTipText("Lapiz");
        btnLapiz.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnLapiz.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnLapiz.addActionListener(this::btnLapizActionPerformed);

        jSeparador1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        javax.swing.GroupLayout zonaHerramientasLayout = new javax.swing.GroupLayout(zonaHerramientas);
        zonaHerramientas.setLayout(zonaHerramientasLayout);
        zonaHerramientasLayout.setHorizontalGroup(
            zonaHerramientasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(zonaHerramientasLayout.createSequentialGroup()
                .addContainerGap(52, Short.MAX_VALUE)
                .addComponent(btnLapiz, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                .addGap(27, 27, 27)
                .addComponent(btnBorra, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(90, 90, 90)
                .addComponent(jSeparador1, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        zonaHerramientasLayout.setVerticalGroup(
            zonaHerramientasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparador1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(zonaHerramientasLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(zonaHerramientasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnLapiz, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                    .addComponent(btnBorra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        PanelCinta.add(zonaHerramientas, java.awt.BorderLayout.WEST);

        PanelCentral.setLayout(new java.awt.BorderLayout());

        zonaFiguras.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        zonaFiguras.setMaximumSize(new java.awt.Dimension(257, 256));
        zonaFiguras.setMinimumSize(new java.awt.Dimension(2, 2));

        btnLinea.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/linea-diagonal_1.png"))); // NOI18N
        btnLinea.setToolTipText("Linea");

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
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                                .addGroup(zonaFigurasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(zonaFigurasLayout.createSequentialGroup()
                                        .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, zonaFigurasLayout.createSequentialGroup()
                                        .addComponent(jButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(2, 2, 2)))
                                .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))))
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
                    .addComponent(btnLinea, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(zonaFigurasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addContainerGap())
        );

        PanelCentral.add(zonaFiguras, java.awt.BorderLayout.LINE_START);

        jSeparador2.setOrientation(javax.swing.SwingConstants.VERTICAL);
        PanelCentral.add(jSeparador2, java.awt.BorderLayout.LINE_END);

        jCheckBox1.setText("Relleno");
        zonaEstadoFiguras.add(jCheckBox1);

        PanelCentral.add(zonaEstadoFiguras, java.awt.BorderLayout.CENTER);

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

        zonaColores.setPreferredSize(new java.awt.Dimension(290, 100));

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

        btnDorado.setBackground(new java.awt.Color(255, 222, 0));
        btnDorado.setToolTipText("Dorado");
        btnDorado.setPreferredSize(new java.awt.Dimension(25, 25));
        panelBotones.add(btnDorado);

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

        btnSelectorColores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/colores.png"))); // NOI18N
        btnSelectorColores.setToolTipText("Selector de colores");
        btnSelectorColores.setBorder(null);
        btnSelectorColores.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnSelectorColores.setFocusTraversalPolicyProvider(true);
        btnSelectorColores.setMaximumSize(new java.awt.Dimension(25, 25));
        btnSelectorColores.setMinimumSize(new java.awt.Dimension(25, 25));
        btnSelectorColores.setPreferredSize(new java.awt.Dimension(25, 25));
        zonaColores.add(btnSelectorColores);

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
    private javax.swing.JButton btnDorado;
    private javax.swing.JButton btnGris;
    private javax.swing.JButton btnGrisClaro;
    private javax.swing.JButton btnLapiz;
    private javax.swing.JButton btnLavanda;
    private javax.swing.JButton btnLima;
    private javax.swing.JButton btnLinea;
    private javax.swing.JButton btnMarron;
    private javax.swing.JButton btnMarronClaro;
    private javax.swing.JButton btnNaranja;
    private javax.swing.JButton btnNegro;
    private javax.swing.JButton btnPurpura;
    private javax.swing.JMenuItem btnRehacer1;
    private javax.swing.JButton btnRojo;
    private javax.swing.JButton btnRosa;
    private javax.swing.JButton btnSelectorColores;
    private javax.swing.JButton btnVerde;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparador1;
    private javax.swing.JSeparator jSeparador2;
    private javax.swing.JPanel panelBotones;
    private javax.swing.JPanel panelColorActual;
    private javax.swing.JPanel panelColorAnterior;
    private javax.swing.JPanel panelDerecho;
    private javax.swing.JPanel zonaColores;
    private javax.swing.JPanel zonaEstadoColor;
    private javax.swing.JPanel zonaEstadoFiguras;
    private javax.swing.JPanel zonaFiguras;
    private javax.swing.JPanel zonaGrosor;
    private javax.swing.JPanel zonaHerramientas;
    // End of variables declaration//GEN-END:variables
   

}
