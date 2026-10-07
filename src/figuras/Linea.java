/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package figuras;

import java.awt.Graphics;
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

    public Linea(Point puntoInicial, Point puntoFinal) {
        this.puntoInicial = puntoInicial;
        this.puntoFinal = puntoFinal;
    }
    
    @Override
    public void dibujar(Graphics g) {
        if(puntoInicial != null && puntoFinal != null) {
            g.drawLine(puntoInicial.x, puntoInicial.y, puntoFinal.x, puntoFinal.y);
        }
    }

    @Override
    public void actualizar(Point puntoActual) {
        puntoFinal = puntoActual;
    }
    
    
    
    
}
