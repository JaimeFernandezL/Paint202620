/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package figuras;

/**
 *
 * @author Jeffrey Santana
 */

import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

public class Circulo extends FiguraRellenable {
    public Circulo(Point puntoInicial, boolean relleno) {
        super(puntoInicial, relleno);
    }

    @Override
    protected Shape crearForma() {
        int ancho = Math.abs(puntoFinal.x - puntoInicial.x);
        int alto = Math.abs(puntoFinal.y - puntoInicial.y);
        int diametro = Math.min(ancho, alto);

        // Mantiene el punto inicial como esquina del cuadrado delimitador.
        int x = puntoFinal.x < puntoInicial.x ? puntoInicial.x - diametro : puntoInicial.x;
        int y = puntoFinal.y < puntoInicial.y ? puntoInicial.y - diametro : puntoInicial.y;
        return new Ellipse2D.Double(x, y, diametro, diametro);
    }
}

