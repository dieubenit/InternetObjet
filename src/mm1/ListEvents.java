package mm1;

import java.util.Vector;

public class ListEvents {
	Vector event;
	public ListEvents(){
	event = new Vector();
	}
	//
public void addEvent(Event nvEvent){
	int i = 0; 
	while (i < event.size()) {
	Event e = (Event) event.elementAt(i); 
	if (e.getTime() > nvEvent.getTime()) 
		break; 
	i++; 
		}
	event.insertElementAt(nvEvent, i); 
}
}
