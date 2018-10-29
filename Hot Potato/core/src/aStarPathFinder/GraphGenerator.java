package aStarPathFinder;


import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.utils.Array;

public class GraphGenerator {
	
	TiledMap tiledMap;
	
	public static GraphImp generateGraph(TiledMap map)
	{
		
		Array<Node> nodes = new Array<Node>();
		
		
		TiledMapTileLayer tiles = (TiledMapTileLayer)map.getLayers().get(0);
		//Nur dieses Layer??? 
		//Ongoing !!!!!
		
		int mapHeight = LevelManager.lvlTileHeight;
		int mapWidth = LevelManager.lvlTileHeight;
		
		
		//Erstellen hier für jedes Tile (Kachel; 16px * 16 px) einen Knoten; Angefangen von unten links (zuerst horizontal und dann vertikal)
		//x;y haben die Anzahl an Tiles(Kacheln) und nicht die Pixel
		
		for(int y = 0; y < mapHeight;y++)
		{
			for(int x = 0;x < mapWidth;x++)
			{
				Node node = new Node();
				node.type = Node.Type.REGULAR;
				nodes.add(node);
			}
		}
		
		
		//Erstellt Connections (Verbindungen) zwischen den jeweiligen Knoten, aber nur wenn dieser Knoten im Spiel "Luft" ist bzw. man physikalisch auf 
		// den Knoten zugehen kann (!= null --> Hindernis)
		
		for(int y = 0; y <mapHeight;y++)
		{
			for(int x = 0; x < mapHeight;x++)
			{
				TiledMapTileLayer.Cell target = tiles.getCell(x,y);
				TiledMapTileLayer.Cell up = tiles.getCell(x,y+1);
				TiledMapTileLayer.Cell down = tiles.getCell(x,y-1);
				TiledMapTileLayer.Cell left = tiles.getCell(x-1,y);
				TiledMapTileLayer.Cell right = tiles.getCell(x+1,y);
				
				Node targetNode = nodes.get(mapWidth*y+x);
				if(target == null)
				{
					if(y!=0 && down == null)
					{
						Node downNode = nodes.get(mapWidth * (y-1)+x);
						targetNode.createConnection(downNode,1);	
					}
					if(x!=0 && left == null)
					{
						Node leftNode = nodes.get(mapWidth * y +x-1);
						targetNode.createConnection(leftNode,1);	
					}
							
					if(y != mapWidth && right == null)
					{
						Node rightNode = nodes.get(mapWidth * y +x +1);
						targetNode.createConnection(rightNode,1);	
					}
								
					if(x!=mapHeight && up == null)
					{
						Node upNode = nodes.get(mapWidth * (y+1)+x);
						targetNode.createConnection(upNode,1);	
					}
					
				}
				
			}
		}
		
	
		return new GraphImp(nodes);
		
		
		
	}

}
