package dev.hkor.vcprogram.mathobjects;

public class NeighbourList implements Comparable<NeighbourList>{
	private Vertex vertex; //aktualny wierzcho�ek
	private NeighbourListElement first; //pierwszy element listy s�siedztwa
	private NeighbourListElement current; //s�u�y do przegl�dania listy
	
	public NeighbourList(Vertex vertex){
		this.vertex = vertex;
		this.vertex.setDegree(0);
		first = null;
		current = first;
	}
	
	public void addNeighbour(Vertex vertex){
		if(first == null){
			first = new NeighbourListElement(vertex);
			current = first;
		}
		else
			first.addVertex(vertex);
		this.vertex.setDegree(this.vertex.getDegree() + 1);
	}
	
	//przechodzenie przez list�
	public NeighbourListElement getNext(){
		NeighbourListElement temp;
		if(current == null)
			return null;
		else{
			temp = current;
			current = current.getNext();
			return temp;
		}
	}
	
	//ustawia przegl�danie listy od pierwszego elementu
	public void resetViewing(){
		current = first;
	}
	
	public Vertex getVertex(){
		return vertex;
	}
	
	public int getNumOfNeighbours(){
		return vertex.getDegree();
	}

	@Override
	public int compareTo(NeighbourList other) {
		return this.getVertex().compareTo(other.getVertex());
	}
	
	//kod testowy
	public void show(){
		NeighbourListElement temp;
		while((temp = getNext()) != null){
			temp.getVertex().show();
		}
		current = first;
	}
}
