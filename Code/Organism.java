import java.util.Scanner;

public class Organism {
	
	short dna; 
	//It stores all the information about Organism's initial state, 
	//currenty its short but may change according to requirement.
	byte age;
	//Will be used to ensure that Organism do not become immortal, and keep populatin under control.
	byte energy;
	//It will be the currency for doing certain tasks such as locomotion, caturing prey, etc.
	
	
	public byte strength(){ //in case of a competion or capturing prey probability of succeeding will be proportional to strength.
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
	

	Organism(short dNA){
		dna=dNA;
		energy=this.iEnergy(); //initial energy is determined form dna.
		}
	
	public void show(){ //used to check organism details.

			System.out.println(
			"age:"+age+
			"\nstrength:"+this.strength()+
			"\ninitial energy:"+this.iEnergy()+

			"\ntype:"+this.type()+
			"\ndna:"+this.binDNA()+"\n_________________________________");
		}
	

	// THE COMMENTED CODE IS FOR TEST USE ONLY.
	

	// public static void main(String[] args) {
	// 	short i;
	// 	Scanner s = new Scanner(System.in);
	// 	while (true) {
	// 		try {
	// 			System.out.println("\nEnter a new DNA:");	
	// 			i = s.nextShort();
	// 			Organism org = new Organism(i);
	// 			org.show();
	// 		} catch(Exception e){
	// 			System.out.println(e);
	// 			s.next();
	// 		}
	// 	}}
}
