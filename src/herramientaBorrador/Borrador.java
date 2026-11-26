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
import java.awt.Shape;
import java.awt.geom.Area;
import java.util.Map;

/**
 * Clase Borrador. Se encarga de cubrir lo dibujado en el lienzo ordenado por
 * capas, con grosor ajustable y capturar los eventos del mouse para las marcas
 * de borrado.
 *
 * @author Winiffer Hernandez (Tarea 3.1.3)
 */
public class Borrador {

    class Marca {
        ArrayList<Point> puntos = new ArrayList<>();
        int grosor;
        int capa;
    }

    PanelDeDibujo panel;
    Field campoFiguras;
    Field campoRecortes;
    Capa capa = new Capa();
    ArrayList<Marca> marcas = new ArrayList<>();
    ArrayList<Marca> marcasRehacer = new ArrayList<>();
    Marca actual;
    Point posicion;
    boolean activo = false;
    int grosor = 10;
    private Runnable alBorrar;

    public Borrador(PanelDeDibujo panel) {
        this.panel = panel;

        try {
            campoFiguras = PanelDeDibujo.class.getDeclaredField("figuras");
            campoFiguras.setAccessible(true);

            campoRecortes = PanelDeDibujo.class.getDeclaredField("recortesFiguras");
            campoRecortes.setAccessible(true);
        } catch (Exception ex) {
            throw new RuntimeException("No se encontro la lista figuras en PanelDeDibujo");
        }

        panel.setLayout(null);
        panel.add(capa);
        capa.setBounds(0, 0, panel.getWidth(), panel.getHeight());

        panel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                capa.setBounds(0, 0, panel.getWidth(), panel.getHeight());
            }
        });

        MouseAdapter raton = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                posicion = e.getPoint();
                actual = new Marca();
                actual.grosor = grosor;
                actual.capa = obtenerFiguras().size();
                actual.puntos.add(e.getPoint());
                marcas.add(actual);
                marcasRehacer.clear();
                if (alBorrar != null) {
                    alBorrar.run();
                }
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

    public void setAlBorrar(Runnable alBorrar) {
        this.alBorrar = alBorrar;
    }

    public void repintar() {
        capa.repaint();
    }

    public boolean deshacerMarca() {
        if (!marcas.isEmpty()) {
            marcasRehacer.add(marcas.remove(marcas.size() - 1));
            capa.repaint();
            return true;
        }
        return false;
    }

    public boolean rehacerMarca() {
        if (!marcasRehacer.isEmpty()) {
            marcas.add(marcasRehacer.remove(marcasRehacer.size() - 1));
            capa.repaint();
            return true;
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    List<Figura> obtenerFiguras() {
        try {
            return (List<Figura>) campoFiguras.get(panel);
        } catch (Exception ex) {
            return new ArrayList<>();
        }
    }

    @SuppressWarnings("unchecked")
    Map<Figura, Area> obtenerRecortes() {
        try {
            return (Map<Figura, Area>) campoRecortes.get(panel);
        } catch (Exception ex) {
            return new java.util.HashMap<>();
        }
    }

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
        panel.deseleccionarFigura();
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
        marcasRehacer.clear();
        actual = null;
        capa.repaint();
    }

    public void setGrosor(int grosor) {
        this.grosor = grosor;
    }

    class Capa extends JComponent {

        @Override
        public boolean contains(int x, int y) {
            return activo && super.contains(x, y);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;

            if (!marcas.isEmpty()) {
                pintarTodo(g2);
            }

            if (activo && posicion != null && !isPaintingForPrint()) {
                pintarIcono(g2);
            }
        }

        void pintarTodo(Graphics2D g2) {
            List<Figura> figuras = obtenerFiguras();
            Map<Figura, Area> recortes = obtenerRecortes();
            int total = figuras.size();

            Color colorAntes = g2.getColor();
            g2.setColor(panel.getBackground());
            g2.fillRect(0, 0, getWidth(), getHeight());
            g2.setColor(colorAntes);

            if (panel.getImagenFondo() != null) {
                g2.drawImage(panel.getImagenFondo(), 0, 0, null);
            }

            for (int i = 0; i <= total; i++) {
                for (Marca m : marcas) {
                    if (m.capa == i) {
                        pintarMarca(g2, m);
                    }
                }
                if (i < total) {
                    Figura f = figuras.get(i);
                    Shape clipAnterior = g2.getClip();
                    Area permitida = recortes.get(f);
                    if (permitida != null) {
                        g2.clip(permitida);
                    }
                    f.dibujar(g2);
                    g2.setClip(clipAnterior);
                }
            }

            panel.dibujarSeleccionSiExiste(g2);
        }

        void pintarMarca(Graphics2D g2, Marca m) {
            Color colorAntes = g2.getColor();
            Stroke strokeAntes = g2.getStroke();

            g2.setColor(panel.getBackground());
            g2.setStroke(new BasicStroke(m.grosor, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER));

            if (m.puntos.size() == 1) {
                Point p = m.puntos.get(0);
                g2.drawLine(p.x, p.y, p.x, p.y);
            }
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

            g2.setColor(colorAntes);
            g2.setStroke(strokeAntes);
        }
    }
}