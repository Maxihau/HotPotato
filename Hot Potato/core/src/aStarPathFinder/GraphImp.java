package aStarPathFinder;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ai.pfa.Connection;
import com.badlogic.gdx.ai.pfa.indexed.*;
import com.badlogic.gdx.utils.Array;

import screens.HPotato;


public class GraphImp implements IndexedGraph<Node>{
	protected Array<Node> nodes = new Array<>();
	protected int capacity;
	HPotato spiel;
	public GraphImp()
	{
		super();
	}
	
	public GraphImp(int capacity)
	{
		this.capacity = capacity;
	}
	
	public GraphImp(Array<Node> nodes) 
	{
        this.nodes = nodes;

       
        for (int x = 0; x < nodes.size; ++x) 
        {
            nodes.get(x).index = x;
        }
    }
	

	
	public Node getNodeByXY(int x, int y)
	{
		int modX = (x / LevelManager.tilePixelWidth);
		int modY = (y / LevelManager.tilePixelHeight);
		
		
		
		
		//Wandelt die Pixel in Knoten um
		//Holt sich den Knoten aus dem Array "nodes" vom Graphen
		
		//Gdx.app.log("Knoten aus X und Y Eingabe(GraphImp)"," "+nodes.get(LevelManager.lvlTileWidth*modY+ modX));
		//Es gibt 900 Knoten insgesamt und es sind verschiedene Knoten
		//Problem bei der Suche irgendwie --> Troubleshooting 
		//Gdx.app.log("Knoten Array Länge (GraphImp)"," "+);
		//Gdx.app.log("Knoten bei Random Position (Index) im Array"," "+nodes.get(561));
		//Gdx.app.log("Knoten bei Random Position (Index) im Array"," "+nodes.get(88));
		
		//Gdx.app.log("TilePixelWidth (GraphImp)", " "+LevelManager.tilePixelWidth);
		//Gdx.app.log("TilePixelHeight(GraphImp)", " "+LevelManager.tilePixelHeight);
		
		
		Gdx.app.log("mlvl tile Width"," "+LevelManager.lvlTileWidth);
		Gdx.app.log("modY"," "+modY);
		Gdx.app.log("modX"," "+modX);
		Gdx.app.log("magic number"," "+(LevelManager.lvlTileWidth * modY + modX));
		
		return nodes.get(LevelManager.lvlTileWidth * modY + modX);
		
	}
	
	
	
	@Override
	public Array<Connection<Node>> getConnections(Node fromNode) {
		// TODO Auto-generated method stub
		return fromNode.getConnections();
	}
	@Override
	public int getIndex(Node node) {
		// TODO Auto-generated method stub
		return nodes.indexOf(node,true);
	}
	@Override
	public int getNodeCount() {
		// TODO Auto-generated method stub
		return nodes.size;
	}
	
	

	

	
}
