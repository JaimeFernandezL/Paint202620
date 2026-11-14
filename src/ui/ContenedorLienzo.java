/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;

// Importación de gestores de diseño (Layouts) para organizar el lienzo, las asas y el área gris
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

// Importaciones para colores, cursores de redimensionamiento, dimensiones y coordenadas
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Point;

// Importaciones para capturar los eventos de clic y arrastre sobre las asas del lienzo
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

// Importaciones de componentes visuales de Swing
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 * Envuelve el lienzo de dibujo al estilo MS Paint: el lienzo mantiene un tamaño fijo
 * dentro de un área gris con barras de desplazamiento (scroll).
 * Agrandar la ventana principal NO estira el lienzo automáticamente; este se redimensiona
 * únicamente al arrastrar sus tres asas (derecha, abajo y esquina inferior derecha).
 *
 * @author Juana (Equipo 3) / Integración y documentación: Miguel Castillo
 */
public class ContenedorLienzo extends JScrollPane {

    // Dimensiones iniciales predeterminadas al abrir la aplicación (800x450 píxeles)
    public static final int ANCHO_INICIAL = 800;
    public static final int ALTO_INICIAL = 450;

    // Límites mínimo y máximo permitidos para evitar tamaños inválidos o desbordamiento de memoria
    private static final int MINIMO = 100;
    private static final int MAXIMO = 4000;

    // Tamaño en píxeles de cada cuadrito blanco que sirve como asa de arrastre (7x7 px)
    private static final int ASA = 7;

    // Referencia al componente interno que actúa como lienzo de dibujo (PanelDeDibujo)
    private final JComponent lienzo;

    // Panel contenedor que agrupa el lienzo en el centro y las asas en los bordes derecho e inferior
    private final JPanel marco = new JPanel(new BorderLayout());

    /**
     * Constructor del ContenedorLienzo.
     * Configura el tamaño inicial del lienzo, construye las asas de redimensionamiento
     * y ubica el conjunto dentro de un área de trabajo gris con soporte de scroll.
     *
     * @param lienzo Componente visual (PanelDeDibujo) que será envuelto y redimensionado.
     */
    public ContenedorLienzo(JComponent lienzo) {
        // Guardamos la referencia al lienzo recibido
        this.lienzo = lienzo;

        // Establecemos el tamaño preferido inicial del lienzo (800x450)
        lienzo.setPreferredSize(new Dimension(ANCHO_INICIAL, ALTO_INICIAL));

        // Creamos las tres asas de redimensionamiento con su cursor y ejes habilitados:
        // 1. Asa derecha: solo modifica el ancho (horizontal = true, vertical = false)
        JPanel asaDerecha = crearAsa(Cursor.E_RESIZE_CURSOR, true, false);
        // 2. Asa inferior: solo modifica el alto (horizontal = false, vertical = true)
        JPanel asaAbajo = crearAsa(Cursor.S_RESIZE_CURSOR, false, true);
        // 3. Asa esquina: modifica tanto ancho como alto simultáneamente (ambos en true)
        JPanel asaEsquina = crearAsa(Cursor.SE_RESIZE_CURSOR, true, true);

        // Creamos una franja inferior para alojar el asa de abajo en el centro y el asa de esquina a la derecha
        JPanel franjaInferior = new JPanel(new BorderLayout());
        // Hacemos la franja transparente para que se vea el fondo gris del área de trabajo
        franjaInferior.setOpaque(false);
        franjaInferior.add(asaAbajo, BorderLayout.CENTER);
        franjaInferior.add(asaEsquina, BorderLayout.EAST);

        // Configuramos el marco transparente que une el lienzo con sus asas
        marco.setOpaque(false);
        marco.add(lienzo, BorderLayout.CENTER);          // Lienzo en el área principal
        marco.add(asaDerecha, BorderLayout.EAST);        // Asa derecha pegada al borde derecho
        marco.add(franjaInferior, BorderLayout.SOUTH);   // Franja con asas pegada al borde inferior

        // Definimos el color gris claro para el fondo del área de trabajo fuera del lienzo
        Color gris = new Color(0xD9DDE3);

        // Creamos el panel del área gris usando GridBagLayout para anclar el lienzo arriba a la izquierda
        JPanel area = new JPanel(new GridBagLayout());
        area.setBackground(gris);

        // Configuramos las restricciones para que el marco no se estire al maximizar la ventana
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.NORTHWEST; // Anclado en la esquina superior izquierda
        gbc.weightx = 1;                           // Absorbe el espacio horizontal sobrante sin estirar el lienzo
        gbc.weighty = 1;                           // Absorbe el espacio vertical sobrante sin estirar el lienzo
        gbc.insets = new Insets(12, 12, 12, 12);   // Margen visual de 12 píxeles alrededor del lienzo
        area.add(marco, gbc);

        // Asignamos el área gris completa como la vista desplazable del JScrollPane
        setViewportView(area);
        getViewport().setBackground(gris);

        // Eliminamos el borde por defecto del JScrollPane para una apariencia más limpia
        setBorder(BorderFactory.createEmptyBorder());

        // Ajustamos la velocidad de desplazamiento de las barras vertical y horizontal (16 px por paso)
        getVerticalScrollBar().setUnitIncrement(16);
        getHorizontalScrollBar().setUnitIncrement(16);
    }

    /**
     * Obtiene las dimensiones actuales configuradas para el lienzo.
     *
     * @return Objeto Dimension con el ancho y alto actuales del lienzo.
     */
    public Dimension getTamanoLienzo() {
        return lienzo.getPreferredSize();
    }

    /**
     * Actualiza el tamaño del lienzo respetando los límites mínimo y máximo permitidos,
     * y notifica a Swing para recalcular el diseño y repintar la interfaz.
     *
     * @param ancho Nuevo ancho deseado en píxeles.
     * @param alto  Nuevo alto deseado en píxeles.
     */
    public void setTamanoLienzo(int ancho, int alto) {
        // Restringimos el ancho y el alto dentro del rango [MINIMO, MAXIMO]
        int w = Math.max(MINIMO, Math.min(MAXIMO, ancho));
        int h = Math.max(MINIMO, Math.min(MAXIMO, alto));

        // Si el lienzo es una instancia de PanelDeDibujo, aplicamos el recorte destructivo
        if (lienzo instanceof PanelDeDibujo panel) {
            panel.recortarAlTamano(w, h);
        }

        // Aplicamos las nuevas dimensiones preferidas al lienzo
        lienzo.setPreferredSize(new Dimension(w, h));

        // Revalidamos la jerarquía de contenedores para que el scroll y las asas se ajusten al nuevo tamaño
        lienzo.revalidate();
        marco.revalidate();
        revalidate();

        // Solicitamos el repintado visual inmediato
        repaint();
    }

    /**
     * Construye cada asa de redimensionamiento como un cuadrito blanco de 7x7 píxeles
     * centrado dentro de una franja transparente, y le asocia los eventos de arrastre del ratón.
     *
     * @param cursor     Tipo de cursor de flecha a mostrar al pasar el ratón por encima.
     * @param horizontal Indica si esta asa permite modificar el ancho del lienzo.
     * @param vertical   Indica si esta asa permite modificar el alto del lienzo.
     * @return JPanel configurado listo para insertarse en el borde del marco.
     */
    private JPanel crearAsa(int cursor, boolean horizontal, boolean vertical) {
        // Creamos el cuadrito visual blanco con borde gris oscuro
        JPanel cuadro = new JPanel();
        cuadro.setPreferredSize(new Dimension(ASA, ASA));
        cuadro.setBackground(Color.WHITE);
        cuadro.setBorder(BorderFactory.createLineBorder(new Color(0x6B7280)));
        cuadro.setCursor(Cursor.getPredefinedCursor(cursor));

        // Creamos la franja contenedora con GridBagLayout para mantener el cuadrito centrado
        JPanel franja = new JPanel(new GridBagLayout());
        franja.setOpaque(false);
        franja.add(cuadro);
        franja.setCursor(Cursor.getPredefinedCursor(cursor));

        // Definimos el escuchador de eventos del ratón para calcular el desplazamiento al arrastrar
        MouseAdapter arrastre = new MouseAdapter() {
            // Punto en coordenadas absolutas de pantalla donde inició el clic
            private Point inicio;
            // Dimensiones que tenía el lienzo justo antes de empezar a arrastrar
            private Dimension tamInicial;

            @Override
            public void mousePressed(MouseEvent e) {
                // Capturamos la posición inicial en pantalla (evita saltos cuando el componente se mueve)
                inicio = e.getLocationOnScreen();
                // Guardamos el tamaño inicial del lienzo
                tamInicial = new Dimension(lienzo.getPreferredSize());
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                // Validación de seguridad por si no se registró el punto inicial
                if (inicio == null) {
                    return;
                }
                // Obtenemos la posición actual del cursor en pantalla
                Point ahora = e.getLocationOnScreen();

                // Calculamos el nuevo ancho y alto sumando la diferencia de movimiento solo en los ejes activos
                int w = tamInicial.width + (horizontal ? ahora.x - inicio.x : 0);
                int h = tamInicial.height + (vertical ? ahora.y - inicio.y : 0);

                // Aplicamos el nuevo tamaño al lienzo
                setTamanoLienzo(w, h);
            }
        };

        // Registramos los eventos de clic y arrastre tanto en el cuadrito como en toda su franja
        for (JComponent c : new JComponent[]{franja, cuadro}) {
            c.addMouseListener(arrastre);
            c.addMouseMotionListener(arrastre);
        }

        return franja;
    }
}