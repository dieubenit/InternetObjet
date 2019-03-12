package mm1;

public class Costumers {
	// temps d'arrivée
	private double tempsArrive;
	// C'est une durée de service
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
