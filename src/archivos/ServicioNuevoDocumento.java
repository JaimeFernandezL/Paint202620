/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package archivos;

import javax.swing.JOptionPane;
import ui.PanelDeDibujo;


/**
 *
 * @author Yoni
 */

public class ServicioNuevoDocumento {

    private final PanelDeDibujo panel;
    int respuesta;

    public ServicioNuevoDocumento(PanelDeDibujo panel) {
        this.panel = panel;
    }

    public void nuevoDocumento() {

       respuesta = JOptionPane.showConfirmDialog(
                panel,
                "¿Deseas guardar el dibujo actual antes de crear "
                + "un nuevo documento?",
                "Nuevo documento",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.WARNING_MESSAGE
        );

        if (respuesta == JOptionPane.CANCEL_OPTION
                || respuesta == JOptionPane.CLOSED_OPTION) {
            return;
        }

        if (respuesta == JOptionPane.YES_OPTION) {
            ServicioGuardarImagen guardar =
                    new ServicioGuardarImagen();
            boolean guardado = guardar.guardarImagen(panel, panel);
            if (!guardado) {
                return;
            }
        }
        panel.Limpiar();
    }
}
