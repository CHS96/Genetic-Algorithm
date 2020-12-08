
public class Main {

	public static void main(String[] args) throws InterruptedException {
		long startTime = System.currentTimeMillis();

		Simulation sm = new Simulation();
		GUI gui;
		
		sm.node = sm.inital();
		
		int generation = 0;
		System.out.println("========================================================");
		while (generation++ < 100) {
			String title = "\t\t   [ " + generation + "st Generation ]";
			System.out.println(title);
			
			sm.node = sm.getNumber(sm.bestChoice(sm.node));
			
			sm.print(sm.node);
			
			gui = new GUI(sm, title);
			while (gui.isFinish()) { gui.thread[15].join(); }
			
			gui.dispose();
			
			System.out.println("========================================================");
		}

		long endTime = System.currentTimeMillis();
		System.out.println("			Exec Time : " + (endTime - startTime));
		
	}
}
