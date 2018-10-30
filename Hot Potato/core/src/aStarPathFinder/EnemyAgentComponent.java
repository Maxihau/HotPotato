package aStarPathFinder;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ai.pfa.indexed.IndexedAStarPathFinder;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

import SteeringBehavior.EnemyBehavior;
import figuren.Enemy;
import figuren.Player;
import screens.HPotato;

public class EnemyAgentComponent {
	
private IndexedAStarPathFinder<Node> pathFinder;
private GraphPathImp resultPath = new GraphPathImp();
private Enemy enemy;
private Player target;
private HPotato spiel;
private Vector2 nodePos;
OrthographicCamera camera;

EnemyBehavior enemyBehavior;

public EnemyAgentComponent(Enemy enemy, Player target,HPotato spiel)
{
	this.spiel = spiel;
	this.camera = camera;
	
	this.enemy = enemy;
	this.target = target;
	
	pathFinder = new IndexedAStarPathFinder<Node>(LevelManager.graph, false);

	//Erster Knoten fängt beim Gegener an
	int startX = (int) (enemy.GetBodyPosX()*spiel.PPM);
	int startY = (int) (enemy.GetBodyPosY()*spiel.PPM);
	
	int endX = (int) (target.GetBodyPosX()*spiel.PPM);
	int endY = (int) (target.GetBodyPosY()*spiel.PPM);
	
	
	Gdx.app.log("Spiel PPM (EnemyAgentC/ Constructor)", "Wert " + spiel.PPM);
	Gdx.app.log("start(EnemyAgentC/ Constructor)", "X " + startX + " Y" + startY);
	Gdx.app.log("end(EnemyAgentC/ Constructor)", "X " + endX + " Y" + endY);
	
	Node startNode = LevelManager.graph.getNodeByXY(startX, startY);
	Node endNode = LevelManager.graph.getNodeByXY(endX, endY);
	
	Gdx.app.log("start (EnemyAgentC/ Constructor)", " "+startNode);
	Gdx.app.log("end(EnemyAgentC/ Constructor)"," "+endNode);
	
	pathFinder.searchNodePath(startNode, endNode, new HeuristicImp(), resultPath);
	Gdx.app.log("Path(EnemyAgentC/ Construkcor)"," " +resultPath.getCount());
	
	
	
	enemyBehavior = new EnemyBehavior(enemy, target);
	
}



public void update(float delta)
	{
		if (Gdx.input.isKeyJustPressed(Input.Keys.P)) 
		{
		 
			int startX =  (int) (enemy.GetBodyPosX()*spiel.PPM);
			int startY =  (int) (enemy.GetBodyPosY()*spiel.PPM);
			
			int endX = (int) (target.GetBodyPosX()*spiel.PPM);
			int endY = (int) (target.GetBodyPosY()*spiel.PPM);
	
	
	
	
			Gdx.app.log("EnemyPositon (EnemyAgentC)", "X " + enemy.GetBodyPosX()*spiel.PPM);
			Gdx.app.log("EnemyPositon(EnemyAgentC)", "Y " 	+ enemy.GetBodyPosY()*spiel.PPM);
	
			Gdx.app.log("TargetPositon(EnemyAgentC)", "X " + target.GetBodyPosX()*spiel.PPM);
			Gdx.app.log("TargetPositon(EnemyAgentC)", "Y " + target.GetBodyPosY()*spiel.PPM);
	
	
	
			Gdx.app.log("start(EnemyAgentC)", "X " + startX + " Y" + startY);
			Gdx.app.log("end(EnemyAgentC)", "X " + endX + " Y" + endY);
	
	
	
	
	
			Node startNode = LevelManager.graph.getNodeByXY(startX, startY);
			Node endNode = LevelManager.graph.getNodeByXY(endX, endY);
	
			Gdx.app.log("start Node (EnemyAgentC)", " "+startNode);
			Gdx.app.log("end Node (EnemyAgentC)"," "+endNode);
	
		
	
			pathFinder.searchNodePath(startNode, endNode, new HeuristicImp(), resultPath);
	
			//Problem zw star/endNode und resultPath !!
			
			Gdx.app.log("Path"," " +resultPath.getCount());
			
			
			//RESULTPATH IS THERE!!!!!!!!
			Gdx.app.log("Is it a resultPath???"," " +resultPath);
			//Ich bewege hier die KI
			//Ki bewegt sich von Knoten zu Knoten
			//KI muss wissen, welcher Knoten das ist
			//KI muss wissen, an welcher Position sich der Knoten befindet
			//Ki bewegt sich zum Knoten, Stück für Stück
			//
			//Suche node bei Index i
			//Node 
		}
			
		if (resultPath != null) 
			 {
			
				if (Gdx.input.isKeyJustPressed(Input.Keys.U)) 
				{
		         // Change this to time based
		      //  try 
		        		//{
							
							resultPath.removeIndex(0);
		                    int waypointIndex = resultPath.get(0).getIndex();
		                    Gdx.app.log("WaypointIndex(EnAgCom/ Pathfinder)"," "+waypointIndex);
		                    nodePos = new Vector2(waypointIndex % LevelManager.lvlTileWidth * LevelManager.tilePixelWidth + 25, waypointIndex / LevelManager.lvlTileWidth * LevelManager.tilePixelHeight + 25);
		                    enemyBehavior.MoveEnemy(nodePos);
		                //} 
		                	//catch (Exception e) 
		                	//{
		                    // do nothing
		                	//}
				}
			}
			
		
	}



			 
			 
public GraphPathImp GetResultPath()
	{
		return resultPath;
	}






	
}
