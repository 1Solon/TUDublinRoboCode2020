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
	int move;
	int x;
	int y;

	//gets century area
	double century = getSentryBorderSize();

	//gets max height and with so that Oberon won't leave valid playspace and get shot at by century bot
	double maxPlayHeight = getBattleFieldHeight();
	double maxPlayWidth = getBattleFieldWidth();

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

	//generates move
	move = rand(400)+100;

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
