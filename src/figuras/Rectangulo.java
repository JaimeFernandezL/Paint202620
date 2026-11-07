/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package figuras;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;

public class Rectangulo extends FiguraRellenable {
    public Rectangulo(Point puntoInicial, boolean relleno) {
        super(puntoInicial, relleno);
    }

    @Override
    protected Shape crearForma() {
        int x = Math.min(puntoInicial.x, puntoFinal.x);
        int y = Math.min(puntoInicial.y, puntoFinal.y);
        int ancho = Math.abs(puntoInicial.x - puntoFinal.x);
        int alto = Math.abs(puntoInicial.y - puntoFinal.y);
        return new Rectangle(x, y, ancho, alto);
    }
}

