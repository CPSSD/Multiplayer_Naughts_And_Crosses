package edu.dcu.ticTacToeApi2;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import org.json.JSONObject;

public class ServerConnector {
	private static final String SERVER_ADDRESS = "http://cpssd5-web.computing.dcu.ie/";

	private final ConnectionFactory connectionFactory;
	
	public ServerConnector() {
		this.connectionFactory = new ConnectionFactory();
	}

	public ServerConnector(ConnectionFactory connectionFactory) {
		this.connectionFactory = connectionFactory;
	}

	public JSONObject getUrl(String urlFormat, Object... args) throws IOException {
		String urlString = String.format(urlFormat, args);
		System.out.println(urlString);
		URL url = new URL(SERVER_ADDRESS + urlString);
		InputStream inputStream = connectionFactory.getInputStream(url);
		InputStreamReader reader = new InputStreamReader(inputStream);
		StringBuilder sb = new StringBuilder();
		char[] chars = new char[4*1024];
		int len;
		while((len = reader.read(chars))>=0) {
		    sb.append(chars, 0, len);
		}
		
		return new JSONObject(sb.toString());
	}
}
