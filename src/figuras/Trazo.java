
/*
 * Clase: Trazo
 * Descripción: Esta clase permite crear y dibujar trazos
 * mediante una serie de puntos conectados entre sí.
 */

package figuras;

// Importación de las clases necesarias.
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.awt.BasicStroke;

/**
 * Clase Trazo.
 *
 * Hereda de la clase Figura y permite representar
 * un dibujo formado por varias líneas consecutivas.
 */
public class Trazo extends Figura {

    // Lista que almacena todos los puntos que forman el trazo.
    ArrayList<Point> puntos = new ArrayList<>();
    // private final ArrayList<Point> puntos = new ArrayList<>();
    
    public Trazo() {
        super();
    }

    public Trazo(Point puntoInicial) {
        super(puntoInicial, false);
        puntos.add(new Point(puntoInicial));
    }
    
    /**
     * Método dibujar.
     * Se encarga de unir los puntos almacenados mediante líneas
     * para representar el trazo en la pantalla.
     *
     * @param g Objeto Graphics utilizado para dibujar.
     */
    /*
    @Override
    public void dibujar(Graphics g) {

        // Recorre la lista de puntos comenzando desde el segundo.
        for (int i = 1; i < puntos.size(); i++) {

            // Une el punto anterior con el punto actual
            // creando una línea entre ambos.
            new Linea(puntos.get(i - 1), puntos.get(i)).dibujar(g);
        }
    }
    */
    @Override
    public void dibujar(Graphics g) {
        Graphics2D copia = prepararGraficos(g);
        copia.setStroke(new BasicStroke(grosor, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        
        try {
            for (int i = 1; i < puntos.size(); i++) {
                //Point anterior = puntos.get(i - 1);
                //Point actual = puntos.get(i);
                //copia.drawLine(anterior.x, anterior.y, actual.x, actual.y);
                Linea linea = new Linea(puntos.get(i - 1), puntos.get(i));
                linea.setGrosor(this.grosor); 
                linea.dibujar(copia);
            }
        } finally {
            copia.dispose();
        }
    }    
    
    /**
     * Método actualizar.
     * Agrega un nuevo punto a la lista cada vez que se actualiza
     * la posición del trazo.
     *
     * @param puntoActual Punto que se va a agregar al trazo.
     */
    /*
    @Override
    public void actualizar(Point puntoActual) {
        
        // Guarda el nuevo punto en la lista.
        puntos.add(puntoActual);
    }
    */
    
    @Override
    public void actualizar(Point puntoActual) {
        super.actualizar(puntoActual);
        if (puntos.isEmpty()) {
            puntoInicial = new Point(puntoActual);
        }
        puntos.add(new Point(puntoActual));
    }
}
