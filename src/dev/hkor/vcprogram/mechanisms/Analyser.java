package dev.hkor.vcprogram.mechanisms;

import dev.hkor.vcprogram.algorithm.Algorithm;
import dev.hkor.vcprogram.mathobjects.Graph;

public class Analyser {
	
	public Graph doAction(Graph graph, Algorithm algorithm){
		return algorithm.method(graph);
	}
}
