package chatBot;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import javax.swing.text.html.HTMLDocument.Iterator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONString;


public class synonymAPI extends gui{
	
	public static String[] synonyms(String word) {
		String hold = "";
		int i = 0;
		int beg = 0;
		int end = 0;
		String[] strArr = new String[100];
		
		//We use an HTTP GET request here in order to retrieve the synonyms we require from the API
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create("https://www.dictionaryapi.com/api/v3/references/thesaurus/json/" + word + "?key=f2be3352-0863-45df-a88b-085a31690fea"))
				.method("GET", HttpRequest.BodyPublishers.noBody())
				.build();
		HttpResponse<String> response;
		try {
			response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
			hold = response.body();
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
			strArr[0] = "Error in code. Caught exception.";
			return strArr;
		}
		
		//This is to only get a substring of the result, as we're only needing the synonyms
		beg = hold.indexOf("syns");
		end = hold.indexOf("ants");
		
		hold = hold.substring(beg + 8, end - 4);
		
		//To check the synonyms that the API is putting out, include the code below:
			//System.out.println(hold);
		
		
		
		//This loop is to separate our synonyms into different array indexes
		while(hold.contains("\"")) {
			end = hold.indexOf("\"", 1);
			
			strArr[i] = hold.substring(1, end);
			
			try {
				hold = hold.substring(end + 2);
			} catch(StringIndexOutOfBoundsException e) {
				hold = hold.substring(end);
			}
			i++;
			
			
		//These exceptions/if statements are specific to the format of the string that we receive from this API, it will not function properly with other APIs attempting the same thing
			if(hold.contains("[") && hold.indexOf("[", 1) < 2) {
				hold = hold.substring(2);
			}
			if(hold.length() < 2)
				hold = "";
		}
		
		return strArr;
		
	}
	public static String geocode(String Address) {
		String hold = "";
		String directions = null;
		int temp = 0;
		int temp2 = 0;
		String lat = "";
		String lng = "";
		Address = Address.replaceAll(" ", "%20");
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create("https://google-maps-geocoding.p.rapidapi.com/geocode/json?address="+Address+"&language=en"))
				.header("x-rapidapi-key", "172a70af2dmsh6475de0845e5dfcp1eb6e0jsna0f0948a23c7")
				.header("x-rapidapi-host", "google-maps-geocoding.p.rapidapi.com")
				.method("GET", HttpRequest.BodyPublishers.noBody())
				.build();
		HttpResponse<String> response;
		try {
			response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
			hold = response.body();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		temp = hold.indexOf("location");
		
		temp2 = hold.indexOf("lat",temp);
		lat = hold.substring(temp2+6, temp2+20);
		//System.out.println(lat);
		temp2 = hold.indexOf("lng",temp);
		lng = hold.substring(temp2+6,temp2+20);
		//System.out.println(lng);
		directions = lat + lng;
		directions = directions.replaceAll("\\s+", "");
		
		return directions;
	}
	public static String directions(String start) {
		String hold = "";
		String destination = "49.873300,-119.493810";
		int temp = 0;
		int temp2 = 0;
		String tempString = "";
		boolean hasNext = true;
		StringBuilder directions = new StringBuilder("First drive straight for ");
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create("https://trueway-directions2.p.rapidapi.com/FindDrivingPath?origin="+start+"&destination="+destination))
				.header("x-rapidapi-key", "172a70af2dmsh6475de0845e5dfcp1eb6e0jsna0f0948a23c7")
				.header("x-rapidapi-host", "trueway-directions2.p.rapidapi.com")
				.method("GET", HttpRequest.BodyPublishers.noBody())
				.build();
		HttpResponse<String> response;
		try {
			response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
			hold = response.body();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		temp = hold.indexOf("steps");
		boolean first = true;
		while(hasNext) {
			if(first)
				temp2 = hold.indexOf("distance",temp);
			tempString = hold.substring(temp2+10, temp2+18);
			if(tempString.indexOf(",") >=0) {
				//System.out.println("found a comma");
				tempString = tempString.replace(",", "");
			}
			directions.append(tempString +"m, ");
			//System.out.println(tempString);
			temp2 = hold.indexOf("maneuver",temp2);
			tempString = hold.substring(temp2+10, temp2+25);
			//System.out.println(tempString);
			directions.append("then " +tempString + ".");
			
			if(hold.indexOf("distance",temp2) == -1) {
				hasNext = false;
				//System.out.println(directions);
			}
			temp2 = hold.indexOf("distance", temp2);
			first = false;
		}
		
		//terator jitr = JSA.iterator();
		
		String directions2 = directions.toString();
		//System.out.println("Directions: " + directions2);
		directions2 = directions2.replaceAll("\\s+", " ");
		directions2 = directions2.replace(".", ". Drive for ");
		return directions2;
	}

	public static void main(String[] args){

		//This area of the code is just being used to test the API and ensuring the method works properly.
		String start = "49.860661,-119.585831";
		String destination = "49.873300,-119.493810";
		String address = "1699 Ross rd., West Kelowna, BC";
		String gCode = geocode(address);
		System.out.println("Geocode: "+gCode);
		System.out.println(directions(gCode));
		
		
		
		
	}
}