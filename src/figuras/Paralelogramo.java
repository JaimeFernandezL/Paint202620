package figuras;

import java.awt.Point;
import java.awt.Polygon;
import java.awt.Shape;

public class Paralelogramo extends FiguraRellenable {

    public Paralelogramo(Point puntoInicial, boolean relleno) {
        super(puntoInicial, relleno);
    }

    @Override
    protected Shape crearForma() {
        int izquierda = Math.min(puntoInicial.x, puntoFinal.x);
        int derecha = Math.max(puntoInicial.x, puntoFinal.x);
        int arriba = Math.min(puntoInicial.y, puntoFinal.y);
        int abajo = Math.max(puntoInicial.y, puntoFinal.y);

        int ancho = derecha - izquierda;
        int alto = abajo - arriba;
        int inclinacion = Math.min(ancho / 4, alto / 2);

        Polygon paralelogramo = new Polygon();
        paralelogramo.addPoint(izquierda + inclinacion, arriba);
        paralelogramo.addPoint(derecha, arriba);
        paralelogramo.addPoint(derecha - inclinacion, abajo);
        paralelogramo.addPoint(izquierda, abajo);

        return paralelogramo;
    }
}
