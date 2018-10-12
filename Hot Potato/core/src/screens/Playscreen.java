package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ai.steer.behaviors.Arrive;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

import entities.B2dSteeringEntity;
import entities.HotPotato;
import entities.B2dSteeringEntity;
import figuren.Player;
import tools.CreateWorld;

public class Playscreen implements Screen, InputProcessor{

	Player spieler1;
	HotPotato hotpotato1;
	HPotato spiel;
	B2dSteeringEntity kiSteuerung;
	Player kiKoerper;
	
    TiledMap tiledMap;
    TiledMapRenderer tiledMapRenderer;
	OrthographicCamera spielkamera; //2D Ansicht --> Von "vorne"
	
	World welt;
	Box2DDebugRenderer debugR;
	Body koerper;
	B2dSteeringEntity entity, target;
	
	private TextureAtlas atlas;
	
	public Playscreen(HPotato spiel)	//Uebertragung der Eigenschaften der "Hauptklasse (MyGdxGame)" auf diese Klasse (Zum Rendern bsp.)
	{
		atlas = new TextureAtlas("Figur.pack");
		
		this.spiel = spiel;

		float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
		
		spielkamera = new OrthographicCamera(); //Für die Ansicht der Kamera
		spielkamera.setToOrtho(false,w/spiel.PPM,h/spiel.PPM); //false --> Y Achse nach oben
		spielkamera.update(); //Spielkamera wird geupdated
		tiledMap = new TmxMapLoader().load("Karte.tmx"); //Karte laden mit Map loader
		tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap, 1/spiel.PPM); //Karte in den Renderer speichern
		tiledMapRenderer.setView(spielkamera); //Ansicht der Kamera in dem Renderer
		
		welt = new World (new Vector2(0, -10f),true); //Beschleunigungen x,y Positionen, true = nicht bewegende Figuren werden ignoriert, erst wenn, dann gilt die Besdchleunigungen
		debugR = new Box2DDebugRenderer(); // debugRenderer für die Anzeige der Boxen in Box2d
		
		new CreateWorld(welt,tiledMap); //Klasse, um die Physics der Objekte zu generieren 

		spieler1 = new Player(welt, this); //Um den Atlas zu laden (siehe unten)
		kiKoerper = new Player(welt,this);
		
		target = new B2dSteeringEntity(spieler1.getBody(),10);
		entity = new B2dSteeringEntity(kiKoerper.getBody(),10);
		
		
		//Allgemeine Einstellung für SteeringBehavior
		final Arrive<Vector2> arriveSB = new Arrive<Vector2>(entity,target) 
				.setTimeToTarget(0.1f) 
				.setArrivalTolerance(0.001f) 
				.setDecelerationRadius(0.1f);
			entity.setBehavior(arriveSB);
		
		
	}
	
	
	public TextureAtlas getAtlas() { // Lädt die Sprites, die vom TexturePacker erstellt worden sind
		return atlas;
	}
	
	
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		

		
	
	}
	
	void update(float dt)
	{
		entity.update(dt);
	}
	
	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub		
		update(delta);
		spieler1.updatePlayer(delta);
		
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
        spiel.batch.setProjectionMatrix(spielkamera.combined); // Gibt vor, wo die Objekte gerendert werden sollten, bezüglich der Position der Kamera (Wichtig für die Figur)
        
		tiledMapRenderer.render();
		
		spiel.batch.begin();
		spieler1.draw(spiel.batch);
		kiKoerper.draw(spiel.batch);
		spiel.batch.end();
		
		welt.step(Gdx.graphics.getDeltaTime(), 6, 2);
		// wie oft die Gravity refrescht wird; sagt, wie präzise die Kalkulation der Kollisionen der Objekte sind.
		
		debugR.render(welt,spielkamera.combined); //Box2D Debug
		
		
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		tiledMap.dispose();
		welt.dispose();
		debugR.dispose();

	}


	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}





}
