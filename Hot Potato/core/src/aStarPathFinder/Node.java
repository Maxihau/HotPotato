package aStarPathFinder;

import com.badlogic.gdx.ai.pfa.Connection;
import com.badlogic.gdx.ai.pfa.indexed.IndexedGraph;
import com.badlogic.gdx.utils.Array;

public class Node implements IndexedGraph<Node>{

	
	//Beziehungen zwischen den einzelnen Knoten
	private Array<Connection<Node>> connections = new Array<Connection<Node>>();
	public int type;
	public int index;

	
	public Node()
	{
		index = Node.Indexer.getIndex(); //Jeder Knoten bekommt einen eigenen Index
	}
	
	//Erstellt die Beziehung zwischen den Knoten
	
	
	public void createConnection(Node toNode, float cost)
	{
		connections.add(new ConnectionImp(this, toNode, cost));
		//Verbindung von Knoten 1 zu Knoten 2 mit den dazugeh�rigen "Kosten" f�r die Bewegung der KI
		
	}	
	
	private static class Indexer
	{
		private static int index = 0;
		public static int getIndex()
		{
			return index++;
		}
	}
	public static class Type 
	{
		public static final int REGULAR = 1;
	}
	
	
	@Override
	public Array<Connection<Node>> getConnections(Node fromNode) {
		// TODO Auto-generated method stub
		return connections;
	}

	@Override
	public int getIndex(Node node) {
		// TODO Auto-generated method stub
		return index;
	}

	@Override
	public int getNodeCount() {
		// TODO Auto-generated method stub
		return 0;
	}
	

}