/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;

import java.awt.GridLayout;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

public class PanelFiguras extends JPanel {

    private final PanelDeDibujo panelDeDibujo;
    private final ButtonGroup grupoHerramientas;

    public PanelFiguras(PanelDeDibujo panelDeDibujo, ButtonGroup grupoHerramientas) {
        this.panelDeDibujo = panelDeDibujo;
        this.grupoHerramientas = grupoHerramientas;

        // Cuadricula compacta: 2 filas, las columnas se ajustan solas
        setLayout(new GridLayout(2, 0, 2, 2));

        agregarBoton("/imagenes/linea-diagonal_1.png", PanelDeDibujo.TipoFigura.LINEA);
        agregarBoton("Rectángulo", PanelDeDibujo.TipoFigura.RECTANGULO);
        agregarBoton("Óvalo", PanelDeDibujo.TipoFigura.OVALO);
        agregarBoton("Círculo", PanelDeDibujo.TipoFigura.CIRCULO);
        agregarBoton("Rombo", PanelDeDibujo.TipoFigura.ROMBO);
        agregarBoton("Pentágono", PanelDeDibujo.TipoFigura.PENTAGONO);
        agregarBoton("Hexágono", PanelDeDibujo.TipoFigura.HEXAGONO);
        agregarBoton("Corazón", PanelDeDibujo.TipoFigura.CORAZON);
        agregarBoton("Flecha", PanelDeDibujo.TipoFigura.FLECHA);
        agregarBoton("Estrella", PanelDeDibujo.TipoFigura.ESTRELLA);
        agregarBoton("Triángulo", PanelDeDibujo.TipoFigura.TRIANGULO);
        agregarBoton("Paralelogramo", PanelDeDibujo.TipoFigura.PARALELOGRAMO);
    }

    private void agregarBoton(String texto, PanelDeDibujo.TipoFigura tipo) {
        JToggleButton boton = new JToggleButton(texto);
        boton.setFocusPainted(false);
        boton.setToolTipText(texto);

        // Al hacer clic, el panel de dibujo cambia de figura
        boton.addActionListener(e -> panelDeDibujo.setTipoActual(tipo));

        // Mismo grupo que lapiz y borrador: solo uno seleccionado a la vez
        grupoHerramientas.add(boton);
        add(boton);
    }
}
