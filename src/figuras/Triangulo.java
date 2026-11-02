package figuras;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;

/** Triángulo isósceles que puede dibujarse vacío o relleno. */
public class Triangulo extends Figura {
    private final Point puntoInicial;
    private Point puntoFinal;
    private final boolean relleno;

    public Triangulo(Point puntoInicial, boolean relleno) {
        this.puntoInicial = new Point(puntoInicial);
        this.puntoFinal = new Point(puntoInicial);
        this.relleno = relleno;
    }

    @Override
    public void dibujar(Graphics g) {
        if (puntoInicial == null || puntoFinal == null) return;

        int izquierda = Math.min(puntoInicial.x, puntoFinal.x);
        int derecha = Math.max(puntoInicial.x, puntoFinal.x);
        int arriba = Math.min(puntoInicial.y, puntoFinal.y);
        int abajo = Math.max(puntoInicial.y, puntoFinal.y);
        int centro = izquierda + (derecha - izquierda) / 2;

        Polygon triangulo = new Polygon(
            new int[]{centro, derecha, izquierda},
            new int[]{arriba, abajo, abajo},
            3
        );

        if (relleno) g.fillPolygon(triangulo);
        else g.drawPolygon(triangulo);
    }

    @Override
    public void actualizar(Point puntoActual) {
        this.puntoFinal = new Point(puntoActual);
    }
}
