package Oberon;
import robocode.*;
import java.util.Random;
import static robocode.util.Utils.normalRelativeAngleDegrees;


/**
 * Oberon - a robot by (Alan Byju, Paul Geoghegan and Saul Burgess)
 */


public class Oberon extends AdvancedRobot
{

	//declairs rand for use in locate
	Random rand = new Random();
	double enemyV, enemyB;
	byte scanDirection = 1;


	// Oberon info
	int TurnCounter = 0;
	double move;
	double x;
	double y;
	double h;
	double Px;
	double Py;
	double centerX;
	double centerY;
	double BorderX;
	double BorderY;
	double Sentry;

	// Declairs variables to keep track of enemy energy so that we can check if they have fired
	double enemyE = 100;
	double enemyCE = 0;

	////enemy info
	double enemyD;


	//main
	public void run()
	{
		// setColors(Color.red,Color.blue,Color.green); // body,gun,radar

		setAdjustGunForRobotTurn(true);
		setAdjustRadarForGunTurn(true);
		setAdjustRadarForRobotTurn(true);

		centerX = getBattleFieldWidth() / 2;
		centerY = getBattleFieldHeight() / 2;
		BorderX = getBattleFieldWidth();
		BorderY = getBattleFieldHeight();
		Sentry = getSentryBorderSize();

		//gets coardinates and defines future coordinates
		x = getX();
		y = getY();
		h = getHeadingRadians();
		Px = getX();
		Py = getY();

			//main loop
			while(true)
			{

				if (TurnCounter == 0){
					//GoToCentre(centerX,centerY);
				}//End GoToCentre

				else{
					MoveRobot();
				}//End else

				//Begin by iterating TurnCounter
				TurnCounter++;

				//makes sure everything excicutes
				execute();

			}//End While
	}



	//when a robot is scanned by the radar this method will run
	public void onScannedRobot(ScannedRobotEvent e)
	{

		/*
		if(isSentryRobot == false)
		{
		*/

		scanDirection *= -1; // changes value from 1 to -1
		setTurnRadarRight(360 * scanDirection);

		//turns gun towards enemy
		setTurnGunRight(getRadarHeading() - getGunHeading() + e.getBearing());

		//attacks enemy
		attack();

		/*
		} //end sentry check
		else
		{

		} //end else
	*/

	} // end scanned robot event/method

		//shoots at the enemy
		public void attack()
		{


			//checks if the gun is ready to fire
			if(getGunHeat() == 0)
			{

			fire(1);

			} //end if

		} //end attack

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


		public void MoveRobot()
		{

			//generates and distance from centre
			Double fromcentre = (Math.sqrt(Math.pow(2, (getX() - centerX)) + Math.pow(2, (getY() - centerY))));
			int pass = fromcentre.intValue();
			move = rand.nextInt(pass)+2;


			//Generates projected coordinates
			Px += Math.sin(h) + move;
			Py += y+Math.cos(h) + move;


			//checks if movement is within bounds
			if ( (Sentry > Px && Px < (BorderX - Sentry)) && (Sentry > Py && Py < (BorderY - Sentry)) )
			{
				setAhead(move);
			} else {
				turnRight(45);
			}//End Else

		}//End moverobot

}//End Oberon
