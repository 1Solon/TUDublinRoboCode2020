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

	// variables for max play space
	double maxPlayHeight, maxPlayWidth;

	//gets max height and with so that Oberon won't leave valid playspace and get shot at by century bot
	maxPlayHeight = getBattleFieldHeight() - (getSentryBorderSize() * 2);
	maxPlayWidth = getBattleFieldWidth() - (getSentryBorderSize() * 2);

	// Declairs variables to keep track of enemy energy so that we can check if they have fired
	double enemyE = 100;
	double CE;

		////enemy info
	double enemyD;

	//main
	public void run()
	{
		// setColors(Color.red,Color.blue,Color.green); // body,gun,radar

		//Stuff to make the radar do stuff and things
		setAdjustRadarForGunTurn(true);
		setTurnRadarRight(36000);
		execute();s
	} //end main


	//fires at the enemy robot
	public void runGun()
	{

		//TEMP

	} //end fire method

	//when a robot is scanned by the radar this method will run
	public void onScannedRobot(ScannedRobotEvent e)
	{

	scanDirection *= -1; // changes value from 1 to -1
	setTurnRadarRight(360 * scanDirection);

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
