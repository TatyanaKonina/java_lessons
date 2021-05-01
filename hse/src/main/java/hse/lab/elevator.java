package hse.lab;

import java.lang.reflect.Array;
import java.util.ArrayList;
public class elevator implements ElevatorObject {
	int current_floor;
	int t0=0; //Используется для записи времени открытия
	private double position; //сейчас находиться лифт
	boolean button[]=new boolean[floor_num]; //стек внутренних кнопок
	boolean ex_button[]=new boolean[floor_num]; //стек внешник кнопок
	final double speed=0.4; //изменение положения в секунду
	final double ACCURACY=0.01;
	final int doortime=12;
	final int DEFAULT_FLOOR=1; //начальное положение
	private int state; 
	private int state0=STATE_INITIAL;
	//1:up  2: down  0:initial 
	//3:opening  4:waiting 
	//5:closing  6:ready The door is closed. Stand by.
	int getState()
	{
		return state;
	}
	void Start()
	{
		if(state==STATE_WAITING)
		{
			state=STATE_CLOSING; //Close the elevator.
			button[current_floor]=false;
			ex_button[current_floor]=false;
		}
	}
	double getPosition()
	{
		return position;
	}
	public elevator()
	{
		current_floor=1;
		position=1;
		state=STATE_INITIAL;
		for(int i=0;i<=floor_num-1;i++)
			{
			button[i]=false; //At first, the outside button wasn't pressed.
			ex_button[i]=false;
			}
	}
	boolean isEmpty()
	{
		for(int i=0;i<=button.length-1;i++)
			if(button[i]||ex_button[i]) return false;
		return true;//no pressed buttons
	}
	boolean upperRequest() 
	{
		if(current_floor==floor_num-1) return false;
		else
		for(int i=current_floor+1;i<=floor_num-1;i++)
			if(button[i]||ex_button[i]) return true;
		return false;
	}
	boolean lowerRequest() 
	{
		if(current_floor==0) return false;
		else
		for(int i=current_floor-1;i>=0;i--)
			if(button[i]||ex_button[i]) return true;
		return false;
	}
	void move(boolean up)
	{
		if(up)
			position+=INTERVAL*speed;
		else
			position-=INTERVAL*speed;
	}
	void upDate(int t)
	{
		
		if(state==STATE_UP)
		{
			state0=STATE_UP;
			if((Math.abs((int)position-position)<ACCURACY)||(Math.abs((int)position+1-position)<ACCURACY))
			{
				int passby=Math.abs((int)position-position)<Math.abs((int)position+1-position)?(int)position:(int)position+1;
				if(passby==1&&isEmpty()) state=STATE_INITIAL;
				else if(button[passby]||ex_button[passby])  //stop we a on floor
				{
					state=STATE_OPENING;
					button[passby]=false;
					ex_button[passby]=false;
				}
				else move(true);
				current_floor=passby;
			}
			else move(true);
		}
		if(state==STATE_DOWN)
		{
			state0=STATE_DOWN;
			if((Math.abs((int)position-position)<ACCURACY)||(Math.abs((int)position+1-position)<ACCURACY))
			{
				int passby=Math.abs((int)position-position)<Math.abs((int)position+1-position)?(int)position:(int)position+1;
				if(passby==1&&isEmpty()) state=STATE_INITIAL;
				else if(button[passby]||ex_button[passby])  //stop and open doors
				{
					state=STATE_OPENING;
					button[passby]=false;
					ex_button[passby]=false;
				}
				else move(false);
				current_floor=passby;
			}
			else move(false);
		}
		if(state==STATE_OPENING)
		{
			if(t0==0) t0=t;
			if(t==t0+doortime) 
			{
				state=STATE_WAITING;
				t0=0;
			}
		}
		if(state==STATE_CLOSING)
		{
			if(t0==0) t0=t;
			if(t==t0+doortime) 
			{
				state=STATE_READY;
				t0=0;
			}
		}
		if(state==STATE_INITIAL)
		{
			if(upperRequest()) state=STATE_UP;
			else if(lowerRequest()) state=STATE_DOWN;
			else if(button[current_floor]||ex_button[current_floor]) state=STATE_OPENING; //The current floor is pressed to open the door
		}
		if(state==STATE_READY)
		{
			if(!isEmpty()) 
			{
				if(state0==STATE_UP)
					if(!upperRequest()) state=STATE_DOWN;
					else state=STATE_UP;
				else if(state0==STATE_DOWN)
					if(!lowerRequest()) state=STATE_UP;
					else state=STATE_DOWN;
				else if(state0==STATE_INITIAL) //When someone was on the elevator at the beginning.
				{
					if(upperRequest()) state=STATE_UP;
					else if(lowerRequest()) state=STATE_DOWN;
				}
			}
			else //No button was pressed
			{
				if(current_floor>DEFAULT_FLOOR) state=STATE_DOWN;
				else if(current_floor<DEFAULT_FLOOR) state=STATE_UP;
				else state=STATE_INITIAL;
			}
			
		}
	}
}


