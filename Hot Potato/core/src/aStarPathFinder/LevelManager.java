package aStarPathFinder;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

public class LevelManager {
public static int lvlTileWidth;
public static int lvlTileHeight;
public static int lvlPixelWidth;
public static int lvlPixelHeight;
public static int tilePixelWidth;
public static int tilePixelHeight;
public static TiledMap tiledMap;
public static GraphImp graph;





public static void loadLevel(String fileName)
{
	
	//Properties (Eigentlich: Eigentschaften der Karte) werden gespeichert
	
	tiledMap = new TmxMapLoader().load(fileName);
	
	MapProperties properties = tiledMap.getProperties();
	lvlTileWidth = properties.get("width",Integer.class);
	lvlTileHeight = properties.get("height", Integer.class);
	tilePixelWidth = properties.get("tilewidth",Integer.class);
	tilePixelHeight = properties.get("tileheight",Integer.class);
	lvlPixelWidth = lvlTileWidth * tilePixelWidth;
	lvlPixelHeight = lvlTileHeight * tilePixelHeight;
	
	
	
	graph = GraphGenerator.generateGraph(tiledMap);
	
	Gdx.app.log("baum","baummm ");
	
	
	}



public TiledMap getMap() {

	return tiledMap;
}
}
