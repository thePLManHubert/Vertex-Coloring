package dev.hkor.vcprogram;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferStrategy;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import dev.hkor.vcprogram.algorithm.LF;
import dev.hkor.vcprogram.display.Display;
import dev.hkor.vcprogram.display.Menu;
import dev.hkor.vcprogram.mathobjects.Graph;
import dev.hkor.vcprogram.mechanisms.Analyser;
import dev.hkor.vcprogram.mechanisms.GraphCreator;

public class Program implements Runnable {

	private Display display;
	private Menu menu;
	public int width, height, windowSize;
	private String titleM, titleD;
	private JFileChooser openFileChooser;
	private String path;
	private Graph graph;
	private int fps = 60;
	
	private boolean openedDisplay = false;
	private boolean running = false;
	private Thread thread;
	
	private BufferStrategy bs;
	private Graphics g;
	
	public Program(String titleM, String titleD, int width, int height, int windowSize){
		this.width = width;
		this.height = height;
		this.titleM = titleM;
		this.titleD = titleD;
		this.windowSize = windowSize;
	}
	
	private void init(){
		menu = new Menu(titleM, width, height);
		menuActions();
	}
	
	private void menuActions(){
		
		openFileChooser = new JFileChooser();
		openFileChooser.setCurrentDirectory(new File("."));
		openFileChooser.setFileFilter(new FileNameExtensionFilter("TXT", "txt"));
		openFileChooser.setDialogTitle("Otwórz");
		
		menu.getOpenButton().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(openFileChooser.showOpenDialog(menu.getOpenButton()) == JFileChooser.APPROVE_OPTION){
					path = openFileChooser.getSelectedFile().getAbsolutePath();
					menu.getLabel().setText(path);
					if(path != null){
						GraphCreator gc = new GraphCreator();
						try{
							graph = gc.createGraph(path);
							graph.sortVertices();
							menu.getTextArea().setText(graph.toString());
						}catch(Exception ex){	
							menu.getTextArea().setText(" Podano nieprawid³owo sformatowany plik.");
						}
						
					}
				}
			}
		});
		
		menu.getAnalyseButton().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
					if(graph != null){
						if(!graph.isColored()){
							Analyser a = new Analyser();
							graph = a.doAction(graph, new LF());
							menu.getTextArea().setText(graph.toString());
						}
					}
				}
		});
		
		menu.getShowButton().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(display == null)
					display = new Display(titleD, windowSize);
				else
					display.getFrame().setVisible(true);
				openedDisplay = true;
				
			}
		});
		
		menu.getResetButton().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
					if(graph != null){
						graph.resetColors();
						menu.getTextArea().setText(graph.toString());
					}
				}
		});
	}
	
	private void update(){
		
	}
	
	private void render(){
		bs = display.getCanvas().getBufferStrategy();
		if(bs == null){
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		//Resize screen
		Dimension d = display.getFrame().getSize();
		display.getFrame().setSize(d.width, d.height);
		//Clear Screen
		g.clearRect(0, 0, d.width, d.height);
		//Draw Here!
		
		if(graph != null){
			graph.setVertices(30, 30, d.width, d.height);
			graph.draw(g);
		}
		
		//End Drawing!
		bs.show();
		g.dispose();
	}
	
	public void run(){
		
		init();
		double timePerTick = 1000000000 / fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		int ticks = 0;
		
		while(running){
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			timer += now - lastTime;
			lastTime = now;
			
			if(delta >= 1){
				if(openedDisplay){
					update();
					render();
				}
				ticks = ticks + 1;
				delta--;
			}
			
			if(timer >= 1000000000){
				ticks = 0;
				timer = 0;
			}
		}
		
		stop();
		
	}
	
	public synchronized void start(){
		if(running)
			return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public synchronized void stop(){
		if(!running)
			return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}