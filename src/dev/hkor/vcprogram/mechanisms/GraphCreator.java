package dev.hkor.vcprogram.mechanisms;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import dev.hkor.vcprogram.mathobjects.Graph;
import dev.hkor.vcprogram.mathobjects.NeighbourList;
import dev.hkor.vcprogram.mathobjects.Vertex;

public class GraphCreator {
	
	private Scanner scanner;
	private Graph graph;
	
	public Graph createGraph(String path){
		
		graph = null;
		
		graph = readFromFile(path);
		
		return graph;
	}
	
	private Graph readFromFile(String path){
			
		Vertex[] vertexTab;
		int numOfVertex;
		
		try {
			scanner = new Scanner(new File(path));
			
			numOfVertex = Integer.parseInt(scanner.nextLine());
			vertexTab = new Vertex[numOfVertex];
			
			graph = new Graph(numOfVertex);
			
			int count = 0;
			
			//wy³uskanie pojedynczych wierzcho³ków z pliku
			while(scanner.hasNextLine()){
				vertexTab[count] = new Vertex(seekVertex(scanner.nextLine()));
				count++;
			}
			scanner.close();
			scanner = new Scanner(new File(path));
			scanner.nextLine();
			count = 0;
			
			//tworzenie list s¹siedztwa
			while(scanner.hasNextLine()){

				String line = scanner.nextLine();
				NeighbourList nblist = new NeighbourList(vertexTab[count]);
				
				int numOfNeighbours = seekNeighbours(line).length;
				
				for(int i = 0; i < numOfNeighbours; i++){
					for(int j = 0; j<numOfVertex; j++){
						if(seekNeighbours(line)[i].equals(vertexTab[j].getLabel())){
							nblist.addNeighbour(vertexTab[j]);
						}
					}
				}
				graph.setVertex(nblist);
				count++;
			}
			scanner.close();
			return graph;
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	private String seekVertex(String line){
		
		String vertex = "";
		char charTab[] = line.toCharArray();
		int count = 0;
		
		while(charTab[count] != '{'){
			vertex += charTab[count];
			count++;
		}
		
		return vertex;
	}
	
	private String[] seekNeighbours(String line){
		
		String[] neighbours;
		String temp = "";
		char charTab[] = line.toCharArray();
		int count = 0;
		
		while(charTab[count] != '{')
			count++;
		count++;
		
		while(charTab[count] != '}'){
			temp += charTab[count];
			count++;
		}

		neighbours = temp.split(",");

		return neighbours;
	}
}











