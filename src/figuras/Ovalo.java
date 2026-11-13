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

public class Ovalo extends FiguraRellenable {
    public Ovalo(Point puntoInicial, boolean relleno) {
        super(puntoInicial, relleno);
    }

    @Override
    protected Shape crearForma() {
        int x = Math.min(puntoInicial.x, puntoFinal.x);
        int y = Math.min(puntoInicial.y, puntoFinal.y);
        int ancho = Math.abs(puntoFinal.x - puntoInicial.x);
        int alto = Math.abs(puntoFinal.y - puntoInicial.y);
        // Los semiejes son ancho / 2 y alto / 2.
        return new Ellipse2D.Double(x, y, ancho, alto);
    }
}
