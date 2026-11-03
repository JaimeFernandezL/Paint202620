/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package figuras;

/**
 *
 * @author josearielpereyra
 */
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

public class Trazo extends Figura {
    private final ArrayList<Point> puntos = new ArrayList<>();

    public Trazo() {
        super();
    }

    public Trazo(Point puntoInicial) {
        super(puntoInicial, false);
        puntos.add(new Point(puntoInicial));
    }

    @Override
    public void dibujar(Graphics g) {
        Graphics2D copia = prepararGraficos(g);
        try {
            for (int i = 1; i < puntos.size(); i++) {
                Point anterior = puntos.get(i - 1);
                Point actual = puntos.get(i);
                copia.drawLine(anterior.x, anterior.y, actual.x, actual.y);
            }
        } finally {
            copia.dispose();
        }
    }

    @Override
    public void actualizar(Point puntoActual) {
        super.actualizar(puntoActual);
        if (puntos.isEmpty()) {
            puntoInicial = new Point(puntoActual);
        }
        puntos.add(new Point(puntoActual));
    }
}
