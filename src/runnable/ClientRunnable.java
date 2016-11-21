package runnable;


import executors.Exec;
import people.Client;

public class ClientRunnable implements Runnable {

	private Exec exec;
	private Client newClient;
	
	public ClientRunnable(Exec exec, String name) {
		this.newClient = new Client(name);
		this.exec = exec;
	}
	public ClientRunnable(Exec exec, Client client) {
		this.newClient = client;
		this.exec = exec;
	}

	@Override
	public void run() {
synchronized (exec) {
	//stworzono klienta
	System.out.println("**" + newClient + " coming to the Barbershop");
	exec.sitDown(newClient);
}
	}

	public Client getNewClient() {
		return newClient;
	}
}
