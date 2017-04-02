/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paint;


public class Quadrado extends Figuras {

    private double base;
    private double altura;
    
    
    public Quadrado(double base, double altura, String cor){
        this.base = base;
        this.altura = altura;
        super.cor = cor;
    }
    
    @Override
    String getCor() {
        return super.cor;
    }

    @Override
    void setCor(String newCor) {
        super.cor = newCor;
    }

    @Override
    public double calcularArea() {
        return (base*altura);
    }

    public double getBase() {
        return base;
    }

    public double getAltura() {
        return altura;
    }


    public void setBase(double newBase) {
        base = newBase;
    }

    public void setAltura(double newAltura) {
        altura = newAltura;
    }
    
        @Override
    public String toString(){
            return "Forma: Quadrado\nCor: "+cor
                +"\nBase: "+base
                +"\nAltura: "+altura
                +"\nÁrea: "+calcularArea()
                +"\n\n";
    }
    
    
    
}
