/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetopaint.Model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * As classes Circulo, Triangulo e Retangulo tinham métodos e variáveis comuns
 * Subi a responsabilidade para a classe Figuras2D Ganhamos em reuso de código.
 * Podemos criar uma nova forma sem precisar implementar os métodos de salvar o
 * objeto e de settar e pintar a figura. Também, podemos adicionar um novo
 * método comum a todas as figuras sem precisar implementá-las em cada classe
 * 
 * Superclasse Model, de acordo com o Pattern MVC (Model View Controller).
 * Essa classe abstrata modela os atributos e comportamento das figuras 
 * exibidas na View (a interface de interação com o usuário)
 * 
 * @author Mayara Machado
 * @author Luiz Felipe Souza
 */
public abstract class Figuras2D implements Desenho {
	/**
	 * 
	 */
	private Color corDeLinha = null;
	private Color cor = null;

	public abstract void desenhar(Graphics g);
	
	public abstract void redesenhar(int x, int y, int w, int h);

	public abstract boolean contemPonto(Point p);

	public abstract void construirObject(Point startDrag, Point endDrag);

	public abstract void editarObject(Point startDrag, Point endDrag);
	
	
	/**
	 * Pinta a figura de acordo com o parâmetro dado
	 * 
	 * @param cor - Cor de preenchimento da figura
	 */
	public void preencher(Color cor) {
		this.setCor(cor);
	}

	/*public void writetoFile(String nomeArquivo) throws IOException {
		PrintStream ps;
		ObjectOutputStream object;

		ps = new PrintStream(nomeArquivo);
		object = new ObjectOutputStream(ps);
		object.writeObject(this);
		object.flush();
		object.close();

	} */

	/**s
	 * Lê a figura serializada em um arquivo
	 * 
	 * @param nomeArquivo - O nome do arquivo onde a figura será salva
	 * @return List<Figuras2D>
	 * @throws IOException
	 * @thorws ClassNotFoundException
	 */
	public static Figuras2D lerArquivo(File arquivo) throws IOException, ClassNotFoundException {
		// Entrada de arquivo (em bytes)
		InputStream in = new FileInputStream(arquivo);
		// Converte os bytes para um objeto java
		ObjectInputStream object = new ObjectInputStream(in);
		// Aqui serão colocados os objetos lidos do arquivo
		List<Figuras2D> figuras = new ArrayList<>();
		
		while (true) {
			try {
				Figuras2D figura = (Figuras2D) object.readObject();
				figuras.add(figura);
			} catch (EOFException e) {
				/* readObject() lança EOFException caso se tente 
				 * ler mais objetos do que foram salvos no arquivo
				 * O loop para quando essa exception é lançada; ou seja,
				 * quando todos os objetos salvos já foram lidos */
				break;
			}
		}
		object.close();
		return (Figuras2D) figuras;
	}
	
	public void setCor(Color cor) {
		this.cor = cor;
	}
	
	
	public Color getCorDeLinha() {
		return corDeLinha;
	}

	public void setCorDeLinha(Color corDeLinha) {
		this.corDeLinha = corDeLinha;
	}

	public Color getCor() {
		return cor;
	}

}
