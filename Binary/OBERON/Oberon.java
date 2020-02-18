package Oberon;
import robocode.*;
import robocode.util.Utils;
import java.util.*;
import java.awt.Color;


/**
 * Oberon - Decision tree based melee (1v1) robot
 * <p>
 * Moves around the central grid within the borderzone, and uses it's decision tree to decide upon optimal strategies for combat
 *
 * @author Paul Geoghegan
 * @author Saul Burgess
 * @author Alan Byju
 */


public class Oberon extends Robot
{
	//imports rand and declairs variables
	Random rand = new Random();
	double x, y, h, rangeX, rangeY;
	byte scanDirection = -1;
	int a, b;

	//declairs arrays that will dictate the grid that Oberon follows
	double[] goToX = new double[5];
	double[] goToY = new double[5];

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
		rangeX = rangeX / 5;
		rangeY = rangeY / 5;

		//this loop creates the points that Oberon will use
		for(int i = 0 ; i < 5 ; i++)
		{

			//this saves the coardinates in the arrays which will be 5 points * 5 points for a total of 65 points
			goToX[i] = rangeX / 4 + (rangeX * i) + getSentryBorderSize();
			goToY[i] = rangeY / 4 + (rangeY * i) + getSentryBorderSize();

		} //end for

		//main loop
		while(true){

			//this turns the radar at the start of the battle
			turnRadarRight(360);

			//this checks if Oberon has finished moveing

			if (getVelocity() < 1){

				//goes to the movement stratagy controller
				movementStrategyController();

			}//End Getvelocity


		}//End While
	}//End Run



	//This function is the key component of Oberon, the basis of it's decision-tree. This function decides the best strategy for each scenario and executes them
	void movementStrategyController(){

		//generates random numbers to choose what point Oberon will go to
		a = rand.nextInt(5);
		b = rand.nextInt(5);

		// Goes to the goTo method
		goTo(goToX[a],goToY[b]);

	}//End movementStrategyController

	//when a robot is scanned by the radar this method will run
	public void onScannedRobot(ScannedRobotEvent ScannedRobotEvent)
	{

		//this checks if the scanned robot is a sentry bot or not
		if(!ScannedRobotEvent.isSentryRobot())
		{

			//Calculates teh values required for a firing solution to be calculated, also includes systems for converting import variables to radians
			double BodyRadians = getHeading() * 0.0174533, GunRadians = getGunHeading() * 0.0174533, BearingRadians = ScannedRobotEvent.getBearing() * 0.0174533, absoluteBearing = BodyRadians + BearingRadians;

			// This uses basic triganomitry to do a linear prediction of where the enemy will be and fires at that projected point
			turnGunRight((Utils.normalRelativeAngle(absoluteBearing - GunRadians + (ScannedRobotEvent.getVelocity() * Math.sin(BodyRadians - absoluteBearing) / 13.0))) / 0.0174533);
			fire(3.0);

		}//End sentrychecker if
	}//End scanned robot event/method


	//when Oberon hits another robot
	public void onHitRobot(HitWallEvent e)
	{

		//reverses Oberon by 10 if it colides with another robot
		back(10);

	} //end on hit robot event/method

	//A function that controls the goTo strategy of Oberon using passed cordinates from the movement controller in main
	public void goTo (double x, double y){
		//this gets the distance from our current and futuren X and Y coardinates
		x = x - getX();
		y = y - getY();

		//Calc data for formulas
		double BodyRadians = getHeading() * 0.0174533;

		//this figures out the angle that Oberon will need to turn in order to reach it's desired destination
		double goAngle = Utils.normalRelativeAngleDegrees(Math.atan2(x, y) - BodyRadians);

		//turns Oberon and moves forward to coardinates
		turnRight((Math.atan(Math.tan(goAngle))) / 0.0174533);
		ahead(Math.cos(goAngle) * Math.hypot(x, y));
	}//End goTo

}//End Oberon
