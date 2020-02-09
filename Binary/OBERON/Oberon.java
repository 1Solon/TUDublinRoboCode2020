package Oberon;
import robocode.*;
import java.util.Random;
import static robocode.util.Utils.normalRelativeAngleDegrees;


/**
 * Oberon - a robot by (Alan Byju, Paul Geoghegan and Saul Burgess)
 */


public class Oberon extends AdvancedRobot
{

	//main
	public void run()
	{
		// setColors(Color.red,Color.blue,Color.green); // body,gun,radar

	//declairs rand for use in locate
	Random rand = new Random();
	public static double enemyV, enemyB;
	public static byte scanDirection = 1;

	// Oberon info
	public static int move;
	public static int x;
	public static int y;

	//gets century area
	public static double century = getSentryBorderSize();

	//gets max height and with so that Oberon won't leave valid playspace and get shot at by century bot
	public static double maxPlayHeight = getBattleFieldHeight();
	public static double maxPlayWidth = getBattleFieldWidth();

	// Declairs variables to keep track of enemy energy so that we can check if they have fired
	public static double enemyE = 100;
	public static double enemyCE = 0;

		////enemy info
	public static double enemyD;

		//Stuff to make the radar do stuff and things
		setAdjustRadarForGunTurn(true);
		setTurnRadarRight(36000);

	//generates move
	move = rand(400+100);

	//checks if movement is within bounds
	if((x + move) < (maxPlayHeight - century) || (x + move) < (maxPlayWidth - century) || (x + move) > (maxPlayHeight - century) || (x + move) > (maxPlayWidth - century))
	{

		turnRight(180);

	} // end if
	else
	{

		// moves forward
		setAhead(move);

	} //end else

		execute();
	} //end main


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
	enemyD = getDistance();

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
