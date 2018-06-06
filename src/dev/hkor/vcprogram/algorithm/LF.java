package dev.hkor.vcprogram.algorithm;
import java.awt.Color;
import dev.hkor.vcprogram.mathobjects.Graph;
import dev.hkor.vcprogram.mathobjects.NeighbourListElement;
import dev.hkor.vcprogram.mechanisms.ColorTranslator;

public class LF extends Algorithm {
	
	ColorTranslator ct = new ColorTranslator();
	private Color[] colorPalette = ct.getColorPalette();
	
	@Override
	public Graph method(Graph graph) {
		
		sortVertices(graph);
		boolean[] C = new boolean[colorPalette.length];
		int[] CT = new int[graph.getSize()];
		NeighbourListElement temp = null;
		
		int i, v;
		
		for(i = 0; i < graph.getSize(); i++){
			CT[i] = -1; // Oznaczenie braku przypisania koloru
		}

		  graph.getList()[0].getVertex().setColor(colorPalette[0]);  // Wierzcho�ek startowy
		  CT[0] = 0;		// Przydzielamy kolor do wierzcho�ka startowego w pomocniczej tabeli
		  
		  for(v = 1; v < graph.getSize(); v++)          // Przegl�damy reszt� grafu
		  {
			  for(i = 0; i < colorPalette.length; i++) C[i] = false;
			  while((temp = graph.getList()[v].getNext()) != null){  // Sprawdzamy s�siad�w obecnego wierzcho�ka
				  if(temp.getVertex().hasColor()){ // Je�li ma przypisany kolor
					  for(i = 0; i < graph.getSize(); i++) //sprawd� kt�ry to element
						  if(temp.getVertex().getLabel().compareTo(graph.getList()[i].getVertex().getLabel()) == 0){
							  C[CT[i]] = true; // Oznacz kolor jako niedost�pny
							  break;
						  }
				  }
			  }
			  graph.getList()[v].resetViewing();

			  for(i = 0; i < colorPalette.length; i++){        // Szukamy wolnego koloru
				  if(!C[i]) {
					  graph.getList()[v].getVertex().setColor(colorPalette[i]); // Przypisujemy go bie��cemu wierzcho�kowi
					  CT[v] = i;
					  break;
				  }
			  }
		  }
		  graph.setColored(true);
		  return graph;
	}
	
	private void sortVertices(Graph graph){
		graph.sortVertices();
	}

}