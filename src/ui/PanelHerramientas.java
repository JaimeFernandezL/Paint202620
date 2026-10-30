/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;
 
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Path2D;
import java.awt.geom.RoundRectangle2D;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
 
/**
 * Grupo "Herramientas" al estilo del Paint de Windows: iconos arriba y la
 * etiqueta del grupo abajo. Responsable: Juana (Equipo 3).
 * Los iconos se dibujan con Java2D, no hacen falta archivos de imagen.
 * La lógica real (limpiar, borrador, grosor) la implementan los Equipos 1 y 5.
 */
public class PanelHerramientas extends JPanel {
 
    private static final int TAM_BOTON = 40;
 
    public final JToggleButton btnLapiz = new JToggleButton(new IconoLapiz());
    public final JToggleButton btnBorrador = new JToggleButton(new IconoBorrador());
    public final JButton btnLimpiar = new JButton(new IconoLimpiar());
    // Rango PROVISIONAL (1-50) hasta que el Equipo 5 confirme el suyo.
    public final JSlider sliderGrosor = new JSlider(1, 50, 3);
    public final JLabel lblGrosor = new JLabel("3");
 
    public PanelHerramientas() {
        setLayout(new BorderLayout(0, 2));
        Color borde = UIManager.getColor("controlShadow");
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 0, 1, borde != null ? borde : Color.GRAY),
                BorderFactory.createEmptyBorder(4, 6, 2, 8)));
 
        configurar(btnLapiz, "Lápiz");
        configurar(btnBorrador, "Borrador");
        configurar(btnLimpiar, "Limpiar lienzo");
 
        JPanel filaIconos = new JPanel(new FlowLayout(FlowLayout.CENTER, 4, 0));
        filaIconos.add(btnLapiz);
        filaIconos.add(btnBorrador);
        filaIconos.add(btnLimpiar);
 
        sliderGrosor.setPreferredSize(new Dimension(110, 24));
        JPanel filaGrosor = new JPanel(new FlowLayout(FlowLayout.CENTER, 4, 0));
        filaGrosor.add(new JLabel("Grosor"));
        filaGrosor.add(sliderGrosor);
        filaGrosor.add(lblGrosor);
 
        JPanel contenido = new JPanel(new BorderLayout(0, 4));
        contenido.add(filaIconos, BorderLayout.CENTER);
        contenido.add(filaGrosor, BorderLayout.SOUTH);
 
        JLabel etiqueta = new JLabel("Herramientas", SwingConstants.CENTER);
 
        add(contenido, BorderLayout.CENTER);
        add(etiqueta, BorderLayout.SOUTH);
 
        sliderGrosor.addChangeListener(e
                -> lblGrosor.setText(String.valueOf(sliderGrosor.getValue())));
    }
 
    private static void configurar(javax.swing.AbstractButton b, String tooltip) {
        b.setToolTipText(tooltip);
        b.setFocusPainted(false);
        b.setMargin(new java.awt.Insets(0, 0, 0, 0));
        b.setPreferredSize(new Dimension(TAM_BOTON, TAM_BOTON));
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
}