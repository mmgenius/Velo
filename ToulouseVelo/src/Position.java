
public class Position {
	
	private double longitude ; 
	private double latitude ; 
	
	public Position(double longi, double lat){
		this.longitude = longi ; 
		this.latitude = lat ; 
	}
	
	public double getLongitude(){
		return this.longitude ; 
	}
	public double getLatitude(){
		return this.latitude ; 
	}
	
	public String toString(){
		return "Longitude "+this.longitude+" Latitude "+this.latitude ; 
	}

}
