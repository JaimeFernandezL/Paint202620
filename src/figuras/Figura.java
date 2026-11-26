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
import java.awt.Color;
import java.awt.Rectangle;

/**
 *
 * @author josearielpereyra
 */
public abstract class Figura {

    protected Point puntoInicial;
    protected Point puntoFinal;
    protected boolean relleno;
    protected int grosor;
    protected float opacidad;
    protected Color color;

    protected Figura() {
        this(new Point(), false);
    }

    protected Figura(Point puntoInicial, boolean relleno) {
        this.puntoInicial = new Point(Objects.requireNonNull(puntoInicial));
        this.puntoFinal = new Point(puntoInicial);
        this.relleno = relleno;
        this.grosor = 1;
        this.opacidad = 1.0f;
        this.color = Color.BLACK;
    }

    protected Graphics2D prepararGraficos(Graphics g) {
        Graphics2D graphics2D = (Graphics2D) g.create();
        graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, this.opacidad));
        graphics2D.setColor(this.color);
        return graphics2D;
    }

    public abstract void dibujar(Graphics g);

    public void setColor(Color color) {
        this.color = (color != null) ? color : Color.BLACK;
    }

    public Color getColor() {
        return color;
    }

    public void actualizar(Point puntoActual) {
        puntoFinal = new Point(Objects.requireNonNull(puntoActual));
    }

    public void setGrosor(int grosor) {
        this.grosor = grosor;
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

    public boolean isRelleno() {
        return relleno;
    }

    public void setRelleno(boolean relleno) {
        this.relleno = relleno;
    }

    public Point getPuntoInicial() {
        return puntoInicial;
    }

    public Point getPuntoFinal() {
        return puntoFinal;
    }

    public void setLimites(int x, int y, int ancho, int alto) {
        this.puntoInicial = new Point(x, y);
        this.puntoFinal = new Point(x + ancho, y + alto);
    }

    public Rectangle getBounds() {
        int x = Math.min(puntoInicial.x, puntoFinal.x);
        int y = Math.min(puntoInicial.y, puntoFinal.y);
        int w = Math.abs(puntoFinal.x - puntoInicial.x);
        int h = Math.abs(puntoFinal.y - puntoInicial.y);
        return new Rectangle(x, y, w, h);
    }


    //protected Graphics2D crearGraphicsConOpacidad(Graphics g) {
    //    Graphics2D graphics2D = (Graphics2D) g.create();
    //    graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacidad));
    //    return graphics2D;
    //}
}
