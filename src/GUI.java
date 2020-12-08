import java.awt.GridLayout;

import javax.swing.JFrame;

class GUI extends JFrame {

	MyPanel[] panel;
	Thread[] thread;

	int numberSize;

	public GUI(Simulation sm, String title) {
		this.numberSize = sm.numberSize;

		setTitle("   Simulation GUI   :" + title);
		setSize(800, 800);
		setLocation(300, 150);

		panel = new MyPanel[numberSize];
		thread = new Thread[numberSize];

		setLayout(new GridLayout(numberSize / 4, 4));
		for (int i = 0; i < numberSize; ++i) {
			panel[i] = new MyPanel((i + 1) + " player", sm.node[i].h);
			add(panel[i]);
			thread[i] = new Thread(panel[i]);
			thread[i].start();
		}

		setVisible(true);
	}
	
	public boolean isFinish() {

		for(Thread t : thread) {
			if (t.isAlive()) return true;
		}
		return false;
	}
	
}
