
package archivos;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ServicioGuardarImagen {

    // MÉTODO 1: CAPTURAR EL LIENZO
    // Obtiene el contenido actual del lienzo y lo convierte en una imagen.
    public BufferedImage capturarLienzo(Component lienzo) {

        // Obtiene el ancho actual del componente del lienzo.
        int ancho = lienzo.getWidth();

        // Obtiene el alto actual del componente del lienzo.
        int alto = lienzo.getHeight();

        // Comprueba que el lienzo tenga dimensiones válidas.
        if (ancho <= 0 || alto <= 0) {
            throw new IllegalArgumentException(
                "El lienzo no tiene dimensiones válidas."
            );
        }

        // Crea una imagen en memoria con el mismo tamaño del lienzo.
        // TYPE_INT_RGB indica que utiliza el modelo de color RGB.
        BufferedImage imagen = new BufferedImage(
            ancho, alto, BufferedImage.TYPE_INT_RGB
        );

        // Obtiene el objeto gráfico que permite dibujar sobre la imagen.
        Graphics2D g = imagen.createGraphics();

        try {
            // Establece el color blanco como fondo de la imagen.
            g.setColor(Color.WHITE);

            // Pinta todo el fondo de la imagen de color blanco.
            g.fillRect(0, 0, ancho, alto);

            // Dibuja todo el contenido visible del lienzo sobre la imagen.
            lienzo.printAll(g);

        } finally {
            // Libera los recursos gráficos utilizados.
            // Se ejecuta incluso si ocurre algún error.
            g.dispose();
        }

        // Devuelve la imagen capturada para poder guardarla.
        return imagen;
    }

    // MÉTODO 2: ESCRIBIR LA IMAGEN
    // Guarda la imagen en el archivo indicado utilizando el formato PNG.
    public boolean escribirImagen(BufferedImage imagen, File archivo)
            throws IOException {

        // ImageIO.write escribe la imagen en el archivo.
        // "PNG" indica el formato en el que se guardará.
        // Devuelve true si encuentra un escritor compatible.
        return ImageIO.write(imagen, "PNG", archivo);
    }

    // MÉTODO 3: GUARDAR LA IMAGEN
    // Coordina todo el proceso de guardar el contenido del lienzo.
    public boolean guardarImagen(Component lienzo, Component ventana) {

        // Crea el selector de archivos para elegir dónde guardar la imagen.
        JFileChooser selector = new JFileChooser();

        // Establece el título de la ventana de selección.
        selector.setDialogTitle("Guardar imagen");

        // Permite mostrar archivos con extensión PNG.
        selector.setFileFilter(
            new FileNameExtensionFilter("Imagen PNG", "png")
        );

        // Establece un nombre predeterminado para el archivo.
        selector.setSelectedFile(new File("dibujo.png"));

        // Muestra el selector y comprueba si el usuario aceptó.
        if (selector.showSaveDialog(ventana) != JFileChooser.APPROVE_OPTION) {

            // Si cancela, termina la operación sin guardar.
            return false;
        }

        // Obtiene el archivo y la ubicación seleccionados por el usuario.
        File archivo = selector.getSelectedFile();

        // Comprueba si el nombre del archivo termina en .png.
        // Si no tiene la extensión, se la agrega automáticamente.
        if (!archivo.getName().toLowerCase().endsWith(".png")) {
            archivo = new File(archivo.getAbsolutePath() + ".png");
        }

        // Comprueba si ya existe un archivo con ese nombre.
        if (archivo.exists()) {

            // Pregunta al usuario si desea reemplazar el archivo existente.
            int r = JOptionPane.showConfirmDialog(
                ventana,
                "El archivo ya existe. ¿Desea reemplazarlo?",
                "Confirmar",
                JOptionPane.YES_NO_OPTION
            );

            // Si el usuario no acepta reemplazarlo, cancela la operación.
            if (r != JOptionPane.YES_OPTION) {
                return false;
            }
        }

        try {
            // Captura el contenido actual del lienzo y crea una imagen.
            BufferedImage imagen = capturarLienzo(lienzo);

            // Intenta escribir la imagen en el archivo seleccionado.
            // Si ImageIO no encuentra un escritor PNG, genera un error.
            if (!escribirImagen(imagen, archivo)) {
                throw new IOException(
                    "No hay un escritor disponible para PNG."
                );
            }

            // Informa al usuario que la imagen se guardó correctamente.
            JOptionPane.showMessageDialog(
                ventana,
                "Imagen guardada correctamente."
            );

            // Indica que la operación terminó correctamente.
            return true;

        } catch (IOException | SecurityException | IllegalArgumentException e) {

            // Captura errores de escritura, permisos o dimensiones inválidas.
            // Muestra el motivo del error para informar al usuario.
            JOptionPane.showMessageDialog(
                ventana,
                "Error al guardar la imagen: " + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE
            );

            // Indica que no se pudo completar el guardado.
            return false;
        }
    }
}