package edu.dcu.ticTacToeApi2.ui.testExternalUrl;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class ConnectionFactory {

	public InputStream getInputStream(URL url) throws IOException {
		return url.openStream();
	}

}
