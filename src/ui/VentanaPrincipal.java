
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
        jButton1.addActionListener(e -> cambiarColor(jButton1.getBackground()));
        jButton2.addActionListener(e -> cambiarColor(jButton2.getBackground()));
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
        jButton21.addActionListener(e -> cambiarColor(jButton21.getBackground()));
        jButton19.addActionListener(e -> {
            if (gestorColor.abrirSelector(this)) {
                // Si eligen un color nuevo, el botón 18 (el que marcaste) se pinta de ese color
                jButton21.setBackground(gestorColor.getColorActual());
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
        jPanel5 = new javax.swing.JPanel();
        PanelCentral = new javax.swing.JPanel();
        zonaFiguras = new javax.swing.JPanel();
        zonaEstadoFiguras = new javax.swing.JPanel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        panelDerecho = new javax.swing.JPanel();
        zonaColores = new javax.swing.JPanel();
        panelBotones = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();
        jButton18 = new javax.swing.JButton();
        jButton19 = new javax.swing.JButton();
        jButton21 = new javax.swing.JButton();
        ZonaLienzo = new javax.swing.JPanel();
        zonaGrosor = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        barraEstado1 = new ui.BarraEstado();
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
        setTitle("Sin Nombre - Paint");
        setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        setMinimumSize(new java.awt.Dimension(800, 600));
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

        btnBorra.setText("jButton2");

        btnLapiz.setText("jButton1");
        btnLapiz.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnLapiz.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnLapiz.addActionListener(this::btnLapizActionPerformed);

        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout zonaHerramientasLayout = new javax.swing.GroupLayout(zonaHerramientas);
        zonaHerramientas.setLayout(zonaHerramientasLayout);
        zonaHerramientasLayout.setHorizontalGroup(
            zonaHerramientasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(zonaHerramientasLayout.createSequentialGroup()
                .addContainerGap(38, Short.MAX_VALUE)
                .addComponent(btnLapiz, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnBorra, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        zonaHerramientasLayout.setVerticalGroup(
            zonaHerramientasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(zonaHerramientasLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(zonaHerramientasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBorra)
                    .addComponent(btnLapiz))
                .addContainerGap(52, Short.MAX_VALUE))
            .addGroup(zonaHerramientasLayout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        PanelCinta.add(zonaHerramientas, java.awt.BorderLayout.WEST);

        PanelCentral.setLayout(new java.awt.BorderLayout());

        zonaFiguras.setBackground(new java.awt.Color(255, 255, 255));
        PanelCentral.add(zonaFiguras, java.awt.BorderLayout.CENTER);

        zonaEstadoFiguras.setBackground(new java.awt.Color(255, 255, 255));
        zonaEstadoFiguras.setPreferredSize(new java.awt.Dimension(100, 90));

        jCheckBox1.setText("Relleno");

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout zonaEstadoFigurasLayout = new javax.swing.GroupLayout(zonaEstadoFiguras);
        zonaEstadoFiguras.setLayout(zonaEstadoFigurasLayout);
        zonaEstadoFigurasLayout.setHorizontalGroup(
            zonaEstadoFigurasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(zonaEstadoFigurasLayout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBox1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        zonaEstadoFigurasLayout.setVerticalGroup(
            zonaEstadoFigurasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(zonaEstadoFigurasLayout.createSequentialGroup()
                .addContainerGap(31, Short.MAX_VALUE)
                .addComponent(jCheckBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, zonaEstadoFigurasLayout.createSequentialGroup()
                .addGroup(zonaEstadoFigurasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        PanelCentral.add(zonaEstadoFiguras, java.awt.BorderLayout.LINE_END);

        PanelCinta.add(PanelCentral, java.awt.BorderLayout.CENTER);

        panelDerecho.setLayout(new java.awt.BorderLayout());

        zonaColores.setBackground(new java.awt.Color(255, 255, 255));
        zonaColores.setPreferredSize(new java.awt.Dimension(290, 100));

        panelBotones.setBackground(new java.awt.Color(255, 255, 255));
        panelBotones.setName("PanelBotones"); // NOI18N
        panelBotones.setPreferredSize(new java.awt.Dimension(280, 63));
        panelBotones.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jButton1.setBackground(new java.awt.Color(0, 0, 0));
        jButton1.setToolTipText("Negro");
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setPreferredSize(new java.awt.Dimension(25, 25));
        panelBotones.add(jButton1);

        jButton11.setBackground(new java.awt.Color(102, 102, 102));
        jButton11.setToolTipText("Gris");
        jButton11.setPreferredSize(new java.awt.Dimension(25, 25));
        panelBotones.add(jButton11);

        jButton2.setBackground(new java.awt.Color(204, 0, 0));
        jButton2.setToolTipText("Rojo");
        jButton2.setPreferredSize(new java.awt.Dimension(25, 25));
        panelBotones.add(jButton2);

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

        jButton6.setBackground(new java.awt.Color(51, 51, 255));
        jButton6.setToolTipText("Azul");
        jButton6.setPreferredSize(new java.awt.Dimension(25, 25));
        panelBotones.add(jButton6);

        jButton7.setBackground(new java.awt.Color(204, 0, 204));
        jButton7.setToolTipText("Púrpura");
        jButton7.setPreferredSize(new java.awt.Dimension(25, 25));
        panelBotones.add(jButton7);

        jButton9.setBackground(new java.awt.Color(153, 102, 0));
        jButton9.setToolTipText("Marrón");
        jButton9.setPreferredSize(new java.awt.Dimension(25, 25));
        panelBotones.add(jButton9);

        jButton8.setToolTipText("Blanco");
        jButton8.setPreferredSize(new java.awt.Dimension(25, 25));
        panelBotones.add(jButton8);

        jButton12.setBackground(new java.awt.Color(153, 153, 153));
        jButton12.setToolTipText("Gris Claro");
        jButton12.setPreferredSize(new java.awt.Dimension(25, 25));
        panelBotones.add(jButton12);

        jButton10.setBackground(new java.awt.Color(255, 204, 204));
        jButton10.setToolTipText("Rosa");
        jButton10.setPreferredSize(new java.awt.Dimension(25, 25));
        panelBotones.add(jButton10);

        jButton13.setBackground(new java.awt.Color(255, 222, 0));
        jButton13.setToolTipText("Dorado");
        jButton13.setPreferredSize(new java.awt.Dimension(25, 25));
        panelBotones.add(jButton13);

        jButton14.setBackground(new java.awt.Color(255, 255, 157));
        jButton14.setToolTipText("Amarillo Claro");
        jButton14.setPreferredSize(new java.awt.Dimension(25, 25));
        panelBotones.add(jButton14);

        jButton15.setBackground(new java.awt.Color(51, 255, 51));
        jButton15.setToolTipText("Lima");
        jButton15.setPreferredSize(new java.awt.Dimension(25, 25));
        panelBotones.add(jButton15);

        jButton16.setBackground(new java.awt.Color(115, 251, 253));
        jButton16.setToolTipText("Azul Cielo");
        jButton16.setPreferredSize(new java.awt.Dimension(25, 25));
        panelBotones.add(jButton16);

        jButton17.setBackground(new java.awt.Color(238, 138, 248));
        jButton17.setToolTipText("Lavanda");
        jButton17.setPreferredSize(new java.awt.Dimension(25, 25));
        panelBotones.add(jButton17);

        jButton18.setBackground(new java.awt.Color(196, 164, 132));
        jButton18.setToolTipText("Marrón Claro");
        jButton18.setPreferredSize(new java.awt.Dimension(25, 25));
        panelBotones.add(jButton18);

        jButton19.setText("Elegir Color");

        jButton21.setToolTipText("Marrón Claro");
        jButton21.setPreferredSize(new java.awt.Dimension(20, 24));

        javax.swing.GroupLayout zonaColoresLayout = new javax.swing.GroupLayout(zonaColores);
        zonaColores.setLayout(zonaColoresLayout);
        zonaColoresLayout.setHorizontalGroup(
            zonaColoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(zonaColoresLayout.createSequentialGroup()
                .addGroup(zonaColoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(zonaColoresLayout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jButton19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton21, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(zonaColoresLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(panelBotones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        zonaColoresLayout.setVerticalGroup(
            zonaColoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(zonaColoresLayout.createSequentialGroup()
                .addComponent(panelBotones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(zonaColoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton21, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jButton19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        panelBotones.getAccessibleContext().setAccessibleDescription("");

        panelDerecho.add(zonaColores, java.awt.BorderLayout.LINE_END);

        PanelCinta.add(panelDerecho, java.awt.BorderLayout.EAST);

        getContentPane().add(PanelCinta, java.awt.BorderLayout.PAGE_START);
        PanelCinta.getAccessibleContext().setAccessibleDescription("");

        ZonaLienzo.setLayout(new java.awt.BorderLayout());
        ZonaLienzo.add(zonaGrosor, java.awt.BorderLayout.LINE_START);

        getContentPane().add(ZonaLienzo, java.awt.BorderLayout.CENTER);

        barraEstado1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.add(barraEstado1);

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

        setBounds(0, 0, 892, 428);
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
    private ui.BarraEstado barraEstado1;
    private javax.swing.JButton btnBorra;
    private javax.swing.JMenuItem btnDeshacer1;
    private javax.swing.JButton btnLapiz;
    private javax.swing.JMenuItem btnRehacer1;
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
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JCheckBox jCheckBox1;
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
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel panelBotones;
    private javax.swing.JPanel panelDerecho;
    private javax.swing.JPanel zonaColores;
    private javax.swing.JPanel zonaEstadoFiguras;
    private javax.swing.JPanel zonaFiguras;
    private javax.swing.JPanel zonaGrosor;
    private javax.swing.JPanel zonaHerramientas;
    // End of variables declaration//GEN-END:variables

}
