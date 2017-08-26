/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetopaint;

import java.awt.Color;
import java.awt.Graphics;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import javax.swing.JComponent;
import javax.swing.JFileChooser;

import projetopaint.Model.Circulo;
import projetopaint.Model.Desenho;
import projetopaint.Model.Figuras2D;
import projetopaint.Model.Retangulo;
import projetopaint.Model.Triangulo;
import projetopaint.View.View;

/**
 * Classe responsável pela interação da View com as Models, de acordo com o
 * padrão MVC (Model View Controller).
 *
 * @author Mayara Machado
 * @author Luiz Felipe Souza
 */
public class Controller extends JComponent {

	public Point startDrag, endDrag;
	public Desenho dtemp; // instancia temporaria de Desenho
	private static JFileChooser fc;

	/**
	 * O construtor é responsável por "escutar" as interações com a view e
	 * alterar os atributos da respectiva Model. Além disso, as modiicaçãoes da
	 * Model refletem em sua exibição na view. Esta responsabilidade também é
	 * delegada à Controller
	 */
	public Controller() {
		this.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				// Posição inicial onde o mouse foi clicado
				startDrag = new Point(e.getX(), e.getY());

				endDrag = startDrag;
				if (View.selecionarForma == "Delete") {
					for (int i = View.paint.size() - 1; i >= 0; i = i - 1) {
						Desenho pt = View.paint.get(i);
						if (pt.contemPonto(startDrag)) {
							dtemp = null; // instancia de Desenho temporaria
							View.paint.remove(pt);
							break;
						}
					}
				} else if (View.selecionarForma == "pintar") {
					for (int i = View.paint.size() - 1; i >= 0; i = i - 1) {
						Desenho fig = View.paint.get(i);
						try {
							Figuras2D f = (Figuras2D) fig;
							if (f.contemPonto(startDrag)) {
								try {
									f.preencher(View.selecionarCor);
								} catch (Exception exc) {
								}
								break;
							}
						} catch (Exception exc) {
						}
					}
				} else if (View.selecionarForma == "editar") {
					for (int i = 0; i < View.paint.size(); i++) {
						Desenho fig = View.paint.get(i);
						if (fig.contemPonto(startDrag)) {
							dtemp = fig;
							View.paint.remove(fig);
							break;
						}
					}
				}
			}

			public void mouseReleased(MouseEvent e) {
				// Posição final quando o mouse solta
				Point p = new Point(e.getX(), e.getY());

				Figuras2D figura;
				// Usei switch/case pra reaproveitar a variável figura
				switch (View.selecionarForma) {
				case "Retangulo":
					figura = new Retangulo();
					figura.construirObject(startDrag, p);
					figura.setCorDeLinha(View.selecionarCor);
					View.paint.add(figura);
					break;
				case "Triangulo":
					figura = new Triangulo();
					figura.construirObject(startDrag, p);
					figura.setCorDeLinha(View.selecionarCor);
					View.paint.add(figura);
					break;
				case "Circulo":
					figura = new Circulo();
					figura.construirObject(startDrag, p);
					figura.setCorDeLinha(View.selecionarCor);
					View.paint.add(figura);
					break;
				case "editar":
                                    try{
					dtemp.editarObject(startDrag, p);
					View.paint.add(dtemp);
                                    }catch(NullPointerException npex){}
				default:
					break;
				}

				startDrag = null;
				endDrag = null;
				repaint(); // atualizar o componente
			}
		});

		/*
		 * Método que fica atualizando enquanto o mouse é arrastado
		 */
		this.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseArrastar(MouseEvent e) {
				endDrag = new Point(e.getX(), e.getY());
				repaint();
			}
		});
	}

	/**
	 * Pega o ponto de inicio de arraste e o ponto em que solta e contrói o
	 * desenho
	 * 
	 * @param Graphics
	 *            g
	 * @return void
	 */
	public void paint(Graphics g) {

		for (Desenho ds : View.paint) {
			ds.desenhar(g);
		}

		// Usei switch/case pra reaproveitar a variável figura
		if (startDrag != null && endDrag != null) {
			Figuras2D figura;
			switch (View.selecionarForma) {
			case "Retangulo":
				figura = new Retangulo();
				figura.construirObject(startDrag, endDrag);
				figura.desenhar(g);
				break;
			case "Circulo":
				figura = new Circulo();
				figura.construirObject(startDrag, endDrag);
				figura.desenhar(g);
			case "Triangulo":
				figura = new Triangulo();
				figura.construirObject(startDrag, endDrag);
				figura.desenhar(g);
			default:
				break;
			}

		}
	}

	/**
	 * Método chamado quando o subitem "Abrir" é clicado
	 * 
	 * @param parent
	 *            O componente pai da janela de abrir
	 */
	public static void abrirDesenho(JComponent parent) {
		
		// JFileChooser é a janela de seleção de arquivo
		JFileChooser fc = new JFileChooser();
		
		// Configura o diretorio inicial
		fc.setCurrentDirectory(new File("C:\\Users\\No one\\Desktop\\Trabalho POO"));
		
		/*
		 * Exibe um JFileChooser para abertura de arquivo Retorna um inteiro que
		 * indica se o usuario selecionou um arquivo
		 */
		int returnVal = fc.showOpenDialog(null);

		/*
		 * A variavel estatica APPROVE_OPTION retorna um inteiro se uma
		 * opcao de sim ou nao for selecionada (open ou cancel no caso)
		 */
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			// getSelectedFile() retorna o arquivo selecionado
			File arquivo = fc.getSelectedFile();
			// Adicionar algo para exibir o file na tela

			try {
				FileInputStream k = new FileInputStream(arquivo);
				InputStreamReader in = new InputStreamReader(k);

				BufferedReader br = new BufferedReader(in);
				String st = br.readLine();

				if (st == null) {
					View.mostrarErro("Leitura");
				} else {
					while (st != null) {
						String[] a = st.split(";");
                                                
                                                try{

						if (a[0].equals("Retangulo")) {
							Retangulo r = new Retangulo();
							r.redesenhar(Integer.parseInt(a[1].toString()), Integer.parseInt(a[2].toString()),
									Integer.parseInt(a[3].toString()), Integer.parseInt(a[4].toString()));
							r.setCorDeLinha(
									new Color(Integer.parseInt(a[5]), Integer.parseInt(a[6]), Integer.parseInt(a[7])));
							if (a[8].equals("null")) {
								r.setCor(null);
							} else {
								r.setCor(new Color(Integer.parseInt(a[8]), Integer.parseInt(a[9]),
										Integer.parseInt(a[10])));
							}
							View.paint.add(r);

						} else if (a[0].equals("Circulo")) {
							Circulo c = new Circulo();
							c.redesenhar(Integer.parseInt(a[1]), Integer.parseInt(a[2]), Integer.parseInt(a[3]),
									Integer.parseInt(a[4]));
							c.setCorDeLinha(
									new Color(Integer.parseInt(a[5]), Integer.parseInt(a[6]), Integer.parseInt(a[7])));
							if (a[8].equals("null")) {
								c.setCor(null);
							} else {
								c.setCor(new Color(Integer.parseInt(a[8]), Integer.parseInt(a[9]),
										Integer.parseInt(a[10])));
							}
							View.paint.add(c);

						} else if (a[0].equals("Triangulo")) {
							Triangulo t = new Triangulo();
							t.redesenhar(Integer.parseInt(a[1]), Integer.parseInt(a[2]), Integer.parseInt(a[3]),
									Integer.parseInt(a[4]));
							t.setCorDeLinha(
									new Color(Integer.parseInt(a[5]), Integer.parseInt(a[6]), Integer.parseInt(a[7])));
							if (a[8].equals("null")) {
								t.setCor(null);
							} else {
								t.setCor(new Color(Integer.parseInt(a[8]), Integer.parseInt(a[9]),
										Integer.parseInt(a[10])));
							}
							View.paint.add(t);

						}
                                                }catch(NumberFormatException nfex){
                                                    View.mostrarErro("Leitura");}
						st = br.readLine();
					}
				}
				br.close();
			} catch (IOException ex) {
				View.mostrarErro("Leitura");
			}

		}
	}

	/**
	 * Método chamado quando o subitem "Salvar" é clicado
	 * 
	 * @param parent
	 *            O componente pai da janela de abrir
	 */
	public static void salvarDesenho(JComponent parent) {
		String nomeArquivo;

		fc = new JFileChooser();
		fc.setCurrentDirectory(new File("C:\\Users\\No one\\Desktop\\Trabalho POO"));
		
		// Seleciona os formatos válidos de arquivo
		// fc.setFileFilter(new FileNameExtensionFilter("Arquivos de
		// serialização", ".dat", ".txt"));
		int returnVal = fc.showSaveDialog(parent);
		nomeArquivo = fc.getSelectedFile() + ".txt";

		if (returnVal == JFileChooser.APPROVE_OPTION) {

			/*
			 * Percorre a lista desenho e chama o método de escrita em arquivo
			 * de cada objeto da lista
			 */
			for (Desenho desenho : View.paint) {
				try {
					OutputStream os = new FileOutputStream(nomeArquivo, true);
					OutputStreamWriter osw = new OutputStreamWriter(os);
					BufferedWriter writer = new BufferedWriter(osw);
					
					desenho.writetoFile(writer);
					
				} catch (IOException e) {
					View.mostrarErro("Escrita");
				}
				
			}

		}
	}
}
