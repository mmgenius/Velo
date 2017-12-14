

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;


public class EtatVeloByURL{
	
	private final String monUrl = "https://api.jcdecaux.com/vls/v1/stations?contract=Toulouse&apiKey=660886648617740220c3a0edefb2cb48f62e3e4e";
	
	private static String readAll(Reader rd) throws IOException {
	    StringBuilder sb = new StringBuilder();
	    int cp;
	    while ((cp = rd.read()) != -1) {
	      sb.append((char) cp);
	    }
	    return sb.toString();
	  }
		
	  public List<JSONObject> readJsonFromUrl() throws IOException, JSONException {
	    InputStream is = new URL(this.getMonUrl()).openStream();
	    try {
	      BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
	      String jsonText = readAll(rd);
	      List<JSONObject> lesObj = new ArrayList<JSONObject>(); 
	      JSONArray json = new JSONArray(jsonText);
	      for(int i=0; i<json.length(); i++){
	    	  JSONObject jsonObj = json.getJSONObject(i);
	    	  lesObj.add(jsonObj);
	      }
	      
	      return lesObj;
	    } finally {
	      is.close();
	    }
	  }
	  
	  
	  
	  public static void main(String[] args) throws IOException, JSONException {
		List<JSONObject> maliste; 
	    maliste = new EtatVeloByURL().readJsonFromUrl();
	    for(int i=0; i<maliste.size(); i++){
	    	System.out.println(maliste.get(i));
	    	JSONObject position = new JSONObject(maliste.get(i).getString("position"));
	    	System.out.println(position.getDouble("lng"));
	    	System.out.println();
	    }
	   
	    
	  }

	public String getMonUrl() {
		return monUrl;
	}
}
