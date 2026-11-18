/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package figuras;

import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.Path2D;

/**
 *
 * @author Ailyn A. Martínez - 100634353
 */
public class Flecha extends FiguraRellenable {
    
    public Flecha (Point puntoInicial, boolean relleno){
        super(puntoInicial, relleno);
    }
    
    @Override
    protected Shape crearForma() {
    
        int x = Math.min(puntoInicial.x, puntoFinal.x);
        int y = Math.min(puntoInicial.y, puntoFinal.y);

        int ancho = Math.abs(puntoFinal.x - puntoInicial.x);
        int alto = Math.abs(puntoFinal.y - puntoInicial.y);

        Path2D.Double flecha = new Path2D.Double();

        flecha.moveTo(x, y + alto / 3.0);
        flecha.lineTo(x + ancho * 0.65, y + alto / 3.0);
        flecha.lineTo(x + ancho * 0.65, y);
        flecha.lineTo(x + ancho, y + alto / 2.0);
        flecha.lineTo(x + ancho * 0.65, y + alto);
        flecha.lineTo(x + ancho * 0.65, y + alto * 2.0 / 3.0);
        flecha.lineTo(x, y + alto * 2.0 / 3.0);

        flecha.closePath();

        return flecha;
    

    }
}
