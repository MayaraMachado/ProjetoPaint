package projetopaint.Model;

import java.awt.Graphics;
import java.awt.Point;
import java.io.BufferedWriter;
import java.io.IOException;

/**
 * Essa interface determina que determinada classe possui características que
 * a possibilita de ser "desenhável" na tela, de acordo com os critérios 
 * definidos pelos autores do software, quais não possuem relação com os
 * critérios da interface Shape do java, exceto em sua implementação indireta.
 * 
 * @author Mayara Machado
 * @author Luiz Felipe Souza
 */
public interface Desenho {

	/**
	 * A figura é desenhada, de fato, a partir desse método
	 * 
	 * @param g
	 *            O tipo de Graphics (figura) que será desenhada
	 */
	public void desenhar(Graphics g);

	/**
	 * Verifica se dado ponto (x,y) existe dentro da figura Consideramos como
	 * área da figura, o alcance em termos de coordenadas do retângulo de
	 * enquadramento da figura.
	 * 
	 * @param p
	 * @return boolean true se a ponto existe detro da figura; false caso
	 *         contrário
	 */
	public boolean contemPonto(Point p);
	
	/**
	 * Redesenha a figura
	 * 
	 * @param x Coordenada x do retângulo de enquadramento
	 * @param y Coordenada y do retângulo de enquadramento
	 * @param w Largura do retângulo de enquadramento
	 * @param h Altura do retângulo de enquadramento
	 */
	public abstract void redesenhar(int x, int y, int w, int h);


	/**
	 * Serializa a figura em um arquivo .dat
	 * 
	 * @param nomeArquivo
	 *            O nome do arquivo onde a figura será salva
	 * @throws IOException
	 */
	public void writetoFile(BufferedWriter b) throws IOException;

	/**
	 * Chamado para construir uma figura que começa no ponto startDrag e
	 * finaliza no ponto endDrag
	 * 
	 * @param startDrag
	 *            O ponto inicial da figura
	 * @param endDrag
	 *            Ponto final da figura
	 */
	public void construirObject(Point startDrag, Point endDrag);

	/**
	 * Chamado para reconstruir uma figura já existente. Esse método irá alterar
	 * as coordenadas de início e final da figura
	 * 
	 * @param startDrag
	 *            O novo ponto inicial da figura
	 * @param endDrag
	 *            O novo ponto final da figura
	 */
	public void editarObject(Point startDrag, Point endDrag);

}
