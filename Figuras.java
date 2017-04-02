/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paint;


abstract class Figuras {
    
    String cor;
    
    abstract String getCor();
    abstract void setCor(String newCor);
    abstract double calcularArea();
    
    
}
