/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package figuras;

import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.Path2D;

public class Estrella extends FiguraRellenable {
    private static final int PUNTAS = 5;
    private static final double PROPORCION_INTERIOR = 0.4;

    public Estrella(Point puntoInicial, boolean relleno) {
        super(puntoInicial, relleno);
    }

    @Override
    protected Shape crearForma() {
        int ancho = Math.abs(puntoFinal.x - puntoInicial.x);
        int alto = Math.abs(puntoFinal.y - puntoInicial.y);
        int lado = Math.min(ancho, alto);

        // Mantiene el punto inicial como esquina del cuadrado delimitador.
        int x = puntoFinal.x < puntoInicial.x ? puntoInicial.x - lado : puntoInicial.x;
        int y = puntoFinal.y < puntoInicial.y ? puntoInicial.y - lado : puntoInicial.y;

        double cx = x + lado / 2.0;
        double cy = y + lado / 2.0;
        double radioExterior = lado / 2.0;

        Path2D.Double estrella = new Path2D.Double();
        for (int i = 0; i < PUNTAS * 2; i++) {
            double r = (i % 2 == 0) ? radioExterior : radioExterior * PROPORCION_INTERIOR;
            double ang = Math.toRadians(-90 + i * 180.0 / PUNTAS);
            double px = cx + r * Math.cos(ang);
            double py = cy + r * Math.sin(ang);
            if (i == 0) {
                estrella.moveTo(px, py);
            } else {
                estrella.lineTo(px, py);
            }
        }
        estrella.closePath();
        return estrella;
    }
}