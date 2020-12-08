import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Label;
import java.awt.RenderingHints;

import javax.swing.JPanel;

class MyPanel extends JPanel implements Runnable {

	final int ovalRadius = 6, T = 55;

	String[] moveBit;
	int x, y, width, height, maxHeight;
	boolean flag;
	
	public MyPanel(String str, int maxHeight) {
		Label title = new Label(str);;
		title.setForeground(Color.DARK_GRAY);
		add(title);

		moveBit = new String[4];
		width = 5;
		flag = true;

		x = 98;
		y = 160;

		this.maxHeight = Math.min(160, maxHeight);
		height = (160 - this.maxHeight) / 11 + 1;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;

		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		g2.setStroke(new BasicStroke(1));
		g2.setColor(Color.BLACK);

		g2.drawLine(getWidth() / 2 + 3, getHeight() / 2 + 3, x + 3, y + 3);
		g2.fillOval(getWidth() / 2, getHeight() / 2, ovalRadius, ovalRadius);
		g2.fillOval(x, y, ovalRadius, ovalRadius);
	}

	@Override
	public void run() {
		int t = 0;
		while (t++ < T) {
			repaint();

			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			x += width;
			if (x > getWidth() - 5) {
				flag = false;
				width = -8;
			} else if (x < 5) {
				flag = true;
				width = 8;
			}


			if (x > 5 && x < getWidth() / 2 - 10) {
				if (flag) y += height; 
				else y -= height;
			} else if (x > getWidth() / 2 && x < getWidth() - 10) {
				if (flag) y -= height; 
				else y += height;
			}
			
		}

	}
}