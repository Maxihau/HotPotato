package aStarPathFinder;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ai.pfa.indexed.IndexedAStarPathFinder;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.physics.box2d.Body;

import figuren.Enemy;
import figuren.Player;
import screens.HPotato;

public class EnemyAgentComponent {
	
private IndexedAStarPathFinder<Node> pathFinder;
private GraphPathImp resultPath = new GraphPathImp();
private Enemy enemy;
private Player target;
private HPotato spiel;

OrthographicCamera camera;

public EnemyAgentComponent(Enemy enemy, Player target,OrthographicCamera camera,HPotato spiel)
{
	this.spiel = spiel;
	this.camera = camera;
	
	this.enemy = enemy;
	this.target = target;
	
	pathFinder = new IndexedAStarPathFinder<Node>(LevelManager.graph, false);

	//Erster Knoten fängt beim Gegener an
	int startX = (int) ( enemy.getBodyPosX()*spiel.PPM);
	int startY = (int) ( enemy.getBodyPosY()*spiel.PPM);
	
	int endX = (int) ( target.getBodyPosX()*spiel.PPM);
	int endY = (int) ( target.getBodyPosY()*spiel.PPM);
	
	
	Gdx.app.log("Spiel PPM (EnemyAgentC/ Constructor)", "Wert " + spiel.PPM);
	Gdx.app.log("start(EnemyAgentC/ Constructor)", "X " + startX + " Y" + startY);
	Gdx.app.log("end(EnemyAgentC/ Constructor)", "X " + endX + " Y" + endY);
	
	Node startNode = LevelManager.graph.getNodeByXY(startX, startY);
	Node endNode = LevelManager.graph.getNodeByXY(endX, endY);
	
	Gdx.app.log("start (EnemyAgentC/ Constructor)", " "+startNode);
	Gdx.app.log("end(EnemyAgentC/ Constructor)"," "+endNode);
	
	pathFinder.searchNodePath(startNode, endNode, new HeuristicImp(), resultPath);
	Gdx.app.log("Path(EnemyAgentC/ Construkcor)"," " +resultPath.getCount());
	
	
}



public void update(float delta)
{
	 if (Gdx.input.isKeyJustPressed(Input.Keys.P)) {
		 
		int startX =  (int) ( enemy.getBodyPosX()*spiel.PPM);
		int startY =  (int) ( enemy.getBodyPosY()*spiel.PPM);
			
		int endX = (int) ( target.getBodyPosX()*spiel.PPM);
		int endY = (int) ( target.getBodyPosY()*spiel.PPM);
	
	
	
	
	Gdx.app.log("EnemyPositon (EnemyAgentC)", "X " + enemy.getBodyPosX()*spiel.PPM);
	Gdx.app.log("EnemyPositon(EnemyAgentC)", "Y " 	+ enemy.getBodyPosY()*spiel.PPM);
	
	Gdx.app.log("TargetPositon(EnemyAgentC)", "X " + target.getBodyPosX()*spiel.PPM);
	Gdx.app.log("TargetPositon(EnemyAgentC)", "Y " + target.getBodyPosY()*spiel.PPM);
	
	
	
	Gdx.app.log("start(EnemyAgentC)", "X " + startX + " Y" + startY);
	Gdx.app.log("end(EnemyAgentC)", "X " + endX + " Y" + endY);
	
	
	
	
	
	Node startNode = LevelManager.graph.getNodeByXY(startX, startY);
	Node endNode = LevelManager.graph.getNodeByXY(endX, endY);
	
	Gdx.app.log("start Node (EnemyAgentC)", " "+startNode);
	Gdx.app.log("end Node (EnemyAgentC)"," "+endNode);
	
	
	
	pathFinder.searchNodePath(startNode, endNode, new HeuristicImp(), resultPath);
	
	Gdx.app.log("Path"," " +resultPath.getCount());
	}
	 
	 
	 //Temporär!!!!!
	 PathfindingDebugger.setCamera(camera);
	  PathfindingDebugger.drawPath(resultPath);
	 
	 
}


	
}
