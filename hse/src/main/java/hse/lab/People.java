package hse.lab;

import java.util.ArrayList;

public class People implements ElevatorObject {
	private ArrayList<Person> crew = new ArrayList<Person>();
	final int ENTERTIME = 12;
	private int counter = 0, counter2 = 0;
	int chooseMachine(ArrayList<elevator> machines,int floor0){
		int minElevator = 0;
		
		for (int i =0;i<machines.size();i++){
			if (machines.get(i).getState() == STATE_UP && floor0 > machines.get(i).current_floor){
				if(machines.get(minElevator).current_floor > machines.get(i).current_floor){
					minElevator = i;
				}
			}
			else if (machines.get(i).getState() == STATE_DOWN && floor0 < machines.get(i).current_floor){
				if(machines.get(minElevator).current_floor < machines.get(i).current_floor){
					minElevator = i;
				}
			}
			else{
				if (Math.abs(floor0 - machines.get(i).current_floor) < Math.abs(machines.get(minElevator).current_floor - machines.get(i).current_floor) ){
					minElevator = i;
				}
			}
			// System.out.println(minElevator);
		}
		return minElevator;
		
	}

	void upDate(int t, ArrayList<elevator> machines) // 处理新人的到来，等烦的人的离去 到达的人适时删去
	{

		if (crew.size() == 0) {
			crew.add(new Person(t));
			
			int machine = chooseMachine(machines,crew.get(crew.size() - 1).floor0);
			crew.get(crew.size() - 1).setElevatorId(machine);
			machines.get(machine).ex_button[crew.get(crew.size() - 1).floor0] = true;
		} else
			for (int i = 0; i <= crew.size() - 1; i++) {
				int machine = crew.get(i).getElevatorId();
				crew.get(i).upDate(t, machines.get(machine));
				if (crew.get(i).nextone) {
					if (!crew.get(i).nextonecome) {
						crew.add(new Person(t));

						machine = chooseMachine(machines,crew.get(crew.size() - 1).floor0);
						crew.get(crew.size() - 1).setElevatorId(machine);
						machines.get(machine).ex_button[crew.get(crew.size() - 1).floor0] = true;
						crew.get(i).nextonecome = true;
						System.out.println("User " + crew.get(crew.size() - 1).identity + " elevator " + machine + " flo0r0 " + crew.get(crew.size() - 1).floor0 + " to: " + crew.get(crew.size() - 1).d_floor);
					}
					// if(crew.get(i).bored) crew.remove(i); //如果有人等的时间过长，而且下一位已经来到，就可以移除了
				}
				if (crew.get(i).inelevator) {
					if (crew.get(i).outelevator)
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

	boolean getOn(int current_floor, int t, elevator machine) // 让电梯停下的楼层内等候的人上电梯
	{

		for (int i = 0; i <= crew.size() - 1;) {
			if (crew.get(i).floor0 == current_floor && !crew.get(i).bored && !crew.get(i).inelevator)
			// 人满足的条件：1.身处current_floor 2.还没无聊 3.还没进电梯
			{
				if (counter == 0)
					counter = t;
				if (((t - counter) % ENTERTIME == 0) && t != counter) // 每ENTERTIME个单位时间才执行一次内部代码
				{
					counter = t;
					crew.get(i).inelevator = true;
					crew.get(i).boarding = false;
					machine.button[crew.get(i).d_floor] = true; // 在内部按按钮的操作
				} else
					crew.get(i).boarding = true;
				return false;
			}
			i++;
		}
		counter = 0;
		return true; // 表示所有该上的人都已经上电梯了
	}

	boolean getOff(int current_floor, int t) // 让电梯停下的楼层是目的地的人下电梯
	{

		for (int i = 0; i <= crew.size() - 1;) {
			if (crew.get(i).d_floor == current_floor && crew.get(i).inelevator && !crew.get(i).outelevator)
			// 人满足的条件：目的地是current_floor 2.已经在电梯了 3. 还没出电梯
			{
				if (counter2 == 0)
					counter2 = t;
				if (((t - counter2) % ENTERTIME == 0) && t != counter2) // 每ENTERTIME个单位时间才执行一次内部代码
				{
					counter2 = t;
					crew.get(i).outelevator = true;
					crew.get(i).outtime = t;
					crew.get(i).boarding = false;
					
				} else
					crew.get(i).boarding = true;
				return false;
			}
			i++;
		}
		counter2 = 0;
		return true; // 表示所有该下的人都已经下电梯了
	}

	void Print() {
		for (int i = 0; i <= crew.size() - 1; i++) {
			System.out.println("crew" + i);
			System.out.println("floor0" + crew.get(i).floor0);
			System.out.println("d_floor" + crew.get(i).d_floor);
		}
	}

}
