/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;

import figuras.Figura;
import figuras.Linea;
import figuras.Trazo;
import figuras.Rectangulo;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author josearielpereyra
 */
public class PanelDeDibujo extends JPanel {

    public enum TipoFigura {
        LINEA, TRAZO, RECTANGULO
    }

    ArrayList<Figura> figuras = new ArrayList<>();
    Figura figuraActual;

    private TipoFigura tipoActual = TipoFigura.TRAZO;
    private boolean rellenarFigura = false;

    public PanelDeDibujo() {
        setBackground(Color.LIGHT_GRAY);
        MouseAdapter manejador = new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                switch (tipoActual) {
                    case LINEA:
                        figuraActual = new Linea(e.getPoint());
                        break;
                    case RECTANGULO:
                        figuraActual = new Rectangulo(e.getPoint(), rellenarFigura);
                        break;
                    case TRAZO:
                    default:
                        figuraActual = new Trazo();
                        break;
                }
                figuras.add(figuraActual);
                repaint();
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                figuraActual.actualizar(e.getPoint());
                repaint();
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
        this.tipoActual = tipo;
    }

    public void setRellenarFigura(boolean rellenar) {
        this.rellenarFigura = rellenar;
    }

    public void Limpiar() {
        figuras.clear();
        repaint();
    }
}
