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

public class Pentagono extends FiguraRellenable {
    
    //Divide la altura para la posicion de los vertices laterales
    private static final int Div_Altura_Lat = 3;
    //Multiplica el ancho para la posicion del vertice inferior derecho
    private static final int Multi_Punto_Base = 4;
    //Divide el ancho para la posicion de los vertices de la base
    private static final int Div_Ancho_Base = 5;
    
    public Pentagono(Point puntoInicial, boolean relleno){
        super(puntoInicial, relleno);
    }

    @Override
    protected Shape crearForma() {
        int x1 = Math.min(puntoInicial.x, puntoFinal.x);
        int y1 = Math.min(puntoInicial.y, puntoFinal.y);
        int x2 = Math.max(puntoInicial.x, puntoFinal.x);
        int y2 = Math.max(puntoInicial.y, puntoFinal.y);
        
        int ancho = x2 - x1;
        int alto = y2 - y1;
        
        int centroX = (x1 + x2) / 2;
        
        Polygon pentagono = new Polygon();
        
        pentagono.addPoint(centroX, y1);
        pentagono.addPoint(x2, y1 + alto / Div_Altura_Lat);
        pentagono.addPoint(x1 + ancho * Multi_Punto_Base / Div_Ancho_Base, y2);
        pentagono.addPoint(x1 + ancho / Div_Ancho_Base, y2);
        pentagono.addPoint(x1, y1 + alto / Div_Altura_Lat);
        
        return pentagono;
    }
}
