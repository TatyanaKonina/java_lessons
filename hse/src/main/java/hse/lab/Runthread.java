package hse.lab;

import javax.swing.JFrame;

public class Runthread extends Thread implements ElevatorObject {
	public void run() {
		JFrame drawframe = new JFrame();
		drawframe.setTitle("Elevator");
		drawframe.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		Draw draw = new Draw(2);
		drawframe.add(draw);
		// drawframe.pack();
		// drawframe.repaint();
		drawframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		drawframe.setVisible(true);
		int t = 0, t0 = 0;
		long milli = 0L;
		for (milli = System.currentTimeMillis();;) {

			t = (int) (System.currentTimeMillis() - milli) / 100;
			draw.upDate(t);
			draw.repaint();
			if (t - t0 == 8) {
				t0 = t;

			}
			try {
				Thread.sleep((long) (INTERVAL * 1000));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		Runthread run = new Runthread();
		run.start();

		// drawframe.repaint();
	}
}
