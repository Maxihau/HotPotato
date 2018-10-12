package tools;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.physics.box2d.World;


import entities.Ground;

import entities.Platform;
import screens.HPotato;



public class CreateWorld {
	
World world;
TiledMap map;	
HPotato spiel;

	
public CreateWorld(World world, TiledMap map){
	
	// map und die "world" wird uerbertragen
		this.world = world;
		this.map = map;
		
		
	for (MapObject object : map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)) {
			
			new Ground(world, map);
		}
	
	//Für jedes Objekt auf diesem Layer aus dem tmx (Map) wird ein Objekt in Box2D erstellt
	
	for (MapObject object : map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class)) {
		
		new Platform(world, map);
	}
	
		
		
	}
	
	
	

}
