package ui;

import java.awt.Color;

/**
 * Gestiona el color actual de dibujo y lo propaga al lienzo. No modifica las
 * clases existentes; solo se conecta con PanelDeDibujo.
 */
public class GestorColor {

    private Color colorActual = Color.BLACK;
    private Color colorAnterior = Color.BLACK;
    private PanelDeDibujo lienzo;

    public boolean abrirSelector(java.awt.Component padre) {
        java.awt.Color c = javax.swing.JColorChooser.showDialog(
                padre, "Seleccionar color", colorActual);
        if (c != null) {
            cambiarColor(c);
            return true;
        }
        return false;
    }

    public GestorColor(PanelDeDibujo lienzo) {
        this.lienzo = lienzo;
        aplicar();
    }

    public Color getColorActual() {
        return colorActual;
    }

    public Color getColorAnterior() {
        return colorAnterior;
    }

    /**
     * Cambia el color actual. Si es igual al actual, no hace nada. Si es
     * distinto, guarda el anterior y lo aplica al lienzo.
     */
    public void cambiarColor(Color nuevo) {
        if (nuevo == null || nuevo.equals(colorActual)) {
            return;
        }
        colorAnterior = colorActual;
        colorActual = nuevo;
        aplicar();
    }

    private void aplicar() {
        if (lienzo != null) {
            lienzo.setColor(colorActual);
        }
    }

    public void setLienzo(PanelDeDibujo lienzo) {
        this.lienzo = lienzo;
        aplicar();
    }
}
