package mm1;

import java.util.Vector;

public class FES {

	private Vector events;

	public FES() {
		this.events = new Vector();
	}

	public void addEvent(Event nveauEvent){
		int index = 0;
		while (index < this.events.size()){
			Event e = (Event)this.events.elementAt(index);
			if (e.getTime() > nveauEvent.getTime()) {
				break;
			} index++;
		}
		this.events.insertElementAt(nveauEvent, index);
	} 
	public Event nextEvent() {
		Event firstEvent = (Event)this.events.elementAt(0);
		this.events.removeElementAt(0);
		return firstEvent;
	}

}
