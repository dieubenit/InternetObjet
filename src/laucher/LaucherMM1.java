package laucher;

import java.util.Scanner;
import mm1.SimulationMM1;
import mm1.Queue;
import mm1.SimulationMD1;

public class LaucherMM1 {

	public static void main(String[] args) {
		 
		Scanner scanner = new Scanner(System.in);
		System.out.println("choississez M/M/1 = 1(par default), M/D/1 = 2 :");
		int val=scanner.nextInt();
		if(val==1){
			SimulationMM1.exec();
		}else{
			SimulationMD1.exec();
		}

	}

}
