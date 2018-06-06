package dev.hkor.vcprogram.mathobjects;

//elementy tworz�ce list� wierzcho�k�w-s�siad�w
public class NeighbourListElement {
	private Vertex vertex; //wierzcho�ek, jako w�a�ciwy element listy
	private NeighbourListElement next; //nast�pny element listy
	
	public NeighbourListElement(Vertex vertex) {
		this.vertex = vertex;
		next = null;
	}
	
	public void addVertex(Vertex vertex){
		NeighbourListElement temp = this;
		while(temp.next != null) temp = temp.next;
		temp.next = new NeighbourListElement(vertex);
	}
	
	//metoda wspomagaj�ca przechodzenie przez list�
	public NeighbourListElement getNext(){
		return next;
	}

	public Vertex getVertex() {
		return vertex;
	}
}
