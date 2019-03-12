package mm1;

import java.util.Vector;

public class Queue {
	/* Liste des clients dans la queue */

	Vector theCostumers;
	double sommeS;
	double sommeS2;
	double surface;
	double sommeW;
	double sommeW2;
	int nbCostumers;
	double t;

	//Construire une queue vide
	public Queue() {
		theCostumers = new Vector();
		this.sommeW = 0.0D;
		this.sommeW2 = 0.0D;
		this.sommeS = 0.0D;
		this.sommeS2 = 0.0D;
		this.nbCostumers = 0;
		this.surface = 0.0D;
		this.t = 0.0D;
	}
	public Costumers getCostumers(int in) {
		return (Costumers)this.theCostumers.elementAt(in);
	}
	public void addCostumers(Costumers costumers) {

		int size = this.theCostumers.size();
		double time = costumers.getTempsArrive();
		this.surface += size * (time - this.t);
		this.t = time; 
		this.theCostumers.addElement(costumers);
	}

	public int getSize() {
		return this.theCostumers.size();
	}

	public void deleteCostumers(double time) {
		Costumers costumers = (Costumers)this.theCostumers.elementAt(0);
		this.sommeS += time - costumers.getTempsArrive();
		this.sommeS2 += (time - costumers.getTempsArrive()) * (time - costumers.getTempsArrive());
		this.sommeW += time - costumers.getTempsArrive() - costumers.getTempsService();
		this.sommeW2 += (time - costumers.getTempsArrive() - costumers.getTempsService()) * (time - costumers.getTempsArrive() - costumers.getTempsService());
		this.nbCostumers += 1;
		int size = this.theCostumers.size();
		this.surface += size * (time - this.t);
		this.t = time;
		this.theCostumers.removeElementAt(0);
	}

	public double getTempsMoyensAttente()
	{
		return this.sommeW / this.nbCostumers;
	}

	public double getNbMoyenCostumersQueue()
	{
		return this.surface / this.t;
	}

	public int getNombreCostumers()
	{
		return this.nbCostumers;
	}

	//
	public double getMeanSojournTime()
	  {
	return this.sommeS / this.nbCostumers;
 }   
	//
	public double getVarianceWaitingTime(){
    return (this.sommeW2 - this.sommeW * this.sommeW / this.nbCostumers) / (this.nbCostumers - 1);
	}
//
	public double getVarianceSojournTime()
	 {
	    return (this.sommeS2 - this.sommeS * this.sommeS / this.nbCostumers) / (this.nbCostumers - 1);
   }
	
	
	public double getConfiance95(double variance){
		//calcul d'intervale de confiance : Za/2 (a=%de confiance, içi 0,95) a/2 = 0,475, avec la table de Z on a Z(a/2=0,475)=1,96
		// Z*ecart type/racine(moynne)
		double z=1.96;
		return z*Math.sqrt(variance)/Math.sqrt(this.nbCostumers);
	}

}
