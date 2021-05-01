package hse.lab;

import java.lang.Math;
public class Person implements ElevatorObject {
	static int ID=0; //Used to mark everyone in gui
	private int elevatorId;
	private int identity;
	private int floorWhere; //Initial Floor
	private int destinationFloor; //Target Floor
	public int timeWait; //The moment to start waiting
	public int outtime; //Out of the elevator moment
	public int boredtime; 
	public int time_interval; 
	public boolean bored=false; 
	public boolean nextone=false; //Is the next person coming?
	public boolean nextonecome=false; //Does the next person produce
	private boolean inelevator=false; 
	private boolean outelevator=false; 
	private boolean boarding=false; //Are you getting on the elevator?
	final int WAITTIME=300;
	final int OUTTIME=100; //Display time after out of the elevator
	final int REMAININGTIME=150;
	final int MAXINTERVAL=120;
	final int MININTERVAL=20;
	public Person(int t)
	{
		floorWhere=(int)(Math.random()*floor_num);
		identity=++ID;
		while((destinationFloor=(int)(Math.random()*floor_num))==floorWhere); 
		timeWait=t;
		while((time_interval=(int)(Math.random()*MAXINTERVAL))<MININTERVAL);
		
	}
	public int getInitialFloor(){
		return floorWhere;
	}

	public int getElevatorId(){
		return elevatorId;
	}
	public int getIdentity(){
		return identity;
	}
	public int getDestinationFloor(){
		return destinationFloor;
	}
	public boolean cheakInElevator(){
		return inelevator;
	}
	public boolean checkOutElevator(){
		return outelevator;
	}
	public void setOutElevator(boolean b){
		this.outelevator = b;
	}
	public void setInElevator(boolean b){
		this.inelevator = b;
	}
	public void setBoarding(boolean b){
		this.boarding = b;
	}
	public boolean getBoarding(){
		return boarding;
	}

	public void setElevatorId(int id){
		this.elevatorId = id;
	}
	public void upDate(int t, elevator machine)
	{
		if(!inelevator&&bored==false&&!boarding) 
			if(t-timeWait>=WAITTIME) 
			{
				if(Math.abs(machine.getPosition()-floorWhere)>=1)
				{bored=true;boredtime=t;}
			}
		if(t-timeWait>=time_interval) nextone=true;
	}
}
