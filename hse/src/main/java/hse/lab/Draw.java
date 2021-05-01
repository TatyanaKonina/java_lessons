package hse.lab;

import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;


public class Draw extends JPanel implements ElevatorObject {
	ArrayList<elevator> machines = new ArrayList<elevator>();
	People crew = new People();
	final int ELEVATOR_WIDTH = 150;
	final int ELEVATOR_HEIGHT = 50;
	final int PERSON_WIDTH = 18;
	final int PERSON_INTERVAL = 20; 
	final int BUTTON_INTERVAL = 25;
	final int ELEVATOR_SHIFT = 100;
	int BUTTON_Y = 30;

	public Draw(int elevatorsNum) {
		for (int i = 0; i < elevatorsNum; i++) {
			machines.add(new elevator());
		}
	}

	int getElevatorXcord() {
		return getWidth() * 3 / 5;
	}

	int calYPosition(double position) {
		return ((int) ((floor_num - position - 1) * getHeight() / (floor_num + 1) + getHeight() / (floor_num + 1)));
	}

	void upDate(int t) {
		for (int i = 0; i < machines.size(); i++) {
			machines.get(i).upDate(t);
		}
		crew.upDate(t, machines);
		for (int i = 0; i < machines.size(); i++) {
			if (machines.get(i).getState() == State.STATE_WAITING) {
				if (crew.getOff(machines.get(i).getCurentFloor(), t))
					if (crew.getOn(machines.get(i).getCurentFloor(), t, machines.get(i)))
						machines.get(i).Start();
			}
		}



	}

	public void paintElevator(Graphics2D g, int y, int i) {
		g.setColor(Color.BLACK);
		g.drawRect(getElevatorXcord() + ELEVATOR_SHIFT + i * (ELEVATOR_WIDTH + ELEVATOR_SHIFT), y - ELEVATOR_HEIGHT, ELEVATOR_WIDTH,
				ELEVATOR_HEIGHT);
		String str = new String();
		if (machines.get(i).getState() == State.STATE_OPENING)
			str = "Opening...";
		if (machines.get(i).getState() == State.STATE_CLOSING)
			str = "Closing...";
		if (machines.get(i).getState() == State.STATE_WAITING)
			str = "Waiting...";
		if (machines.get(i).getState() == State.STATE_UP)
			str = "Going up";
		if (machines.get(i).getState() == State.STATE_DOWN)
			str = "Going down";
		if (machines.get(i).getState() == State.STATE_INITIAL)
			str = "Initial State";
		g.drawString(str, getElevatorXcord() + 100 + i * (ELEVATOR_WIDTH + ELEVATOR_SHIFT), y - ELEVATOR_HEIGHT - 5);
	}

	public void paintPeople(Graphics2D g, int id) {
		g.setFont(new Font("Calibri", Font.PLAIN, 16));
		ArrayList<Person> p = crew.getPeople();
		int[] coord_wait = new int[floor_num];
		int coordInElevator = getElevatorXcord() + ELEVATOR_SHIFT + id * (ELEVATOR_WIDTH + ELEVATOR_SHIFT);
		for (int i = 0; i <= floor_num - 1; i++) {
			coord_wait[i] = getElevatorXcord() - 2 * PERSON_WIDTH + ELEVATOR_SHIFT + id * (ELEVATOR_WIDTH + ELEVATOR_SHIFT);
		}
		for (int i = 0; i <= p.size() - 1; i++) {
			if (p.get(i).getElevatorId() == id) {
				if (!p.get(i).cheakInElevator()) // Draw people waiting outside Draw impatient people in red Draw impatient people in blue Draw normal waiting people in yellow draw people on the elevator
				{
					if (p.get(i).bored)
						g.setColor(Color.RED);
					else if (p.get(i).getBoarding())
						g.setColor(Color.YELLOW);
					else
						g.setColor(Color.BLUE);
					g.fillOval(coord_wait[p.get(i).getInitialFloor()], calYPosition(p.get(i).getInitialFloor()) - PERSON_WIDTH, PERSON_WIDTH,
							PERSON_WIDTH);
					g.setColor(Color.GRAY);
					g.drawString(String.valueOf(p.get(i).getIdentity()), coord_wait[p.get(i).getInitialFloor()],
							calYPosition(p.get(i).getInitialFloor()) - PERSON_WIDTH);
					coord_wait[p.get(i).getInitialFloor()] -= PERSON_INTERVAL;
				} else // Painting the man who is taking the elevator in orange
				{
					if (!p.get(i).checkOutElevator() && p.get(i).getElevatorId() == id) {
						g.setColor(Color.ORANGE);
						g.fillOval(coordInElevator,
								calYPosition(machines.get(p.get(i).getElevatorId()).getPosition()) - PERSON_WIDTH,
								PERSON_WIDTH, PERSON_WIDTH);
						g.setColor(Color.GRAY);
						g.drawString(String.valueOf(p.get(i).getIdentity()), coordInElevator,
								calYPosition(machines.get(p.get(i).getElevatorId()).getPosition()) - PERSON_WIDTH);
						coordInElevator += PERSON_INTERVAL;
					} else // People who have gotten off the elevator use green
					{
						g.setColor(Color.GREEN);
						g.fillOval(coord_wait[p.get(i).getDestinationFloor()], calYPosition(p.get(i).getDestinationFloor()) - PERSON_WIDTH,
								PERSON_WIDTH, PERSON_WIDTH);
						g.setColor(Color.GRAY);
						g.drawString(String.valueOf(p.get(i).getIdentity()), coord_wait[p.get(i).getDestinationFloor()],
								calYPosition(p.get(i).getDestinationFloor()) - PERSON_WIDTH);
								coord_wait[p.get(i).getDestinationFloor()] -= PERSON_INTERVAL;
					}

				}
			}

		}
	}

	public void paintButtons(Graphics2D g, int id) {
		g.setFont(new Font("Calibri", Font.BOLD, 30));
		for (int i = 0; i <= floor_num - 1; i++) {
			if (machines.get(id).getButtons()[i])
				g.setColor(Color.BLACK);
			else
				g.setColor(Color.GRAY);
			g.drawString(String.valueOf(i), (getWidth() / (2 * floor_num) * i), BUTTON_Y * 2 + id * ELEVATOR_SHIFT);
			if (machines.get(id).getExButtons()[i])
				g.setColor(Color.BLACK);
			else
				g.setColor(Color.GRAY);
			g.drawString(String.valueOf(i), getWidth() / (2 * floor_num) * i,
					BUTTON_Y*2 + BUTTON_INTERVAL + id * 50);
		}
		g.setFont(new Font("等线", Font.PLAIN, 20));
		g.setColor(Color.BLACK);
		g.drawString("Elevator " + id, getWidth() / 2, BUTTON_Y + id * ELEVATOR_SHIFT);
		g.drawString("Внутренние кнопки", getWidth() / 2, BUTTON_Y + BUTTON_INTERVAL + id * ELEVATOR_SHIFT);
		g.drawString("Внешние кнопки", getWidth() / 2, BUTTON_Y + BUTTON_INTERVAL * 2 + id * ELEVATOR_SHIFT);
		g.setFont(new Font("Calibri", Font.BOLD, 20));
		g.setColor(Color.GRAY);
		for (int i = 0; i <= floor_num - 1; i++)
			g.drawString(String.valueOf(i), getElevatorXcord() + 250 + id * (ELEVATOR_WIDTH + ELEVATOR_SHIFT), calYPosition(i));

	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.RED);
		for (int i = 0; i < machines.size(); i++) {
			paintElevator(g2d, calYPosition(machines.get(i).getPosition()), i);
			paintPeople(g2d, i);
			paintButtons(g2d, i);
		}

		// paintElevator(g2d, calYPosition(machine.getPosition()));

	}

}
