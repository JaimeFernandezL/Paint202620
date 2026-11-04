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
 * @author josearielpereyra
 */

public class Linea extends Figura {
    public Linea() {
        this(new Point(), new Point());
    }

    public Linea(Point puntoInicial) {
        this(puntoInicial, puntoInicial);
    }

    public Linea(Point puntoInicial, Point puntoFinal) {
        super(puntoInicial, false);
        actualizar(puntoFinal);
    }

    @Override
    public void dibujar(Graphics g) {
        Graphics2D copia = prepararGraficos(g);
        try {
            copia.drawLine(puntoInicial.x, puntoInicial.y, puntoFinal.x, puntoFinal.y);
        } finally {
            copia.dispose();
        }
    }
}