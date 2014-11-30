package edu.dcu.ticTacToeApi2.test;

import static org.junit.Assert.*;
import static edu.dcu.ticTacToeApi2.TicTacToeClient.*;
import static org.mockito.Mockito.*;

import java.io.IOException;

import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import edu.dcu.ticTacToeApi2.ServerConnector;
import edu.dcu.ticTacToeApi2.TicTacToeClient;

public class TicTacToeClientTest {
	JSONObject version = new JSONObject("{\"status\":\"okay\",\"major\":\"2\",\"minor\":\"1\"}");
	JSONObject startGameResponse = new JSONObject(
			"{\"status\":\"okay\",\"id\":\"game-23\",\"secret\":\"12WW253y438976\",\"leter\":\"1\"} ");
	JSONObject startGameResponse2 = new JSONObject(
			"{\"status\":\"okay\",\"id\":\"game-24\",\"secret\":\"ABCDEF53y438976\",\"leter\":\"2\"} ");

	JSONObject listGame = new JSONObject(
			"{\"status\":\"okay\",\"id\":\"game-23\",\"secret\":\"12WW253y438976\",\"leter\":\"1\"} ");
	JSONObject joinGame = new JSONObject(
			"{\"status\":\"okay\",\"secret\":\"12WW253y438976\",\"leter\":\"1\"} ");
	JSONObject next = new JSONObject(
			"{\"status\":\"okay\",\"board\":[0,0,0,0,1,0,0,0,0],\"turn\":\"2\"} ");
	JSONObject move = new JSONObject("{\"status\":\"okay\"}");
	JSONObject endGame = new JSONObject("{\"status\":\"okay\"}");

	String name = "jenny";
	String description = "new game";
	String letter = "X";
	String isPrivate = "1";
	String name2 = "jenny";
	String letter2 = "O";
	String id = "game-23";
	String secret = "12WW253y438976";
	String position = "8";

	@Mock
	private ServerConnector serverConnector;
	private TicTacToeClient ticTacToeClient;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		ticTacToeClient = new TicTacToeClient(serverConnector);
		when(serverConnector.getUrl(VERSION_REQUEST_FORMAT)).thenReturn(version);
		when(
				serverConnector.getUrl(START_GAME_REQUEST_FORMAT, name, description, letter,
						isPrivate)).thenReturn(startGameResponse);
		when(
				serverConnector.getUrl(START_GAME_REQUEST_FORMAT, name2, description, letter2,
						isPrivate)).thenReturn(startGameResponse2);
		when(serverConnector.getUrl(JOIN_GAME_REQUEST_FORMAT, id, name)).thenReturn(joinGame);
		when(serverConnector.getUrl(MOVE_REQUEST_FORMAT, secret, position)).thenReturn(move);
		when(serverConnector.getUrl(NEXT_REQUEST_FORMAT, secret)).thenReturn(next);

	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testOneMove() throws IOException {
		JSONObject game1 = ticTacToeClient.startGame(name, description, letter, isPrivate);
		JSONObject game2 = ticTacToeClient.startGame(name2, description, letter2, isPrivate);
		assertNotNull(game1);
		assertNotNull(game2);
		verify(serverConnector).getUrl(eq(START_GAME_REQUEST_FORMAT), eq(name), eq(description),
				eq(letter), eq(isPrivate));
		verify(serverConnector).getUrl(eq(START_GAME_REQUEST_FORMAT), eq(name2), eq(description),
				eq(letter2), eq(isPrivate));
	}

	@Test
	public final void testJoinGame() throws IOException {
		JSONObject game1 = ticTacToeClient.joinGame(id, name);
		// JSONObject game2 = ticTacToeClient.startGame(name2, description,
		// letter2, isPrivate);
		assertNotNull(game1);
		// assertNotNull(game2);
		verify(serverConnector).getUrl(eq(JOIN_GAME_REQUEST_FORMAT), eq(id), eq(name));
	}

	@Test
	public final void testMove() throws IOException {
		JSONObject game1 = ticTacToeClient.move(secret, position);
		// JSONObject game2 = ticTacToeClient.startGame(name2, description,
		// letter2, isPrivate);
		assertNotNull(game1);
		// assertNotNull(game2);
		verify(serverConnector).getUrl(eq(MOVE_REQUEST_FORMAT), eq(secret), eq(position));
	}

	@Test
	public final void testNext() throws IOException {
		JSONObject game1 = ticTacToeClient.next(secret);
		assertNotNull(game1);
		verify(serverConnector).getUrl(eq(NEXT_REQUEST_FORMAT), eq(secret));
	}
}
