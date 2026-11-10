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
public class Corazon extends FiguraRellenable{
    
    
    public Corazon (Point puntoInicial, boolean relleno){
        super(puntoInicial, relleno);
    }
  @Override
    protected Shape crearForma() {
    
        int x = Math.min(puntoInicial.x, puntoFinal.x);
        int y = Math.min(puntoInicial.y, puntoFinal.y);

        int ancho = Math.abs(puntoFinal.x - puntoInicial.x);
        int alto = Math.abs(puntoFinal.y - puntoInicial.y);

        Path2D.Double corazon = new Path2D.Double();

        // Centro superior
        corazon.moveTo(x + ancho / 2.0, y + alto * 0.30);

         // Curva izquierda
        corazon.curveTo(
        x + ancho * 0.05, y - alto * 0.15,
        x - ancho * 0.20, y + alto * 0.45,
        x + ancho / 2.0, y + alto );

        // Curva derecha
        corazon.curveTo(
        x + ancho * 1.20, y + alto * 0.45,
        x + ancho * 0.95, y - alto * 0.15,
        x + ancho / 2.0, y + alto * 0.30);

        corazon.closePath();

         return corazon;
        
    
}
    
    
    
    
}
