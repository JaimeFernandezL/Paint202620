/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package figuras;

import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.Path2D;

public class Hexagono extends FiguraRellenable {
    public Hexagono(Point puntoInicial, boolean relleno) {
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
        double radio = lado / 2.0;

        Path2D.Double hexagono = new Path2D.Double();
        for (int i = 0; i < 6; i++) {
            double ang = Math.toRadians(60 * i);
            double px = cx + radio * Math.cos(ang);
            double py = cy + radio * Math.sin(ang);
            if (i == 0) {
                hexagono.moveTo(px, py);
            } else {
                hexagono.lineTo(px, py);
            }
        }
        hexagono.closePath();
        return hexagono;
    }
}