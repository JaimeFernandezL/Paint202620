/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package figuras;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author josearielpereyra
 */
public class Trazo extends Figura {

    ArrayList<Point> puntos = new ArrayList<>();

    @Override
    public void dibujar(Graphics g) {
        Graphics2D g2d = (Graphics2D) g; 
        g2d.setStroke(new BasicStroke(grosor, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        
        for (int i = 1; i < puntos.size(); i++) {
            Linea l = new Linea(puntos.get(i - 1), puntos.get(i));
            l.setGrosor(this.grosor); 
            l.dibujar(g2d);
        }
    }

    @Override
    public void actualizar(Point puntoActual) {
        puntos.add(puntoActual);
    }

}
