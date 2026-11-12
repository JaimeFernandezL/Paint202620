package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 * Envuelve el lienzo como en el Paint: el lienzo tiene un tamaño fijo dentro de
 * un área gris con scroll. Agrandar la ventana NO agranda el lienzo; el lienzo
 * se redimensiona solo arrastrando las asas (derecha, abajo y esquina).
 * Responsable: Juana (Equipo 3). No contiene lógica de dibujo.
 */
public class ContenedorLienzo extends JScrollPane {

    public static final int ANCHO_INICIAL = 800;
    public static final int ALTO_INICIAL = 450;
    private static final int MINIMO = 100;
    private static final int MAXIMO = 4000;
    private static final int ASA = 7;

    private final JComponent lienzo;
    private final JPanel marco = new JPanel(new BorderLayout());

    public ContenedorLienzo(JComponent lienzo) {
        this.lienzo = lienzo;
        lienzo.setPreferredSize(new Dimension(ANCHO_INICIAL, ALTO_INICIAL));

        // Asas: derecha, abajo y esquina inferior derecha
        JPanel asaDerecha = crearAsa(Cursor.E_RESIZE_CURSOR, true, false);
        JPanel asaAbajo = crearAsa(Cursor.S_RESIZE_CURSOR, false, true);
        JPanel asaEsquina = crearAsa(Cursor.SE_RESIZE_CURSOR, true, true);

        JPanel franjaInferior = new JPanel(new BorderLayout());
        franjaInferior.setOpaque(false);
        franjaInferior.add(asaAbajo, BorderLayout.CENTER);
        franjaInferior.add(asaEsquina, BorderLayout.EAST);

        marco.setOpaque(false);
        marco.add(lienzo, BorderLayout.CENTER);
        marco.add(asaDerecha, BorderLayout.EAST);
        marco.add(franjaInferior, BorderLayout.SOUTH);

        // Área gris: el marco queda arriba a la izquierda, sin estirarse
        Color gris = new Color(0xD9DDE3);
        JPanel area = new JPanel(new GridBagLayout());
        area.setBackground(gris);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.insets = new Insets(12, 12, 12, 12);
        area.add(marco, gbc);

        setViewportView(area);
        getViewport().setBackground(gris);
        setBorder(BorderFactory.createEmptyBorder());
        getVerticalScrollBar().setUnitIncrement(16);
        getHorizontalScrollBar().setUnitIncrement(16);
    }

    /** Tamaño actual del lienzo. */
    public Dimension getTamanoLienzo() {
        return lienzo.getPreferredSize();
    }

    /** Cambia el tamaño del lienzo (también lo usan las asas). */
    public void setTamanoLienzo(int ancho, int alto) {
        int w = Math.max(MINIMO, Math.min(MAXIMO, ancho));
        int h = Math.max(MINIMO, Math.min(MAXIMO, alto));
        lienzo.setPreferredSize(new Dimension(w, h));
        lienzo.revalidate();
        marco.revalidate();
        revalidate();
        repaint();
    }

    private JPanel crearAsa(int cursor, boolean horizontal, boolean vertical) {
        // Cuadrito de 7x7 centrado dentro de una franja
        JPanel cuadro = new JPanel();
        cuadro.setPreferredSize(new Dimension(ASA, ASA));
        cuadro.setBackground(Color.WHITE);
        cuadro.setBorder(BorderFactory.createLineBorder(new Color(0x6B7280)));
        cuadro.setCursor(Cursor.getPredefinedCursor(cursor));

        JPanel franja = new JPanel(new GridBagLayout());
        franja.setOpaque(false);
        franja.add(cuadro);
        franja.setCursor(Cursor.getPredefinedCursor(cursor));

        MouseAdapter arrastre = new MouseAdapter() {
            private Point inicio;
            private Dimension tamInicial;

            @Override
            public void mousePressed(MouseEvent e) {
                inicio = e.getLocationOnScreen();
                tamInicial = new Dimension(lienzo.getPreferredSize());
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                if (inicio == null) {
                    return;
                }
                Point ahora = e.getLocationOnScreen();
                int w = tamInicial.width + (horizontal ? ahora.x - inicio.x : 0);
                int h = tamInicial.height + (vertical ? ahora.y - inicio.y : 0);
                setTamanoLienzo(w, h);
            }
        };
        for (JComponent c : new JComponent[]{franja, cuadro}) {
            c.addMouseListener(arrastre);
            c.addMouseMotionListener(arrastre);
        }
        return franja;
    }
}