/*
 * Paquete que contiene las clases de la interfaz de usuario.
 */
package ui;

// Importación de las clases de figuras geométricas del paquete figuras
import figuras.*;

// Importaciones para manejo de gráficos, colores, puntos y renderizado en Swing
import java.awt.Color;
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

    /**
     * Enumeración que define los tipos de figuras disponibles para dibujar.
     */
    public enum TipoFigura {
        LINEA, TRAZO, RECTANGULO, OVALO, CIRCULO, ROMBO, PENTAGONO, CORAZON, FLECHA, HEXAGONO, ESTRELLA, TRIANGULO, PARALELOGRAMO
    }

    // Lista dinámica que almacena todas las figuras dibujadas en el lienzo
    private ArrayList<Figura> figuras = new ArrayList<>();

    // Referencia a la figura que se está creando o arrastrando actualmente
    private Figura figuraActual;

    // Bandera para determinar si la figura geométrica tendrá relleno
    private boolean rellenarFigura = false;

    // Estado actual de la herramienta seleccionada (por defecto: TRAZO libre)
    private TipoFigura tipoActual = TipoFigura.TRAZO;

    // Lista dinámica para almacenar las figuras deshechas y permitir rehacerlas
    ArrayList<Figura> figurasRehacer = new ArrayList<>();

    // Grosor de línea actual del trazo o contorno
    private int grosorActual = 3;

    // Nivel de opacidad actual (1.0f = 100% opaco)
    private float opacidadActual = 1.0f;

    // Mapa que almacena el área máxima permitida (recorte destructivo) para cada figura dibujada
    private final Map<Figura, Area> recortesFiguras = new IdentityHashMap<>();

    /**
     * Constructor del PanelDeDibujo. Configura el fondo del lienzo y registra
     * los oyentes de eventos del ratón.
     */
    public PanelDeDibujo() {

        // Establece el color de fondo inicial del lienzo
        setBackground(Color.WHITE);

        // Creación del adaptador para escuchar eventos de clic y arrastre del ratón
        MouseAdapter manejador = new MouseAdapter() {

            /**
             * Se ejecuta al presionar un botón del ratón sobre el panel.
             */
            @Override
            public void mousePressed(MouseEvent e) {

                // Ignoramos cualquier clic que no sea el botón izquierdo del ratón
                if (!SwingUtilities.isLeftMouseButton(e)) {
                    return;
                }

                // Restringimos el punto inicial para asegurar que esté dentro de los límites del lienzo
                Point puntoInicio = limitarPuntoAlLienzo(e.getPoint());

                // Según el tipo de figura seleccionado, instanciamos el objeto correspondiente
                switch (tipoActual) {
                    case LINEA:
                        figuraActual = new Linea(puntoInicio);
                        break;
                    case RECTANGULO:
                        figuraActual = new Rectangulo(puntoInicio, rellenarFigura);
                        break;
                    case OVALO:
                        figuraActual = new Ovalo(puntoInicio, rellenarFigura);
                        break;
                    case CIRCULO:
                        figuraActual = new Circulo(puntoInicio, rellenarFigura);
                        break;
                    case ROMBO:
                        figuraActual = new Rombo(puntoInicio, rellenarFigura);
                        break;
                    case PENTAGONO:
                        figuraActual = new Pentagono(puntoInicio, rellenarFigura);
                        break;
                    case CORAZON:
                        figuraActual = new Corazon(puntoInicio, rellenarFigura);
                        break;
                    case FLECHA:
                        figuraActual = new Flecha(puntoInicio, rellenarFigura);
                        break;
                    case TRIANGULO:
                        figuraActual = new Triangulo(puntoInicio, rellenarFigura);
                        break;
                    case PARALELOGRAMO:
                        figuraActual = new Paralelogramo(puntoInicio, rellenarFigura);
                        break;
                    case HEXAGONO:
                        figuraActual = new Hexagono(puntoInicio, rellenarFigura);
                        break;
                    case ESTRELLA:
                        figuraActual = new Estrella(puntoInicio, rellenarFigura);
                        break;
                    case TRAZO:
                    default:
                        figuraActual = new Trazo(puntoInicio);
                        break;
                }

                // Aplicamos el grosor y la opacidad configurados actualmente
                figuraActual.setGrosor(grosorActual);
                figuraActual.setOpacidad(opacidadActual);
                figuraActual.setColor(colorActual);   // ← aplica el color elegido
                // Agregamos la nueva figura al historial (una sola vez) y limpiamos la pila de rehacer
                figuras.add(figuraActual);
                // Agregamos la nueva figura al historial (una sola vez) y limpiamos la pila de rehacer
                figuras.add(figuraActual);
                // Asociamos a esta figura los límites actuales del lienzo
                recortesFiguras.put(figuraActual, new Area(new Rectangle(0, 0, getWidth(), getHeight())));
                figurasRehacer.clear();

                // Solicitamos a Swing que repinte el componente
                repaint();
            }

            /**
             * Se ejecuta mientras el usuario arrastra el ratón con el botón
             * presionado.
             */
            @Override
            public void mouseDragged(MouseEvent e) {

                // Si existe una figura en creación, actualizamos sus coordenadas dentro de los límites del lienzo
                if (figuraActual != null) {
                    Point puntoActual = limitarPuntoAlLienzo(e.getPoint());
                    figuraActual.actualizar(puntoActual);
                    // Solicitamos el redibujado inmediato para mostrar la figura en tiempo real
                    repaint();
                }
            }

            /**
             * Se ejecuta al soltar el botón del ratón para finalizar la figura
             * actual.
             */
            @Override
            public void mouseReleased(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e) && figuraActual != null) {
                    Point puntoFinal = limitarPuntoAlLienzo(e.getPoint());
                    figuraActual.actualizar(puntoFinal);
                    figuraActual = null;
                    repaint();
                }
            }
        };

        // Registramos el manejador para los clics y el arrastre del ratón
        addMouseListener(manejador);
        addMouseMotionListener(manejador);
    }

    /**
     * Restringe (hace clamping) las coordenadas de un punto para que nunca
     * excedan los bordes actuales del lienzo [0 .. ancho-1] y [0 .. alto-1].
     * Evita que al arrastrar el ratón fuera del área blanca se dibujen trazos o
     * figuras fuera del lienzo.
     *
     * @param p Punto original reportado por el evento del ratón.
     * @return Nuevo objeto Point con sus coordenadas X e Y ajustadas dentro del
     * lienzo.
     */
    private Point limitarPuntoAlLienzo(Point p) {
        // Calculamos los límites máximos válidos según el tamaño actual del panel
        int maxX = Math.max(0, getWidth() - 1);
        int maxY = Math.max(0, getHeight() - 1);

        // Restringimos X e Y entre 0 y el límite máximo
        int xLimitada = Math.max(0, Math.min(p.x, maxX));
        int yLimitada = Math.max(0, Math.min(p.y, maxY));

        return new Point(xLimitada, yLimitada);
    }

    /**
     * Método sobreescrito de JPanel encargado de renderizar el contenido del
     * lienzo.
     *
     * @param g Objeto Graphics proporcionado por el sistema para dibujar.
     */
    @Override
    protected void paintComponent(Graphics g) {

        // Llama a la implementación base para limpiar el fondo del panel adecuadamente
        super.paintComponent(g);

        // Convertimos Graphics a Graphics2D para habilitar opciones avanzadas de renderizado
        Graphics2D g2 = (Graphics2D) g;

        // Definimos el área de recorte (clip) exactamente al tamaño actual del lienzo
        // Esto garantiza que ningún trazo o figura se pinte fuera de los bordes visibles
        g2.clipRect(0, 0, getWidth(), getHeight());

        // Activamos el suavizado de bordes (Antialiasing) para mejorar la calidad visual de los trazos
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Iteramos sobre la lista dinámica y dibujamos cada figura aplicando su recorte destructivo
        for (Figura figura : figuras) {
            // Guardamos el recorte gráfico global actual del lienzo
            Shape clipAnterior = g2.getClip();

            // Si la figura tiene un área de recorte registrada, intersectamos el clip con dicha área
            Area areaPermitida = recortesFiguras.get(figura);
            if (areaPermitida != null) {
                g2.clip(areaPermitida);
            }

            // Dibujamos la figura (solo se pintará dentro de su área no recortada)
            figura.dibujar(g2);

            // Restauramos el clip global del lienzo para la siguiente figura
            g2.setClip(clipAnterior);
        }
    }

    /**
     * Establece el tipo de figura/herramienta que se creará con el ratón.
     *
     * @param tipo TipoFigura seleccionado.
     */
    public void setTipoActual(TipoFigura tipo) {
        tipoActual = java.util.Objects.requireNonNull(tipo);
    }

    /**
     * Define si las figuras a dibujar tendrán relleno sólido.
     *
     * @param rellenar boolean true para rellenar, false para solo borde.
     */
    public void setRellenarFigura(boolean rellenar) {
        rellenarFigura = rellenar;
    }

    /**
     * Limpia completamente el lienzo de dibujo. Vacía el historial de figuras
     * almacenadas, reinicia la figura en progreso y solicita el repintado de la
     * interfaz gráfica.
     */
    public void limpiarLienzo() {

        // Vacía la lista dinámica que contiene todas las figuras y trazos dibujados
        this.figuras.clear();

        // Vacía también el historial de rehacer
        this.figurasRehacer.clear();

        // Vacía el Mapa que almacena el área máxima permitida (recorte destructivo) para cada figura dibujada
        this.recortesFiguras.clear();

        // Reinicia la referencia de la figura actual para evitar incongruencias
        this.figuraActual = null;

        // Invalida la vista actual y solicita a Swing la ejecución de paintComponent
        this.repaint();
    }

    /**
     * Método de compatibilidad para mantener soporte con las llamadas
     * existentes. Redirige la ejecución hacia limpiarLienzo().
     */
    public void Limpiar() {
        limpiarLienzo();
    }

    /**
     * Establece el grosor del trazo o contorno de las próximas figuras.
     *
     * @param grosor Valor numérico del grosor.
     */
    public void setGrosorActual(int grosor) {
        this.grosorActual = grosor;
    }

    /**
     * Deshace la última figura dibujada en el lienzo.
     */
    public void deshacer() {
        if (figuras != null && !figuras.isEmpty()) {
            Figura ultimaFigura = figuras.remove(figuras.size() - 1);
            figurasRehacer.add(ultimaFigura);
            repaint();
        }
    }

    /**
     * Restaura la última figura que fue deshecha.
     */
    public void rehacer() {
        if (figurasRehacer != null && !figurasRehacer.isEmpty()) {
            Figura figuraARestaurar = figurasRehacer.remove(figurasRehacer.size() - 1);
            figuras.add(figuraARestaurar);
            repaint();
        }
    }

    /**
     * Establece la opacidad porcentual (0 a 100) para las nuevas figuras.
     *
     * @param opacidad Entero entre 0 y 100.
     */
    public void setOpacidad(int opacidad) {
        this.opacidadActual = opacidad / 100.0f;
    }

    /**
     * Aplica un recorte destructivo (estilo MS Paint clásico) sobre todas las
     * figuras existentes cuando el lienzo cambia de tamaño. Todo lo que quede
     * fuera de las nuevas dimensiones se elimina permanentemente y no reaparece
     * al volver a expandir.
     *
     * @param nuevoAncho Nuevo ancho del lienzo en píxeles.
     * @param nuevoAlto Nuevo alto del lienzo en píxeles.
     */
    public void recortarAlTamano(int nuevoAncho, int nuevoAlto) {
        // Creamos el rectángulo que representa los nuevos límites válidos del lienzo
        Area nuevoLimite = new Area(new Rectangle(0, 0, Math.max(0, nuevoAncho), Math.max(0, nuevoAlto)));

        // Intersectamos el área de cada figura existente con el nuevo límite del lienzo
        for (Figura figura : figuras) {
            Area areaFigura = recortesFiguras.get(figura);
            if (areaFigura == null) {
                areaFigura = new Area(nuevoLimite);
                recortesFiguras.put(figura, areaFigura);
            } else {
                // La intersección recorta de forma permanente cualquier parte fuera del nuevo tamaño
                areaFigura.intersect(nuevoLimite);
            }
        }

        // Aplicamos también el recorte a las figuras en la pila de rehacer por coherencia
        for (Figura figura : figurasRehacer) {
            Area areaFigura = recortesFiguras.get(figura);
            if (areaFigura != null) {
                areaFigura.intersect(nuevoLimite);
            }
        }
    }
    // --- Nuevo campo ---
    private Color colorActual = Color.BLACK;

// --- Nuevo método público ---
    public void setColor(Color color) {
        if (color != null) {
            this.colorActual = color;
        }
    }

    public Color getColor() {
        return colorActual;
    }
}
