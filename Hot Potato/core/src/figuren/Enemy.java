package figuren;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ai.fsm.DefaultStateMachine;
import com.badlogic.gdx.ai.fsm.StateMachine;
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

import SteeringBehavior.EnemyBehavior;
import SteeringBehavior.EnemyState;
import screens.HPotato;
import screens.Playscreen;
public class Enemy extends Sprite{
	
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
	public boolean bomb;
	
	int i = 0;
	
	public StateMachine<Enemy,EnemyState> stateMachine;
	


	
	
	
	public Enemy(World world,Playscreen screen)
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
		
		bomb = true;
		
		
		Array<TextureRegion> frames = new Array <TextureRegion>();
		
		
		for (int i = 1;i <3;i++) {
			frames.add(new TextureRegion(getTexture(), i*50+i*1,0,50,50)); // 
		}
		playerRUNNING = new Animation<TextureRegion>(1f,frames);
		frames.clear();
		
		//Frames,um die Bewegung einer Figur darstellen zu k�nnen
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
		
		
		
		setPosition(75,200);

		
		bodyDef = new BodyDef();
		FigurErstellen();
		
		
		stateMachine = new DefaultStateMachine<Enemy,EnemyState>();
		stateMachine.setInitialState(EnemyState.SEEK);
		
		
		
		
	}
	

	public float GetBodyPosX()
	{
		return body.getPosition().x;
	}
	
	public float GetBodyPosY()
	{
		return body.getPosition().y;
	}
	
	public boolean IfBomb()
	{
		return bomb;
	}
	
	
	
	void FigurErstellen() {
		
		
		bodyDef.type = BodyType.DynamicBody;
		//Definition des K�rpers (Welcher Typ und Position)
		//Dynamic: Von der Erdanziehungskraft und von anderen Objekten beeinflussbar
		//Kinematic: Nur von anderen Objekten beeinflussbar
		//Static: Von nichts beeinflussbar
		
		bodyDef.position.set(getX()/spiel.PPM,getY()/spiel.PPM);
		
		body = world.createBody(bodyDef);
		
		CircleShape shape = new CircleShape();
		shape.setRadius(25/spiel.PPM);
		
		
		
		FixtureDef fdef = new FixtureDef(); //Definition der Box f�r die Kollision
		
		fdef.shape = shape; 
		body.createFixture(fdef);
		
		fdef.density = 1.2f; // Masse der Figur; Gewicht der Figur
		
		Fixture fixture = body.createFixture(fdef);
		body.setFixedRotation(true);
		
		shape.dispose();
		
		
	}
	public void updateEnemy(float dt) {
		
		setPosition(body.getPosition().x-getWidth()/2, body.getPosition().y-getHeight()/2);
		
		//stateMachine.update();

	}


	public TextureRegion getFrame (float dt) 
	{
		
		currentState = getState();
		
		
		 switch(currentState) {
		 case JUMPING:
			region = playerJUMPING.getKeyFrame(stateTimer,false);
			break;
		
		 case RUNNING:
			region = playerRUNNING.getKeyFrame(stateTimer,true); //true f�r ein Loop
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
			







