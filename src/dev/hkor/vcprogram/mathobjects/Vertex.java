package dev.hkor.vcprogram.mathobjects;

import java.awt.Color;
import java.awt.Graphics;

public class Vertex implements Comparable<Vertex>{
	private float x, y, width, height;
	private String label;
	private Color color;
	private int degree;
	Graphics g;
	
	public void draw(Graphics g){
		this.g = g;
		if(color == null){
			g.setColor(Color.LIGHT_GRAY);
			g.fillOval((int)x, (int)y, (int)width, (int)height);
		}
		else{
			g.setColor(color);
			g.fillOval((int)x, (int)y, (int)width, (int)height);
		}
	}
	
	
	public Vertex(){}
	public Vertex(String label){
		this.label = label;
		degree = 0;
		color = null;
	}
	
	public boolean equals(Vertex other) {
		return (this.getLabel() == other.getLabel());
	}
	
	@Override
	public int compareTo(Vertex other) {
		int compare = 0;
		
		if(this.getDegree() > other.getDegree())
			compare = 1;
		else if(this.getDegree() < other.getDegree())
			compare = -1;
		else compare = 0;
		
		return compare;
	}
	
	public boolean hasColor(){
		boolean has = false;
		if(color != null)
			has = true;
		return has;
	}
	
	//Gettery i Settery
	public int getDegree() {
		return degree;
	}
	public void setDegree(int degree) {
		this.degree = degree;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public float getX() {
		return x;
	}
	public void setX(float x) {
		this.x = x;
	}
	public float getY() {
		return y;
	}
	public void setY(float y) {
		this.y = y;
	}
	public void setPosition(float x, float y){
		this.x = x;
		this.y = y;
	}
	public float getWidth() {
		return width;
	}
	public void setWidth(float width) {
		this.width = width;
	}
	public float getHeight() {
		return height;
	}
	public void setHeight(float height) {
		this.height = height;
	}
	public void setDimentions(float width, float height) {
		this.width = width;
		this.height = height;
	}
	public void setG(Graphics g) {
		this.g = g;
	}


	@Override
	public String toString() {
		return String.format("vertex: %s, %d, %s", label, degree, color);
	}
	//funkcje testuj¹ce
	public void show(){
		System.out.println(toString());
	}
}
