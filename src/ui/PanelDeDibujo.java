/*
 * Paquete que contiene las clases de la interfaz de usuario.
 */
package ui;

// Importación de las clases de figuras geométricas del paquete figuras
import figuras.Figura;
import figuras.Linea;
import figuras.Trazo;
import figuras.Rectangulo;

// Importaciones para manejo de gráficos, colores y renderizado en Swing
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

// Importaciones para la captura de eventos del ratón
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

// Importación de estructuras de datos para almacenar el historial de dibujos
import java.util.ArrayList;

// Importación del componente base JPanel de Swing
import javax.swing.JPanel;

/**
 * Clase PanelDeDibujo que extiende de JPanel.
 * Se encarga de actuar como lienzo de dibujo, capturar los eventos del ratón
 * para crear figuras/trazos y repintar de forma dinámica el contenido.
 * 
 * @author Miguel Castillo (Tarea 3.1.1)
 */
public class PanelDeDibujo extends JPanel {

    /**
     * Enumeración que define los tipos de figuras disponibles para dibujar.
     */
    public enum TipoFigura {
        LINEA,
        TRAZO,
        RECTANGULO
    }

    // Lista dinámica que almacena todas las figuras dibujadas en el lienzo
    private ArrayList<Figura> figuras = new ArrayList<>();
    
    // Referencia a la figura que se está creando o arrastrando actualmente
    private Figura figuraActual;

    // Estado actual de la herramienta seleccionada (por defecto: TRAZO libre)
    private TipoFigura tipoActual = TipoFigura.TRAZO;
    
    // Bandera para determinar si la figura geométrica tendrá relleno
    private boolean rellenarFigura = false;

    /**
     * Constructor del PanelDeDibujo.
     * Configura el fondo del lienzo y registra los oyentes de eventos del ratón.
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
                
                // Según el tipo de figura seleccionado, instanciamos el objeto correspondiente
                switch (tipoActual) {
                    
                    case LINEA:
                        
                        // Instancia una nueva Línea iniciando en el punto del clic
                        figuraActual = new Linea(e.getPoint());
                        break;
                    
                    case RECTANGULO:
                        
                        // Instancia un nuevo Rectángulo iniciando en el punto del clic y su opción de relleno
                        figuraActual = new Rectangulo(e.getPoint(), rellenarFigura);
                        break;
                    
                    case TRAZO:
                    
                    default:
                        
                        // Instancia un nuevo Trazo libre
                        figuraActual = new Trazo();
                        break;
                }
                
                // Agregamos la nueva figura a la lista dinámica del lienzo
                figuras.add(figuraActual);
                
                // Solicitamos a Swing que repinte el componente
                repaint();
            }

            /**
             * Se ejecuta mientras el usuario arrastra el ratón con el botón presionado.
             */
            @Override
            public void mouseDragged(MouseEvent e) {
                
                // Si existe una figura en creación, actualizamos sus coordenadas con la posición actual del ratón
                if (figuraActual != null) {
                    
                    figuraActual.actualizar(e.getPoint());
                    // Solicitamos el redibujado inmediato para mostrar la figura en tiempo real
                    repaint();
                }
            }
        };

        // Registramos el manejador para los eventos del ratón (clics)
        addMouseListener(manejador);
        
        // Registramos el manejador para los movimientos del ratón con arrastre
        addMouseMotionListener(manejador);
    }

    /**
     * Método sobreescrito de JPanel encargada de renderizar el contenido del lienzo.
     * 
     * @param g Objeto Graphics proporcionado por el sistema para dibujar.
     */
    @Override
    protected void paintComponent(Graphics g) {
        
        // Llama a la implementación base para limpiar el fondo del panel adecuadamente
        super.paintComponent(g);

        // Convertimos Graphics a Graphics2D para habilitar opciones avanzadas de renderizado
        Graphics2D g2 = (Graphics2D) g;

        // Activamos el suavizado de bordes (Antialiasing) para mejorar la calidad visual de los trazos
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Iteramos sobre la lista dinámica y dibujamos cada figura almacenada
        for (Figura figura : figuras) {
            figura.dibujar(g2);
        }
    }

    /**
     * Establece el tipo de figura/herramienta que se creará con el ratón.
     * 
     * @param tipo TipoFigura seleccionado.
     */
    public void setTipoActual(TipoFigura tipo) {
        this.tipoActual = tipo;
    }

    /**
     * Define si las figuras a dibujar tendrán relleno sólido.
     * 
     * @param rellenar boolean true para rellenar, false para solo borde.
     */
    public void setRellenarFigura(boolean rellenar) {
        this.rellenarFigura = rellenar;
    }

    /**
     * Limpia la lista dinámica de figuras y refresca el lienzo.
     */
    public void Limpiar() {
        figuras.clear();
        repaint();
    }
}