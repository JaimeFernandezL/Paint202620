/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package figuras;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.Objects;

/**
 *
 * @author josearielpereyra
 */
public abstract class Figura {
    protected Point puntoInicial;
    protected Point puntoFinal;
    protected boolean relleno;

    protected Figura() {
        this(new Point(), false);
    }

    protected Figura(Point puntoInicial, boolean relleno) {
        this.puntoInicial = new Point(Objects.requireNonNull(puntoInicial));
        this.puntoFinal = new Point(puntoInicial);
        this.relleno = relleno;
    }

    // Cada figura utiliza una copia independiente del contexto grafico.
    protected Graphics2D prepararGraficos(Graphics g) {
        return (Graphics2D) g.create();
    }

    public abstract void dibujar(Graphics g);

    public void actualizar(Point puntoActual) {
        puntoFinal = new Point(Objects.requireNonNull(puntoActual));
    }
}
