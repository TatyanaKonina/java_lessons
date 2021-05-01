package hse.lab;

public class elevator implements ElevatorObject {
	private int currentFloor;
	private int tOpenDoors = 0; // Используется для записи времени открытия
	private double position; // сейчас находиться лифт
	private boolean button[] = new boolean[floor_num]; // стек внутренних кнопок
	private boolean exButton[] = new boolean[floor_num]; // стек внешник кнопок
	final double speed = 0.4; // изменение положения в секунду
	final double ACCURACY = 0.01;
	final int doortime = 12;
	final int DEFAULT_FLOOR = 1; // начальное положение
	private State state;
	private State state0 = State.STATE_INITIAL;

	// 1:up 2: down 0:initial
	// 3:opening 4:waiting
	// 5:closing 6:ready The door is closed. Stand by.
	public State getState() {
		return state;
	}

	public void Start() {
		if (state == State.STATE_WAITING) {
			state = State.STATE_CLOSING; // Close the elevator.
			button[currentFloor] = false;
			exButton[currentFloor] = false;
		}
	}

	public double getPosition() {
		return position;
	}
	public int getCurentFloor(){
		return currentFloor;
	}
	public boolean[] getButtons(){
		return button;
	}
	public boolean[] getExButtons(){
		return exButton;
	}

	public elevator() {
		currentFloor = 1;
		position = 1;
		state = State.STATE_INITIAL;
		for (int i = 0; i <= floor_num - 1; i++) {
			button[i] = false; // At first, the outside button wasn't pressed.
			exButton[i] = false;
		}
	}

	private boolean isEmpty() {
		for (int i = 0; i <= button.length - 1; i++) {
			if (button[i] || exButton[i]) {
				return false;
			}
		}
		return true;// no pressed buttons
	}

	private boolean upperRequest() {
		if (currentFloor == floor_num - 1) {
			return false;
		} else
			for (int i = currentFloor + 1; i <= floor_num - 1; i++) {
				if (button[i] || exButton[i]) {
					return true;
				}
			}
		return false;
	}

	private boolean lowerRequest() {
		if (currentFloor == 0) {
			return false;
		} else {
			for (int i = currentFloor - 1; i >= 0; i--) {
				if (button[i] || exButton[i]) {
					return true;
				}
			}
		}
		return false;
	}

	private void move(boolean up) {
		if (up) {
			position += INTERVAL * speed;
		} else {
			position -= INTERVAL * speed;
		}
	}

	private void goToPosition(boolean direction) {
		if ((Math.abs((int) position - position) < ACCURACY) || (Math.abs((int) position + 1 - position) < ACCURACY)) {
			int passby = Math.abs((int) position - position) < Math.abs((int) position + 1 - position) ? (int) position
					: (int) position + 1;
			if (passby == 1 && isEmpty()) {
				state = State.STATE_INITIAL;
			} else if (button[passby] || exButton[passby]) // stop we a on floor
			{
				state = State.STATE_OPENING;
				button[passby] = false;
				exButton[passby] = false;
			} else {
				move(direction);
			}
			currentFloor = passby;
		} else {
			move(direction);
		}
	}

	public void upDate(int t) {
		switch (state) {
			case STATE_UP:
				state0 = State.STATE_UP;
				goToPosition(true);
				break;
			case STATE_DOWN:
				state0 = State.STATE_DOWN;
				goToPosition(false);
				break;
			case STATE_OPENING:
				if (tOpenDoors == 0) {
					tOpenDoors = t;
				}
				if (t == tOpenDoors + doortime) {
					state = State.STATE_WAITING;
					tOpenDoors = 0;
				}
				break;
			case STATE_CLOSING:
				if (tOpenDoors == 0) {
					tOpenDoors = t;
				}
				if (t == tOpenDoors + doortime) {
					state = State.STATE_READY;
					tOpenDoors = 0;
				}
				break;
			case STATE_INITIAL:
				if (upperRequest()) {
					state = State.STATE_UP;
				} else if (lowerRequest()) {
					state = State.STATE_DOWN;
				} else if (button[currentFloor] || exButton[currentFloor]) {
					state = State.STATE_OPENING; // The current floor is pressed to open the door
				}
				break;
			case STATE_READY:
				if (!isEmpty()) {
					if (state0 == State.STATE_UP)
						if (!upperRequest()) {
							state = State.STATE_DOWN;
						} else {
							state = State.STATE_UP;
						}
					else if (state0 == State.STATE_DOWN)
						if (!lowerRequest()) {
							state = State.STATE_UP;
						} else {
							state = State.STATE_DOWN;
						}
					else if (state0 == State.STATE_INITIAL) // When someone was on the elevator at the beginning.
					{
						if (upperRequest()) {
							state = State.STATE_UP;
						} else if (lowerRequest()) {
							state = State.STATE_DOWN;
						}
					}
				} else // No button was pressed
				{
					if (currentFloor > DEFAULT_FLOOR) {
						state = State.STATE_DOWN;
					} else if (currentFloor < DEFAULT_FLOOR) {
						state = State.STATE_UP;
					} else {
						state = State.STATE_INITIAL;
					}
				}
				break;

		}

	}
}
