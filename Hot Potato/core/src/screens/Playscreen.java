package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ai.steer.behaviors.Arrive;
import com.badlogic.gdx.ai.steer.behaviors.Seek;
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

import SteeringBehavior.B2dSteeringEntity;
import SteeringBehavior.EnemyBehavior;
import SteeringBehavior.EnemyState;
import aStarPathFinder.EnemyAgentComponent;
import aStarPathFinder.LevelManager;
import aStarPathFinder.PathfindingDebugger;
import entities.HotPotato;
import figuren.Enemy;
import figuren.Player;
import tools.CreateWorld;

public class Playscreen implements Screen, InputProcessor{

	Player spieler1;
	HotPotato hotpotato1;
	HPotato spiel;
	B2dSteeringEntity kiSteuerung;
	Player kiKoerper;
	Enemy enemy;
	
    TiledMap tiledMap;
    TiledMapRenderer tiledMapRenderer;
	OrthographicCamera spielkamera; //2D Ansicht --> Von "vorne"
	
	World welt;
	Box2DDebugRenderer debugR;
	Body koerper;
	B2dSteeringEntity entity, target;
	
	private TextureAtlas atlas;
	
	EnemyAgentComponent pathFinder;
	
	LevelManager levelManager;
	
	PathfindingDebugger pfd;
	
	public Playscreen(HPotato spiel)	//Uebertragung der Eigenschaften der "Hauptklasse (MyGdxGame)" auf diese Klasse (Zum Rendern bsp.)
	{
		atlas = new TextureAtlas("Figur.pack");
		
		this.spiel = spiel;

		float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        
        //Gdx.app.log("Auflösung Breite"," "+Gdx.graphics.getWidth());
        //Gdx.app.log("Auflösung Höhe", " "+Gdx.graphics.getHeight());
		//Auflösung 480x800px
        
        
		spielkamera = new OrthographicCamera(); //Für die Ansicht der Kamera
		spielkamera.setToOrtho(false,w/spiel.PPM,h/spiel.PPM); //false --> Y Achse nach oben
		spielkamera.update(); //Spielkamera wird geupdated
		
		
		//tiledMap = new TmxMapLoader().load("Karte.tmx"); //Karte laden mit Map loader
		//LevelManager.loadLevel(tiledMap);
		
		levelManager = new LevelManager();
		levelManager.loadLevel("Karte.tmx");
		tiledMap = levelManager.getMap();
		
		tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap, 1/spiel.PPM); //Karte in den Renderer speichern
		tiledMapRenderer.setView(spielkamera); //Ansicht der Kamera in dem Renderer
		
		welt = new World (new Vector2(0, -10f),true); //Beschleunigungen x,y Positionen, true = nicht bewegende Figuren werden ignoriert, erst wenn, dann gilt die Besdchleunigungen
		debugR = new Box2DDebugRenderer(); // debugRenderer für die Anzeige der Boxen in Box2d
		
		new CreateWorld(welt,tiledMap); //Klasse, um die Physics der Objekte zu generieren 

		spieler1 = new Player(welt, this); //Um den Atlas zu laden (siehe unten)
		enemy = new Enemy(welt,this);
		
		
		
		//target = new B2dSteeringEntity(spieler1.getBody(),10);
		//entity = new B2dSteeringEntity(enemy.getBody(),10);
		
		
		
		
		pathFinder = new EnemyAgentComponent(enemy,spieler1, spiel);
		pfd = new PathfindingDebugger();
		PathfindingDebugger.setCamera(spielkamera);
		PathfindingDebugger.drawPath(pathFinder.GetResultPath());
		
		
		//enemyBehavior = new EnemyBehavior(enemy,spieler1,pathFinder.GetResultPath());
		
		
		/*
		//Allgemeine Einstellung für SteeringBehavior
		final Arrive<Vector2> arriveSB = new Arrive<Vector2>(entity,target)
				.setTimeToTarget(0.1f) 
				.setArrivalTolerance(0.001f) 
				.setDecelerationRadius(1f);
			entity.setBehavior(arriveSB);
		*/
		
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
		//entity.update(dt);
		
		pathFinder.update(dt);
		
		//Doesn't work somehow
		//enemyState.update(enemy);
		
		//Just for Tests
		
		

		
		
		if (Gdx.input.isKeyJustPressed(Input.Keys.M)) {
		
			
			Gdx.app.log("SpielerPositon (Playscreen)", "X " + spieler1.getX());
			Gdx.app.log("SpielerPositon(Playscreen)", "Y " + spieler1.getY());
			
			Gdx.app.log("GegnerPositon(Playscreen)", "X " + enemy.getX());
			Gdx.app.log("GegnerPositon(Playscreen)", "Y " + enemy.getY());
		//Output ist zw. 1 bis 4 (????)
			
			
		}
		/*
		if (Gdx.input.isKeyJustPressed(Input.Keys.I)) {
			Gdx.app.log("Trhe RealX"," " +spieler1.getrealX());
			Gdx.app.log("Trhe RealY"," " +spieler1.getrealY());
		}

		*/
		
		
	}
	
	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub		
		update(delta);
		spieler1.updatePlayer(delta);
		enemy.updateEnemy(delta);
		
		
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
        spiel.batch.setProjectionMatrix(spielkamera.combined); // Gibt vor, wo die Objekte gerendert werden sollten, bezüglich der Position der Kamera (Wichtig für die Figur)
        
		tiledMapRenderer.render();
		
		spiel.batch.begin();
		spieler1.draw(spiel.batch);
		enemy.draw(spiel.batch);
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
