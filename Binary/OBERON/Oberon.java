package Oberon;
import robocode.*;
import java.util.Random;

/**
 * Oberon - a robot by (Alan Byju, Paul Geoghegan and Saul Burgess)
 */
public class Oberon extends AdvancedRobot
{

	//main
	public void run()
	{

		Random rand = new Random();

		// setColors(Color.red,Color.blue,Color.green); // body,gun,radar

		// variables for max play space
		int maxPlayHeight, maxPlayWidth;

		//gets max height and with so that Oberon won't leave valid playspace and get shot at by century bot
		maxPlayHeight = getBattleFieldHeight() - (getSentryBorderSize() * 2);
		maxPlayWidth = getBattleFieldWidth() - (getSentryBorderSize() * 2);

		// declairs random utility
		Random rand = newRandom();

		// Declairs variables to keep track of enemy energy so that we can check if they have fired
		int enemyEnergy = 100;
		int currentEnemyEnergy;

		// Declairs variables for keeping track of enemy position and movement
		int enemyV, enemyB;

		// Robot main loop
		while(true)
		{

			//sets enemy v and b to -1 for use in if statement that checks if enemy is in line of sight
			enemyV = -1;
			enemyB = -1;

			//gets enemy velocity, bareing and stores them in relevant variables
			enemyV = getVelocity();
			enemyB = getBearing();

			//checks if Oberon got enemy energy or not by checking if the values are still equil to -1 as this value is not one that a robot can have
			if(enemyV == -1 && enemyB == -1)
			{

				//goes to the locate method
				locate();

			} //end if
			else
			{

				//checks what direction the enemy is heading in
				if(enemyB == 180 || enemyB == 0)
				{

					//fires if the enemy is heading directly towards or away from Oberon
					fire();

				} //end if
				else if(enemyB < 180)
				{

					//turns radar right in order to keep line of sight on the enemy
					turnRadarRight(8);

					// Goes to the fire method
					fire();

				} //end else if
				else if(enemyB > 180)
				{

					//turns radar left in order to keep line of sight on the enemy
					turnRadarRight(-8);

					// Goes to the fire method
					fire();

				} //end else if
				else
				{

					// Goes to the locate method
					locate();

				} //ends else for keeping track of enemy movements

			} //end else for checking if the enemy is in line of sight

		} //end main loop
	} //end main


	// finds robot if Oberon loses line of sight of the enemy
	public void locate()
	{

		// uses dir to store a random value which determins the direction to turn
		int dir = rand(2);

		//checks which direction to turn based on random number stored in dir
		if(dir == 0)
		{

			//this turns the radar right 360 degrees
			turnRadarRight(360);

		} // end if
		else
		{

			// This turns the radar left 360 degrees
			turnRadarRight(-360);

		} //end else for chooseing random direction to turn radar

	} // end locate method

	//fires at the enemy robot
	public void fire()
	{

		//TEMP

	} //end fire method

	//when a robot is scanned by the radar this method will run
	public void onScannedRobot(ScannedRobotEvent e)
	{

		//TEMP

	} // end scanned robot event/method

	//when Oberon is hit by a bullit this method will run
	public void onHitByBullet(HitByBulletEvent e)
	{

		//TEMP

	} //end when hit by bullit event/method

	//when robot hits wall
	public void onHitWall(HitWallEvent e)
	{

		//TEMP

	} //end on hit wall event/method

} //end oberon
