/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetopaint.Model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;
import java.io.BufferedWriter;
import java.io.IOException;

import projetopaint.View.View;

/**
 * Classe Model, de acordo com o padrão MVC (Model View Controller). Essa classe
 * especifica os atributos e comportamento dos objetos Triangulo exibidos na
 * View (A interface gráfica de interação com o usuário).
 *
 * @author Mayara Machado
 * @author Luiz Felipe Souza
 */
public class Triangulo extends Figuras2D {

	private Polygon Triangulo;

	/**
	 * Construtor vazio
	 */
	public Triangulo() {

	}

	/**
	 * Constrói um triângulo a partir da Polygon passada
	 * 
	 * @param pol
	 *            Polígono (triângulo) que será construído
	 */
	public Triangulo(Polygon pol) {
		this.Triangulo = pol;
	}

	/**
	 * Constrói um triângulo com uma dada cor de linha (preto por padrão)
	 * 
	 * @param pol
	 *            Polígono (triângulo) que será construído
	 * @param corLinha
	 *            Cor do contorno da figura
	 */
	public Triangulo(Polygon pol, Color corLinha) {
		this.Triangulo = pol;
		this.setCorDeLinha(corLinha);
	}

	/**
	 * Constrói um triângulo com determinada cor de linha e de preenchimento
	 * 
	 * @param pol
	 *            Polígono (triângulo) que será construído
	 * @param corLinha
	 *            Cor do contorno da figura
	 * @param cor
	 *            Cor de preenchimento da figura
	 */
	public Triangulo(Polygon pol, Color corLinha, Color cor) {
		this.Triangulo = pol;
		this.setCorDeLinha(corLinha);
		this.setCor(cor);
	}

	@Override
	public void construirObject(Point startDrag, Point endDrag) {
		int midx;
		int midy;
		if (startDrag.x > endDrag.x) {
			midx = endDrag.x + (Math.abs(startDrag.x - endDrag.x));
			midy = endDrag.y;

		} else {
			midx = endDrag.x - (Math.abs(startDrag.x - endDrag.x));
			midy = endDrag.y;
		}
		int[] xs = { startDrag.x, endDrag.x, midx };
		int[] ys = { startDrag.y, endDrag.y, midy };
		Polygon pol = new Polygon(xs, ys, xs.length);
		this.setTriangulo(pol);

	}

	@Override
	public void editarObject(Point startDrag, Point endDrag) {
		int midx;
		int midy;
		if (startDrag.x > endDrag.x) {
			midx = endDrag.x + (Math.abs(startDrag.x - endDrag.x));
			midy = endDrag.y;

		} else {
			midx = endDrag.x - (Math.abs(startDrag.x - endDrag.x));
			midy = endDrag.y;
		}
		int[] xs = { startDrag.x, endDrag.x, midx };
		int[] ys = { startDrag.y, endDrag.y, midy };
		Polygon pol = new Polygon(xs, ys, xs.length);
		this.setTriangulo(pol);

	}

	@Override
	public void desenhar(Graphics g) {
		if (getCor() == null) {
			g.setColor(getCorDeLinha());
			g.drawPolygon(getTriangulo());
		} else {
			g.setColor(getCor());
			g.fillPolygon(getTriangulo());
		}
	}

	@Override
	public void redesenhar(int x1, int y1, int x2, int y2) {
		int midx;
		int midy;
		if (x1 > x2) {
			midx = x2 + (Math.abs(x1 - x2));
			midy = y2;
		} else {
			midx = x2 - (Math.abs(x1 - x2));
			midy = y2;
		}
		int[] xs = { x1, x2, midx };
		int[] ys = { y1, y2, midy };
		Polygon pol = new Polygon(xs, ys, xs.length);
		this.setTriangulo(pol);
	}

	@Override
	public boolean contemPonto(Point p) {
		return getTriangulo().contains(p);
	}
	
	@Override
	public void writetoFile(BufferedWriter b) throws IOException {
		try {
			b.write(getClass().getSimpleName() + ";");
			b.write(getTriangulo().xpoints[0] + ";" + getTriangulo().ypoints[0] + ";");
			b.write(getTriangulo().xpoints[1] + ";" + getTriangulo().ypoints[1] + ";");
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
	 * Getters and Setters
	 */

	public Polygon getTriangulo() {
		return Triangulo;
	}

	public void setTriangulo(Polygon trglo) {
		Triangulo = trglo;
	}

}
