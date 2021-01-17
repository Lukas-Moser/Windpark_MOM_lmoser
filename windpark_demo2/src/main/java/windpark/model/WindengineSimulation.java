package windpark.model;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import windpark.model.WindengineData;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
@Service
public class WindengineSimulation {
	
	private double getRandomDouble( int inMinimum, int inMaximum ) {

		double number = ( Math.random() * ( (inMaximum-inMinimum) + 1 )) + inMinimum; 
		double rounded = Math.round(number * 100.0) / 100.0; 
		return rounded;
		
	}

	private int getRandomInt( int inMinimum, int inMaximum ) {

		double number = ( Math.random() * ( (inMaximum-inMinimum) + 1 )) + inMinimum; 
		Long rounded = Math.round(number); 
		return rounded.intValue();

	}
	
	public WindengineData getData( String inWindengineID ) {
		
		WindengineData data = new WindengineData();
		data.setWindengineID( inWindengineID );
		data.setWindspeed( getRandomDouble( 0, 80 ) );
		data.setTemperature( getRandomDouble( -40, 40 ) );
		data.setPower( getRandomDouble( 0, 2000 ) );
		data.setBlindpower( getRandomDouble( 0, 200 ) );
		data.setRotationspeed( getRandomDouble( 0, 200 ) );
		data.setBladeposition( getRandomInt( 0, 45 ) );
		return data;
		
	}
	@Scheduled(fixedRate=2000)
	public void send(){
		try {
			HttpClient client = HttpClient.newHttpClient();
			HttpRequest request = HttpRequest.newBuilder()
					.uri(URI.create("http://localhost:8080/api/windengine"))
					.POST(HttpRequest.BodyPublishers.ofString(new WindengineSimulation().getData("001").toJSON()))
					.setHeader("Content-Type", "application/json; utf-8")
					.build();
			HttpResponse<String> response = client.send(request,
					HttpResponse.BodyHandlers.ofString());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
