/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetopaint.Model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.geom.Ellipse2D;
import java.io.BufferedWriter;
import java.io.IOException;

import projetopaint.View.View;

/**
 * Classe Model, de acordo com o padrão MVC (Model View Controller). Essa classe
 * especifica os atributos e comportamento dos objetos Circulo exibidos na View
 * (A interface gráfica de interação com o usuário).
 *
 * @author Mayara Machado
 * @author Luiz Felipe Souza
 */
public class Circulo extends Figuras2D {

	private Ellipse2D circulo;

	/*
	 * Construtores do objeto
	 */
	/**
	 * Construtor vazio
	 */
	public Circulo() {
	}

	/**
	 * Constrói um círculo a partir da Ellipse2D passada
	 * 
	 * @param e
	 *            - Ellipse2D (círculo) que erá construída
	 */
	public Circulo(Ellipse2D e) {
		this.circulo = e;
	}

	/**
	 * Constrói um círculo com uma dada cor de linha (preto por padrão)
	 * 
	 * @param e
	 *            - Ellipse2D (círculo) que erá construída
	 * @param corLinha
	 *            - Define a cor do contorno da figura
	 */
	public Circulo(Ellipse2D e, Color corLinha) {
		this.circulo = e;
		this.setCorDeLinha(corLinha);
	}

	/**
	 * Constrói um círculo com determinada cor de linha e de preenchimento
	 * 
	 * @param e
	 *            - Ellipse2D (círculo) que erá construída
	 * @param corLinha
	 *            - Define a cor do contorno da figura
	 * @param cor
	 *            - Define a cor do preenchimento da figura
	 */
	public Circulo(Ellipse2D e, Color corLinha, Color cor) {
		this.circulo = e;
		this.setCorDeLinha(corLinha);
		this.setCor(cor);
	}

	@Override
	public void construirObject(Point startDrag, Point endDrag) {

		float x = Math.min(startDrag.x, endDrag.x);
		float y = Math.min(startDrag.y, endDrag.y);
		float w = Math.abs(startDrag.x - endDrag.x);
		float h = Math.abs(startDrag.y - endDrag.y);

		/*
		 * O construtor da classe aninhada Ellipse2D.Float recebe os seguintes
		 * argumentos: Coordenada x do retângulo de enquadramento Coordenada y
		 * do retângulo de enquadramento Largura do retângulo de enquadramento
		 * Altura do retângulo de enquadramento
		 */
		Ellipse2D r = new Ellipse2D.Float(x, y, w, h);
		this.setCirculo(r);
	}

	@Override
	public void editarObject(Point startDrag, Point endDrag) {
		float x = Math.min(startDrag.x, endDrag.x);
		float y = Math.min(startDrag.y, endDrag.y);
		float w = Math.abs(startDrag.x - endDrag.x);
		float h = Math.abs(startDrag.y - endDrag.y);

		/*
		 * O construtor da classe aninhada Ellipse2D.Float recebe os seguintes
		 * argumentos: Coordenada x do retângulo de enquadramento Coordenada y
		 * do retângulo de enquadramento Largura do retângulo de enquadramento
		 * Altura do retângulo de enquadramento
		 */
		Ellipse2D r = new Ellipse2D.Float(x, y, w, h);
		this.setCirculo(r);
	}

	@Override
	public void desenhar(Graphics g) {

		int x = (int) getCirculo().getX();
		int y = (int) getCirculo().getY();
		int width = (int) getCirculo().getWidth();
		int height = (int) getCirculo().getHeight();

		if (getCor() == null) {
			g.setColor(getCorDeLinha());
			g.drawOval(x, y, width, height);

		} else {
			g.setColor(getCor());
			g.fillOval(x, y, width, height);
		}
	}

	@Override
	public void redesenhar(int x, int y, int w, int h) {
		Ellipse2D r = new Ellipse2D.Float(x, y, w, h);
		this.setCirculo(r);
	}

	public boolean contemPonto(Point p) {
		return this.getCirculo().contains(p);
	}

	@Override
	public void writetoFile(BufferedWriter b) throws IOException {
		try {
			b.write(getClass().getSimpleName() + ";");
			b.write((int) getCirculo().getX() + ";" + (int) getCirculo().getY() + ";" + (int) getCirculo().getWidth()
					+ ";" + (int) getCirculo().getHeight() + ";");
			b.write(getCorDeLinha().getRed() + ";" + getCorDeLinha().getGreen() + ";" + getCorDeLinha().getBlue()
					+ ";");
			if (getCor() == null) {
				b.write("null" + ";" + "null" + ";" + "null");
			} else {
				b.write(getCor().getRed() + ";" + getCor().getGreen() + ";" + getCor().getBlue());
			}
			b.newLine();
			b.flush();
			b.close();
		} catch (IOException e) {
			View.mostrarErro("Escrita");
		}

	}

	/*
	 * Métodos Acessores: getters e setters
	 */
	public Ellipse2D getCirculo() {
		return circulo;
	}

	public void setCirculo(Ellipse2D r) {
		this.circulo = r;

	}

}
