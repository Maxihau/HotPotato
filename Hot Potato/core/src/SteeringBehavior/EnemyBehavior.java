package SteeringBehavior;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ai.fsm.DefaultStateMachine;
import com.badlogic.gdx.ai.fsm.StateMachine;
import com.badlogic.gdx.ai.steer.Limiter;
import com.badlogic.gdx.ai.steer.behaviors.Arrive;
import com.badlogic.gdx.ai.steer.behaviors.Flee;
import com.badlogic.gdx.ai.utils.Location;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

import aStarPathFinder.GraphPathImp;
import aStarPathFinder.Node;
import figuren.Enemy;
import figuren.Player;


public class EnemyBehavior {

	Enemy enemy;
	Player player;
	GraphPathImp resultPath;
	B2dSteeringEntity targetSteering;
	B2dSteeringEntity enemySteering;
	Limiter limiter; //for Flee class

	public StateMachine<Enemy,EnemyState> stateMachine;
	
	public EnemyBehavior(Enemy enemy, Player target) 
	{
		this.enemy = enemy;
		this.player = target;
		this.resultPath = resultPath;
		targetSteering = new B2dSteeringEntity(target.getBody(),10);
		enemySteering = new B2dSteeringEntity(enemy.getBody(),10);
		//Bodyies arrived here
		
		//Gdx.app.log("The Positon X TARGET (EnemnyBehavior)"," "+target.GetBodyPosX());
		//Gdx.app.log("The Positon Y TARGET (EnemnyBehavior)"," "+target.GetBodyPosY());
		
		
		//Allgemeine Einstellung für SteeringBehavior
					//entity.setBehavior(arriveSB);
		
		Arrive<Vector2> steeringBehavior = new Arrive<Vector2>(enemySteering,targetSteering) 
				.setTimeToTarget(0.1f) 
				.setArrivalTolerance(0.001f) 
				.setDecelerationRadius(1f);
				//.setTarget((Location<Vector2>) nodePos);
		
		
		
			
		stateMachine = new DefaultStateMachine<Enemy,EnemyState>();
		stateMachine.setInitialState(EnemyState.SEEK);
	}
	

	
	public void MoveEnemy(Vector2 nodePos)
	{
		Arrive<Vector2> steeringBehavior = new Arrive<Vector2>(enemySteering) 
				.setTimeToTarget(0.1f) 
				.setArrivalTolerance(0.001f) 
				.setDecelerationRadius(1f)
				.setTarget((Location<Vector2>) nodePos);
		enemySteering.setBehavior(steeringBehavior);
		Gdx.app.log("Position of Node (EnemyBehavior)"," "+nodePos);
		//Shows Null !!!!!
	}
	
	
	
	
	public void setSteeringBehavior(String behavior, Vector2 nodePos)
	{
		//NOT done yet
		/*
		if (behavior == "Arrive")
		{
			Arrive<Vector2> steeringBehavior = new Arrive<Vector2>(enemySteering) 
					.setTimeToTarget(0.1f) 
					.setArrivalTolerance(0.001f) 
					.setDecelerationRadius(1f)
					.setTarget((Location<Vector2>) nodePos);
		}
		else if(behavior == "Flee")
		{
			Flee<Vector2> steeringBehavior = new Flee<Vector2>(enemySteering,targetSteering);
					
					
		}
		*/

		
	}
	
	
	
	
	
}
