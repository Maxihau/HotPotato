package aStarPathFinder;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ai.pfa.indexed.IndexedAStarPathFinder;
import com.badlogic.gdx.physics.box2d.Body;

public class EnemyAgentComponent {
	
private IndexedAStarPathFinder<Node> pathFinder;
private GraphPathImp resultPath = new GraphPathImp();


public EnemyAgentComponent(Body enemy, Body target)
{
	pathFinder = new IndexedAStarPathFinder<Node>(LevelManager.graph, false);

	//Erster Knoten fängt beim Gegener an
	int startX = (int) enemy.getPosition().x;
	int startY = (int) enemy.getPosition().y;
	
	int endX = (int) target.getPosition().x;
	int endY = (int) target.getPosition().y;
	
	Gdx.app.log("start", "X " + startX + " Y" + startY);
	Gdx.app.log("end", "X " + endX + " Y" + endY);
	
	Node startNode = LevelManager.graph.getNodeByXY(startX, startY);
	Node endNode = LevelManager.graph.getNodeByXY(endX, endY);
	
	Gdx.app.log("start", " "+startNode);
	Gdx.app.log("end"," "+endNode);
	
	pathFinder.searchNodePath(startNode, endNode, new HeuristicImp(), resultPath);
	Gdx.app.log("Path"," " +resultPath.getCount());
	
	
}


	
}
