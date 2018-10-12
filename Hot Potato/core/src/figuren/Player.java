package figuren;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

import screens.HPotato;
import screens.Playscreen;
public class Player extends Sprite{
	
	boolean laufen;
	boolean ducken;
	boolean istAufPlattform;
	
	public Texture texture;
	int frame = 0;
	public Sprite spieler;
	
	private World world;
	Body body;
	BodyDef bodyDef;
	
	HPotato spiel;
	
	private TextureRegion figurStehen;
	public enum State {SPRINGEN,STEHEN,LAUFEN, FALLEN};
	public State currentState;
	public State previousState;
	private Animation<TextureRegion> figurLaufen;
	private Animation<TextureRegion> figurSpringen;
	private float stateTimer;
	private boolean laufenRechts;
	private TextureRegion region;
	private boolean besitzBombe;
	
	public Player(World world,Playscreen screen)
	{
		super(screen.getAtlas().findRegion("FigurLaufenRechts")); 
		//super der Sprite Klasse,die nach aus einer Textureregion eine Sprite erstellt (Spart Speicher)
		//Sucht nach der Region, die den Namen hat
		//Siehe txt Datei vom Texturepacker
		
		this.world = world;
		currentState = State.STEHEN;
		previousState = State.STEHEN;
		stateTimer = 0;
		laufenRechts = true;
		region = figurStehen;
		
		
		Array<TextureRegion> frames = new Array <TextureRegion>();
		
		
		for (int i = 1;i <3;i++) {
			frames.add(new TextureRegion(getTexture(), i*50+i*1,0,50,50)); // 
		}
		figurLaufen = new Animation<TextureRegion>(0.1f,frames);
		frames.clear();
		
		//Frames,um die Bewegung einer Figur darstellen zu können
		//Speicher den Frame, sowie die Dauer des Frames
		
		
		for (int i = 0;i <2;i++) 
		{
			frames.add(new TextureRegion(getTexture(), 305+i*50,0,50,50));
		}
		figurSpringen = new Animation<TextureRegion>(0.1f,frames);
		frames.clear();
		
		
		figurStehen = new TextureRegion(getTexture(), 0,0,50,50);
		
		
		
		setBounds(0,0,50/spiel.PPM,50/spiel.PPM);
		setRegion(figurStehen);
		
		setPosition(75,400);
		bodyDef = new BodyDef();
		FigurErstellen();
		
		
		
		
	}
	
	
	
	
	void FigurErstellen() {
		
		
		bodyDef.type = BodyType.DynamicBody;
		//Definition des Körpers (Welcher Typ und Position)
		//Dynamic: Von der Erdanziehungskraft und von anderen Objekten beeinflussbar
		//Kinematic: Nur von anderen Objekten beeinflussbar
		//Static: Von nichts beeinflussbar
		
		bodyDef.position.set(getX()/spiel.PPM,getY()/spiel.PPM);
		
		body = world.createBody(bodyDef);
		
		CircleShape shape = new CircleShape();
		shape.setRadius(25/spiel.PPM);
		
		/*
		PolygonShape shape = new PolygonShape(); //box für Spieler
		shape.setAsBox(13/spiel.PPM, 24/spiel.PPM); //Größe der Kollisionsbox
		*/
		FixtureDef fdef = new FixtureDef(); //Definition der Box für die Kollision
		
		fdef.shape = shape; 
		body.createFixture(fdef);
		
		fdef.density = 1f; // Masse der Figur; Gewicht der Figur
		
		Fixture fixture = body.createFixture(fdef);
		
		shape.dispose();
		
		
	}
	public void updatePlayer(float dt) {
		
		System.out.println(body.getPosition().x + " kartoffel "+ body.getPosition().y);
		
		setPosition(body.getPosition().x-getWidth()/2, body.getPosition().y-getHeight()/2);
		//Schnittpunkt der X- und Y-Achse der Figur ist im Mittelpunkt (!! Nicht in einer Ecke!!)
		//2-(2/2) == 1 --> 1 ist die Hälfte von 2 --> Koordinaten System des Prites fängt in der Mitte an
		
		setRegion(getFrame(dt)); // Siehe Methoden
		//Dient grundsätzlich zum Animieren bzw. Änderung der Sprites
		
		
		if (Gdx.input.isKeyJustPressed(Input.Keys.UP) ){
			body.applyLinearImpulse(new Vector2(0,0.6f), body.getWorldCenter(), true);
		}
		
		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && body.getLinearVelocity().x <=3){
			body.setLinearVelocity(2, 0);

		}
		
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)&& body.getLinearVelocity().x <=3){
			body.setLinearVelocity(-2, 0); }
		}
	
	public TextureRegion getFrame (float dt) 
	{
		
		currentState = getState();
		
		
		 switch(currentState) {
		 case SPRINGEN:
			region = figurSpringen.getKeyFrame(stateTimer,false);
			break;
		
		 case LAUFEN:
			region = figurLaufen.getKeyFrame(stateTimer,true); //true für ein Loop
			break;
		 
		 case FALLEN:
			 region = figurStehen;
			 break;
			 
		 case STEHEN:
			 region = figurStehen;
			 break;
		 default:
			region = figurStehen;
		 }
		 
		 
		
		 if((body.getLinearVelocity().x <0.2 || !laufenRechts) && !region.isFlipX())
		 {
			region.flip(true,false); // x Achse true, y Achse false
			laufenRechts = false; 
		 	}
		 	else 
		 	{
		 		if((body.getLinearVelocity().x > 0.2 || laufenRechts) && region.isFlipX()) 
		 			{
					 region.flip(true, false);
					 laufenRechts = true;
				 }
		 	}
		 	
		 
		 
		 stateTimer = currentState == previousState ? stateTimer + dt: 0;
		 
		 previousState = currentState;
		 return region;
		 
	
	
	}
		
	
	
	
public State getState() {
		if(body.getLinearVelocity().y>0 || (body.getLinearVelocity().y > 0 && previousState == State.SPRINGEN))	{
			System.out.println(body.getLinearVelocity().y);
			return State.SPRINGEN;
			
			}
		else if (body.getLinearVelocity().y<0) 
		{
			System.out.println("fallen");
			return State.FALLEN;
			
		}
		else if (body.getLinearVelocity().x!=0) {
			System.out.println("laufen");
			return State.LAUFEN;
			
		}
		else
			System.out.println("stehen");
		return State.STEHEN;
		
		}

public Body getBody() {
		return this.body;	//Bewegung der Boxen
	}
	
	
}
			







