package tools;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.physics.box2d.World;

import entities.BODEN;
import entities.PLATTFORM;
import screens.HPotato;



public class WeltErstellen {
	
World welt;
TiledMap karte;	
HPotato spiel;

	
public WeltErstellen(World welt, TiledMap karte){
	
	// Karte und die "Welt" wird uerbertragen
		this.welt = welt;
		this.karte = karte;
		
		
	for (MapObject object : karte.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)) {
			
			new BODEN(welt, karte);
		}
	
	//Für jedes Objekt auf diesem Layer aus dem tmx (Map) wird ein Objekt in Box2D erstellt
	
	for (MapObject object : karte.getLayers().get(3).getObjects().getByType(RectangleMapObject.class)) {
		
		new PLATTFORM(welt, karte);
	}
	
		
		
	}
	
	
	

}
