/*
 * Paquete que contiene las clases de la interfaz de usuario.
 */
package ui;

// Importación de las clases de figuras geométricas del paquete figuras
import figuras.*;
import java.awt.BasicStroke;

// Importaciones para manejo de gráficos, colores, puntos y renderizado en Swing
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Area;
import java.util.IdentityHashMap;
import java.util.Map;

// Importaciones para la captura de eventos del ratón
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

// Importación de estructuras de datos para almacenar el historial de dibujos
import java.util.ArrayList;
import java.util.Objects;

// Importaciones de componentes base de Swing
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 * Clase PanelDeDibujo que extiende de JPanel. Se encarga de actuar como lienzo
 * de dibujo, capturar los eventos del ratón restringidos a los límites del
 * lienzo para crear figuras/trazos y repintar de forma dinámica el contenido.
 *
 * @author Miguel Castillo (Tarea 3.1.1 / Tarea 3.1.5)
 */
public class PanelDeDibujo extends JPanel {

    public enum TipoFigura {
        LINEA, TRAZO, RECTANGULO, OVALO, CIRCULO, ROMBO, PENTAGONO, CORAZON, FLECHA, HEXAGONO, ESTRELLA, TRIANGULO, PARALELOGRAMO
    }

    private ArrayList<Figura> figuras = new ArrayList<>();
    private Figura figuraActual;
    private Figura figuraSeleccionada = null;

    // 0: Sup-Izq, 1: Sup-Der, 2: Inf-Der, 3: Inf-Izq, 4: Mover
    private int nodoArrastrado = -1;
    private Point puntoArrastreAnterior = null;

    private boolean rellenarFigura = false;
    private TipoFigura tipoActual = TipoFigura.TRAZO;
    ArrayList<Figura> figurasRehacer = new ArrayList<>();
    private int grosorActual = 3;
    private float opacidadActual = 1.0f;
    private final Map<Figura, Area> recortesFiguras = new IdentityHashMap<>();
    private java.awt.image.BufferedImage imagenFondo = null;
    private Color colorActual = Color.BLACK;
    private Runnable alCrearFigura;

    public PanelDeDibujo() {
        setBackground(Color.WHITE);

        MouseAdapter manejador = new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                if (!SwingUtilities.isLeftMouseButton(e)) {
                    return;
                }

                Point p = limitarPuntoAlLienzo(e.getPoint());

                // 1. Verificar si se hizo clic en algún nodo o dentro de la figura seleccionada
                if (figuraSeleccionada != null) {
                    nodoArrastrado = detectarNodo(p, figuraSeleccionada.getBounds());
                    if (nodoArrastrado != -1) {
                        puntoArrastreAnterior = p;
                        return;
                    }
                }

                // 2. Si hizo clic fuera, deseleccionamos la figura anterior y creamos una nueva
                figuraSeleccionada = null;
                nodoArrastrado = -1;

                switch (tipoActual) {
                    case LINEA:
                        figuraActual = new Linea(p);
                        break;
                    case RECTANGULO:
                        figuraActual = new Rectangulo(p, rellenarFigura);
                        break;
                    case OVALO:
                        figuraActual = new Ovalo(p, rellenarFigura);
                        break;
                    case CIRCULO:
                        figuraActual = new Circulo(p, rellenarFigura);
                        break;
                    case ROMBO:
                        figuraActual = new Rombo(p, rellenarFigura);
                        break;
                    case PENTAGONO:
                        figuraActual = new Pentagono(p, rellenarFigura);
                        break;
                    case CORAZON:
                        figuraActual = new Corazon(p, rellenarFigura);
                        break;
                    case FLECHA:
                        figuraActual = new Flecha(p, rellenarFigura);
                        break;
                    case TRIANGULO:
                        figuraActual = new Triangulo(p, rellenarFigura);
                        break;
                    case PARALELOGRAMO:
                        figuraActual = new Paralelogramo(p, rellenarFigura);
                        break;
                    case HEXAGONO:
                        figuraActual = new Hexagono(p, rellenarFigura);
                        break;
                    case ESTRELLA:
                        figuraActual = new Estrella(p, rellenarFigura);
                        break;
                    case TRAZO:
                    default:
                        figuraActual = new Trazo(p);
                        break;
                }

                figuraActual.setGrosor(grosorActual);
                figuraActual.setOpacidad(opacidadActual);
                figuraActual.setColor(colorActual);

                figuras.add(figuraActual);
                recortesFiguras.put(figuraActual, new Area(new Rectangle(0, 0, getWidth(), getHeight())));
                figurasRehacer.clear();

                if (alCrearFigura != null) {
                    alCrearFigura.run();
                }

                repaint();
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                Point p = limitarPuntoAlLienzo(e.getPoint());

                if (figuraSeleccionada != null && nodoArrastrado != -1) {
                    redimensionarOMover(p);
                    repaint();
                    return;
                }

                if (figuraActual != null) {
                    figuraActual.actualizar(p);
                    repaint();
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (!SwingUtilities.isLeftMouseButton(e)) {
                    return;
                }

                if (nodoArrastrado != -1) {
                    nodoArrastrado = -1;
                    puntoArrastreAnterior = null;
                    repaint();
                    return;
                }

                if (figuraActual != null) {
                    Point p = limitarPuntoAlLienzo(e.getPoint());
                    figuraActual.actualizar(p);

                    // Deja seleccionada solo la figura geométrica recién dibujada
                    if (figuraActual instanceof FiguraRellenable) {
                        figuraSeleccionada = figuraActual;
                    }
                    figuraActual = null;
                    repaint();
                }
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                if (figuraSeleccionada == null) {
                    setCursor(Cursor.getDefaultCursor());
                    return;
                }
                int nodo = detectarNodo(e.getPoint(), figuraSeleccionada.getBounds());
                switch (nodo) {
                    case 0:
                    case 2:
                        setCursor(Cursor.getPredefinedCursor(Cursor.NW_RESIZE_CURSOR));
                        break;
                    case 1:
                    case 3:
                        setCursor(Cursor.getPredefinedCursor(Cursor.NE_RESIZE_CURSOR));
                        break;
                    case 4:
                        setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
                        break;
                    default:
                        setCursor(Cursor.getDefaultCursor());
                        break;
                }
            }
        };

        addMouseListener(manejador);
        addMouseMotionListener(manejador);
    }

    public void enviarAtras() {
        if (figuraSeleccionada == null) {
            return;
        }
        int indice = figuras.indexOf(figuraSeleccionada);
        // Si es mayor a 0, tiene al menos una figura detrás con la cual intercambiar lugar
        if (indice > 0) {
            java.util.Collections.swap(figuras, indice, indice - 1);
            repaint();
        }
    }

    /**
     * Trae la figura seleccionada una posición hacia adelante en el orden de
     * dibujo (la intercambia con la figura inmediatamente siguiente).
     */
    public void traerAdelante() {
        if (figuraSeleccionada == null) {
            return;
        }
        int indice = figuras.indexOf(figuraSeleccionada);
        // Si es válida y no está ya en la última posición (encima de todas)
        if (indice >= 0 && indice < figuras.size() - 1) {
            java.util.Collections.swap(figuras, indice, indice + 1);
            repaint();
        }
    }

    public void setAlCrearFigura(Runnable alCrearFigura) {
        this.alCrearFigura = alCrearFigura;
    }

    public void deseleccionarFigura() {
        this.figuraSeleccionada = null;
        this.nodoArrastrado = -1;
        setCursor(Cursor.getDefaultCursor());
        repaint();
    }

    private Point limitarPuntoAlLienzo(Point p) {
        int maxX = Math.max(0, getWidth() - 1);
        int maxY = Math.max(0, getHeight() - 1);
        int xLimitada = Math.max(0, Math.min(p.x, maxX));
        int yLimitada = Math.max(0, Math.min(p.y, maxY));
        return new Point(xLimitada, yLimitada);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        if (imagenFondo != null) {
            g2.drawImage(imagenFondo, 0, 0, null);
        }

        g2.clipRect(0, 0, getWidth(), getHeight());
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        for (Figura figura : figuras) {
            Shape clipAnterior = g2.getClip();
            Area areaPermitida = recortesFiguras.get(figura);
            if (areaPermitida != null) {
                g2.clip(areaPermitida);
            }
            figura.dibujar(g2);
            g2.setClip(clipAnterior);
        }

        dibujarSeleccionSiExiste(g2);
    }

    public void dibujarSeleccionSiExiste(Graphics2D g2) {
        if (figuraSeleccionada != null) {
            dibujarMarcoSeleccion(g2, figuraSeleccionada.getBounds());
        }
    }

    private void dibujarMarcoSeleccion(Graphics2D g2, Rectangle r) {
        Graphics2D gSel = (Graphics2D) g2.create();
        float[] dash = {4f, 4f};
        gSel.setColor(new Color(0, 120, 215));
        gSel.setStroke(new BasicStroke(1f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10f, dash, 0f));
        gSel.drawRect(r.x, r.y, r.width, r.height);

        gSel.setStroke(new BasicStroke(1f));
        int s = 7;
        int[][] puntos = {
            {r.x, r.y},
            {r.x + r.width, r.y},
            {r.x + r.width, r.y + r.height},
            {r.x, r.y + r.height}
        };
        for (int[] pt : puntos) {
            gSel.setColor(Color.WHITE);
            gSel.fillRect(pt[0] - s / 2, pt[1] - s / 2, s, s);
            gSel.setColor(new Color(0, 120, 215));
            gSel.drawRect(pt[0] - s / 2, pt[1] - s / 2, s, s);
        }
        gSel.dispose();
    }

    private int detectarNodo(Point p, Rectangle r) {
        int tol = 8;
        if (p.distance(r.x, r.y) <= tol) {
            return 0;
        }
        if (p.distance(r.x + r.width, r.y) <= tol) {
            return 1;
        }
        if (p.distance(r.x + r.width, r.y + r.height) <= tol) {
            return 2;
        }
        if (p.distance(r.x, r.y + r.height) <= tol) {
            return 3;
        }
        if (r.contains(p)) {
            return 4;
        }
        return -1;
    }

    private void redimensionarOMover(Point p) {
        Rectangle r = figuraSeleccionada.getBounds();
        int x1 = r.x;
        int y1 = r.y;
        int x2 = r.x + r.width;
        int y2 = r.y + r.height;

        switch (nodoArrastrado) {
            case 0:
                x1 = p.x;
                y1 = p.y;
                break;
            case 1:
                x2 = p.x;
                y1 = p.y;
                break;
            case 2:
                x2 = p.x;
                y2 = p.y;
                break;
            case 3:
                x1 = p.x;
                y2 = p.y;
                break;
            case 4:
                int dx = p.x - puntoArrastreAnterior.x;
                int dy = p.y - puntoArrastreAnterior.y;
                x1 += dx;
                y1 += dy;
                x2 += dx;
                y2 += dy;
                puntoArrastreAnterior = p;
                break;
        }

        figuraSeleccionada.setLimites(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x2 - x1), Math.abs(y2 - y1));
        recortesFiguras.put(figuraSeleccionada, new Area(new Rectangle(0, 0, getWidth(), getHeight())));
    }

    public void setTipoActual(TipoFigura tipo) {
        tipoActual = Objects.requireNonNull(tipo);
        deseleccionarFigura();
    }

    public void setRellenarFigura(boolean rellenar) {
        this.rellenarFigura = rellenar;
        if (figuraSeleccionada != null) {
            figuraSeleccionada.setRelleno(rellenar);
            repaint();
        }
    }

    public void limpiarLienzo() {
        this.figuras.clear();
        this.figurasRehacer.clear();
        this.recortesFiguras.clear();
        this.figuraActual = null;
        this.figuraSeleccionada = null;
        this.imagenFondo = null;
        this.repaint();
    }

    public void Limpiar() {
        limpiarLienzo();
    }

    public void setGrosorActual(int grosor) {
        this.grosorActual = grosor;
        if (figuraSeleccionada != null) {
            figuraSeleccionada.setGrosor(grosor);
            repaint();
        }
    }

    public void deshacer() {
        if (figuras != null && !figuras.isEmpty()) {
            Figura ultimaFigura = figuras.remove(figuras.size() - 1);
            if (ultimaFigura == figuraSeleccionada) {
                figuraSeleccionada = null;
            }
            figurasRehacer.add(ultimaFigura);
            repaint();
        }
    }

    public void rehacer() {
        if (figurasRehacer != null && !figurasRehacer.isEmpty()) {
            Figura figuraARestaurar = figurasRehacer.remove(figurasRehacer.size() - 1);
            figuras.add(figuraARestaurar);
            figuraSeleccionada = null;
            repaint();
        }
    }

    public void setOpacidad(int opacidad) {
        this.opacidadActual = opacidad / 100.0f;
        if (figuraSeleccionada != null) {
            figuraSeleccionada.setOpacidad(this.opacidadActual);
            repaint();
        }
    }

    public void recortarAlTamano(int nuevoAncho, int nuevoAlto) {
        Area nuevoLimite = new Area(new Rectangle(0, 0, Math.max(0, nuevoAncho), Math.max(0, nuevoAlto)));

        for (Figura figura : figuras) {
            Area areaFigura = recortesFiguras.get(figura);
            if (areaFigura == null) {
                areaFigura = new Area(nuevoLimite);
                recortesFiguras.put(figura, areaFigura);
            } else {
                areaFigura.intersect(nuevoLimite);
            }
        }

        for (Figura figura : figurasRehacer) {
            Area areaFigura = recortesFiguras.get(figura);
            if (areaFigura != null) {
                areaFigura.intersect(nuevoLimite);
            }
        }
    }

    public void setColor(Color color) {
        if (color != null) {
            this.colorActual = color;
            if (figuraSeleccionada != null) {
                figuraSeleccionada.setColor(color);
                repaint();
            }
        }
    }

    public Color getColor() {
        return colorActual;
    }

    public void cargarImagen(java.awt.image.BufferedImage imagen) {
        if (imagen != null) {
            this.imagenFondo = imagen;
            this.repaint();
        }
    }

    public ArrayList<Figura> obtenerCopiaFiguras() {
        return new ArrayList<>(this.figuras);
    }

    public void restaurarFiguras(ArrayList<Figura> figurasGuardadas) {
        this.figuras.clear();
        this.figuras.addAll(figurasGuardadas);
        this.figurasRehacer.clear();
        this.figuraSeleccionada = null;
        this.repaint();
    }

    public java.awt.image.BufferedImage getImagenFondo() {
        return this.imagenFondo;
    }

    public void setImagenFondoDirecto(java.awt.image.BufferedImage img) {
        this.imagenFondo = img;
        this.repaint();
    }
}
