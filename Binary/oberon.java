package oberon.pack;
import robocode.*;
//import java.awt.Color;
import java.util.Random;

/**
 * Oberon - a robot by (Alan Byju, Paul Geoghegan and Saul Burgess)
 */
public class Oberon extends Robot
{

	Random rand = newRandom();

	//main
	public void run()
	{

		// setColors(Color.red,Color.blue,Color.green); // body,gun,radar

		// variables for max play space
		int maxPlayHeight, maxPlayWidth;
		maxPlayHeight = getbattlefieldHeight - )getSenturyBoaderSize * 2);
		maxPlayWidth = getBattlefieldWidth - )getsenturyBoaderSize * 2);

		//get enemy energy
		int enemyEnergy = 100;
		int currentEnemyEnergy;

		// enemy info
		int enemyV, enemyB;

		// Robot main loop
		while(true)
		{

			//sets enemy energy to -1 for use in if statement
			enemyV = -1;
			enemyB = -1;

			//gets enemy velocity and bareing
			enemyV = getVelocity;
			enemyB = getbearing;

			//checks if Oberon got enemy energy
			if(enemyV == -1 && enemyB == -1)
			{

				//goes to locate
				locate();

			} //end if
			else
			{

				//checks what direction enemy is heading
				if(enemyb == 180 || enemyb == 0)
				{

					fire();

				} //end if
				else if(enemyb < 180)
				{

					//turns radar right
					turnRadarRight(8);

				} //end else if
				else if(enemyb > 180)
				{

					//turns radar left
					turnRadarRight(-8);

				} //end else if
				else
				{

					locate();

				} //ends else


			} //end else

		} //end main loop
	} //end main

	// finds robot
	public void locate()
	{

		dir = rand(2);

		//checks which direction to turn
		if(dir == 0)
		{

			turnRadarRight(360);

		} // end if
		else
		{

			turnRadarRight(-360);

		} //end else

	} // end locate

	//when a robot is scanned
	public void onScannedRobot(ScannedRobotEvent e)
	{

		enemyV = -1;
		enemyB = -1;

		enemyV = getVelocity;
		enemyB = getbearing;

	}

	//when robot is hit by a bullit
	public void onHitByBullet(HitByBulletEvent e)
	{

		back(10);

	} //end when hit by bullit

	//when robot hits wall
	public void onHitWall(HitWallEvent e)
	{

		back(20);

	} //end on hit wall

} //end oberon