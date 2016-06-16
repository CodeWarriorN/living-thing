package jayank.livingthing;

import java.util.Random;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.util.Date;
import javafx.scene.canvas.Canvas;

public class Organism implements Runnable {//
	
    static Canvas livingSpace = new Canvas(300,300);
    
	int x;//distance from left end of canvas.
        int y;//distance from top of canvas.

	short dna; 
	//It stores all the information about Organism's initial state, 
	//currenty its short but may change according to requirement.
	byte energy;
	//It will be the currency for doing certain tasks such as locomotion, caturing prey, etc.
	
	Organism(short dNA){
		dna=dNA;
		energy=this.iEnergy(); //initial energy is determined form dna.
                x=35;
                y=70;
		}

	Organism(Organism org, int accuracy){ // for reproduction(asexual)
		Random ran = new Random();
		if (ran.nextInt(accuracy)==0){
			dna=(short)ran.nextInt(32767);// just a placeholder, gonna get more realistic error source in future
			energy = (byte)((dna & 254)/2);
			x = org.x;
                        y = org.y;

		} else { // i donno whats wrong but it just doesnt looks nice with so much perfection.
			dna = org.dna;
			energy = org.iEnergy();
			x = org.x;
                        y = org.y;

		}
	}

	public void locomotion(int height, int width){//temp locomotion
		GraphicsContext space = livingSpace.getGraphicsContext2D();
                //space.setLineWidth(110);
                Random ran = new Random();
                 space.setFill(Color.WHITE); //to erase last held position
                space.fillRect(this.y,this.x,3,3); // this line currently results in ArrayIndexOutOfBoundsException, gotta resolve this
		if((this.x<width-1)&&(this.x>1)) {
			this.x += 1-ran.nextInt(3);
		} else if (this.x==width-1) {
			this.x -= ran.nextInt(2);
		} else if (this.x==1) {
			this.x += ran.nextInt(2);
		}

		if((this.y<height-1)&&(this.y>1)){
			this.y += 1-ran.nextInt(3); 
		} else if (this.y==height-1) {
			this.y -= ran.nextInt(2);
		} else if (this.y==1) {
			this.y += ran.nextInt(2);
		}
                space.setFill(this.orgColor());
                space.fillRect(this.y,this.x,3,3); // shows current location.	
		this.energy--; // as locomotion needs energy
	}

	public byte fertility(){ //proportional to rate of successful reproduction.
		return (byte)((dna & 2032)/16);
	}
	
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
	
	public Color orgColor(){ //The idea is that we will represent Organisms on a 2d graph with their 
							 //color showing there properties.
		float r=((float)this.strength())/126;
		float b=((float)this.iEnergy())/126;
		float g=((float)this.fertility())/126;
		float a=(((float)this.energy)/126);// disappears as its energy goes to zero.
		Color orgC = new Color(r,g,b,a);
		return orgC;
	}
	
	public void showInConsole(){ //used to check organism details in console.

		System.out.println(
			"dna:"+this.binDNA()+
			"\nstrength:"+this.strength()+
			"\ninitial energy:"+this.iEnergy()+
			"\ntype:"+this.type()+
			"\nfertility:"+this.fertility()+"\n_________________________________");
		}

	public void live(){ // temp, only for test 
		Random ran = new Random();
                
		while((energy>0)&&(dna!=0)){
		//System.out.println(d.getTime()+"a");
			if (ran.nextBoolean()){// codition for if Will be time dependent, random bool is just placeholder
				energy--;// as even idle Organisms lose energy.
			}
			
			if (ran.nextBoolean()&&(World.count<90)){// temp placeholder for actual reproduction, 
								//final function will use fertility function to determine the rate of reproduction
				Organism org = new Organism(this,10);
//                               // org.showInConsole();
				
				World.th.execute(org);
				World.count++;
                               // System.out.println(World.count);
                                
			}
			this.locomotion(300,300);
			//System.out.println(this.x+" "+this.y);
			
		}
	}
    @Override
	public void run(){ // for multithreading.
		
		live();
	}
}
