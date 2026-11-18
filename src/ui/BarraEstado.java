package ui;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.BevelBorder;

public class BarraEstado extends JPanel {

    private JLabel lblHerramienta;
    private JPanel pnlColorActivo;
    private JLabel lblMensajeArchivo;
    private JLabel lblGrosor;

    public BarraEstado() {
        // Configuracion del diseño del panel principal
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        setPreferredSize(new Dimension(800, 30));

        // Seccion izquierda: Herramienta y Color actual
        JPanel panelIzquierdo = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 2));
        lblHerramienta = new JLabel("Herramienta: Ninguna");

        pnlColorActivo = new JPanel();
        pnlColorActivo.setPreferredSize(new Dimension(15, 15));
        pnlColorActivo.setBackground(Color.BLACK); // Color inicial
        pnlColorActivo.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        panelIzquierdo.add(lblHerramienta);
        panelIzquierdo.add(new JLabel("Color:"));
        panelIzquierdo.add(pnlColorActivo);

        // Seccion central: Mensajes de guardado/apertura (Coordinación Equipo 4)
        JPanel panelCentral = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 2));
        lblMensajeArchivo = new JLabel("Listo");
        panelCentral.add(lblMensajeArchivo);

        // Seccion derecha: Informacion del grosor (Coordinacion Equipo 5)
        JPanel panelDerecho = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 2));
        lblGrosor = new JLabel("Grosor: 1px");
        panelDerecho.add(lblGrosor);

        // Ensamblar las tres secciones en la barra
        add(panelIzquierdo, BorderLayout.WEST);
        add(panelCentral, BorderLayout.CENTER);
        add(panelDerecho, BorderLayout.EAST);
    }

    //Metodos publicos para actualizar la informacion
    public void setHerramientaActiva(String herramienta) {
        lblHerramienta.setText("Herramienta: " + herramienta);
    }

    public void setColorActivo(Color color) {
        pnlColorActivo.setBackground(color);
    }

    public void setMensajeArchivo(String mensaje) {
        lblMensajeArchivo.setText(mensaje);
    }

    public void setGrosor(String grosor) {
        lblGrosor.setText("Grosor: " + grosor);
    }
}
