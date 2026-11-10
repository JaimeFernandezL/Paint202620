/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package figuras;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.Objects;
import java.awt.AlphaComposite;

/**
 *
 * @author josearielpereyra
 */
public abstract class Figura {
    
    protected Point puntoInicial;
    protected Point puntoFinal;
    protected boolean relleno;
    //El grosor por default
    protected int grosor;
    protected float opacidad;

    protected Figura() {
        this(new Point(), false);
    }
    
    //protected Figura(float opacidad) {
    //    this.opacidad = Math.max(0.0f, Math.min(1.0f, opacidad));
    //}

    protected Figura(Point puntoInicial, boolean relleno) {
        this.puntoInicial = new Point(Objects.requireNonNull(puntoInicial));
        this.puntoFinal = new Point(puntoInicial);
        this.relleno = relleno;
        this.grosor = 1;
        this.opacidad = Math.max(0.0f, Math.min(1.0f, 1.0f));
    }

    // Cada figura utiliza una copia independiente del contexto grafico.
    protected Graphics2D prepararGraficos(Graphics g) {
        Graphics2D graphics2D = (Graphics2D) g.create();
        graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, this.opacidad));
        return graphics2D;
        //return (Graphics2D) g.create();
    }

    public abstract void dibujar(Graphics g);

    public void actualizar(Point puntoActual) {
        puntoFinal = new Point(Objects.requireNonNull(puntoActual));
    }
    
    public void setGrosor(int grosor) {
        this.grosor=grosor;
    }
    
    public int getGrosor() {
        return grosor;
    }
    
    public float getOpacidad() {
        return opacidad;
    }

    public void setOpacidad(float opacidad) {
        this.opacidad = opacidad;
    }
    
    //protected Graphics2D crearGraphicsConOpacidad(Graphics g) {
    //    Graphics2D graphics2D = (Graphics2D) g.create();
    //    graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacidad));
    //    return graphics2D;
    //}
}
