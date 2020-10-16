package no.hvl.dat100ptc.oppgave5;

import javax.swing.JOptionPane;

import easygraphics.EasyGraphics;
import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave3.GPSUtils;
import no.hvl.dat100ptc.oppgave4.GPSComputer;

public class ShowRoute extends EasyGraphics {

	private static int MARGIN = 50;
	private static int MAPXSIZE = 800;
	private static int MAPYSIZE = 800;

	private GPSPoint[] gpspoints;
	private GPSComputer gpscomputer;
	
	public ShowRoute() {

		String filename = JOptionPane.showInputDialog("GPS data filnavn: ");
		gpscomputer = new GPSComputer(filename);

		gpspoints = gpscomputer.getGPSPoints();

	}

	public static void main(String[] args) {
		launch(args);
	}

	public void run() {

		makeWindow("Route", MAPXSIZE + 2 * MARGIN, MAPYSIZE + 2 * MARGIN);

		showRouteMap(MARGIN + MAPYSIZE);
		
		showStatistics();
	}

	// antall x-pixels per lengdegrad
	public double xstep() {

		double maxlon = GPSUtils.findMax(GPSUtils.getLongitudes(gpspoints));
		double minlon = GPSUtils.findMin(GPSUtils.getLongitudes(gpspoints));

		double xstep = MAPXSIZE / (Math.abs(maxlon - minlon)); 

		return xstep;
	}

	// antall y-pixels per breddegrad
	public double ystep() {
	
		
		// TODO - START
		double maxlat = GPSUtils.findMax(GPSUtils.getLatitudes(gpspoints));
		double minlat = GPSUtils.findMin(GPSUtils.getLatitudes(gpspoints));

		double ystep = MAPYSIZE / (Math.abs(maxlat - minlat)); 

		return ystep;

		// TODO - SLUTT
		
	}

	public void showRouteMap(int ybase) {

		int x, y;
		// TODO - START
		setColor(0, 0, 255);
		for(int i = 0; i < gpspoints.length; i++) {
			y = ybase - (int)(gpspoints[i].getLatitude() - GPSUtils.findMin(GPSUtils.getLatitudes(gpspoints))* ystep());
			x = MAPXSIZE - (int)(gpspoints[i].getLongitude() - GPSUtils.findMin(GPSUtils.getLongitudes(gpspoints)) * xstep());
		fillCircle(x, y, 20);
		}
		// TODO - SLUTT
	}

	public void showStatistics() {

		int TEXTDISTANCE = 20;

		setColor(0,0,0);
		setFont("Courier",12);
		String tt = ("Total time:    "+GPSUtils.formatTime(gpscomputer.totalTime()));
		drawString(tt, TEXTDISTANCE, TEXTDISTANCE);
		String td = ("Total distance:  "+String.format("%.2f", gpscomputer.totalDistance()/1000.0)+" km");
		drawString(td, TEXTDISTANCE, TEXTDISTANCE*2);
		String te = ("Total elevation: "+String.format("%.2f", gpscomputer.totalElevation())+" m");
		drawString(te, TEXTDISTANCE, TEXTDISTANCE*3);
		String ms = ("Max speed:       "+String.format("%.2f", gpscomputer.maxSpeed())+" km/t");
		drawString(ms, TEXTDISTANCE, TEXTDISTANCE*4);
		String as = ("Average speed:   "+String.format("%.2f", gpscomputer.averageSpeed())+" km/t");
		drawString(as, TEXTDISTANCE, TEXTDISTANCE*5);
		String en = ("Energy:          "+String.format("%.2f", gpscomputer.totalKcal(GPSComputer.WEIGHT))+" kcal");
		drawString(en, TEXTDISTANCE, TEXTDISTANCE*6);
		
	}

}