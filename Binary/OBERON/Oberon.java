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
	//imports rand and declairs variables
	Random rand = new Random();
	double x, y, h, rangeX, rangeY;
	byte scanDirection = -1;
	int a, b;

	//declairs arrays that will dictate the grid that Oberon follows
	double[] goToX = new double[25];
	double[] goToY = new double[25];

	//main
	public void run(){
		//sets colors for Oberon
		setBodyColor(Color.green);
		setGunColor(Color.green);
		setRadarColor(Color.green);
		setScanColor(Color.green);

		//sets the area in the center of the arena that Oberon will stay within
		rangeX = getBattleFieldWidth() - (getSentryBorderSize() * 2);
		rangeY = getBattleFieldHeight() - (getSentryBorderSize() * 2);

		//devides up the block in to small blocks that will form a grid
		rangeX = rangeX / 25;
		rangeY = rangeY / 25;

		//this loop creates the points that Oberon will use
		for(int i = 0 ; i < 25 ; i++)
		{

			//this saves the coardinates in the arrays which will be 25 points * 25 points for a total of 625 points
			goToX[i] = rangeX / 4 + (rangeX * i) + getSentryBorderSize();
			goToY[i] = rangeY / 4 + (rangeY * i) + getSentryBorderSize();

		} //end for

		//this turns the radar at the start of the battle
		setTurnRadarRight(2560);

		//main loop
		while(true){

			//this checks if Oberon has finished moveing
			if(getDistanceRemaining() < 1)
			{

				//goes to the movement stratagy controller
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

		//this uses the random numbers generated to pick semi random coardinates inside of our valid playspace
		x = goToX[a];
		y = goToY[b];

		// Goes to the goTo method
		goTo();

	}//End movementStrategyController

	//when a robot is scanned by the radar this method will run
	public void onScannedRobot(ScannedRobotEvent ScannedRobotEvent)
	{

		//this checks if the scanned robot is a sentry bot or not
		if(!ScannedRobotEvent.isSentryRobot())
		{

			//this oscilates the radar so that Oberon can keep track of the enemy robot
			scanDirection *= -1; // changes value from 1 to -1
			setTurnRadarRight(2560 * scanDirection);

			// This uses basic triganomitry to do a linier prediction of where the enemy will be and fires at it
			double absoluteBearing = getHeadingRadians() + ScannedRobotEvent.getBearingRadians();
			setTurnGunRightRadians(Utils.normalRelativeAngle(absoluteBearing - getGunHeadingRadians() + (ScannedRobotEvent.getVelocity() * Math.sin(ScannedRobotEvent.getHeadingRadians() - absoluteBearing) / 13.0)));
			setFire(3.0);

		} //end sentry check
		else
		{

			//this keeps turning the radar if it has detected a sentry bot
			setTurnRadarRight(2560);

		} //end else

	} // end scanned robot event/method


	//when Oberon hits another robot
	public void onHitRobot(HitWallEvent e)
	{

		//reverses Oberon by 10 if it colides with another robot
		setBack(10);

	} //end on hit robot event/method

	//A function that controls the goTo strategy of Oberon using passed cordinates from the movement controller in main
	public void goTo (){
		//this gets the distance from our current and futuren X and Y coardinates
		x = x - getX();
		y = y - getY();

		//this figures out the angle that Oberon will need to turn in order to reach it's desired destination
		double goAngle = Utils.normalRelativeAngleDegrees(Math.atan2(x, y) - getHeadingRadians());

		//turns Oberon and moves forward to coardinates
		setTurnRightRadians(Math.atan(Math.tan(goAngle)));
		setAhead(Math.cos(goAngle) * Math.hypot(x, y));
	}//End goTo

}//End Oberon
