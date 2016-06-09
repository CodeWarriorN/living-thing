package jayank.livingthing;

import java.util.Scanner;
import java.awt.Color;
import java.util.Date;

public class Organism{
	
	short dna; 
	//It stores all the information about Organism's initial state, 
	//currenty its short but may change according to requirement.
	byte age;
	//Will be used to ensure that Organism do not become immortal, and keep populatin under control.
	byte energy;
	//It will be the currency for doing certain tasks such as locomotion, caturing prey, etc.
	
	Organism(short dNA){
		dna=dNA;
		energy=this.iEnergy(); //initial energy is determined form dna.
		}

	public byte fertility(){ //proportional to rate of successful reproduction.
		return (byte)((dna & 2032)/16);
	}
	
	public byte strength(){ //in case of a competion or capturing prey probability of succeeding 
				//will be proportional to strength.
		return (byte)((dna & 32256)/512);
	}
	
	public byte iEnergy(){ //Initial energy
		return (byte)((dna & 254)/2);
	}
	
	public byte type(){ //Autotrops(0), Herbivores(1), Carnivores(2)
		return (byte)(dna%3);
	}
	
	public String binDNA(){// Used to check dna in binary with all 16 bits.
		int i=dna;
		String sDNA = new String();
		if (dna<0){
			i*=-1;
		}
		while(i>0){
			sDNA = (i%2) + sDNA;
			i/=2;	
		}
		i=sDNA.length();
		if (dna<0){
			for (int j=0 ;j<(15-i) ;j++ ) {
			sDNA = 0 + sDNA;
			} 
			sDNA = 1 + sDNA;
		} else {
			for (int j=0 ;j<(16-i) ;j++ ) {
			sDNA = 0 + sDNA;
			}		
		}
		return sDNA;
	} 
	
	public Color orgColor(){ //The idea is that we will represent Organisms on a 
				 //2d graph with their color showing there properties.
		float r=((float)this.strength())/126;
		float b=((float)this.iEnergy())/126;
		float g=((float)this.fertility())/126;
		float a=1-(((float)age)/126);
		Color orgC = new Color(r,g,b,a);
		return orgC;
	}
	
	public void showInConsole(){ //used to check organism details in console.

		System.out.println(
			"dna:"+this.binDNA()+
			"\nage:"+age+
			"\nstrength:"+this.strength()+
			"\ninitial energy:"+this.iEnergy()+
			"\ntype:"+this.type()+
			"\nfertility:"+this.fertility()+"\n_________________________________");
		}

	public void live(long updateAfter){ // Not yet complete.
		Date d = new Date();
		long t = d.getTime();
		byte tempEnergyLoss=0;
		while((age<126)&&(energy>0)&&(dna!=0)){
			if ((t-d.getTime())>updateAfter) {
				age++;
				tempEnergyLoss++;
				if (tempEnergyLoss>1000)energy--;
			}
			

			/*
				  Some code for other life activities.
					...
					...
			*/
				
			
		}
	}

	public void live(){this.live(-1);} //just for setting defoult value of updateAfter parameter to -1
	

	// THE COMMENTED CODE IS FOR TEST USE ONLY.
	

	// public static void main(String[] args) {
	// 	short i;
	// 	Scanner s = new Scanner(System.in);
	// 	while (true) {
			
	// 		try {
	// 			System.out.println("\nEnter a new DNA:");	
	// 			i = s.nextShort();
	// 			Organism org = new Organism(i);
	// 			if (org.dna==0) break;
	// 			org.showInConsole();
	// 		} catch(Exception e){
	// 			System.out.println(e);
	// 			s.next();
	// 		}
	// 	}}
}
