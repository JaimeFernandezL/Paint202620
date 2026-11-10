package figuras;

import java.awt.Point;
import java.awt.Polygon;
import java.awt.Shape;

public class Triangulo extends FiguraRellenable {

    public Triangulo(Point puntoInicial, boolean relleno) {
        super(puntoInicial, relleno);
    }

    @Override
    protected Shape crearForma() {
        int izquierda = Math.min(puntoInicial.x, puntoFinal.x);
        int derecha = Math.max(puntoInicial.x, puntoFinal.x);
        int arriba = Math.min(puntoInicial.y, puntoFinal.y);
        int abajo = Math.max(puntoInicial.y, puntoFinal.y);

        int centro = izquierda + (derecha - izquierda) / 2;

        Polygon triangulo = new Polygon();
        triangulo.addPoint(centro, arriba);
        triangulo.addPoint(derecha, abajo);
        triangulo.addPoint(izquierda, abajo);

        return triangulo;
    }
}
