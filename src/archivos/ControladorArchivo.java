/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package archivos;


import java.awt.Component;
import java.awt.image.BufferedImage;
import ui.PanelDeDibujo;

/**
 *
 * @author samue
 */

public class ControladorArchivo {
    
      private final PanelDeDibujo panel;
    private final Component ventana;

    private final ServicioAbrirImagen servicioAbrir;
    private final ServicioGuardarImagen servicioGuardar;
    private final ServicioNuevoDocumento servicioNuevo;

    public ControladorArchivo(PanelDeDibujo panel, Component ventana) {
        this.panel = panel;
        this.ventana = ventana;

        servicioAbrir = new ServicioAbrirImagen();
        servicioGuardar = new ServicioGuardarImagen();
        servicioNuevo = new ServicioNuevoDocumento(panel);
    }

    // Crear un documento nuevo
    public void nuevo() {
        servicioNuevo.nuevoDocumento();
    }

    // Abrir una imagen
    public void abrir() {
        BufferedImage imagen = servicioAbrir.abrir(ventana);

        if (imagen != null) {
            
            // PENDIENTE DE INTEGRACIÓN:
        // El equipo de PanelDeDibujo debe crear
        // el método cargarImagen(BufferedImage imagen).
        // Cuando esté creado, descomentar la siguiente línea.
        
        
         //panel.cargarImagen(imagen);
        }
    }

    // Guardar el dibujo
    public void guardar() {
        servicioGuardar.guardarImagen(panel, ventana);
    }
}
