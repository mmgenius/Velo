
public class Station extends Sommet{
	
	private String adresse;
	private Position position ;
	public String status ;
	public int nombreTotalVelo, nombreVeloEnMarche, nombreVeloGare; 
	
	public Station(String num, String nom, String adresse, Position p, String status, int nVelo, int nbMarche, int nbArret){
		super(num,nom);
		this.setAdresse(adresse) ; 
		this.setPosition(p) ; 
		this.status = status; 
		this.nombreTotalVelo = nVelo ; 
		this.nombreVeloEnMarche = nbMarche ; 
		this.nombreVeloGare = nbArret; 
		
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
