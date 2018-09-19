

package entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;

public class HotPotato {
	
double zeit = 0f;
double zeitA = 0f;



public void Zaehler(double deltaZeit)
{
	zeit +=deltaZeit;
	
}




public void ZeitStoppen()
{
	zeitA = zeit;
	}
private void ZeitNeustart()
{
	zeitA = zeit;
	}

public boolean SpielEnde(boolean beruehrt)
{
	if (zeit - zeitA >= 15)
	{
		return true;
	}
	else
		if (beruehrt)
		{
			ZeitNeustart();
		}	
	return false;
	
	}


}
