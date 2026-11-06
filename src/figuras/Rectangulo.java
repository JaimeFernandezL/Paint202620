/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package figuras;

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
        this(puntoInicial, relleno, 1.0f);
    }

    public Rectangulo(Point puntoInicial, boolean relleno, float opacidad) {
        super(opacidad);
        this.puntoInicial = puntoInicial;
        this.puntoFinal = puntoInicial;
        this.relleno = relleno;
    }

    @Override
    public void dibujar(Graphics g) {
        if (puntoInicial != null && puntoFinal != null) {
            Graphics2D graphics2D = crearGraphicsConOpacidad(g);
            // Se calcula el origen y las dimensiones dinamicamente para 
            //permitir arrastrar en cualquier dirección
            int x = Math.min(puntoInicial.x, puntoFinal.x);
            int y = Math.min(puntoInicial.y, puntoFinal.y);
            int ancho = Math.abs(puntoInicial.x - puntoFinal.x);
            int alto = Math.abs(puntoInicial.y - puntoFinal.y);

            if (relleno) {
                graphics2D.fillRect(x, y, ancho, alto);
            } else {
                graphics2D.drawRect(x, y, ancho, alto);
            }
            graphics2D.dispose();
        }
    }

    @Override
    public void actualizar(Point puntoActual) {
        this.puntoFinal = puntoActual;
    }

}
