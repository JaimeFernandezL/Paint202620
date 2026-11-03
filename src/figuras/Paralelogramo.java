package figuras;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;

/** Figura de paralelogramo que puede dibujarse vacía o rellena. */
public class Paralelogramo extends Figura {
    private final Point puntoInicial;
    private Point puntoFinal;
    private final boolean relleno;

    public Paralelogramo(Point puntoInicial, boolean relleno) {
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
        int ancho = derecha - izquierda;
        int alto = abajo - arriba;
        int inclinacion = Math.min(ancho / 4, alto / 2);

        Polygon paralelogramo = new Polygon(
            new int[]{izquierda + inclinacion, derecha, derecha - inclinacion, izquierda},
            new int[]{arriba, arriba, abajo, abajo},
            4
        );

        if (relleno) g.fillPolygon(paralelogramo);
        else g.drawPolygon(paralelogramo);
    }

    @Override
    public void actualizar(Point puntoActual) {
        this.puntoFinal = new Point(puntoActual);
    }
}
