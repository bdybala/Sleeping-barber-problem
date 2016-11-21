package runnable;


import executors.Exec;
import people.Barber;

public class BarberRunnable implements Runnable {

	Exec exec;
	
	public BarberRunnable(Exec exec, String name) {
		this.exec = exec;
		exec.setBarber(new Barber(name));
	}

	public BarberRunnable(Exec exec, Barber barber) {
		this.exec = exec;
		exec.setBarber(barber);
	}
	
	@Override
	public void run() {
synchronized (exec) {
	//stworzono barbera  
	System.out.println("**Run " + exec.getBarber());
	try {
		Thread.sleep(1000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	while (!exec.getBarber().isAsleep()) {
		//barber goli klienta z krzesla
		exec.shave();
		//checkujemy czy ktos siedzi w kolejce
		exec.checkQueue();
	}
}

	}
}
