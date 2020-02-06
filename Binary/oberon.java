package oberon.pack;
import robocode.*;
//import java.awt.Color;

/**
 * Oberon - a robot by (Alan Byju, Paul Geoghegan and Saul Burgess)
 */
public class Oberon extends Robot
{

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



		} //end main loop
	} //end main

	// finds robot
	public void locate()
	{



} // end locate

	//when a robot is scanned
	public void onScannedRobot(ScannedRobotEvent e)
	{

		enemyV = -1;
		enemyB = -1;

		enemyV = getVelocity;
		enemyB = getbearing;

		if(enemyV == -1 && enemyB == -1)
		{

			locate();

		} //end if
		else
		{

			

		} //end else


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