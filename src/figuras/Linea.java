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
public class Linea extends Figura{
    private Point puntoInicial;
    private Point puntoFinal;

    public Linea() {
        this(new Point(), new Point());
    }

    
    public Linea(Point puntoInicial) {
        this(puntoInicial, puntoInicial);
    }

    public Linea(Point puntoInicial, float opacidad) {
        this(puntoInicial, puntoInicial, opacidad);
    }

    public Linea(Point puntoInicial, Point puntoFinal) {
        this(puntoInicial, puntoFinal, 1.0f);
    }

    public Linea(Point puntoInicial, Point puntoFinal, float opacidad) {
        super(opacidad);
        this.puntoInicial = puntoInicial;
        this.puntoFinal = puntoFinal;
    }
    
    @Override
    public void dibujar(Graphics g) {
        if(puntoInicial != null && puntoFinal != null) {
            Graphics2D graphics2D = crearGraphicsConOpacidad(g);
            graphics2D.drawLine(puntoInicial.x, puntoInicial.y, puntoFinal.x, puntoFinal.y);
            graphics2D.dispose();
        }
    }

    @Override
    public void actualizar(Point puntoActual) {
        puntoFinal = puntoActual;
    }
    
    
    
    
}
