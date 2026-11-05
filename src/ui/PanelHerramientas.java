package ui;

import javax.swing.SwingUtilities;
import archivos.ServicioAbrirImagen;
import archivos.ServicioGuardarImagen;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Arc2D;
import java.awt.geom.Path2D;
import java.awt.geom.RoundRectangle2D;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JSlider;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

/**
 * Grupo "Herramientas" al estilo del Paint de Windows: iconos arriba y la
 * etiqueta del grupo abajo. Responsable: Juana (Equipo 3). Los iconos se
 * dibujan con Java2D, no hacen falta archivos de imagen. La lógica real
 * (limpiar, borrador, grosor) la implementan los Equipos 1 y 5.
 */
public class PanelHerramientas extends JPanel {

    private static final int TAM_BOTON = 36;

    public final JToggleButton btnLapiz = new JToggleButton(new IconoLapiz());
    public final JToggleButton btnBorrador = new JToggleButton(new IconoBorrador());
    public final JButton btnGuardar = new JButton(new IconoGuardar());
    public final JButton btnLimpiar = new JButton(new IconoLimpiar());
    public final JButton btnDeshacer = new JButton(new IconoDeshacer(false));
    public final JButton btnRehacer = new JButton(new IconoDeshacer(true));

    // Grosor: slider VERTICAL, como en el Paint. Rango 1-50 (el mismo del Equipo 5).
    // NO va dentro de este panel: VentanaPrincipal lo pone en zonaGrosor
    // con getPanelGrosor().
    public final JSlider sliderGrosor = new JSlider(JSlider.VERTICAL, 1, 50, 3);
    public final JLabel lblGrosor = new JLabel("3", SwingConstants.CENTER);
    private final JPanel panelGrosor = new JPanel(new BorderLayout(0, 4));

    public PanelHerramientas() {
        setLayout(new BorderLayout(0, 2));
        Color borde = UIManager.getColor("controlShadow");
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 0, 1, borde != null ? borde : Color.GRAY),
                BorderFactory.createEmptyBorder(4, 6, 2, 8)));

        configurar(btnLapiz, "Lápiz");
        configurar(btnBorrador, "Borrador");
        configurar(btnLimpiar, "Limpiar lienzo");
        configurar(btnDeshacer, "Deshacer (Ctrl+Z)");
        configurar(btnRehacer, "Rehacer (Ctrl+Y)");
        configurar(btnGuardar, "Guardar (Ctrl+S)");
        for (JButton b : new JButton[]{btnGuardar, btnDeshacer, btnRehacer, btnLimpiar}) {
            b.setPreferredSize(new Dimension(30, 26));
        }

        JPanel filaIconos = new JPanel(new FlowLayout(FlowLayout.CENTER, 4, 0));
        filaIconos.add(btnLapiz);
        filaIconos.add(btnBorrador);
        filaIconos.add(btnLimpiar);
        JSeparator separador = new JSeparator(SwingConstants.VERTICAL);
        separador.setPreferredSize(new Dimension(6, 28));
        filaIconos.add(separador);
        filaIconos.add(btnDeshacer);   // deshacer / rehacer: mismos métodos que el menú Edición
        filaIconos.add(btnRehacer);

        add(filaIconos, BorderLayout.CENTER);
        add(new JLabel("Herramientas", SwingConstants.CENTER), BorderLayout.SOUTH);

        armarPanelGrosor();
    }

    /**
     * Panel del grosor (etiqueta, slider vertical y valor) para ponerlo en
     * zonaGrosor.
     */
    public JPanel getPanelGrosor() {
        return panelGrosor;
    }

    private void armarPanelGrosor() {
        sliderGrosor.setToolTipText("Grosor del trazo");
        panelGrosor.setBorder(BorderFactory.createEmptyBorder(6, 4, 6, 4));
        panelGrosor.add(new JLabel("Grosor", SwingConstants.CENTER), BorderLayout.NORTH);
        panelGrosor.add(sliderGrosor, BorderLayout.CENTER);
        panelGrosor.add(lblGrosor, BorderLayout.SOUTH);

        // Solo actualiza el número que se ve junto al slider.
        sliderGrosor.addChangeListener(e
                -> lblGrosor.setText(String.valueOf(sliderGrosor.getValue())));
    }

    /**
     * Conecta mis controles con los métodos públicos de PanelDeDibujo. Si el
     * Equipo 1 cambia un nombre, solo se ajusta AQUÍ.
     */
    public void conectar(PanelDeDibujo lienzo) {
        // Lápiz = trazo libre
        btnLapiz.addActionListener(e
                -> lienzo.setTipoActual(PanelDeDibujo.TipoFigura.TRAZO));

        // Guardar (servicio del Equipo 4)
        btnGuardar.addActionListener(e -> new ServicioGuardarImagen()
                .guardarImagen(lienzo, SwingUtilities.getWindowAncestor(btnGuardar)));

        // Limpiar
        btnLimpiar.addActionListener(e -> lienzo.Limpiar());

        // Deshacer / Rehacer (los mismos que usa el menú Edición)
        btnDeshacer.addActionListener(e -> lienzo.deshacer());
        btnRehacer.addActionListener(e -> lienzo.rehacer());

        // Grosor: cada cambio del slider llega al lienzo
        sliderGrosor.addChangeListener(e
                -> lienzo.setGrosorActual(sliderGrosor.getValue()));
        lienzo.setGrosorActual(sliderGrosor.getValue()); // sincroniza al abrir

        // TODO Equipo 1: el borrador aún no existe en PanelDeDibujo.
        btnBorrador.setEnabled(false);
        btnBorrador.setToolTipText("Borrador (pendiente del Equipo 1)");
    }

    private static void configurar(javax.swing.AbstractButton b, String tooltip) {
        b.setToolTipText(tooltip);
        b.setFocusPainted(false);
        b.setMargin(new java.awt.Insets(0, 0, 0, 0));
        b.setPreferredSize(new Dimension(TAM_BOTON, TAM_BOTON));
    }

    /**
     * Arma el menú Archivo: Nuevo, Abrir, Guardar, línea y Salir. La lógica la
     * hacen los servicios del Equipo 4; aquí solo se presentan y se conectan.
     */
    public void instalarMenuArchivo(JMenu menu, JMenuItem itemNuevo, JMenuItem itemSalir,
            PanelDeDibujo lienzo, ContenedorLienzo contenedor, Component ventana) {

        ServicioGuardarImagen servicioGuardar = new ServicioGuardarImagen();
        ServicioAbrirImagen servicioAbrir = new ServicioAbrirImagen();

        // Abrir
        JMenuItem itemAbrir = new JMenuItem("Abrir...");
        itemAbrir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK));
        itemAbrir.addActionListener(e -> {
            BufferedImage img = servicioAbrir.abrir(ventana);
            if (img != null) {
                contenedor.setTamanoLienzo(img.getWidth(), img.getHeight());
                // TODO Equipo 1/4: mostrar img en el lienzo (falta un método en PanelDeDibujo)
            }
        });
        itemAbrir.setEnabled(false);   // quitar esta línea cuando exista el método de arriba
        itemAbrir.setToolTipText("Pendiente: el lienzo aún no puede mostrar imágenes");

        // Guardar
        JMenuItem itemGuardar = new JMenuItem("Guardar...");
        itemGuardar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));
        itemGuardar.addActionListener(e -> servicioGuardar.guardarImagen(lienzo, ventana));

        // Nuevo (lo conecta el Equipo 4; aquí solo le pongo el atajo)
        itemNuevo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));

        // Orden final: Nuevo, Abrir, Guardar, línea, Salir
        menu.remove(itemSalir);
        menu.add(itemAbrir);
        menu.add(itemGuardar);
        menu.addSeparator();
        menu.add(itemSalir);
    }

    // ---------------------------------------------------------------
    // Iconos dibujados con Java2D (24x24)
    // ---------------------------------------------------------------
    private abstract static class IconoBase implements Icon {

        @Override
        public int getIconWidth() {
            return 24;
        }

        @Override
        public int getIconHeight() {
            return 24;
        }

        @Override
        public void paintIcon(Component c, Graphics g, int x, int y) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setStroke(new BasicStroke(1.4f));
            g2.translate(x, y);
            dibujar(g2, trazo(c));
            g2.dispose();
        }

        private static Color trazo(Component c) {
            Color fg = c != null ? c.getForeground() : null;
            return fg != null ? fg : Color.DARK_GRAY;
        }

        abstract void dibujar(Graphics2D g2, Color trazo);
    }

    private static class IconoLapiz extends IconoBase {

        @Override
        void dibujar(Graphics2D g2, Color trazo) {
            g2.translate(12, 12);
            g2.rotate(Math.toRadians(45));
            g2.setColor(new Color(0xF2B84B));             // cuerpo
            g2.fillRect(-3, -8, 6, 13);
            g2.setColor(new Color(0xE58A9B));             // goma
            g2.fillRect(-3, -11, 6, 3);
            Path2D punta = new Path2D.Double();
            punta.moveTo(-3, 5);
            punta.lineTo(3, 5);
            punta.lineTo(0, 10);
            punta.closePath();
            g2.setColor(new Color(0xE8CFA0));
            g2.fill(punta);
            g2.setColor(trazo);
            g2.drawRect(-3, -11, 6, 16);
            g2.draw(punta);
        }
    }

    private static class IconoBorrador extends IconoBase {

        @Override
        void dibujar(Graphics2D g2, Color trazo) {
            g2.translate(12, 12);
            g2.rotate(Math.toRadians(-35));
            RoundRectangle2D cuerpo = new RoundRectangle2D.Double(-9, -5, 18, 10, 4, 4);
            g2.setColor(new Color(0xF08FA0));             // parte rosada
            g2.fill(cuerpo);
            g2.setColor(new Color(0x7FA6D9));             // franja azul
            g2.fillRect(0, -5, 9, 10);
            g2.setColor(trazo);
            g2.draw(cuerpo);
            g2.drawLine(0, -5, 0, 5);
        }
    }

    private static class IconoLimpiar extends IconoBase {

        @Override
        void dibujar(Graphics2D g2, Color trazo) {
            Path2D cubo = new Path2D.Double();
            cubo.moveTo(6, 9);
            cubo.lineTo(18, 9);
            cubo.lineTo(16.5, 20);
            cubo.lineTo(7.5, 20);
            cubo.closePath();
            g2.setColor(new Color(0xBFD6EE));
            g2.fill(cubo);
            g2.setColor(trazo);
            g2.draw(cubo);
            g2.drawLine(4, 7, 20, 7);                      // tapa
            g2.drawPolyline(new int[]{9, 9, 15, 15}, new int[]{7, 4, 4, 7}, 4); // asa
            g2.drawLine(10, 11, 10, 18);
            g2.drawLine(12, 11, 12, 18);
            g2.drawLine(14, 11, 14, 18);
        }
    }

    /**
     * Flecha curva de deshacer; con invertido = true es la de rehacer (espejo).
     */
    private static class IconoDeshacer extends IconoBase {

        private final boolean invertido;

        IconoDeshacer(boolean invertido) {
            this.invertido = invertido;
        }

        @Override
        void dibujar(Graphics2D g2, Color trazo) {
            if (invertido) {
                g2.translate(24, 0);
                g2.scale(-1, 1);
            }
            Color azul = new Color(0x3F7FC1);
            g2.setColor(azul);
            g2.setStroke(new BasicStroke(2.4f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            g2.draw(new Arc2D.Double(5, 6, 14, 14, 0, 180, Arc2D.OPEN));   // arco
            Path2D punta = new Path2D.Double();                              // cabeza de la flecha
            punta.moveTo(1.5, 13);
            punta.lineTo(8.5, 13);
            punta.lineTo(5, 18);
            punta.closePath();
            g2.fill(punta);
        }
    }

    /**
     * Disquete de guardar.
     */
    private static class IconoGuardar extends IconoBase {

        @Override
        void dibujar(Graphics2D g2, Color trazo) {
            Color azul = new Color(0x3F7FC1);
            Path2D cuerpo = new Path2D.Double();
            cuerpo.moveTo(3, 3);
            cuerpo.lineTo(17, 3);
            cuerpo.lineTo(21, 7);
            cuerpo.lineTo(21, 21);
            cuerpo.lineTo(3, 21);
            cuerpo.closePath();
            g2.setColor(azul);
            g2.fill(cuerpo);
            g2.setColor(Color.WHITE);
            g2.fillRect(6, 3, 10, 6);      // parte metálica de arriba
            g2.fillRect(6, 13, 12, 8);     // etiqueta
            g2.setColor(azul);
            g2.fillRect(12, 4, 2, 4);      // ranura
            g2.setColor(Color.GRAY);
            g2.drawLine(8, 16, 16, 16);
            g2.drawLine(8, 18, 16, 18);
            g2.setColor(trazo);
            g2.draw(cuerpo);
        }
    }
}
