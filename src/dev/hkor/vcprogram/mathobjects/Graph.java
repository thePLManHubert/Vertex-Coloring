package dev.hkor.vcprogram.mathobjects;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import dev.hkor.vcprogram.mechanisms.ColorTranslator;

public class Graph {
	private int size; //liczba wierzcho³ków
	private int counter = 0;
	private NeighbourList[] list;
	private boolean colored = false;
	
	public Graph(int size){
		this.size = size;
		list = new NeighbourList[size];
	}
	
	//dodaje wierzcho³ek z list¹ jego s¹siadów do grafu
	public void setVertex(NeighbourList nblist){
		if(counter < size){
			list[counter] = nblist;
			counter++;
		}
	}

	public int getSize() {
		return size;
	}
	
	public NeighbourList[] getList(){
		return list;
	}
	
	public void draw(Graphics g){
		//rysowanie krawêdzi
		for(int i = 0; i<size; i++){
			Vertex current = list[i].getVertex();
			NeighbourListElement temp;
			while((temp = list[i].getNext()) != null){
				g.drawLine((int)(current.getX()+current.getWidth()/2),
						(int)(current.getY()+current.getHeight()/2),
						(int)(temp.getVertex().getX()+temp.getVertex().getWidth()/2),
						(int)(temp.getVertex().getY()+temp.getVertex().getHeight()/2));
			}
			list[i].resetViewing();
		}
		//rysowanie wierzcho³ków i etyket
		for(NeighbourList elem : list){
			elem.getVertex().draw(g);
			
			if(!colored) g.setColor(Color.BLUE);
			else g.setColor(Color.WHITE);
			
			g.setFont(new Font("Arial", Font.BOLD, 13));
			g.drawString(elem.getVertex().getLabel(), 
					(int)(elem.getVertex().getX()+elem.getVertex().getWidth()/2), 
					(int)(elem.getVertex().getY()+elem.getVertex().getHeight()/2));
		}
		
	}
	
	public void setVertices(int width, int height, int windowWidth, int windowHeight){
		
		int R = (int)(((windowWidth>windowHeight)?windowHeight:windowWidth)/3);
		int propH = (int)(windowWidth/400);
		int propV = (int)(windowHeight/400);
		int shiftH = (int)(R*7/6+propH*(width/2.0));
		int shiftV = (int)(R*7/6+propV*(height/2.0));
		
		double alpha = 2*Math.PI/size;
		
		for(int i = 0; i<size; i++){
			
			float x = (float)(shiftH+R*Math.cos(alpha*i));
			float y = (float)(shiftV+R*Math.sin(alpha*i));
			
			list[i].getVertex().setPosition(x, y);
			list[i].getVertex().setDimentions(width, height);
		}
		
	}
	
	//sortowanie wierzcho³ków malej¹co pod wzglêdem stopnia
	public void sortVertices(){
		NeighbourList temp;
		int zmiana;
		do {
			zmiana = 0;
			for(int i = 0; i < list.length-1; i++){
				if(list[i].compareTo(list[i+1]) < 0) {
					temp = list[i+1];
					list[i+1] = list[i];
					list[i] = temp;
					zmiana++;
				}
			}
		} while(zmiana > 0);
	}
	
	public void resetColors(){
		for(NeighbourList elem : list){
			elem.getVertex().setColor(null);
		}
		colored = false;
	}
	
	//Gettery i Settery
	public boolean isColored() {
		return colored;
	}

	public void setColored(boolean colored) {
		this.colored = colored;
	}

	//kod testowy
	
	@Override
	public String toString() {
		String text;
		text = " ***Informacje o grafie***";
		text += String.format("\n ---------------------------------\n\n"
								+ " Liczba wierzcho³ków: %3d\n"
								+ " Stopieñ grafu: %16d\n"
								+ " ---------------------------------\n", 
								size, list[0].getVertex().getDegree());
		if(!colored)
			text += " stan: niepokolorowany";
		else{
			ColorTranslator ct = new ColorTranslator();
			for(NeighbourList elem : list){
				text += String.format(" %s - kolor %s\n", elem.getVertex().getLabel(), ct.translate(elem.getVertex().getColor()));
			}
		}
		return text;
	}
	
	public void show(){
		NeighbourListElement temp;
		System.out.println("Liczba wierzcho³ków: " + size);
		System.out.println("Graf: ");
		for(NeighbourList elem : list){
			elem.getVertex().show();
			System.out.println("----------------");
			while((temp = elem.getNext()) != null){
				temp.getVertex().show();
			}
			System.out.println("****************");
		}
	}
	
}
