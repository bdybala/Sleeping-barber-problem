package people;

public class Barber extends AbstractPerson {

	private boolean isAsleep;
	
	public Barber(String name) {
		super(name);
		this.isAsleep = false;
	}

	public boolean isAsleep() {
		return isAsleep;
	}

	public void setAsleep(boolean isAsleep) {
		this.isAsleep = isAsleep;
	}

	@Override
	public String toString() {
		return "Barber [name=" + name + "]";
	}
	
}
