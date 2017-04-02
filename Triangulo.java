/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paint;


public class Triangulo extends Figuras {

    private double base; 
    private double altura;
    
    public Triangulo(double base, double altura, String cor){
        this.base = base;
        this.altura = altura;
        super.cor = cor;
        
    }
    
    public double getBase(){
        return base;
    }

    public double getAltura(){
        return altura;
    }
    
    public void setBase(double newBase){
        base = newBase;
    }
    
    public void setAltura(double newAltura){
        altura = newAltura;
    }

 
    
    @Override
    String getCor() {
        return cor;
    }

    @Override
    void setCor(String newCor) {
        cor = newCor;
    }

    @Override
    double calcularArea() {
        return (base*altura);
    }

    @Override
    public String toString(){
        return "Forma: Triângulo\nCor: "+cor
                +"\nBase: "+base
                +"\nAltura: "+altura
                +"\nÁrea: "+calcularArea()
                +"\n\n";
    }

   
   
}
