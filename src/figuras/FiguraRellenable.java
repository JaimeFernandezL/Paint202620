/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package figuras;

/**
 *
 * @author Jeffrey Santana
 */
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;

public abstract class FiguraRellenable extends Figura {
    protected FiguraRellenable(Point puntoInicial, boolean relleno) {
        super(puntoInicial, relleno);
    }

    protected abstract Shape crearForma();

    @Override
    public final void dibujar(Graphics g) {
        Graphics2D copia = prepararGraficos(g);
        try {
            Shape forma = crearForma();
            if (relleno) {
                copia.fill(forma);
            } else {
                copia.draw(forma);
            }
        } finally {
            copia.dispose();
        }
    }
}
