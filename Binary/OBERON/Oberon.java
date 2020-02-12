//things to look at
//setTurnGunRight(getHeading() - getGunHeading() + e.getBearing());

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
	double Px;
	double Py;

	// Declairs variables to keep track of enemy energy so that we can check if they have fired
	double enemyE = 100;
	double enemyCE = 0;

	////enemy info
	double enemyD;

	//main
	public void run()
	{
		// setColors(Color.red,Color.blue,Color.green); // body,gun,radar

		//main loop
		while(true)
		{

			//Begin by iterating TurnCounter
			TurnCounter++;

			//Stuff to make the radar do stuff and things
			setAdjustRadarForGunTurn(true);
			setTurnRadarRight(36000);

			//gets coardinates and defines future coordinates
			x = getX();
			y = getY();

			if (TurnCounter == 0){
				GoToCentre(getBattleFieldWidth()/2,getBattleFieldHeight()/2);
			}//End GoToCentre

			else{
				MoveRobot();
			}//End else

			//makes sure everything excicutes
			execute();

		}//End While

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

	public void GoToCentre(double x, double y)
	{
		double a;
    setTurnRightRadians(Math.tan(
        a = Math.atan2(x -= (int) getX(), y -= (int) getY())
              - getHeadingRadians()));
    setAhead(Math.hypot(x, y) * Math.cos(a));
	}//End GOTO


	public void MoveRobot()
	{

		//gets century area
		double century = getSentryBorderSize();

		//generates move
		move = rand.nextInt(80)+20;

		//Generates projected coordinates
		Px = x+Math.sin(getHeading()) * move;
		Py = y+Math.cos(getHeading()) * move;


		//checks if movement is within bounds
		if ((getSentryBorderSize() < Px && Px < getBattleFieldWidth() - getSentryBorderSize()) && (getSentryBorderSize() < Py && Py < getBattleFieldHeight() - getSentryBorderSize()))
		{
			setAhead(move);
		} else {
			turnRight(45);
		}//End Else

	}//End moverobot

}//End Oberon
