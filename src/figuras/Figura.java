/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package figuras;

import java.awt.Graphics;
import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Point;

/**
 *
 * @author josearielpereyra
 */
public abstract class Figura {
    private final float opacidad;

    protected Figura() {
        this(1.0f);
    }

    protected Figura(float opacidad) {
        this.opacidad = Math.max(0.0f, Math.min(1.0f, opacidad));
    }

    protected Graphics2D crearGraphicsConOpacidad(Graphics g) {
        Graphics2D graphics2D = (Graphics2D) g.create();
        graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacidad));
        return graphics2D;
    }

    public abstract void dibujar(Graphics g);
    public abstract void actualizar(Point puntoActual);
}
