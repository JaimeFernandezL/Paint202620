package ui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;
import java.text.Normalizer;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import java.awt.GridLayout;
import javax.swing.BorderFactory;

public class PanelFiguras extends JPanel {

    private final PanelDeDibujo panelDeDibujo;
    private final ButtonGroup grupoHerramientas;

    public PanelFiguras(PanelDeDibujo panelDeDibujo,
            ButtonGroup grupoHerramientas) {

        this.panelDeDibujo = panelDeDibujo;
        this.grupoHerramientas = grupoHerramientas;

        setLayout(new GridLayout(2, 0, 5, 5));
        setBackground(Color.WHITE);
        setOpaque(true);

        agregarBoton("Línea", PanelDeDibujo.TipoFigura.LINEA);
        agregarBoton("Rectángulo", PanelDeDibujo.TipoFigura.RECTANGULO);
        agregarBoton("Óvalo", PanelDeDibujo.TipoFigura.OVALO);
        agregarBoton("Círculo", PanelDeDibujo.TipoFigura.CIRCULO);
        agregarBoton("Rombo", PanelDeDibujo.TipoFigura.ROMBO);
        agregarBoton("Pentágono", PanelDeDibujo.TipoFigura.PENTAGONO);
        agregarBoton("Hexágono", PanelDeDibujo.TipoFigura.HEXAGONO);
        agregarBoton("Corazón", PanelDeDibujo.TipoFigura.CORAZON);
        agregarBoton("Flecha", PanelDeDibujo.TipoFigura.FLECHA);
        agregarBoton("Estrella", PanelDeDibujo.TipoFigura.ESTRELLA);
        agregarBoton("Triángulo", PanelDeDibujo.TipoFigura.TRIANGULO);
        agregarBoton("Paralelogramo", PanelDeDibujo.TipoFigura.PARALELOGRAMO);
    }

    private void agregarBoton(String texto,
            PanelDeDibujo.TipoFigura tipo) {

        JToggleButton boton = new JToggleButton(texto) {

            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g.create();

                // Fondo del botón
                if (getModel().isSelected()) {
                    g2d.setColor(new Color(210, 225, 245));
                } else if (getModel().isRollover()) {
                    g2d.setColor(new Color(235, 240, 248));
                } else {
                    g2d.setColor(getBackground());
                }

                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 8, 8);

                // Borde
                g2d.setColor(new Color(190, 190, 190));
                g2d.drawRoundRect(0, 0, getWidth() - 1,
                        getHeight() - 1, 8, 8);

                // Icono centrado
                Icon icono = getIcon();

                if (icono != null) {
                    int x = (getWidth() - icono.getIconWidth()) / 2;
                    int y = (getHeight() - icono.getIconHeight()) / 2;

                    icono.paintIcon(this, g2d, x, y);
                }

                g2d.dispose();
            }
        };

        boton.setBackground(Color.WHITE);
        boton.setOpaque(true);
        boton.setContentAreaFilled(true);

        boton.setBorderPainted(false);
        boton.setBorder(BorderFactory.createEmptyBorder());
        boton.setFocusPainted(false);

        boton.setToolTipText(texto);
        boton.setIcon(new IconoForma(forma(normalizar(texto))));

        boton.setPreferredSize(new Dimension(45, 45));
        boton.setMargin(new Insets(2, 2, 2, 2));

        boton.addActionListener(e
                -> panelDeDibujo.setTipoActual(tipo)
        );

        grupoHerramientas.add(boton);
        add(boton);
    }

    private String normalizar(String texto) {
        return Normalizer.normalize(texto, Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "")
                .toLowerCase();
    }

    private Shape forma(String nombre) {

        switch (nombre) {

            case "linea":
                return new Line2D.Double(5, 25, 29, 7);

            case "rectangulo":
                return new Rectangle2D.Double(5, 7, 24, 20);

            case "ovalo":
                return new Ellipse2D.Double(5, 8, 24, 18);

            case "circulo":
                return new Ellipse2D.Double(7, 5, 20, 20);

            case "rombo": {
                Path2D p = new Path2D.Double();
                p.moveTo(17, 4);
                p.lineTo(30, 17);
                p.lineTo(17, 30);
                p.lineTo(4, 17);
                p.closePath();
                return p;
            }

            case "pentagono": {
                Path2D p = new Path2D.Double();
                p.moveTo(17, 4);
                p.lineTo(30, 13);
                p.lineTo(25, 29);
                p.lineTo(9, 29);
                p.lineTo(4, 13);
                p.closePath();
                return p;
            }

            case "hexagono": {
                Path2D p = new Path2D.Double();
                p.moveTo(10, 5);
                p.lineTo(24, 5);
                p.lineTo(31, 17);
                p.lineTo(24, 29);
                p.lineTo(10, 29);
                p.lineTo(3, 17);
                p.closePath();
                return p;
            }

            case "triangulo": {
                Path2D p = new Path2D.Double();
                p.moveTo(17, 4);
                p.lineTo(30, 29);
                p.lineTo(4, 29);
                p.closePath();
                return p;
            }

            case "paralelogramo": {
                Path2D p = new Path2D.Double();
                p.moveTo(11, 5);
                p.lineTo(31, 5);
                p.lineTo(23, 29);
                p.lineTo(3, 29);
                p.closePath();
                return p;
            }

            case "flecha": {
                Path2D p = new Path2D.Double();
                p.moveTo(4, 17);
                p.lineTo(23, 17);
                p.lineTo(23, 9);
                p.lineTo(32, 17);
                p.lineTo(23, 25);
                p.lineTo(23, 17);
                p.closePath();
                return p;
            }

            case "estrella": {
                Path2D p = new Path2D.Double();

                for (int i = 0; i < 10; i++) {
                    double angulo = Math.PI * i / 5 - Math.PI / 2;
                    double radio = (i % 2 == 0) ? 13 : 6;
                    double px = 17 + Math.cos(angulo) * radio;
                    double py = 17 + Math.sin(angulo) * radio;

                    if (i == 0) {
                        p.moveTo(px, py);
                    } else {
                        p.lineTo(px, py);
                    }
                }

                p.closePath();
                return p;
            }

            case "corazon": {
                Path2D p = new Path2D.Double();
                p.moveTo(17, 29);
                p.curveTo(13, 25, 3, 19, 4, 12);
                p.curveTo(5, 4, 14, 3, 17, 11);
                p.curveTo(20, 3, 29, 4, 30, 12);
                p.curveTo(31, 19, 21, 25, 17, 29);
                p.closePath();
                return p;
            }

            default:
                return null;
        }
    }

    private class IconoForma implements Icon {

        private final Shape forma;

        public IconoForma(Shape forma) {
            this.forma = forma;
        }

        @Override
        public void paintIcon(Component c, Graphics g, int x, int y) {

            Graphics2D g2d = (Graphics2D) g.create();

            g2d.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON
            );

            g2d.setColor(Color.DARK_GRAY);

            g2d.setStroke(new BasicStroke(
                    2.0f,
                    BasicStroke.CAP_ROUND,
                    BasicStroke.JOIN_ROUND
            ));

            g2d.translate(x, y);

            if (forma != null) {
                g2d.draw(forma);
            }

            g2d.dispose();
        }

        @Override
        public int getIconWidth() {
            return 34;
        }

        @Override
        public int getIconHeight() {
            return 34;
        }
    }
}