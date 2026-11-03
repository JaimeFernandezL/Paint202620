/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package figuras;

import java.awt.Graphics;
import java.awt.Point;

/**
 *
 * @author josearielpereyra
 */
public abstract class Figura {
    protected int grosor = 1; //El grosor por default
    
    public abstract void dibujar(Graphics g);
    public abstract void actualizar(Point puntoActual);
    
    public void setGrosor(int grosor)
    {
        this.grosor=grosor;
    }
    public int getGrosor()
    {
        return grosor;
    }
}
