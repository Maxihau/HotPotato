package entities;

import com.badlogic.gdx.ai.steer.Steerable;
import com.badlogic.gdx.ai.steer.SteeringAcceleration;
import com.badlogic.gdx.ai.steer.SteeringBehavior;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

import utils.SteeringUtils;

import com.badlogic.gdx.ai.steer.limiters.*;


public class SteeringEntity implements Steerable<Vector2>{

	Body body;
	boolean tagged;
	float boundingRadius;
	float maxLinearSpeed, maxLinearAcceleration;
	float maxAngularspeed, maxAngularAcceleration;
	
	SteeringBehavior<Vector2> behavior; //Wie verhält sich die KI; Entscheidungen werden zum "steeringOutput uebertragen
	SteeringAcceleration<Vector2> steeringOutput; // Enthält die Aktionen, die die KI ausführt (Bewegung in X/Y Richtung)
	
	
	public B2dSteeringEntity(Body body, float boundingRadius)
	{
		this.body = body;
		this.boundingRadius = boundingRadius;
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
	public Location<Vector2> newLocation() {
		// TODO Auto-generated method stub
		return null;
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

	
	
}
