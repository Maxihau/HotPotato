package SteeringBehavior;

import com.badlogic.gdx.ai.fsm.State;
import com.badlogic.gdx.ai.msg.Telegram;

import figuren.Enemy;

public enum EnemyState implements State<Enemy> {

	
	SEEK()
	{
		@Override
		public void update(Enemy enemy) {
			if (!enemy.IfBomb()) 
			{
				enemy.stateMachine.changeState(FLEE);
			}
			
		}
	},
	
	FLEE()
	{
		@Override
		public void update(Enemy enemy) {
			if (enemy.IfBomb()) 
			{
				enemy.stateMachine.changeState(SEEK);
			}
		}
	};
	
	
	

	@Override
	public void enter(Enemy entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Enemy entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exit(Enemy entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onMessage(Enemy entity, Telegram telegram) {
		// TODO Auto-generated method stub
		return false;
	}
	
	

}
