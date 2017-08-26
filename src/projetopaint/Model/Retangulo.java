/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetopaint.Model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.BufferedWriter;
import java.io.IOException;

import projetopaint.View.View;

/**
 * Classe Model, de acordo com o padrão MVC (Model View Controller). Essa classe
 * especifica os atributos e comportamento dos objetos Retangulo exibidos na
 * View (A interface gráfica de interação com o usuário).
 *
 * @author Mayara Machado
 * @author Luiz Felipe Souza
 */
public class Retangulo extends Figuras2D {

	private Rectangle ret;

	/*
	 * Construtores do objeto
	 */

	/**
	 * Construtor vazio
	 */
	public Retangulo() {
	}

	/**
	 * Constrói um retângulo a partir do parâmetro passado
	 * 
	 * @param r
	 *            Retângulo a ser construído
	 */
	public Retangulo(Rectangle r) {
		this.ret = r;
	}

	/**
	 * Constrói um retângulo com uma dada cor de linha (preto por padrão)
	 * 
	 * @param r
	 *            Retângulo a ser construído
	 * @param corLinha
	 *            Cor do contorno da figura
	 */
	public Retangulo(Rectangle r, Color corLinha) {
		this.ret = r;
		this.setCorDeLinha(corLinha);
	}

	/**
	 * 
	 * @param r
	 *            Retângulo a ser construído
	 * @param corLinha
	 *            Cor do contorno da figura
	 * @param cor
	 *            Cor do preenchimento da figura
	 */
	public Retangulo(Rectangle r, Color corLinha, Color cor) {
		this.ret = r;
		this.setCorDeLinha(corLinha);
		this.setCor(cor);
	}

	@Override
	public void construirObject(Point startDrag, Point endDrag) {
		int x = Math.min(startDrag.x, endDrag.x);
		int y = Math.min(startDrag.y, endDrag.y);
		int w = Math.abs(startDrag.x - endDrag.x);
		int h = Math.abs(startDrag.y - endDrag.y);

		Rectangle r = new Rectangle(x, y, w, h);
		this.setRetangulo(r);
	}

	@Override
	public void editarObject(Point startDrag, Point endDrag) {
		int x = Math.min(startDrag.x, endDrag.x);
		int y = Math.min(startDrag.y, endDrag.y);
		int w = Math.abs(startDrag.x - endDrag.x);
		int h = Math.abs(startDrag.y - endDrag.y);

		Rectangle r = new Rectangle(x, y, w, h);
		this.setRetangulo(r);
	}

	@Override
	public boolean contemPonto(Point p) {
		return this.getRetangulo().contains(p);
	}

	@Override
	public void writetoFile(BufferedWriter b) throws IOException {
		try {
			b.write(getClass().getSimpleName() + ";");
			b.write(getRetangulo().x + ";" + getRetangulo().y + ";" + getRetangulo().width + ";" + getRetangulo().height
					+ ";");
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

	@Override
	public void desenhar(Graphics g) {
		int x = this.getRetangulo().x;
		int y = this.getRetangulo().y;
		int width = this.getRetangulo().width;
		int height = this.getRetangulo().height;

		if (getCor() == null) {
			g.setColor(this.getCorDeLinha());
			g.drawRect(x, y, width, height);
		} else {
			g.setColor(this.getCor());
			g.fillRect(x, y, width, height);
			g.drawRect(x, y, width, height);
		}
	}

	@Override
	public void redesenhar(int x, int y, int w, int h) {
		Rectangle r = new Rectangle(x, y, w, h);
		this.setRetangulo(r);

	}

	/*
	 * Métodos Acessores: getters e setters
	 */
	public Rectangle getRetangulo() {
		return ret;
	}

	public void setRetangulo(Rectangle rect) {
		this.ret = rect;
	}

}
