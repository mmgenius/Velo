

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


public class RequeteUrl{
	
	private String monUrl = "";
	
	public RequeteUrl(String url){
		this.monUrl = url ; 
	}
	
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
	    maliste = new RequeteUrl("").readJsonFromUrl();
	    for(int i=0; i<maliste.size(); i++){
	    	System.out.println(maliste.get(i));
	    	JSONObject position = new JSONObject(""+maliste.get(i).get("position"));
	    	System.out.println(position.getDouble("lng")+", "+position.getDouble("lat"));
	    	System.out.println();
	    }
	   
	    
	  }

	public String getMonUrl() {
		return monUrl;
	}
}
