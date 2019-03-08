package mm1;

public class Event {
	// temps de depart, temps d'arrivee
	private int type; 
	// instance d'evenement
	private double instant; 
	public Event(int type, double time) {
		this.type = type;
		this.instant = time;
	}
	public int getType() {
		return type;
	}
	public double getTime() {
		return instant;
	}

}
