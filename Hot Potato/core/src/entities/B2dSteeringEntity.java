package entities;

import com.badlogic.gdx.ai.steer.Steerable;
import com.badlogic.gdx.ai.steer.Limiter;
import com.badlogic.gdx.ai.steer.SteeringAcceleration;
import com.badlogic.gdx.ai.steer.SteeringBehavior;
import com.badlogic.gdx.ai.steer.behaviors.Arrive;
import com.badlogic.gdx.ai.utils.Location;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;


import utils.SteeringUtils;


public class B2dSteeringEntity implements Steerable<Vector2> {

	Body body;
	boolean tagged;
	float boundingRadius;
	float maxLinearSpeed, maxLinearAcceleration;
	float maxAngularspeed, maxAngularAcceleration;
	
	SteeringBehavior<Vector2> behavior; //Wie verhält sich die KI; Entscheidungen werden zum "steeringOutput uebertragen
	SteeringAcceleration<Vector2> steerOutput; // Enthält die Aktionen, die die KI ausführt (Bewegung in X/Y Richtung)
	
	
	public B2dSteeringEntity(Body body, float boundingRadius)
	{
		this.body = body;
		this.boundingRadius = boundingRadius;
		
		this.maxLinearSpeed = 5;
		this.maxLinearAcceleration = 100;
		this.maxAngularspeed = 30;
		this.maxAngularAcceleration = 5;
		
		this.tagged = false;
		
		this.steerOutput = new SteeringAcceleration<Vector2>(new Vector2());
		this.body.setUserData(this);
		
		
	}
	
	
	public void update (float delta)
	{
		if (behavior!= null)
		{
			behavior.calculateSteering(steerOutput); //Wie schnell darf er gehen? Wo geht er hin?
			ApplySteering(delta);
		}
	}
	
	private void ApplySteering(float delta)
	{
		boolean anyAccelerations = false;
		
		if(!steerOutput.linear.isZero())
		{
			Vector2 force = steerOutput.linear.scl(delta);
			body.applyForceToCenter(force, true);
			anyAccelerations = true;
		}
		
		if (anyAccelerations)
		{
			Vector2 velocity = body.getLinearVelocity();
			float currentSpeedSquare = velocity.len2();
			if(currentSpeedSquare > maxLinearSpeed * maxLinearSpeed)
			{
				body.setLinearVelocity(velocity.scl(maxLinearSpeed / (float) Math.sqrt(currentSpeedSquare)));
				
			}
		}
	}
	
	public void setBehavior(SteeringBehavior<Vector2> behavior)
	{
		this.behavior = behavior;
	}
	
	public SteeringBehavior<Vector2> getBehavior()
	{
		return behavior;
	}
	
	
	@Override
	public Vector2 getPosition() {
		// TODO Auto-generated method stub
		return body.getPosition();
	}

	@Override
	public float getOrientation() {
		// TODO Auto-generated method stub
		return body.getAngle();
	}

	@Override
	public void setOrientation(float orientation) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public float vectorToAngle(Vector2 vector) {
		// TODO Auto-generated method stub
		return SteeringUtils.vectorToAngle(vector);
	}

	//Um die Richtung des Bodies zu ändern, die sich ändert
	
	@Override
	public Vector2 angleToVector(Vector2 outVector, float angle) {
		// TODO Auto-generated method stub
		return SteeringUtils.angleToVector(outVector,angle);
	}



	@Override
	public float getZeroLinearSpeedThreshold() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setZeroLinearSpeedThreshold(float value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public float getMaxLinearSpeed() {
		// TODO Auto-generated method stub
		return maxLinearSpeed;
	}

	@Override
	public void setMaxLinearSpeed(float maxLinearSpeed) {
		// TODO Auto-generated method stub
		this.maxLinearSpeed = maxLinearSpeed;
	}

	@Override
	public float getMaxLinearAcceleration() {
		// TODO Auto-generated method stub
		return maxLinearAcceleration;
	}

	@Override
	public void setMaxLinearAcceleration(float maxLinearAcceleration) {
		// TODO Auto-generated method stub
		this.maxLinearAcceleration = maxLinearAcceleration;
	}

	@Override
	public float getMaxAngularSpeed() {
		// TODO Auto-generated method stub
		return maxAngularspeed;
	}

	@Override
	public void setMaxAngularSpeed(float maxAngularSpeed) {
		// TODO Auto-generated method stub
		this.maxAngularspeed = maxAngularSpeed;
	}

	@Override
	public float getMaxAngularAcceleration() {
		// TODO Auto-generated method stub
		return maxAngularAcceleration;
	}

	@Override
	public void setMaxAngularAcceleration(float maxAngularAcceleration) {
		// TODO Auto-generated method stub
		this.maxAngularAcceleration = maxAngularAcceleration;
	}

	@Override
	public Vector2 getLinearVelocity() {
		// TODO Auto-generated method stub
		return body.getLinearVelocity();
	}

	@Override
	public float getAngularVelocity() {
		// TODO Auto-generated method stub
		return body.getAngularVelocity();
	}

	@Override
	public float getBoundingRadius() {
		// TODO Auto-generated method stub
		return boundingRadius;
	}

	@Override
	public boolean isTagged() {
		// TODO Auto-generated method stub
		return tagged;
	}

	@Override
	public void setTagged(boolean tagged) {
		// TODO Auto-generated method stub
		this.tagged = tagged;
	}


	@Override
	public Location<Vector2> newLocation() {
		// TODO Auto-generated method stub
		return null;
	}


	public void setSteeringBehavior(Arrive<Vector2> arriveSB) {
		// TODO Auto-generated method stub
		this.behavior = arriveSB;
	}

	
	
}
