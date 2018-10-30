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
	
	boolean RUNNING;
	boolean ducken;
	boolean istAufPlattform;
	
	public Texture texture;
	int frame = 0;
	public Sprite spieler;
	
	private World world;
	public Body body;
	BodyDef bodyDef;
	
	HPotato spiel;
	
	private TextureRegion figurSTANDING;
	public enum State {JUMPING,STANDING,RUNNING, FALLING};
	public State currentState;
	public State previousState;
	private Animation<TextureRegion> playerRUNNING;
	private Animation<TextureRegion> playerJUMPING;
	private float stateTimer;
	private boolean RUNNINGRechts;
	private TextureRegion region;
	private boolean besitzBombe;
	
	int i = 0;
	
	//Temporär (Wenn Zeit ist, kann man's eleganter lösen)
	public float realX;
	public float realY;
	
	
	public Player(World world,Playscreen screen)
	{
		
		
		super (screen.getAtlas().findRegion("FigurLaufenRechts"));

		//super der Sprite Klasse,die nach aus einer Textureregion eine Sprite erstellt (Spart Speicher)
		//Sucht nach der Region, die den Namen hat
		//Siehe txt Datei vom Texturepacker
		
		
		this.world = world;
		currentState = State.STANDING;
		previousState = State.STANDING;
		stateTimer = 0;
		RUNNINGRechts = true;
		region = figurSTANDING;
		
		
		Array<TextureRegion> frames = new Array <TextureRegion>();
		
		
		for (int i = 1;i <3;i++) {
			frames.add(new TextureRegion(getTexture(), i*50+i*1,0,50,50)); // 
		}
		playerRUNNING = new Animation<TextureRegion>(1f,frames);
		frames.clear();
		
		//Frames,um die Bewegung einer Figur darstellen zu können
		//Speicher den Frame, sowie die Dauer des Frames
		
		
		for (int i = 0;i <2;i++) 
		{
			frames.add(new TextureRegion(getTexture(), 305+i*50,0,50,50));
		}
		playerJUMPING = new Animation<TextureRegion>(0.1f,frames);
		frames.clear();
		
		
		figurSTANDING = new TextureRegion(getTexture(), 0,0,50,50);
		
		
		
		setBounds(0,0,50/spiel.PPM,50/spiel.PPM);
		setRegion(figurSTANDING);
		
		
	
		setPosition(275,400);
	
		
		bodyDef = new BodyDef();
		FigurErstellen();
		
		
		
		
	}
	
//Für EnemyAgentComponent, da die Nodes in "normalen" Pixeln sind X & Y und ncihtr die vom Body (1.0-1.5....
	
	public float GetBodyPosX()
	{
		return body.getPosition().x;
	}
	
	public float GetBodyPosY()
	{
		return body.getPosition().y;
	}
	
	
	
	void FigurErstellen() {
		
		
		bodyDef.type = BodyType.DynamicBody;
		//Definition des Körpers (Welcher Typ und Position)
		//Dynamic: Von der Erdanziehungskraft und von anderen Objekten beeinflussbar
		//Kinematic: Nur von anderen Objekten beeinflussbar
		//Static: Von nichts beeinflussbar
		
		bodyDef.position.set(getX()/spiel.PPM,getY()/spiel.PPM);
		//Gdx.app.log("GetX (Player)", " "+getX());
		//Gdx.app.log("GetY (Player)", " "+getY());
		// --> Geben "nromale" Werte aus und dann / spiel.PPM
		//GetX ist normal
		
		body = world.createBody(bodyDef);
		
		CircleShape shape = new CircleShape();
		shape.setRadius(25/spiel.PPM);
		
		
		
		FixtureDef fdef = new FixtureDef(); //Definition der Box für die Kollision
		
		fdef.shape = shape; 
		body.createFixture(fdef);
		
		fdef.density = 1.2f; // Masse der Figur; Gewicht der Figur
		
		Fixture fixture = body.createFixture(fdef);
		body.setFixedRotation(true);
		
		shape.dispose();
		
		
	}
	public void updatePlayer(float dt) {
		
		// TO-DO: Mit Delta Time die Animation verlangsamen (iwas mit if)
		
		
		//System.out.println(body.getPosition().x + " kartoffel "+ body.getPosition().y);
		
		
		setPosition(body.getPosition().x-getWidth()/2, body.getPosition().y-getHeight()/2);
		
		
		//Schnittpunkt der X- und Y-Achse der Figur ist im Mittelpunkt (!! Nicht in einer Ecke!!)
		//2-(2/2) == 1 --> 1 ist die Hälfte von 2 --> Koordinaten System des Prites fängt in der Mitte an
		
		//Gdx.app.log("Position CHarakter X", " " +(body.getPosition().x-getWidth()/2));
		//Gdx.app.log("Position CHarakter Y", " " +(body.getPosition().y-getWidth()/2));
		
		setRegion(getFrame(dt)); // Siehe Methoden
		//Dient grundsätzlich zum Animieren bzw. Änderung der Sprites
		
		
		if (Gdx.input.isKeyJustPressed(Input.Keys.UP) ){
			body.applyLinearImpulse(new Vector2(0,0.6f), body.getWorldCenter(), true);
		}
		
		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && body.getLinearVelocity().x <=3){
			body.setLinearVelocity(2,0);
			

		}
		
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)&& body.getLinearVelocity().x <=3){
			body.setLinearVelocity(-2, 0);
			}
		}
	
	public TextureRegion getFrame (float dt) 
	{
		
		currentState = getState();
		
		
		 switch(currentState) {
		 case JUMPING:
			region = playerJUMPING.getKeyFrame(stateTimer,false);
			break;
		
		 case RUNNING:
			region = playerRUNNING.getKeyFrame(stateTimer,true); //true für ein Loop
			break;
		 
		 case FALLING:
			 region = figurSTANDING;
			 break;
			 
		 case STANDING:
			 region = figurSTANDING;
			 break;
		 default:
			region = figurSTANDING;
		 }
		 
		 
		
		 if((body.getLinearVelocity().x <0.2 || !RUNNINGRechts) && !region.isFlipX())
		 {
			region.flip(true,false); // x Achse true, y Achse false
			RUNNINGRechts = false; 
		 	}
		 	else 
		 	{
		 		if((body.getLinearVelocity().x > 0.2 || RUNNINGRechts) && region.isFlipX()) 
		 			{
					 region.flip(true, false);
					 RUNNINGRechts = true;
				 }
		 	}
		 	
		 
		 
		 stateTimer = currentState == previousState ? stateTimer + dt: 0;
		 
		 previousState = currentState;
		 return region;
		 
	
	
	}
		
	
	
	
public State getState() {
		if(body.getLinearVelocity().y>0 || (body.getLinearVelocity().y > 0 && previousState == State.JUMPING))	{
			//System.out.println(body.getLinearVelocity().y);
			return State.JUMPING;
			
			}
		else if (body.getLinearVelocity().y<0) 
		{
			//System.out.println("FALLING");
			return State.FALLING;
			
		}
		else if (body.getLinearVelocity().x!=0) {
			//System.out.println("RUNNING");
			return State.RUNNING;
			
		}
		else
			//System.out.println("STANDING");
		return State.STANDING;
		
		}

public Body getBody() {
		return this.body;	//Bewegung der Boxen
	}
	
	
}
			







