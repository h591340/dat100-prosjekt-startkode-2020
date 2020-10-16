package no.hvl.dat100ptc.oppgave4;

import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave2.GPSData;
import no.hvl.dat100ptc.oppgave2.GPSDataConverter;
import no.hvl.dat100ptc.oppgave2.GPSDataFileReader;
import no.hvl.dat100ptc.oppgave3.GPSUtils;

public class GPSComputer {
	
	private GPSPoint[] gpspoints;
	
	public GPSComputer(String filename) {

		GPSData gpsdata = GPSDataFileReader.readGPSFile(filename);
		gpspoints = gpsdata.getGPSPoints();

	}

	public GPSComputer(GPSPoint[] gpspoints) {
		this.gpspoints = gpspoints;
	}
	
	public GPSPoint[] getGPSPoints() {
		return this.gpspoints;
	}
	
	// beregn total distance (i meter)
	public double totalDistance() {

		double distance = 0;

		// TODO - START
		//why j uten for løke 

		int j = 1; //punkt 2 (++)
		double temp = 0;
		for (int i = 0; i < gpspoints.length - 1; i++) {
			temp = GPSUtils.distance(gpspoints[i], gpspoints[j]);
			distance = distance + temp;
			j++;
		}
		return distance;

		// TODO - SLUTT

	}

	// beregn totale hÃ¸ydemeter (i meter)
	public double totalElevation() {

		double elevation = 0;

		// TODO - START
		//kun telle hødyemeter mellom to punkter dersom det går oppover
		double temp = 0;
		int j = 1;
		for (int i = 0; i < gpspoints.length - 1; i++) {
			double a = gpspoints[i].getElevation();
			double b = gpspoints[j].getElevation();
			if (a < b) { //sjekker om nåværende punkt er mindre/lavere en neste punkt  -> går oppover
				elevation = elevation + (b - a); //legger til de høydemeterene som er klatret siden forrige måling, på totalten
			}
			j++;

		}
		return elevation;
		// TODO - SLUTT

	}

	// beregn total tiden for hele turen (i sekunder)
	public int totalTime() {
		int time=0;
		int j=1;
		for(int i=0;i<gpspoints.length-1;i++)
		{
			
			int temb=0;
			int a=gpspoints[i].getTime();
			int b=gpspoints[j].getTime();
			j++;
			if(a<b)
			{		
				temb=b-a;
			}
			
			time=time+temb;
		}
		return time;
	}
		
	// beregn gjennomsnitshastighet mellom hver av gps-punktene

	public double[] speeds() {
		
		// TODO - START		// OPPGAVE - START
		
		double[] nyTab = new double[gpspoints.length -1];
		int j = 1;
		for (int i = 0; i < gpspoints.length - 1; i++) {
			double a = GPSUtils.speed(gpspoints[i], gpspoints[j]);
			nyTab[i] = a;
			j++;
		}
		
		return nyTab;

		// TODO - SLUTT

	}
	
	public double maxSpeed() {
		
		double maxspeed = 0;
		
		// TODO - START
		//why it is not gpspoints.length-1;		
		for (int i = 1; i < gpspoints.length; i++) {
			double a = GPSUtils.speed(gpspoints[i - 1], gpspoints[i]);
			if (a > maxspeed) {
				maxspeed = a;
			}
		}
		return maxspeed;
		
		// TODO - SLUTT
		
	}

	public double averageSpeed() {

		double average = 0;
		
		// TODO - START

		average = totalDistance()/totalTime();
		average = average*3.6;
		return average;	
		
		// TODO - SLUTT
		
	}

	/*
	 * bicycling, <10 mph, leisure, to work or for pleasure 4.0 bicycling,
	 * general 8.0 bicycling, 10-11.9 mph, leisure, slow, light effort 6.0
	 * bicycling, 12-13.9 mph, leisure, moderate effort 8.0 bicycling, 14-15.9
	 * mph, racing or leisure, fast, vigorous effort 10.0 bicycling, 16-19 mph,
	 * racing/not drafting or >19 mph drafting, very fast, racing general 12.0
	 * bicycling, >20 mph, racing, not drafting 16.0
	 */

	// conversion factor m/s to miles per hour
	public static double MS = 2.236936;

	// beregn kcal gitt weight og tid der kjÃ¸res med en gitt hastighet
	public double kcal(double weight, int secs, double speed) {

		double kcal;

		// MET: Metabolic equivalent of task angir (kcal x kg-1 x h-1)
		double met = 0;		
		double speedmph = speed * MS;

		// TODO - START
	
		if (speed < 10) {
			met  = 4.0;
		}
		else if (speedmph >= 10 && speedmph < 12 ) {
			met = 6.0;
		}
		else if (speedmph >= 12 && speedmph < 14 ) {
			met = 8.0;
		}
		else if (speedmph >= 14 && speedmph < 16 ) {
			met = 10.0;
		}
		else if (speedmph >= 16 && speedmph < 19 ) {
			met = 12.0;
		}
		else {
			met = 16.0;
		}
		
		kcal = met * weight * secs/3600;
		
		return kcal;

		// TODO - SLUTT
		
	}

	public double totalKcal(double weight) {

		double totalkcal = 0;

		// TODO - START
		
		int totalTime = totalTime();
		double totalSpeed = averageSpeed(); 
		totalkcal = kcal(weight, totalTime, totalSpeed);
		return totalkcal;

		// TODO - SLUTT
		
	}
	
	public static double WEIGHT = 80.0;
	
	public void displayStatistics() {

		System.out.println("==============================================");

		// TODO - START

		System.out.print(String.format("%-15s", "Total Time") + ":");
		
		System.out.println(GPSUtils.formatTime(totalTime()));
		
		System.out.print(String.format("%-15s", "Total distance") + ":");
		
		System.out.println(GPSUtils.formatDouble(totalDistance()) + " km");
		
		System.out.print(String.format("%-15s", "Total elevation") + ":");
		
		System.out.println(GPSUtils.formatDouble(totalElevation()) + " m");
		
		System.out.print(String.format("%-15s", "Max speed") + ":");
		
		System.out.println(GPSUtils.formatDouble(maxSpeed()) + " km/t");
		
		System.out.print(String.format("%-15s", "Average speed") + ":");
		
		System.out.println(GPSUtils.formatDouble(averageSpeed()) + " km/t");
		
		System.out.print(String.format("%-15s", "Energy") + ":");
		
		System.out.println(GPSUtils.formatDouble(totalKcal(WEIGHT)) + " kcal");
		
		System.out.println("==============================================");
		
		// TODO - SLUTT
		
	}


}
