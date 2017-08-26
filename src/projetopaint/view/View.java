/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetopaint.View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Icon;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import projetopaint.Model.*;
import projetopaint.*;

/**
 * Classe responsável pela criação da interface gráfica de interação com o usuário (GUI).
 * Denominamos-a como View, de acordo com o padrão MVC (Model View Controller).
 * 
 * Aqui foi colocada, também, o método main
 *
 * @author Mayara Machado
 * @author Luiz Felipe Souza
 */
public class View extends javax.swing.JFrame {

	/**
	 * Creates new form View
	 */
	public static String selecionarForma = "";
	public static Color selecionarCor = Color.black;
	public static List<Desenho> paint = new ArrayList<Desenho>();
	private JPanel painelDesenho;
	public static View frame;
	JFileChooser fc;
	static JPanel panel;
	
	/**
	 * O construtor é responsável por criar a tela (Frame) e seus componentes: 
	 * Botões, submenus, etc.
	 */
	public View() {
		initComponents();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Paint - Programação Orientada a objetos");
		setSize(800, 650);
		setMinimumSize(getSize());

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnMenu = new JMenu("Menu");
		menuBar.add(mnMenu);

		/*
		 * Sub item menu criar novo painel de pintura
		 */
		JMenuItem mntmNew = new JMenuItem("New");
                mntmNew.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
                mntmNew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/projetopaint/images/page_add.png")));
		mntmNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				selecionarForma = "";
				paint.clear();
				repaint();
			}
		});
		mnMenu.add(mntmNew);

		/*
		 * Sub item menu abrir arquivo de pintura
		 */
		JMenuItem mntmAbrir = new JMenuItem("Abrir");
		mntmAbrir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
                mntmAbrir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/projetopaint/images/page_go.png")));
		mntmAbrir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Controller.abrirDesenho(mntmAbrir);
                                repaint();
			}
		});
		mnMenu.add(mntmAbrir);

		/*
		 * Sub item menu salvar pintura
		 */
		JMenuItem mntmSave = new JMenuItem("Salvar");
		mntmSave.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
                mntmSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/projetopaint/images/page_white_put.png")));
		mntmSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Controller.salvarDesenho(mntmSave);
			}
		});
		mnMenu.add(mntmSave);

		/*
		 * SubItem menu fechar janela
		 */
		JMenuItem mntmSair = new JMenuItem("Exit");
		mntmSair.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.ALT_MASK));
		mntmSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				selecionarForma = "Exit";
				System.exit(0);
			}
		});
		mnMenu.add(mntmSair);

		/*
		 * Painel para desenho
		 */

		painelDesenho = new JPanel();
		painelDesenho.setBorder(new EmptyBorder(5, 5, 5, 5));
		painelDesenho.setLayout(new BorderLayout(0, 0));
		painelDesenho.setBackground(Color.white);
		setContentPane(painelDesenho);

		panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel.setBackground(Color.white);
		painelDesenho.add(panel, BorderLayout.NORTH);
		
                JPanel panelRodape = new JPanel();
		FlowLayout flowLayout2 = (FlowLayout) panel.getLayout();
		flowLayout2.setAlignment(FlowLayout.CENTER);
		panelRodape.setBackground(Color.LIGHT_GRAY);
		painelDesenho.add(panelRodape, BorderLayout.SOUTH);
                
                

                JLabel corp = new JLabel();
                JLabel corp2 = new JLabel( "© Luiz Felipe Souza e Mayara Machado");
                corp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/projetopaint/images/cup.png")));
                panelRodape.add(corp);
                panelRodape.add(corp2);

		/*
		 * Botão para liberar o Mouse
		 */
		JButton btnLMou = new JButton("Liberar Mouse");
		btnLMou.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				selecionarForma = "";
			}
		});
		panel.add(btnLMou);
		/*
		 * Botão de selecionar Retangulo
		 */
		JButton btnRet = new JButton("Retangulo");
		btnRet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				selecionarForma = "Retangulo";
			}
		});
		panel.add(btnRet);

		/*
		 * Botão de selecionar Triangulo
		 */
		JButton btnTri = new JButton("Triangulo");
		btnTri.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selecionarForma = "Triangulo";
			}
		});
		panel.add(btnTri);

		/*
		 * Botão de selecionar circulo
		 */
		JButton btnCir = new JButton("Circulo");
		btnCir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				selecionarForma = "Circulo";
			}
		});
		panel.add(btnCir);

		/*
		 * Botão para preencher a forma
		 */
		JButton btnPintar = new JButton("Pintar");
		btnPintar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selecionarForma = "pintar";
			}
		});
		panel.add(btnPintar);

		/*
		 * Botão para editar forma
		 */
		JButton btnEditar = new JButton("Resize");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				selecionarForma = "editar";
			}
		});
		panel.add(btnEditar);

		/*
		 * Botão apagar forma
		 */
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				selecionarForma = "Delete";
			}
		});
		panel.add(btnDelete);

		/*
		 * Inicio dos botões da paleta de cores
		 */
		Dimension SizeColorBox = new Dimension(20, 20);

		JButton btnC_Red = new JButton();
		btnC_Red.setPreferredSize(SizeColorBox);
		btnC_Red.setBackground(Color.red);
		btnC_Red.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selecionarCor = Color.red;
			}
		});
		panel.add(btnC_Red);

		JButton btnC_Blue = new JButton();
		btnC_Blue.setPreferredSize(SizeColorBox);
		btnC_Blue.setBackground(Color.blue);
		btnC_Blue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selecionarCor = Color.blue;
			}
		});
		panel.add(btnC_Blue);

		JButton btnC_Green = new JButton();
		btnC_Green.setPreferredSize(SizeColorBox);
		btnC_Green.setBackground(Color.green);
		btnC_Green.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selecionarCor = Color.green;
			}
		});
		panel.add(btnC_Green);

		JButton btnC_Cyan = new JButton();
		btnC_Cyan.setPreferredSize(SizeColorBox);
		btnC_Cyan.setBackground(Color.cyan);
		btnC_Cyan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selecionarCor = Color.cyan;
			}
		});
		panel.add(btnC_Cyan);

		JButton btnC_DarkGray = new JButton();
		btnC_DarkGray.setPreferredSize(SizeColorBox);
		btnC_DarkGray.setBackground(Color.darkGray);
		btnC_DarkGray.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selecionarCor = Color.darkGray;
			}
		});
		panel.add(btnC_DarkGray);

		JButton btnC_Orange = new JButton();
		btnC_Orange.setPreferredSize(SizeColorBox);
		btnC_Orange.setBackground(Color.orange);
		btnC_Orange.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selecionarCor = Color.orange;
			}
		});
		panel.add(btnC_Orange);

		JButton btnC_Pink = new JButton();
		btnC_Pink.setPreferredSize(SizeColorBox);
		btnC_Pink.setBackground(Color.pink);
		btnC_Pink.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selecionarCor = Color.pink;
			}
		});
		panel.add(btnC_Pink);

		JButton btnC_LightGray = new JButton();
		btnC_LightGray.setPreferredSize(SizeColorBox);
		btnC_LightGray.setBackground(Color.lightGray);
		btnC_LightGray.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selecionarCor = Color.lightGray;
			}
		});
		panel.add(btnC_LightGray);

		/*
		 * Fim dos botões da paleta de cores
		 */

		painelDesenho.add(new Controller(), BorderLayout.CENTER);
		validate();

	}

	/**
	 * Janela de erro 
	 * @param exception Mensagem de erro a ser exibida
	 */
	public static void mostrarErro(String exception) {
		String mensagem;
		switch (exception) {
		case "Escrita":
			mensagem = "Não foi possível salvar o arquivo";
			break;
		case "Leitura":
			mensagem = "Não foi possível encontrar o arquivo";
		default:
			mensagem = "Ops! Algum erro desconhecido ocorreu!";
		}
		// Exibe uma mensagem ao usuário caso haja algum erro de IOException
		JOptionPane.showMessageDialog(panel, mensagem, "Erro!", JOptionPane.ERROR_MESSAGE);
	}
	
	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated
	// Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 400, Short.MAX_VALUE));
		layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 300, Short.MAX_VALUE));

		pack();
	}// </editor-fold>//GEN-END:initComponents

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String args[]) {
		/* Set the Nimbus look and feel */
		// <editor-fold defaultstate="collapsed" desc=" Look and feel setting
		// code (optional) ">
		/*
		 * If Nimbus (introduced in Java SE 6) is not available, stay with the
		 * default look and feel. For details see
		 * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.
		 * html
		 */
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		// </editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new View().setVisible(true);
			}
		});
	}

	// Variables declaration - do not modify//GEN-BEGIN:variables
	// End of variables declaration//GEN-END:variables
}
