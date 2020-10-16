package no.hvl.dat100ptc.oppgave2;

import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;

public class GPSData {

    private GPSPoint[] gpspoints;
    
    protected int antall = 0;
    
//	Konstruktøren skal opprette en tabell av GPS punkter med størrelsen gitt ved parameteren n

    public GPSData(int n) {

        // TODO - START
        gpspoints = new GPSPoint[n];
        
        // TODO - SLUTT
    }
    
    public GPSPoint[] getGPSPoints() {
        return this.gpspoints;
    }
    //setter inn GPSPoint gpspoint i gpspoints-tabellen på posisjonen angitt ved objektvariablen antall
    protected boolean insertGPS(GPSPoint gpspoint) {
    

        boolean inserted = false;
        // TODO - START
        if (gpspoints.length > antall) {
            gpspoints[antall] = gpspoint;
            antall++;
            inserted = true;
        }
        return inserted;
        // TODO - SLUTT
    }
    //tar GPS punkt data i strengrepresentasjon og setter inn et tilsvarende GPSPoint-objekt i gpspoints
    public boolean insert(String time, String latitude, String longitude, String elevation) {

        GPSPoint gpspoint;
        gpspoint = GPSDataConverter.convert(time, latitude, longitude, elevation);
        // TODO - START
        return insertGPS(gpspoint);
        // TODO - SLUTT
    }

    public void print() {

        System.out.println("====== Konvertert GPS Data - START ======");

        // TODO - START
        for (int i = 0; i < antall; i++) {
            System.out.print(gpspoints[i].toString());
        }
        // TODO - SLUTT
        System.out.println("====== Konvertert GPS Data - SLUTT ======");
    }
}
