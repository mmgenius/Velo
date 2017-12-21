import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



public class Station extends Sommet{
	
	private String adresse;
	private Position position ;
	public String status ;
	public int nombreTotalVelo, nombreVeloEnMarche, nombreVeloGare; 

	
	public Station(String num, String nom, String adresse, Position p, String status, int nVelo, int nbArret, int nbMarche){
		super(num,nom);
		this.setAdresse(adresse) ; 
		this.setPosition(p) ; 
		this.status = status; 
		this.nombreTotalVelo = nVelo ; 
		this.nombreVeloEnMarche = nbMarche ; 
		this.nombreVeloGare = nbArret; 
		
	}
	
	private static String readAll(Reader rd) throws IOException {
	    StringBuilder sb = new StringBuilder();
	    int cp;
	    while ((cp = rd.read()) != -1) {
	      sb.append((char) cp);
	    }
	    return sb.toString();
	}
	
	public JSONObject readJsonFromUrl(String uri) throws IOException, JSONException {
	    InputStream is = new URL(uri).openStream();
	    try {
	      BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
	      String jsonText = readAll(rd);
	      JSONObject jsonn = new JSONObject(jsonText);
	      
	      
	      return jsonn;
	    } finally {
	      is.close();
	    }
	  }
	  
	
	
	public String calculerDistance(Position destination) throws IOException, JSONException{
		String url = "https://maps.googleapis.com/maps/api/directions/json?origin="+this.position.getLatitude()+","+this.position.getLongitude()+
						"&destination="+destination.getLatitude()+","+destination.getLongitude()+
						"&alternatives=true&key=AIzaSyBOI3GbFImyoH9NwlAAgRGnNhs3VWSdwCE";
		
		 List<JSONObject> lesObj = new ArrayList<JSONObject>();
		 JSONObject notreObjet = readJsonFromUrl(url);
		 String text = notreObjet.getString("routes");
		 JSONArray table = new JSONArray(text);
		 for(int i=0; i<table.length(); i++){
	    	  JSONObject jsonObj = table.getJSONObject(i);
	    	  lesObj.add(jsonObj);
	     }
		
		  JSONArray distance = new JSONArray(""+lesObj.get(0).get("legs")); 
		  JSONObject jsonObj1 = distance.getJSONObject(0);
		  JSONObject dist = (JSONObject) jsonObj1.get("distance"); 
		  String notreDistance = dist.getString("text"); 
		 
		 
		 return notreDistance; 
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
	
	@Override
	public String toString(){
		return "[ "+super.toString()+" , "+"<"+adresse+"> , "+position.toString()+" , "+status+" , Velo:"+nombreTotalVelo+" , Standing:"+nombreVeloGare+" , Running:"+nombreVeloEnMarche+"]";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
