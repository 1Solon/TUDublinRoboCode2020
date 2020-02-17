package Oberon;
import robocode.*;
import robocode.util.Utils;
import java.util.*;
import robocode.Event;
import java.awt.*;
import static robocode.util.Utils.normalRelativeAngleDegrees;



/**
 * Oberon - Decision tree based melee (1v1) robot
 * <p>
 * Moves around the central grid within the borderzone, and uses it's decision tree to decide upon optimal strategies for combat
 *
 * @author Paul Geoghegan
 * @author Saul Burgess
 * @author Alan Byju
 */


public class Oberon extends AdvancedRobot
{
	Random rand = new Random();
	double x, y, h, rangeX, rangeY;
	byte scanDirection = -1;
	int a, b;
	double[] goToX = new double[25];
	double[] goToY = new double[25];

	//main
	public void run(){
		setBodyColor(Color.green);
		setGunColor(Color.green);
		setRadarColor(Color.green);
		setScanColor(Color.green);

		rangeX = getBattleFieldWidth() - (getSentryBorderSize() * 2);
		rangeY = getBattleFieldHeight() - (getSentryBorderSize() * 2);

		rangeX = rangeX / 25;
		rangeY = rangeY / 25;

		setTurnRadarRight(2560);

		for(int i = 0 ; i < 25 ; i++)
		{

			goToX[i] = rangeX / 4 + (rangeX * i) + getSentryBorderSize();
			goToY[i] = rangeY / 4 + (rangeY * i) + getSentryBorderSize();

		} //end for

		//main loop
		while(true){

			if(getDistanceRemaining() < 1)
			{

				movementStrategyController();

			} //end if

			//makes sure everything excicutes
			execute();

		}//End While
	}//End Run


	//This function is the key component of Oberon, the basis of it's decision-tree. This function decides the best strategy for each scenario and executes them
	void movementStrategyController(){

		//generates random numbers to choose what point Oberon will go to
		a = rand.nextInt(25);
		b = rand.nextInt(25);

		x = goToX[a];
		y = goToY[b];

		//passes x and y coardinates for Oberon to go to
		goTo();

	}//End movementStrategyController

	//when a robot is scanned by the radar this method will run
	public void onScannedRobot(ScannedRobotEvent ScannedRobotEvent)
	{

		if(!ScannedRobotEvent.isSentryRobot())
		{

		scanDirection *= -1; // changes value from 1 to -1
		setTurnRadarRight(2560 * scanDirection);

		//attacks enemy
		double absoluteBearing = getHeadingRadians() + ScannedRobotEvent.getBearingRadians();
		setTurnGunRightRadians(Utils.normalRelativeAngle(absoluteBearing - getGunHeadingRadians() + (ScannedRobotEvent.getVelocity() * Math.sin(ScannedRobotEvent.getHeadingRadians() - absoluteBearing) / 13.0)));
		setFire(3.0);

		} //end sentry check
		else
		{

			setTurnRadarRight(2560);

		} //end else

	} // end scanned robot event/method

	//when Oberon is hit by a bullit this method will run
	public void onHitByBullet(HitByBulletEvent e)
	{

		//TEMP

	} //end when hit by bullit event/method

	//when robot hits another robot
	public void onHitRobot(HitWallEvent e)
	{

		setBack(10);

	} //end on hit robot event/method

	//A function that controls the goTo strategy of Oberon using passed cordinates from the movement controller in main
	public void goTo (){
		x = x - getX();
		y = y - getY();

		double goAngle = Utils.normalRelativeAngleDegrees(Math.atan2(x, y) - getHeadingRadians());

		setTurnRightRadians(Math.atan(Math.tan(goAngle)));
		setAhead(Math.cos(goAngle) * Math.hypot(x, y));
	}//End goTo

}//End Oberon
