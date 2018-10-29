package aStarPathFinder;

import com.badlogic.gdx.ai.pfa.Heuristic;

public class HeuristicImp implements Heuristic<Node>{

	
	//Berechnet die Distanz zwischen zwischen dem Anfangs- und dem Endknoten
	// Sucht nach dem kürzesten Wert
	// Manhattan Berechnung X + Y als ein Wert hinzugefügt und dieser wird 
	//dann mit anderen Graphen verglichen
	//To-Do: Muss in die Seminararbeit
	//Kommt auch schon in anderen Klassen vor (GraphGenerator)
	
	
	
	@Override
	public float estimate(Node startNode, Node endNode) {
		// TODO Auto-generated method stub
		
		int startIndex = startNode.getIndex();
		int endIndex = endNode.getIndex();
		
		int startY = startIndex / LevelManager.lvlTileWidth;
		int startX = startIndex % LevelManager.lvlTileWidth;
		
		int endY = endIndex / LevelManager.lvlTileWidth;
		int endX = endIndex % LevelManager.lvlTileWidth;
		
		
		float distance = Math.abs(startX - endX) + Math.abs(startY - endY);
		
		return distance;
	}

	
}
