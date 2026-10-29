/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package figuras;

import java.awt.Point;
import java.awt.Polygon;
import java.awt.Shape;
/**
 *
 * @author Juan Santana
 */

public class Rombo extends FiguraRellenable{
    
    public Rombo(Point puntoInicial, boolean relleno) {
        super(puntoInicial, relleno);
    }

    @Override
    protected Shape crearForma() {
        int x1 = Math.min(puntoInicial.x, puntoFinal.x);
        int y1 = Math.min(puntoInicial.y, puntoFinal.y);
        int x2 = Math.max(puntoInicial.x, puntoFinal.x);
        int y2 = Math.max(puntoInicial.y, puntoFinal.y);
        
        int centroX = (x1 + x2) / 2;
        int centroY = (y1 + y2) / 2;
        
        Polygon rombo = new Polygon();
        
        rombo.addPoint(centroX, y1);
        rombo.addPoint(x2, centroY);
        rombo.addPoint(centroX, y2);
        rombo.addPoint(x1, centroY);
        
        return rombo;
        
        
        
    }
    
    
}
