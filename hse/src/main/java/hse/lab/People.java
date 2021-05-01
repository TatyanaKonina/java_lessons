package hse.lab;

import java.util.ArrayList;

public class People implements ElevatorObject {
	private ArrayList<Person> crew = new ArrayList<Person>();
	final int ENTERTIME = 12;
	private int counter = 0, counter2 = 0;
	int chooseMachine(ArrayList<elevator> machines,int floor0){
		int minElevator = 0;
		
		for (int i =0;i<machines.size();i++){
			if (machines.get(i).getState() == State.STATE_UP && floor0 > machines.get(i).getCurentFloor()){
				if(machines.get(minElevator).getCurentFloor() > machines.get(i).getCurentFloor()){
					minElevator = i;
				}
			}
			else if (machines.get(i).getState() == State.STATE_DOWN && floor0 < machines.get(i).getCurentFloor()){
				if(machines.get(minElevator).getCurentFloor() < machines.get(i).getCurentFloor()){
					minElevator = i;
				}
			}
			else{
				if (Math.abs(floor0 - machines.get(i).getCurentFloor()) < Math.abs(machines.get(minElevator).getCurentFloor() - machines.get(i).getCurentFloor()) ){
					minElevator = i;
				}
			}
		}
		return minElevator;
		
	}

	void upDate(int t, ArrayList<elevator> machines) 
	//To deal with the arrival of new people, wait for the departure of annoying people to arrive in time to delete
	{

		if (crew.size() == 0) {
			crew.add(new Person(t));
			
			int machine = chooseMachine(machines,crew.get(crew.size() - 1).getInitialFloor());
			crew.get(crew.size() - 1).setElevatorId(machine);
			machines.get(machine).getExButtons()[crew.get(crew.size() - 1).getInitialFloor()] = true;
		} else
			for (int i = 0; i <= crew.size() - 1; i++) {
				int machine = crew.get(i).getElevatorId();
				crew.get(i).upDate(t, machines.get(machine));
				if (crew.get(i).nextone) {
					if (!crew.get(i).nextonecome) {
						crew.add(new Person(t));

						machine = chooseMachine(machines,crew.get(crew.size() - 1).getInitialFloor());
						crew.get(crew.size() - 1).setElevatorId(machine);
						machines.get(machine).getExButtons()[crew.get(crew.size() - 1).getInitialFloor()] = true;
						crew.get(i).nextonecome = true;
						// debug
						System.out.println("User " + crew.get(crew.size() - 1).getIdentity() + " elevator " + machine + " flo0r0 " + crew.get(crew.size() - 1).getInitialFloor() + " to: " + crew.get(crew.size() - 1).getDestinationFloor());
					}
					
				}
				if (crew.get(i).cheakInElevator()) {
					if (crew.get(i).checkOutElevator())
						if (t - crew.get(i).outtime >= crew.get(i).OUTTIME)
							crew.remove(i);
				}
				if (crew.get(i).bored) {
					if (t - crew.get(i).boredtime >= crew.get(i).REMAININGTIME)
						crew.remove(i);
				}
			}

	}

	public ArrayList<Person> getPeople() {
		return crew;
	}

	boolean getOn(int current_floor, int t, elevator machine) // Let the elevator stop on the floor waiting for people to get on the elevator
	{

		for (int i = 0; i <= crew.size() - 1;) {
			if (crew.get(i).getInitialFloor() == current_floor && !crew.get(i).bored && !crew.get(i).cheakInElevator())
			// People meet the conditions: 1.It is located in current_floor 2.Not boring 3.We're not in the elevator yet.
			{
				if (counter == 0)
					counter = t;
				if (((t - counter) % ENTERTIME == 0) && t != counter) // 每ENTERTIME个单位时间才执行一次内部代码
				{
					counter = t;
					crew.get(i).setInElevator(true);
					crew.get(i).setBoarding(false);
					machine.getButtons()[crew.get(i).getDestinationFloor()] = true; // 在内部按按钮的操作
				} else
					crew.get(i).setBoarding(true);
				return false;
			}
			i++;
		}
		counter = 0;
		return true; // It means everyone who should be on the elevator is already on the elevator.
	}

	boolean getOff(int current_floor, int t) // The floor where the elevator stops is where the destination person gets off the elevator.
	{

		for (int i = 0; i <= crew.size() - 1;) {
			if (crew.get(i).getDestinationFloor() == current_floor && crew.get(i).cheakInElevator() && !crew.get(i).checkOutElevator())
			// People meet the conditions:the destination is current_floor 2.Already in the elevator 3. We're not out of the elevator yet.
			{
				if (counter2 == 0)
					counter2 = t;
				if (((t - counter2) % ENTERTIME == 0) && t != counter2) 
				{
					counter2 = t;
					crew.get(i).setOutElevator(true);
					crew.get(i).outtime = t;
					crew.get(i).setBoarding(false);
					
				} else
					crew.get(i).setBoarding(true);
				return false;
			}
			i++;
		}
		counter2 = 0;
		return true; // It means everyone who should get off the elevator has got off the elevator.
	}
}
