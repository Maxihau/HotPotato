package entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import screens.HPotato;

public class PLATTFORM {
	
	private World welt;
	private Body body;
	private TiledMap karte;
	private HPotato spiel;

	public PLATTFORM(World welt, TiledMap karte)
	{
		this.welt = welt;
		this.karte = karte;
		
		BodyDef bDef = new BodyDef();
		FixtureDef fDef = new FixtureDef();	
		PolygonShape shape = new PolygonShape();
		Body body;


		for (MapObject object : karte.getLayers().get(3).getObjects().getByType(RectangleMapObject.class)) {
			Rectangle rect = ((RectangleMapObject)object).getRectangle();
			
			bDef.type = BodyDef.BodyType.StaticBody;
			bDef.position.set((rect.getX()+rect.getWidth()/2)/spiel.PPM, (rect.getY()+rect.getHeight()/2)/spiel.PPM);
			
			body = welt.createBody(bDef);
			shape.setAsBox((rect.getWidth()/2)/spiel.PPM, (rect.getHeight()/2)/spiel.PPM);
			fDef.shape = shape;
			body.createFixture(fDef);
				
		}
			
			
		
	}
	}


