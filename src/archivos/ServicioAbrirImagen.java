package archivos;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author jason
 */
public class ServicioAbrirImagen {
public BufferedImage abrir(Component padre) {
        JFileChooser selector = new JFileChooser();
        selector.setDialogTitle("Abrir imagen");

        FileNameExtensionFilter filtro = new FileNameExtensionFilter(
                "Imágenes (png, jpg, jpeg, gif, bmp)",
                "png", "jpg", "jpeg", "gif", "bmp");
        selector.addChoosableFileFilter(filtro);
        selector.setFileFilter(filtro);

        int resultado = selector.showOpenDialog(padre);
        if (resultado != JFileChooser.APPROVE_OPTION) {
            return null; // El usuario canceló: no es un error.
        }
        return abrir(selector.getSelectedFile(), padre);
    }

public BufferedImage abrir(File archivo, Component padre) {
        if (archivo == null || !archivo.isFile()) {
            mostrarError(padre, "El archivo seleccionado no existe.");
            return null;
        }

        try {
            BufferedImage original = ImageIO.read(archivo);

            if (original == null) {
                // ImageIO devuelve null cuando no reconoce el formato.
                mostrarError(padre, "El formato del archivo no es compatible.");
                return null;
            }
            return convertirAArgb(original);

        } catch (IOException | RuntimeException e) {
            // IOException: no se pudo leer. RuntimeException: archivo dañado.
            mostrarError(padre, "No se pudo abrir la imagen. "
                    + "El archivo puede estar dañado.");
            return null;
        }
    }

 private BufferedImage convertirAArgb(BufferedImage origen) {
        BufferedImage copia = new BufferedImage(
                origen.getWidth(), origen.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = copia.createGraphics();
        g.drawImage(origen, 0, 0, null);
        g.dispose();
        return copia;
    }

    private void mostrarError(Component padre, String mensaje) {
        JOptionPane.showMessageDialog(padre, mensaje,
                "Abrir imagen", JOptionPane.ERROR_MESSAGE);
    }   
}