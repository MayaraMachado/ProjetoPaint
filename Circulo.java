/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paint;


public class Circulo extends Figuras {

    private double raio;
    
    public Circulo(int raio, String cor){
        this.raio = raio;
        super.cor = cor;
    }
    

    
    public void setRaio(double newRaio){
        raio = newRaio;
    }
    
    public void setCor(String newCor){
        cor = newCor;
    }
    
    public double getRaio(double newRaio){
        return raio;
    }
   

    @Override
    String getCor() {
        return cor;
    }

    @Override
    double calcularArea() {
        return (Math.PI*Math.pow(raio, 2));
    }
    
   
    @Override
    public String toString(){
        return "Forma: Circulo\nCor: "+cor
                +"\nRaio: "+raio
                +"\nÁrea: "+calcularArea()
                +"\n\n";
    }
    
}
