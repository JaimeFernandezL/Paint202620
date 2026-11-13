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
import figuras.Triangulo;
import figuras.Paralelogramo;
import figuras.*;
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
import javax.swing.SwingUtilities;

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
        LINEA, TRAZO, RECTANGULO, OVALO, CIRCULO, ROMBO, PENTAGONO, CORAZON, FLECHA, HEXAGONO, ESTRELLA, TRIANGULO,PARALELOGRAMO
    }

    // Lista dinámica que almacena todas las figuras dibujadas en el lienzo
    private ArrayList<Figura> figuras = new ArrayList<>();
    
    // Referencia a la figura que se está creando o arrastrando actualmente
    private Figura figuraActual;
    
    // Bandera para determinar si la figura geométrica tendrá relleno
    private boolean rellenarFigura = false;

    // Estado actual de la herramienta seleccionada (por defecto: TRAZO libre)
    private TipoFigura tipoActual = TipoFigura.TRAZO;
    
    ArrayList<Figura> figurasRehacer = new ArrayList<>();

    private int grosorActual = 3;
    
    private float opacidadActual = 1.0f;
    
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
                
                if (!SwingUtilities.isLeftMouseButton(e)) {
                    return;
                }
                // Según el tipo de figura seleccionado, instanciamos el objeto correspondiente
                switch (tipoActual) {
                    case LINEA:
                        figuraActual = new Linea(e.getPoint());
                        break;
                    case RECTANGULO:
                        figuraActual = new Rectangulo(e.getPoint(), rellenarFigura);
                        break;
                    case OVALO:
                        figuraActual = new Ovalo(e.getPoint(), rellenarFigura);
                        break;
                    case CIRCULO:
                        figuraActual = new Circulo(e.getPoint(), rellenarFigura);
                        break;
                    case ROMBO:
                        figuraActual = new Rombo(e.getPoint(), rellenarFigura);
                        break;
                    case PENTAGONO:
                        figuraActual = new Pentagono(e.getPoint(), rellenarFigura);
                        break;
                    case CORAZON:
                        figuraActual = new Corazon(e.getPoint(), rellenarFigura);
                        break;
                    case FLECHA:
                        figuraActual = new Flecha(e.getPoint(), rellenarFigura);
                         break; 
                    case TRIANGULO:
                        figuraActual = new Triangulo(e.getPoint(), rellenarFigura);
                         break;                           
                    case PARALELOGRAMO:
                         figuraActual = new Paralelogramo(e.getPoint(), rellenarFigura);
                         break;
                    case HEXAGONO:
                        figuraActual = new Hexagono(e.getPoint(), rellenarFigura);
                        break;
                    case ESTRELLA:
                         figuraActual = new Estrella(e.getPoint(), rellenarFigura);
                         break;
                    case TRAZO:
                        figuraActual = new Trazo(e.getPoint());
                        break;
                    default:
                        figuraActual = new Trazo(e.getPoint());
                        break;
                }
                
                figuraActual.setGrosor(grosorActual);
                figuraActual.setOpacidad(opacidadActual);
                figuras.add(figuraActual);
                figurasRehacer.clear();
                
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
            
            @Override
            public void mouseReleased(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e) && figuraActual != null) {
                    figuraActual.actualizar(e.getPoint());
                    figuraActual = null;
                    repaint();
                }
            }
        };

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
     * Limpia completamente el lienzo de dibujo.
     * Vacía el historial de figuras almacenadas, reinicia la figura en progreso
     * y solicita el repintado de la interfaz gráfica.
     */
    public void limpiarLienzo() {
        
        // Vacía la lista dinámica que contiene todas las figuras y trazos dibujados
        this.figuras.clear();
        
        this.figurasRehacer.clear();
        
        // Reinicia la referencia de la figura actual para evitar incongruencias
        this.figuraActual = null;
        
        // Invalida la vista actual y solicita a Swing la ejecución de paintComponent
        this.repaint();
    }

    /**
     * Método de compatibilidad para mantener soporte con las llamadas existentes.
     * Redirige la ejecución hacia limpiarLienzo().
     */
    public void Limpiar() {
        limpiarLienzo();
    }
    
    public void setGrosorActual(int grosor) {
        this.grosorActual = grosor;
    }
    
    public void deshacer() {
        if (figuras != null && !figuras.isEmpty()) {
            //figuras.remove(figuras.size()-1);
            Figura ultimaFigura = figuras.remove(figuras.size() - 1);
            figurasRehacer.add(ultimaFigura);
            repaint();
        }
    }

    public void rehacer() {
        if (figurasRehacer != null && !figurasRehacer.isEmpty()) {
            Figura figuraARestaurar = figurasRehacer.remove(figurasRehacer.size() - 1);
            figuras.add(figuraARestaurar);
            repaint();
        }
    }
    
    public void setOpacidad(int opacidad) {
        this.opacidadActual = opacidad / 100.0f;
    }

}


