package dev.hkor.vcprogram.mathobjects;

//elementy tworz¹ce listê wierzcho³ków-s¹siadów
public class NeighbourListElement {
	private Vertex vertex; //wierzcho³ek, jako w³aœciwy element listy
	private NeighbourListElement next; //nastêpny element listy
	
	public NeighbourListElement(Vertex vertex) {
		this.vertex = vertex;
		next = null;
	}
	
	public void addVertex(Vertex vertex){
		NeighbourListElement temp = this;
		while(temp.next != null) temp = temp.next;
		temp.next = new NeighbourListElement(vertex);
	}
	
	//metoda wspomagaj¹ca przechodzenie przez listê
	public NeighbourListElement getNext(){
		return next;
	}

	public Vertex getVertex() {
		return vertex;
	}
}
