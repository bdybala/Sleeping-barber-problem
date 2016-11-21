package executors;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import people.Barber;
import people.Client;
import runnable.BarberRunnable;
import runnable.ClientRunnable;

public class Exec {

	private Queue<Client> queue;
	private Client chair;
	private Barber barber;
	

	public Exec(Client chair) {
		this.barber = null;
		this.chair = chair;
		this.queue = new LinkedList<>();
	}
	
	public Exec() {
		this.barber = null;
		this.chair = null;
		this.queue = new LinkedList<>();
	}

	public Exec(Queue<Client> queue, Client chair) {
		this.queue = queue;
		this.chair = chair;
		this.barber = null;
	}

	private boolean queueIsEmpty() {
		if(queue.peek() == null) 
			return true;
		return false;
	}
	public void checkQueue() {
//		System.out.println("*checkQueue*");
		if(barber.isAsleep()) return;
		if(!queueIsEmpty()) {
			chair = queue.poll();
			System.out.println(chair + " invited to the chair!");
			shave();
		} else {
			barber.setAsleep(true);
			System.out.println("Queue is empty. Barber falling asleep");
		}
	}
	public void shave() {
//		System.out.println("*shave*");
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		if(chair != null) {
			System.out.println(chair + " has been shaved!");
			chair = null;
		}
		checkQueue();
	}
	public void sitDown(Client newClient) {
//		System.out.println("*sitDown*");
		if(barber == null) {
			if(queue.offer(newClient)) 
				System.out.println("*" + newClient + " waits in queue!");
			return;
		}
		if(barber.isAsleep() && chair == null) {
			chair = newClient;
			System.out.println(chair + " sits down");
			barber.setAsleep(false);
			System.out.println("Barber has been woken up!");
			shave();
		} else {
			if(queue.offer(newClient)) 
				System.out.println(queue.peek() + " goes into queue!");
			else 
				System.out.println("There is no room for " + newClient + " in barbershop");
		}
	}

	public static void main(String[] args) {
		
		int nThreads = 4;
		
		ExecutorService threadPool = Executors.newFixedThreadPool(nThreads);
		Exec exec = new Exec();
		
//		threadPool.submit(new BarberRunnable(exec, "Bob"));
		threadPool.submit(new ClientRunnable(exec, "Client1"));
		threadPool.submit(new ClientRunnable(exec, "Client2"));
		threadPool.submit(new ClientRunnable(exec, "Client3"));
		threadPool.submit(new BarberRunnable(exec, "Bob"));
		threadPool.submit(new ClientRunnable(exec, "Client4"));
		threadPool.submit(new ClientRunnable(exec, "Client5"));
		threadPool.shutdown();

	}
	
	public Queue<Client> getQueue() {
		return queue;
	}
	public void setQueue(Queue<Client> queue) {
		this.queue = queue;
	}
	public Client getChair() {
		return chair;
	}
	public void setChair(Client chair) {
		this.chair = chair;
	}
	public Barber getBarber() {
		return barber;
	}
	public void setBarber(Barber barber) {
		this.barber = barber;
	}
}
