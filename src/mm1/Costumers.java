package mm1;

public class Costumers {
	// temps d'arriv�e
	private double tempsArrive;
	// C'est une dur�e de service
	private double tempsService;
	// double service time
	
	public Costumers(double TimesArrive, double TimeService){
		this.tempsArrive=TimesArrive;
		this.tempsService=TimeService;
		
	}

	public double getTempsArrive() {return this.tempsArrive;
	}

	public double getTempsService() {return this.tempsService;
	}
}
