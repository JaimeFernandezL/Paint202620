/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package figuras;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

/**
 *
 * @author sadex
 */
public class Rectangulo extends Figura {

    final private Point puntoInicial;
    private Point puntoFinal;
    final private boolean relleno;

    public Rectangulo(Point puntoInicial, boolean relleno) {
        this.puntoInicial = puntoInicial;
        this.puntoFinal = puntoInicial;
        this.relleno = relleno;
    }

    @Override
    public void dibujar(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(grosor, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));

        // La matemática para que el rectángulo se dibuje hacia cualquier lado
        int x = Math.min(puntoInicial.x, puntoFinal.x);
        int y = Math.min(puntoInicial.y, puntoFinal.y);
        int ancho = Math.abs(puntoInicial.x - puntoFinal.x);
        int alto = Math.abs(puntoInicial.y - puntoFinal.y);

        if (relleno) {
            g2d.fillRect(x, y, ancho, alto);
        } else {
            g2d.drawRect(x, y, ancho, alto);
        }

    }

    @Override
    public void actualizar(Point puntoActual) {
        this.puntoFinal = puntoActual;
    }

}
