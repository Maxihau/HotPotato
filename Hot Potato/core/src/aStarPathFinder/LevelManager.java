package aStarPathFinder;

import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;

public class LevelManager {
public static int lvlTileWidth;
public static int lvlTileHeight;
public static int lvlPixelWidth;
public static int lvlPixelHeight;
public static int tilePixelWidth;
public static int tilePixelHeight;
public static TiledMap tiledMap;
public static GraphImp graph;

public static void loadLevel(TiledMap map)
{
	
	//Properties (Eigentlich: Eigentschaften der Karte) werden gespeichert
	
	tiledMap = map;
	
	MapProperties properties = tiledMap.getProperties();
	lvlTileWidth = properties.get("width",Integer.class);
	lvlTileHeight = properties.get("height", Integer.class);
	tilePixelWidth = properties.get("tilewidth",Integer.class);
	tilePixelHeight = properties.get("tileheight",Integer.class);
	lvlPixelWidth = lvlTileWidth * tilePixelWidth;
	lvlPixelHeight = lvlTileHeight * tilePixelHeight;
	
	graph = GraphGenerator.generateGraph(tiledMap);
	
	}
}
