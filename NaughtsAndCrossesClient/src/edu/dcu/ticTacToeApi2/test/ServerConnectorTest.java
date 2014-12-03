package edu.dcu.ticTacToeApi2.test;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import edu.dcu.ticTacToeApi2.ConnectionFactory;
import edu.dcu.ticTacToeApi2.ServerConnector;

public class ServerConnectorTest {
	private static final String TEST_URL = "http://";
	@Mock
	private ConnectionFactory connectionFactoryMock;
	private ServerConnector serverConnector;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		serverConnector = new ServerConnector(connectionFactoryMock);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testGetFromURL() throws IOException {
		JSONObject jsonObject = serverConnector.getUrl(TEST_URL);
		assertNotNull(jsonObject);
	}
	
}
