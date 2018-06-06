package dev.hkor.vcprogram.display;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Menu {
	
	private JFrame frame;
	private JPanel panel;
	private JButton openButton;
	private JButton analyseButton;
	private JButton showButton;
	private JButton resetButton;
	private JTextArea textArea;
	private JScrollPane scrollPane;
	private JLabel label;
	
	private String title;
	private int width, height;
	
	public Menu(String title, int width, int height){
		this.title = title;
		this.width = width;
		this.height = height;
		
		createMenu();
	}
	
	private void createMenu(){
		frame = new JFrame(title);
		frame.setSize(width, height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		
		panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		
		scrollPane = new JScrollPane(textArea);
		scrollPane.setPreferredSize(new Dimension(250, 180));
		
		openButton = new JButton("Otwórz plik...");
		analyseButton = new JButton("Koloruj");
		showButton = new JButton("Poka¿ graf");
		resetButton = new JButton("Resetuj");
		label = new JLabel();
		
		panel.add(openButton);
		panel.add(analyseButton);
		panel.add(new JLabel());
		panel.add(showButton);
		panel.add(resetButton);
		panel.add(label);
		panel.add(scrollPane, BorderLayout.SOUTH);
		frame.add(panel);
		frame.setVisible(true);
	}

	//Gettery i Settery
	public JPanel getPanel() {
		return panel;
	}

	public JButton getOpenButton() {
		return openButton;
	}

	public JButton getAnalyseButton() {
		return analyseButton;
	}

	public JButton getShowButton() {
		return showButton;
	}
	
	public JButton getResetButton() {
		return resetButton;
	}

	public JLabel getLabel() {
		return label;
	}
	
	public JTextArea getTextArea(){
		return textArea;
	}

}
