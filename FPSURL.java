import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/*
 * Error Code List;
 * 100: URL Argument is required.
 * 101: Cannot Support un HTTP request
 * 102: Fast Payment Service occurs Error!!!
 * 103: HTTP Connection Error
 * 104:
 * 
 */

public class FPSURL {

	private String apiKey;
	private String targetURL;
	private Map<Integer, String> errMsgMap;

	public String getTargetURL() {
		return targetURL;
	}

	public void setTargetURL(String targetURL) {
		this.targetURL = targetURL;
	}
	
	public static void main (String[] args) {
		FPSURL myFPSURL = new FPSURL();
		
		//invalid usage
		if(args.length == 0) myFPSURL.postError(100);
		
		//URL post
		myFPSURL.setTargetURL(args[0]);
		myFPSURL.postURL();
		
		//program exit successfully
		System.exit(0);
	}
	
	public FPSURL() {
		//initialization
		this.targetURL = null;
		this.apiKey = readAPIKey().trim();
		this.errMsgMap = new HashMap<Integer, String>();
		
		//set for list of error message
		this.errMsgMap.put(100, "URL Argument is required");
		this.errMsgMap.put(101, "Cannot Support un HTTP request");
		this.errMsgMap.put(102, "Response status <> 200");
		this.errMsgMap.put(103, "HTTP Connection Error");
	}
	
	public void postURL() {
		URL url;
		HttpURLConnection httpURLConneciton;
		byte [] urlContent = new byte[1000];
		
		try {
			//get URL
			url = new URL(this.targetURL);
			
			//reject non HTTP protocol
			if(!(url.openConnection() instanceof HttpURLConnection))
				this.postError(101);
			
			//get HttpConnection
			httpURLConneciton = (HttpURLConnection) url.openConnection();
			
			//set timeout
			httpURLConneciton.setReadTimeout(100000);
			httpURLConneciton.setConnectTimeout(100000);
			
			//set API Key;
			httpURLConneciton.setRequestProperty("apiKey", apiKey);
			
			//reject non 200 status' result
			if(httpURLConneciton.getResponseCode() != 200) this.postError(102);
			
			//read URL content
			System.out.print("URL Content: ");
			while(httpURLConneciton.getInputStream().read(urlContent) != -1) {
				System.out.println(new String(urlContent));
			}
			
		}  catch (Exception e) {
			this.postError(103);
		}
	}
	
	//read config file to set APIKey
	public String readAPIKey() {
		String defAPIKey = "FPSService2018";
		String apiKey;
		File configFile = new File("fps.config");
		
		//default apikey if config doesn't exist
		if(!configFile.isFile()) return defAPIKey;
			
		//get apikey
		try {
			//read config file
			BufferedReader br = new BufferedReader(new FileReader(configFile));
			
			//return apiKey
			apiKey = br.readLine();
			if(apiKey == null || apiKey.equals(" ")) return defAPIKey;
			
			return apiKey;
		} catch (Exception e) {
			
			//default apikey if read error
			return defAPIKey;
		}
	}
	
	//Post Error
	public void postError(int errCd){
		//Print Error Message
		if(errMsgMap.containsKey(errCd))
			System.out.println("ERROR: " + errMsgMap.get(errCd));
		else 
			System.out.println("ERROR: Invalid Error Code - " + errCd);
		
		// Return Error Code
		System.exit(errCd);
	}
	
}


