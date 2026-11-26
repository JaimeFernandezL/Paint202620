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
import java.awt.GridLayout;
import herramientaborrador.Borrador;
import java.util.ArrayList;
import java.util.Stack;

/**
 * Grupo "Herramientas" al estilo del Paint de Windows: iconos arriba y la
 * etiqueta del grupo abajo. Responsable: Juana (Equipo 3). Los iconos se
 * dibujan con Java2D, no hacen falta archivos de imagen. La lógica real
 * (limpiar, borrador, grosor) la implementan los Equipos 1 y 5.
 */
public class PanelHerramientas extends JPanel {

    private static final int TAM_BOTON = 36;

    public final JToggleButton btnLapiz = new JToggleButton();
    public final JToggleButton btnBorrador = new JToggleButton();

    public final JButton btnGuardar = new JButton(new IconoGuardar());
    public final JButton btnLimpiar = new JButton(new IconoLimpiar());
    public final JButton btnDeshacer = new JButton(new IconoDeshacer(false));
    public final JButton btnRehacer = new JButton(new IconoDeshacer(true));

    private ArrayList<figuras.Figura> figurasAntesDeBorrar = null;
    private boolean hayUnLimpiadoParaRehacer = false;
    private BufferedImage imagenAntesDeBorrar = null;
    private BufferedImage imagenParaRehacer = null;

    private final Stack<String> historialDeshacer = new Stack<>();
    private final Stack<String> historialRehacer = new Stack<>();

    public final JSlider sliderGrosor = new JSlider(JSlider.VERTICAL, 1, 50, 3);
    public final JLabel lblGrosor = new JLabel("3", SwingConstants.CENTER);
    private final JPanel panelGrosor = new JPanel(new BorderLayout(0, 4));

    public final JSlider sliderOpacidad = new JSlider(JSlider.VERTICAL, 1, 100, 100);
    public final JLabel lblOpacidad = new JLabel("100%", SwingConstants.CENTER);
    private Borrador borrador;

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
        filaIconos.add(btnLimpiar);
        JSeparator separador = new JSeparator(SwingConstants.VERTICAL);
        separador.setPreferredSize(new Dimension(6, 28));
        filaIconos.add(separador);
        filaIconos.add(btnDeshacer);
        filaIconos.add(btnRehacer);

        for (javax.swing.AbstractButton b : new javax.swing.AbstractButton[]{
            btnGuardar, btnDeshacer, btnRehacer, btnLimpiar, btnLapiz, btnBorrador}) {
            resaltar(b);
        }

        armarPanelGrosor();
    }

    public JPanel getPanelGrosor() {
        return panelGrosor;
    }

    private void armarPanelGrosor() {
        sliderGrosor.setToolTipText("Grosor del trazo");
        sliderOpacidad.setToolTipText("Opacidad");
        sliderGrosor.setPreferredSize(new Dimension(20, 400));
        sliderOpacidad.setPreferredSize(new Dimension(20, 400));
        panelGrosor.setLayout(new GridLayout(1, 2, 4, 0));
        panelGrosor.setBorder(BorderFactory.createEmptyBorder(6, 4, 6, 4));
        panelGrosor.add(columnaSlider("Grosor", sliderGrosor, lblGrosor));
        panelGrosor.add(columnaSlider("Opac.", sliderOpacidad, lblOpacidad));

        sliderGrosor.addChangeListener(e
                -> lblGrosor.setText(String.valueOf(sliderGrosor.getValue())));
        sliderOpacidad.addChangeListener(e
                -> lblOpacidad.setText(sliderOpacidad.getValue() + "%"));
    }

    private static JPanel columnaSlider(String titulo, JSlider slider, JLabel valor) {
        JPanel p = new JPanel(new BorderLayout(0, 4));
        p.add(new JLabel(titulo, SwingConstants.CENTER), BorderLayout.NORTH);
        p.add(slider, BorderLayout.CENTER);
        p.add(valor, BorderLayout.SOUTH);
        return p;
    }

    public void conectar(PanelDeDibujo lienzo) {
        btnLapiz.addActionListener(e
                -> lienzo.setTipoActual(PanelDeDibujo.TipoFigura.TRAZO));

        btnGuardar.addActionListener(e -> new ServicioGuardarImagen()
                .guardarImagen(lienzo, SwingUtilities.getWindowAncestor(btnGuardar)));

        // Registrar cada figura/trazo nuevo en el historial unificado
        lienzo.setAlCrearFigura(() -> {
            historialDeshacer.push("FIGURA");
            historialRehacer.clear();
            figurasAntesDeBorrar = null;
            imagenAntesDeBorrar = null;
            hayUnLimpiadoParaRehacer = false;
            if (borrador != null) {
                borrador.repintar();
            }
        });

        // Limpiar
        btnLimpiar.addActionListener(e -> {
            figurasAntesDeBorrar = lienzo.obtenerCopiaFiguras();
            imagenAntesDeBorrar = lienzo.getImagenFondo();
            imagenParaRehacer = null;
            hayUnLimpiadoParaRehacer = true;
            historialDeshacer.clear();
            historialRehacer.clear();
            lienzo.Limpiar();
        });

        // Deshacer unificado
        btnDeshacer.addActionListener(e -> {
            if (figurasAntesDeBorrar != null) {
                lienzo.restaurarFiguras(figurasAntesDeBorrar);
                lienzo.setImagenFondoDirecto(imagenAntesDeBorrar);
                figurasAntesDeBorrar = null;
                imagenAntesDeBorrar = null;
                hayUnLimpiadoParaRehacer = true;
            } else if (!historialDeshacer.isEmpty()) {
                String ultimaAccion = historialDeshacer.pop();
                if ("BORRADOR".equals(ultimaAccion)) {
                    borrador.deshacerMarca();
                } else {
                    lienzo.deshacer();
                }
                historialRehacer.push(ultimaAccion);
            }
            if (borrador != null) {
                borrador.repintar();
            }
        });

        // Rehacer unificado
        btnRehacer.addActionListener(e -> {
            if (hayUnLimpiadoParaRehacer || imagenParaRehacer != null) {
                figurasAntesDeBorrar = lienzo.obtenerCopiaFiguras();
                imagenAntesDeBorrar = lienzo.getImagenFondo();
                lienzo.Limpiar();

                if (imagenParaRehacer != null) {
                    lienzo.setImagenFondoDirecto(imagenParaRehacer);
                    imagenParaRehacer = null;
                }
                hayUnLimpiadoParaRehacer = false;
            } else if (!historialRehacer.isEmpty()) {
                String accionARehacer = historialRehacer.pop();
                if ("BORRADOR".equals(accionARehacer)) {
                    borrador.rehacerMarca();
                } else {
                    lienzo.rehacer();
                }
                historialDeshacer.push(accionARehacer);
            }
            if (borrador != null) {
                borrador.repintar();
            }
        });

        // Grosor
        sliderGrosor.addChangeListener(e -> {
            int grosor = sliderGrosor.getValue();
            lienzo.setGrosorActual(grosor);
            lblGrosor.setText(String.valueOf(grosor));
            if (borrador != null) {
                borrador.repintar();
            }
        });
        lienzo.setGrosorActual(sliderGrosor.getValue());

        // Opacidad
        sliderOpacidad.addChangeListener(e -> {
            lienzo.setOpacidad(sliderOpacidad.getValue());
            if (borrador != null) {
                borrador.repintar();
            }
        });
        lienzo.setOpacidad(sliderOpacidad.getValue());

        // Borrador
        borrador = new Borrador(lienzo);
        borrador.vincularBoton(btnBorrador);
        borrador.vincularLimpiar(btnLimpiar);
        borrador.setGrosor(sliderGrosor.getValue());
        borrador.setAlBorrar(() -> {
            historialDeshacer.push("BORRADOR");
            historialRehacer.clear();
            figurasAntesDeBorrar = null;
            imagenAntesDeBorrar = null;
            hayUnLimpiadoParaRehacer = false;
        });
        sliderGrosor.addChangeListener(e
                -> borrador.setGrosor(sliderGrosor.getValue()));
    }

    public void actualizarCapaBorrador() {
        if (borrador != null) {
            borrador.repintar();
        }
    }

    public void activarLapiz() {
        btnLapiz.setSelected(true);
        btnLapiz.doClick();
    }

    public void activarBorrador() {
        btnBorrador.setSelected(true);
        btnBorrador.doClick();
    }

    private static void configurar(javax.swing.AbstractButton b, String tooltip) {
        b.setToolTipText(tooltip);
        b.setFocusPainted(false);
        b.setMargin(new java.awt.Insets(0, 0, 0, 0));
        b.setPreferredSize(new Dimension(TAM_BOTON, TAM_BOTON));
        b.setBorder(BorderFactory.createEmptyBorder());
        b.setBorderPainted(false);
        b.setContentAreaFilled(false);
        b.setOpaque(false);
        b.setRolloverEnabled(true);
    }

    private static void resaltar(javax.swing.AbstractButton b) {
        Color hover = new Color(0xE5F0FB);
        Color press = new Color(0xC7E0F5);
        b.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                pintar(b, hover);
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                if (!b.isSelected()) {
                    pintar(b, null);
                }
            }

            @Override
            public void mousePressed(java.awt.event.MouseEvent e) {
                pintar(b, press);
            }

            @Override
            public void mouseReleased(java.awt.event.MouseEvent e) {
                if (!b.isSelected()) {
                    pintar(b, null);
                }
            }
        });
        b.addItemListener(e -> pintar(b, b.isSelected() ? hover : null));
    }

    private static void pintar(javax.swing.AbstractButton b, Color c) {
        b.setContentAreaFilled(c != null);
        b.setOpaque(c != null);
        if (c != null) {
            b.setBackground(c);
        }
    }

    public void instalarMenuArchivo(JMenu menu, JMenuItem itemNuevo, JMenuItem itemSalir,
            PanelDeDibujo lienzo, ContenedorLienzo contenedor, Component ventana) {

        ServicioGuardarImagen servicioGuardar = new ServicioGuardarImagen();
        ServicioAbrirImagen servicioAbrir = new ServicioAbrirImagen();

        JMenuItem itemAbrir = new JMenuItem("Abrir...");
        itemAbrir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK));
        itemAbrir.addActionListener(e -> {
            BufferedImage img = servicioAbrir.abrir(ventana);
            if (img != null) {
                figurasAntesDeBorrar = lienzo.obtenerCopiaFiguras();
                imagenAntesDeBorrar = lienzo.getImagenFondo();
                imagenParaRehacer = img;
                hayUnLimpiadoParaRehacer = true;
                historialDeshacer.clear();
                historialRehacer.clear();

                contenedor.setTamanoLienzo(img.getWidth(), img.getHeight());
                lienzo.cargarImagen(img);
                if (borrador != null) {
                    borrador.limpiar();
                }
            }
        });

        JMenuItem itemGuardar = new JMenuItem("Guardar...");
        itemGuardar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));
        itemGuardar.addActionListener(e -> servicioGuardar.guardarImagen(lienzo, ventana));

        itemNuevo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));

        menu.remove(itemSalir);
        menu.add(itemAbrir);
        menu.add(itemGuardar);
        menu.addSeparator();
        menu.add(itemSalir);
    }

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
            g2.drawLine(4, 7, 20, 7);
            g2.drawPolyline(new int[]{9, 9, 15, 15}, new int[]{7, 4, 4, 7}, 4);
            g2.drawLine(10, 11, 10, 18);
            g2.drawLine(12, 11, 12, 18);
            g2.drawLine(14, 11, 14, 18);
        }
    }

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
            g2.draw(new Arc2D.Double(5, 6, 14, 14, 0, 180, Arc2D.OPEN));
            Path2D punta = new Path2D.Double();
            punta.moveTo(1.5, 13);
            punta.lineTo(8.5, 13);
            punta.lineTo(5, 18);
            punta.closePath();
            g2.fill(punta);
        }
    }

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
            g2.fillRect(6, 3, 10, 6);
            g2.fillRect(6, 13, 12, 8);
            g2.setColor(azul);
            g2.fillRect(12, 4, 2, 4);
            g2.setColor(Color.GRAY);
            g2.drawLine(8, 16, 16, 16);
            g2.drawLine(8, 18, 16, 18);
            g2.setColor(trazo);
            g2.draw(cuerpo);
        }
    }
}