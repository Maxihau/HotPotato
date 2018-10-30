package aStarPathFinder;


import com.badlogic.gdx.Gdx;
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
		int mapWidth = LevelManager.lvlTileWidth;
		Gdx.app.log("Höhe lvlTileHeight",""+ LevelManager.lvlTileHeight);
		Gdx.app.log("Höhe lvlTileWidth", ""+LevelManager.lvlTileWidth);
		
		//Erstellen hier für jedes Tile (Kachel; 50Kacheln * 30 Kacheln) einen Knoten; Angefangen von unten links (zuerst horizontal und dann vertikal)
		//x;y haben die Anzahl an Tiles(Kacheln) und nicht die Pixel
		
		for(int y = 0; y < mapHeight;y++)
		{
			for(int x = 0;x < mapWidth;x++)
			{
				Node node = new Node();
				node.type = Node.Type.REGULAR;
				nodes.add(node);
				//Gdx.app.log("Nodes size","" +nodes.size);
			}
		}
		
		
		//Erstellt Connections (Verbindungen) zwischen den jeweiligen Knoten, aber nur wenn dieser Knoten im Spiel "Luft" ist bzw. man physikalisch auf 
		// den Knoten zugehen kann (!= null --> Hindernis)
		
		for(int y = 0; y <mapHeight;y++)
		{
			for(int x = 0; x < mapWidth;x++)
			{
				TiledMapTileLayer.Cell target = tiles.getCell(x,y);
				TiledMapTileLayer.Cell up = tiles.getCell(x,y+1);
				//TiledMapTileLayer.Cell upLeft = tiles.getCell(x-1,y+1);
				//TiledMapTileLayer.Cell upRight = tiles.getCell(x+1,y+1);
				TiledMapTileLayer.Cell down = tiles.getCell(x,y-1);
				//TiledMapTileLayer.Cell downLeft = tiles.getCell(x-1,y-1);
				//TiledMapTileLayer.Cell downRight = tiles.getCell(x+1,y-1);
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
					
					
					/*
					if(y!=0 && x!=0 && downLeft == null)
					{
						Node downLeftNode = nodes.get(mapWidth * (y-1)+(x-1));
						targetNode.createConnection(downLeftNode,1.4f);	
					}
					if(y!=mapWidth -1 && x!=0 && downRight == null)
					{
						Node downRightNode = nodes.get(mapWidth * (y-1)+(x-1));
						targetNode.createConnection(downRightNode,1.4f);	
					}
					
					*/
					
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
					
					/*
					if(y!=0 && x!=mapHeight && upLeft == null)
					{
						Node upLeftNode = nodes.get(mapWidth * (y-1)+(x-1));
						targetNode.createConnection(upLeftNode,1.4f);	
					}
					if(y!=mapWidth - 1 && x!= mapHeight -1 && downRight == null)
					{
						Node upRightNode = nodes.get(mapWidth * (y-1)+(x-1));
						targetNode.createConnection(upRightNode,1.4f);	
					}
					
					*/
					
					
				}
				
			}
		}
		
	
		return new GraphImp(nodes);
		
		
		
	}

}
