package edu.dcu.ticTacToeApi2.ui.testExternalUrl;

import static org.junit.Assert.*;

import java.io.IOException;

import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.dcu.ticTacToeApi2.ServerConnector;

public class ServerConnectorTest {
	/**
	 * http://68.14.247.232/
	 */
	private static final String START_GAME_JENNY_REQUEST_ERROR= "startGame?name=jenny";
	private static final String START_GAME_REQUEST = "startGame?name=jen&description=des&letter=1&private=1&pin=1";
	private static final String JOIN_GAME_REQUEST = "joinGame?id=3&name=jenni";
	private static final String END_GAME_REQUEST = "endGame?secret=ABCDEF53y438976";
	public static final String MOVE_GAME_REQUEST = "move?secret=567489&position=2";
	private static final String STATUS = "status";
	
	private ServerConnector serverConnector;
	
	@Before
	public void setUp() throws Exception {
		this.serverConnector = new ServerConnector();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testGetFromURL() throws IOException {
		JSONObject startGameJennyResponseError = serverConnector.getUrl(START_GAME_JENNY_REQUEST_ERROR);
		JSONObject startGameResponse = serverConnector.getUrl(START_GAME_REQUEST);
		JSONObject joinGameResponse = serverConnector.getUrl(JOIN_GAME_REQUEST);
		JSONObject endGameResponse = serverConnector.getUrl(END_GAME_REQUEST);
		JSONObject moveGameResponse = serverConnector.getUrl(MOVE_GAME_REQUEST);
		assertNotNull(startGameJennyResponseError);
		assertEquals("error", startGameJennyResponseError.getString(STATUS));
		assertEquals("okay", startGameResponse.getString(STATUS));
		assertEquals("error", joinGameResponse.getString(STATUS));
		assertEquals("error", endGameResponse.getString(STATUS));
		assertEquals("error", moveGameResponse.getString(STATUS));
	}
	
}
