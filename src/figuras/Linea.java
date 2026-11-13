/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package figuras;

import java.awt.BasicStroke;
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
    
    // Inicio de logica de constructores de rama feature/control-opacidad
    //public Linea(Point puntoInicial, float opacidad) {
    //    this(puntoInicial, puntoInicial, opacidad);
    //}

    /*
    public Linea(Point puntoInicial, Point puntoFinal) {
        this(puntoInicial, puntoFinal, 1.0f);
    }
    */

    //public Linea(Point puntoInicial, Point puntoFinal, float opacidad) {
    //    super(opacidad);
    //    this.puntoInicial = puntoInicial;
    //    this.puntoFinal = puntoFinal;
    //}
    // Fin de logica de constructores de rama feature/control-opacidad
    
    /*
    // Cambio de la rama feature/equipo-5-herramientas-merge-develop
    public Linea(Point puntoInicial, Point puntoFinal) {
        this.puntoInicial = puntoInicial;
        this.puntoFinal = puntoFinal;
    }
    */

    @Override
    public void dibujar(Graphics g) {
        Graphics2D copia = prepararGraficos(g);
        try {
            copia.setStroke(new BasicStroke(grosor, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            copia.drawLine(puntoInicial.x, puntoInicial.y, puntoFinal.x, puntoFinal.y);
        } finally {
            copia.dispose();
        }
    }
}
