package mm1;

import java.util.Scanner;

public class SimulationMD1 {
	
	double lambda;
	double mu;
	ListEvents liste;
	Queue qe;
	double t;
	public static double tempsMoyenneAttente;
	public static double nbMoyenCostumers;

	public SimulationMD1 (double lambda, double mu) {
	this.lambda = lambda;
	this.mu = mu;
	qe = new Queue();
	liste= new ListEvents();
	}
	public double expo(double taux){
	return -Math.log(Math.random())/taux;
	}
	public void simulation (double simulaLength) {
	    FES fes = new FES();
	    // la queue
	    Queue q = new Queue();
	    //La simulation commence a intervalle de temps t=0
	    double t = 0.0D;
	    long debutSimulation = System.currentTimeMillis();
	    // t0=arrivee et t1=depart
	    fes.addEvent(new Event(0, expo(this.lambda)));
	while (t < simulaLength)
	    {
	      Event e = fes.nextEvent();
	      t = e.getTime();
	      //on traite selon le type d'evenements(depart et arrivee)
	      switch (e.getType())
	      {
	      case 0: 
	        
	        fes.addEvent(new Event(0, t + expo(this.lambda)));
	        //on calcule la durée de service du client, puis la valeur est fixé à 1
	        double TempsService = 1/mu;
	     
	        qe.addCostumers(new Costumers(t, TempsService));
	       	        if (q.getSize() == 1) {
	          fes.addEvent(new Event(1, t + TempsService));
	        }
	        break;
	      case 1: 
	        //suppression de costumers de la file d'attente
	        qe.deleteCostumers(t);
	        if (qe.getSize() > 0)
	        {
	           
	          double sTime = qe.getCostumers(0).getTempsService();  
	          fes.addEvent(new Event(1, t + sTime));
	        }
	        break;
	      }
	    }
	tempsMoyenneAttente = qe.getTempsMoyensAttente();
	nbMoyenCostumers = qe.getNbMoyenCostumersQueue();
}

	public static void main(String[] args) {
		 
		Scanner scanner = new Scanner(System.in);
		double Wmu = 0.0D;
		double Wlambda = 0.0D;
		System.out.println("=============== RAPPORT DE SIMULATION ===============");
		System.out.println("");
		//System.out.println("Saisir la valeur de lambda: \n");
		Wlambda = Double.parseDouble(scanner.nextLine());
		System.out.println("Saisir la valeur de mu: \n");      
		Wmu = Double.parseDouble(scanner.nextLine());
		System.out.println("Saisir la durée de la simulation: \n");
		double duree = Double.parseDouble(scanner.nextLine());;
		SimulationMD1 slt = new SimulationMD1(Wlambda, Wmu);
		slt.simulation(duree);
		System.out.println("Temps moyenne d'attente : "+tempsMoyenneAttente);
		System.out.println("Nombre de costumers : "+nbMoyenCostumers);
		
	}

}
