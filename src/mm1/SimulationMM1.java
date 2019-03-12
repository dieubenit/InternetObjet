package mm1;
import java.util.Scanner;

import mm1.Costumers;
import mm1.Event;
import mm1.FES;
import mm1.ListEvents;
import mm1.Queue;

public class SimulationMM1 {
	double lambda;
	double mu;
	ListEvents liste;
	Queue qe;
	double t;
	public static double tempsMoyenneAttente;
	public static double tempsVarianceAttente;
	public static double nbMoyenCostumers;
	public static double confianceAttente;
	public static double tempsMoyenneService;
	public static double tempsVarianceService;
	public static double confianceService;
	public static int costumerTotal;
	public static long debutSimulation;

	public SimulationMM1 (double lambda, double mu) {
		this.lambda = lambda;
		this.mu = mu;
		qe = new Queue();
		liste= new ListEvents();
		debutSimulation = System.currentTimeMillis();
	}
	public double expo(double taux){
		return -Math.log(Math.random())/taux;
	}
	public void simulation (double simulaLength) {
		FES fes = new FES();
		// la queue
		Queue qe = new Queue();
		//La simulation commence a intervalle de temps t=0
		double t = 0.0D;
		long debutSimulation = System.currentTimeMillis();
		// t(0)=arrivee et t(1)=depart
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
				//on calcule la durée de service du client
				double TempsService = expo(this.mu);

				qe.addCostumers(new Costumers(t, TempsService));
				if (qe.getSize() == 1) {
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
		tempsVarianceAttente= qe.getVarianceWaitingTime();
		//System.out.println("Temps moyenne d'attente : "+tempsMoyenneAttente);
		nbMoyenCostumers = qe.getNbMoyenCostumersQueue();
		//System.out.println("Nombre de costumers : "+nbMoyenCostumers);
		confianceAttente=qe.getConfiance95(tempsVarianceAttente);
		tempsMoyenneService=qe.getMeanSojournTime();
		tempsVarianceService=qe.getVarianceSojournTime();
		confianceService=qe.getConfiance95(tempsVarianceService);
		costumerTotal=qe.getNombreCostumers();
	}

	public static void exec() {

		Scanner scanner = new Scanner(System.in);
		double Wmu = 0.0D;
		double Wlambda = 0.0D;
		System.out.println("=============== SIMULATION M/M/1 ===============");
		System.out.println("");
		System.out.println("Saisir la valeur de lambda: ");
		Wlambda = Double.parseDouble(scanner.nextLine());
		System.out.println("Saisir la valeur de mu: ");      
		Wmu = Double.parseDouble(scanner.nextLine());
		System.out.println("Saisir la durée de la simulation: ");
		double duree = Double.parseDouble(scanner.nextLine());;
		SimulationMM1 slt = new SimulationMM1(Wlambda, Wmu);
		slt.simulation(duree);

		System.out.println("=============== RAPPORT DE SIMULATION ===============");
		System.out.println("");
		System.out.println("Calculation time: " + (System.currentTimeMillis() - debutSimulation) * 0.001D + 
			      " seconds");
		System.out.println("Temps moyenne d'attente : "+tempsMoyenneAttente
				+" \n intervalle de confiance : +_ "+ confianceAttente);
		System.out.println("Variance temps d'attente : "+tempsVarianceAttente);
		System.out.println("Temps moyenne de service : "+tempsMoyenneService
				+" \n intervalle de confiance : +_ "+ confianceAttente);
		System.out.println("Variance temps de service : "+tempsVarianceService);
		System.out.println("Nombre de costumers moyen servi : "+nbMoyenCostumers);
		System.out.println("Nombre total de costumers : "+costumerTotal);

	}
	
}
/*
System.out.println("Calculation time: " + 
      (System.currentTimeMillis() - beginTime) * 0.001D + 
      " seconds");
    System.out.println("Mean waiting time: " + q.getMeanWaitingTime());
    System.out.println("Var. waiting time: " + q.getVarianceWaitingTime());
    System.out.println("Mean sojourn time: " + q.getMeanSojournTime());
    System.out.println("Var. sojourn time: " + q.getVarianceSojournTime());
    System.out.println("Mean nr. of. customers: " + 
       q.getMeanNumberOfCustomers());
*/

