/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;

import figuras.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author josearielpereyra
 */
public class PanelDeDibujo extends JPanel {
    public enum TipoFigura {
        LINEA, TRAZO, RECTANGULO, OVALO, CIRCULO, ROMBO, PENTAGONO
    }

    private final ArrayList<Figura> figuras = new ArrayList<>();
    private Figura figuraActual;
    private TipoFigura tipoActual = TipoFigura.TRAZO;
    private boolean rellenarFigura = false;

    public PanelDeDibujo() {
        setBackground(Color.LIGHT_GRAY);
        MouseAdapter manejador = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (!SwingUtilities.isLeftMouseButton(e)) {
                    return;
                }
                switch (tipoActual) {
                    case LINEA:
                        figuraActual = new Linea(e.getPoint());
                        break;
                    case RECTANGULO:
                        figuraActual = new Rectangulo(e.getPoint(), rellenarFigura);
                        break;
                    case OVALO:
                        figuraActual = new Ovalo(e.getPoint(), rellenarFigura);
                        break;
                    case CIRCULO:
                        figuraActual = new Circulo(e.getPoint(), rellenarFigura);
                        break;
                    case ROMBO:
                        figuraActual = new Rombo(e.getPoint(), rellenarFigura);
                        break;
                    case PENTAGONO:
                        figuraActual = new Pentagono(e.getPoint(), rellenarFigura);
                        break;
                    case TRAZO:
                    default:
                        figuraActual = new Trazo(e.getPoint());
                        break;
                }
                figuras.add(figuraActual);
                repaint();
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                if (figuraActual != null) {
                    figuraActual.actualizar(e.getPoint());
                    repaint();
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e) && figuraActual != null) {
                    figuraActual.actualizar(e.getPoint());
                    figuraActual = null;
                    repaint();
                }
            }
        };
        addMouseListener(manejador);
        addMouseMotionListener(manejador);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Figura figura : figuras) {
            figura.dibujar(g);
        }
    }

    public void setTipoActual(TipoFigura tipo) {
        tipoActual = java.util.Objects.requireNonNull(tipo);
    }

    public void setRellenarFigura(boolean rellenar) {
        rellenarFigura = rellenar;
    }

    public void Limpiar() {
        figuras.clear();
        figuraActual = null;
        repaint();
    }
}
