/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paint;

import java.util.ArrayList;

/**
 *
 * @author Mayara Machado
 */
public class Paint {

    ArrayList<Figuras> figuras;
    
    public Paint(){
        figuras = new ArrayList<>();
    }
    
    public void addFigura(Figuras f){
        figuras.add(f);
    }
    
    public String showFiguras(){
        return figuras.toString();
    }
            
    public Figuras getFiguras(int i){
        return figuras.get(i);
    } 
          
    public Figuras instaciaFigura(Figuras f){
        if (f instanceof Quadrado){
            return (Quadrado)f;
        }else{
            if(f instanceof Triangulo){
                return (Triangulo)f;
            }else{
                return (Circulo)f;
            } 
        }
    }
    
    public static void main(String[] args) {
        Quadrado q =  new Quadrado(2, 3, "azul");
        Triangulo t = new Triangulo(2, 1, "vermelho");
        Circulo c = new Circulo(5, "verde");
        Paint p = new Paint();
        
        p.addFigura(c);
        p.addFigura(t);
        p.addFigura(q);

        System.out.println(p.showFiguras());
        
        Triangulo t1 = (Triangulo) p.instaciaFigura(p.getFiguras(1));
        
        t1.setAltura(2);
        System.out.println(p.showFiguras());
                
                
    }
    
}
