//things to look at
//setTurnGunRight(getHeading() - getGunHeading() + e.getBearing());

package Oberon;
import robocode.*;
import java.util.*;
import static robocode.util.Utils.*;


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
	double move;
	double x;
	double y;
	double goAngle;

	// Declairs variables to keep track of enemy energy so that we can check if they have fired
	double enemyE = 100;
	double enemyCE = 0;

		////enemy info
	double enemyD;

	//main
	public void run()
	{
		// setColors(Color.red,Color.blue,Color.green); // body,gun,radar

		//gets max height and with so that Oberon won't leave valid playspace and get shot at by century bot
		double maxPlayHeight = getBattleFieldHeight() - (getSentryBorderSize() * 2);
		double maxPlayWidth = getBattleFieldWidth() - (getSentryBorderSize() * 2);

		//gets century area
		double century = getSentryBorderSize();

		//main loop
		while(true)
		{

			//Stuff to make the radar do stuff and things
			setAdjustRadarForGunTurn(true);
			setTurnRadarRight(36000);

			//generates move
			move = rand.nextInt(80)+20;


			//makes sure everything executes
			execute();
		} //end while

	} //end main

	//Function to control the botmove system
	private void BotMove(double x, double y)
	{

		x = x - getX();
		y = y - getY();

		double goAngle = Utils.normalRelativeAngle(Math.atan2(x,y) - getHeadingRadians());
		setTurnRightRadians(Math.atan(Math.tan(goAngle)));
		setAhead(Math.cos(goAngle) * Math.hypot(x,y));

	}//End BotMove

	//when a robot is scanned by the radar this method will run
	public void onScannedRobot(ScannedRobotEvent e)
	{

	scanDirection *= -1; // changes value from 1 to -1
	setTurnRadarRight(360 * scanDirection);

	//get enemy energy
	enemyCE = getEnergy();

	if(enemyCE != enemyE)
	{

		enemyE = enemyCE;

	} //end if

	//gets enemy info
//enemyD = e.getDistance;

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
