package herramientaborrador;


import figuras.Figura;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Stroke;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractButton;
import javax.swing.JComponent;
import ui.PanelDeDibujo;

/**
 * Clase Borrador.
 * Se encarga de cubrir lo dibujado en el lienzo ordenado por capas,
 * con grosor ajustable y capturar los eventos del mouse para las marcas
 * de borrado.
 * 
 * @author Winiffer Hernandez (Tarea 3.1.3)
 */


public class Borrador {

    // Un borrado = lo que se borra desde que presiono hasta que suelto el mouse
    class Marca {
        ArrayList<Point> puntos = new ArrayList<>();
        int grosor;
        int capa; // cuantas figuras habia cuando se borro
    }

    PanelDeDibujo panel;
    Field campoFiguras;
    Capa capa = new Capa();
    ArrayList<Marca> marcas = new ArrayList<>();
    Marca actual;
    Point posicion;
    boolean activo = false;
    int grosor = 10;
   

    public Borrador(PanelDeDibujo panel) {
        this.panel = panel;

        // La lista "figuras" del panel no es publica, por eso se usa reflexion
        try {
            campoFiguras = PanelDeDibujo.class.getDeclaredField("figuras");
            campoFiguras.setAccessible(true);
        } catch (Exception ex) {
            throw new RuntimeException("No se encontro la lista figuras en PanelDeDibujo");
        }

        // Pongo la capa encima del panel, del mismo tamaño
        panel.setLayout(null);
        panel.add(capa);
        capa.setBounds(0, 0, panel.getWidth(), panel.getHeight());

        // Si cambia el tamaño de la ventana, la capa tambien cambia
        panel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                capa.setBounds(0, 0, panel.getWidth(), panel.getHeight());
            }
        });

        // Eventos del mouse (solo funcionan cuando el borrador esta activo)
        MouseAdapter raton = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                posicion = e.getPoint();
                actual = new Marca();
                actual.grosor = grosor;
                actual.capa = obtenerFiguras().size();
                actual.puntos.add(e.getPoint());
                marcas.add(actual);
                capa.repaint();
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                posicion = e.getPoint();
                if (actual != null) {
                    actual.puntos.add(e.getPoint());
                }
                capa.repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                actual = null;
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                posicion = e.getPoint();
                capa.repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                posicion = null;
                capa.repaint();
            }
        };
        capa.addMouseListener(raton);
        capa.addMouseMotionListener(raton);
    }

    // Devuelve la lista de figuras que tiene el panel
    List<Figura> obtenerFiguras() {
        try {
            return (List<Figura>) campoFiguras.get(panel);
        } catch (Exception ex) {
            return new ArrayList<>();
        }
    }

    // Activa el borrador cuando el boton esta seleccionado
    public void vincularBoton(AbstractButton boton) {
        boton.addItemListener(e -> {
            if (boton.isSelected()) {
                activar();
            } else {
                desactivar();
            }
        });
    }

    public void vincularLimpiar(AbstractButton botonLimpiar) {
        botonLimpiar.addActionListener(e -> limpiar());
    }

    public void activar() {
        activo = true; 
        capa.repaint();
    }

    public void desactivar() {
        activo = false;
        actual = null;
        capa.setCursor(Cursor.getDefaultCursor());
        capa.repaint();
    }

    public void limpiar() {
        marcas.clear();
        actual = null;
        capa.repaint();
    }

    // Para cambiar el tamaño del borrador
    public void setGrosor(int grosor) {
        this.grosor = grosor;
    }

    // Capa transparente que va encima del panel
    class Capa extends JComponent {

        // Si el borrador no esta activo, el mouse pasa al panel
        @Override
        public boolean contains(int x, int y) {
            return activo && super.contains(x, y);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;

            // Si hay borrados, se vuelve a pintar todo en orden
            if (!marcas.isEmpty()) {
                pintarTodo(g2);
            }

            // Icono del borrador (no sale al guardar la imagen)
            if (activo && posicion != null && !isPaintingForPrint()) {
                pintarIcono(g2);
            }
        }

        void pintarTodo(Graphics2D g2) {
            List<Figura> figuras = obtenerFiguras();
            int total = figuras.size();

            // Fondo
            Color colorAntes = g2.getColor();
            g2.setColor(panel.getBackground());
            g2.fillRect(0, 0, getWidth(), getHeight());
            g2.setColor(colorAntes);

            // Si se borraron figuras (por ejemplo con Limpiar), se ajusta la capa
            for (Marca m : marcas) {
                if (m.capa > total) {
                    m.capa = total;
                }
            }

            // Orden: borrados de la capa i, luego la figura i.
            // Asi lo borrado tapa lo viejo y lo nuevo se dibuja encima.
            for (int i = 0; i <= total; i++) {
                for (Marca m : marcas) {
                    if (m.capa == i) {
                        pintarMarca(g2, m);
                    }
                }
                if (i < total) {
                    figuras.get(i).dibujar(g2);
                }
            }
        }

        // Dibuja un borrado con el color del fondo
        void pintarMarca(Graphics2D g2, Marca m) {
            Color colorAntes = g2.getColor();
            Stroke strokeAntes = g2.getStroke();

            g2.setColor(panel.getBackground());
            g2.setStroke(new BasicStroke(m.grosor, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER));

            // un solo punto (clic sin arrastrar)
            if (m.puntos.size() == 1) {
                Point p = m.puntos.get(0);
                g2.drawLine(p.x, p.y, p.x, p.y);
            }
            // une cada punto con el siguiente para que no queden huecos
            for (int i = 1; i < m.puntos.size(); i++) {
                Point a = m.puntos.get(i - 1);
                Point b = m.puntos.get(i);
                g2.drawLine(a.x, a.y, b.x, b.y);
            }

            g2.setColor(colorAntes);
            g2.setStroke(strokeAntes);
        }

       
        void pintarIcono(Graphics2D g2) {
            Color colorAntes = g2.getColor();
            Stroke strokeAntes = g2.getStroke();
            
            g2.setStroke(new BasicStroke(1));
            g2.setColor(Color.BLACK);
            g2.drawRect(posicion.x, posicion.y, 10, 10);
            
//            Codigo para icono de borrador
//            int r = grosor / 2;
//            int x = posicion.x + r;
//            int y = posicion.y - r - 14;
//            g2.setColor(new Color(240, 140, 150));
//            g2.fillRect(x, y, 20, 12);
//            g2.setColor(new Color(90, 130, 200));
//            g2.fillRect(x, y, 7, 12);
//            g2.setColor(Color.DARK_GRAY);
//            g2.drawRect(x, y, 20, 12);


            g2.setColor(colorAntes);
            g2.setStroke(strokeAntes);
        }
    }
}









