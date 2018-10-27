package aStarPathFinder;

import com.badlogic.gdx.ai.pfa.Connection;
import com.badlogic.gdx.ai.pfa.indexed.*;
import com.badlogic.gdx.utils.Array;


public class GraphImp implements IndexedGraph<Node>{
	private Array<Node> nodes = new Array<Node>();
	
	public GraphImp(Array<Node> nodes)
	{
		super();
	}
	/*
	public GraphImp (int capacity)
	{
		super(capacity);
	}
	
	public GraphImp(Array<Node> nodes)
	{
		super(nodes);
		this.nodes = nodes;
	}	
	*/
	
	public Node getNodeByXY(int x, int y)
	{
		int modX = x / LevelManager.tilePixelWidth;
		int modY = y / LevelManager.tilePixelHeight;
		
		//Wandelt die Pixel in Tiles (Kacheln) um
		
		return nodes.get(LevelManager.lvlTileWidth*modY+ modX);
	}
	@Override
	public Array<Connection<Node>> getConnections(Node fromNode) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int getIndex(Node node) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int getNodeCount() {
		// TODO Auto-generated method stub
		return 0;
	}
	

	

	
}
