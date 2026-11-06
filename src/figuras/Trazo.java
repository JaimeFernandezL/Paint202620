/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package figuras;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author josearielpereyra
 */
public class Trazo extends Figura{
    ArrayList<Point> puntos = new ArrayList<>();

    public Trazo() {
        super();
    }

    public Trazo(float opacidad) {
        super(opacidad);
    }
    
    @Override
    public void dibujar(Graphics g) {
        Graphics2D graphics2D = crearGraphicsConOpacidad(g);
        for (int i = 1; i < puntos.size(); i++) {
            new Linea(puntos.get(i - 1), puntos.get(i)).dibujar(graphics2D);
        }
        graphics2D.dispose();
    }

    @Override
    public void actualizar(Point puntoActual) {
        puntos.add(puntoActual);
    }
    
}
