package screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ai.btree.Task;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.*;

public class HPotato extends Game{
	public static final float PPM = 100; //Pixels to meters
	//(Skalierung, da Box2d in Pixeln rechnet und diese Skalierung zu klein ist)
	// normal is 1 Pixel == 1 Meter+
	
	
	public SpriteBatch batch; 

	public void create () 
	{
		batch = new SpriteBatch();
		
		setScreen(new Playscreen(this)); 
		//Später: Einfacherer Wechsel zwischen den Screens
		
		
	}
	
	
	

	public void render () {
		
		super.render();	//Rendert das gegebene Screen
		
		
	}
	
	
	
	
}
