package dev.hkor.vcprogram.display;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Display {

	private JFrame frame;
	private Canvas canvas;
	
	private String title;
	private int size;
	
	public Display(String title, int size){
		this.title = title;
		this.size = size;
		
		createDisplay();
	}
	
	private void createDisplay(){
		frame = new JFrame(title);
		frame.setSize(size, size);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.setResizable(true);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(size, size));
		canvas.setMaximumSize(new Dimension(size, size));
		canvas.setMinimumSize(new Dimension(size, size));
		
		frame.add(canvas); 
		frame.pack();
	}

	//Gettery
	public Canvas getCanvas() {
		return canvas;
	}
	
	public JFrame getFrame() {
		return frame;
	}
	
}
