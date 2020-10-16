package no.hvl.dat100ptc.oppgave3;

import static java.lang.Math.*;

import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;

public class GPSUtils {

	 public static double findMax(double[] da) {

	        double max;

	        max = da[0];

	        for (double d : da) {
	            if (d > max) {
	                max = d;
	            }
	        }

	        return max;
	    }
	 //finner minste tall i en tabell med flyttal

	    public static double findMin(double[] da) {

	        double min;
	        min = da[0];
	        // TODO - START
	        for (double d : da) {
	            if (d < min) {
	                min = d;
	            }
	        }
	        return min;
	        // TODO - SLUT
	    }
	    //
	    public static double[] getLatitudes(GPSPoint[] gpspoints) {

	        // TODO - START
	    	//opprette en tabell av desimaltall med samme lengde som gpspoint og så kopiere de enkelte latitudes over i den nye tabellen
	        double[] latitudes = new double[gpspoints.length];
	        for (int i = 0; i < gpspoints.length; i++) {
	            latitudes[i] = gpspoints[i].getLatitude();
	        }
	        return latitudes;
	        // TODO - SLUTT
	    }

	    public static double[] getLongitudes(GPSPoint[] gpspoints) {
	        // TODO - START
	        double[] longtitudes = new double[gpspoints.length];
	        for (int i = 0; i < gpspoints.length; i++) {
	            longtitudes[i] = gpspoints[i].getLongitude();
	        }
	        return longtitudes;
	        // TODO - SLUTT
	    }

	    private static int R = 6371000; // jordens radius

	    public static double distance(GPSPoint gpspoint1, GPSPoint gpspoint2) {

	        double d;
	        double latitude1, longitude1, latitude2, longitude2;
	        latitude1 = gpspoint1.getLatitude();
	        latitude2 = gpspoint2.getLatitude();
	        longitude1 = gpspoint1.getLongitude();
	        longitude2 = gpspoint2.getLongitude();
	        double latDistance = Math.toRadians(latitude2 - latitude1);
	        double lonDistance = Math.toRadians(longitude2 - longitude1);
	        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) + Math.cos(Math.toRadians(latitude1)) * Math.cos(Math.toRadians(latitude2)) * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
	        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
	        d = R * c;
	        return d;

	    }

	    public static double speed(GPSPoint gpspoint1, GPSPoint gpspoint2) {

			int secs;
			double speed;

			// TODO - START

			int sec1 = gpspoint1.getTime();
			int sec2 = gpspoint2.getTime();
			secs = sec2 - sec1; //tiden fra punkt 1 til punkt 2
			double distance = distance(gpspoint1, gpspoint2);
			speed = (distance/secs) *3.6;
			
			return speed;

	    }
	    public static String formatTime(int secs) {

	        String timestr;
	        String TIMESEP = ":";
	        int sec = secs % 60;
	        int allMinutes = secs / 60;
	        // TODO - START
	        int hours = allMinutes / 60;
	        int minutes = allMinutes % 60;
	        // TODO - SLUTT
	        timestr = String.format("%02d%s%02d%s%02d", hours, TIMESEP, minutes, TIMESEP, sec);
	        timestr = String.format("%10s", timestr);
	        return timestr;
	    }
	    private static int TEXTWIDTH = 10;

	    public static String formatDouble(double d) {

	        String str;
	        str = String.format("%1$,.2f", d);
	        String str2 = String.format("%" + TEXTWIDTH + "s", str);
	        return str2;
	    }
	
}
