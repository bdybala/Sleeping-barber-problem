package people;

public class AbstractPerson {

	protected String name;

	public String getName() {
		return name;
	}

	public AbstractPerson(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "AbstractPerson [name=" + name + "]";
	}
	
	
}
