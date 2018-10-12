package states;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.ai.steer.behaviors.Arrive;
import com.badlogic.gdx.math.Vector2;

import screens.HPotato;
import screens.Playscreen;



public class AiSteeringState {
	
	
	
	private World world;
	BodyDef bodydef;
	HPotato spiel;
	
	
	public AiSteeringState(World world, Playscreen screen) //Playscreen (?)
	{
		this.world = world;
		
		
		//Verfolger
		Body body = world.createBody(bodydef);
		CircleShape entity = new CircleShape();
		entity.setRadius(25/spiel.PPM);
		
		FixtureDef fdef = new FixtureDef(); //Definition der Box für die Kollision
		
		fdef.shape = entity; 
		body.createFixture(fdef);
		
		fdef.density = 1f; // Masse der Figur; Gewicht der Figur
		
		Fixture fixture = body.createFixture(fdef);
		
		entity.dispose();
		
		
		
		
		
		
		
	
		
		
	}

}
