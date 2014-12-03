package edu.dcu.ticTacToeApi2.ui.test;

import static edu.dcu.ticTacToeApi2.TicTacToeClient.START_GAME_REQUEST_FORMAT;
import static edu.dcu.ticTacToeApi2.TicTacToeClient.VERSION_REQUEST_FORMAT;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import edu.dcu.ticTacToeApi2.ServerConnector;
import edu.dcu.ticTacToeApi2.TicTacToeClient;
import edu.dcu.ticTacToeApi2.ui.TicTacToeMenu;

public class TicTacToeMenuTest {
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
	private BufferedReader inMock;
	@Mock
	private PrintWriter outMock;
	@Mock
	private TicTacToeClient clientMock;
	private TicTacToeMenu ticTacToeMenu;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		this.ticTacToeMenu = new TicTacToeMenu(clientMock, inMock, outMock);
		when(clientMock.getVersion()).thenReturn(version);
		when(clientMock.startGame(name, description, letter, isPrivate)).thenReturn(
				startGameResponse);
		when(clientMock.startGame(name2, description, letter2, isPrivate)).thenReturn(
				startGameResponse2);
		when(clientMock.joinGame(id, name)).thenReturn(joinGame);
		when(clientMock.getVersion()).thenReturn(version);
		when(clientMock.next(secret)).thenReturn(next);
		when(clientMock.move(secret, position)).thenReturn(move);
		when(clientMock.endGame(secret)).thenReturn(endGame);
		when(clientMock.getlistGames()).thenReturn(listGame);

	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testShowMenu() throws IOException {
		when(inMock.readLine()).thenReturn("s").thenReturn(name).thenReturn(description)
				.thenReturn(letter).thenReturn(isPrivate);
		ticTacToeMenu.showMenu();
		verify(outMock, times(14)).println(any(String.class));
		verify(outMock).println("Which operation do you want?");
		verify(outMock).println("start game press: s");
		verify(outMock).println("get version game press: v");
		verify(outMock).println("print list of games press: l");
		verify(outMock).println("join to a game press: j");
		verify(outMock).println("end Game press: e");
		verify(outMock).println("next: n");
		verify(outMock).println("move: m");
	}

	@Test
	public final void testIfShowStartGameMenu() throws IOException {
		when(inMock.readLine()).thenReturn("s").thenReturn(name).thenReturn(description)
				.thenReturn(letter).thenReturn(isPrivate);
		ticTacToeMenu.showMenu();
		verify(outMock).println("Which operation do you want?");

	}

	@Test
	public final void testGetVersion() throws IOException {
		when(inMock.readLine()).thenReturn("v");
		ticTacToeMenu.showMenu();
		verify(outMock, times(9)).println(any(String.class));

	}

	@Test
	public final void testGetListGames() throws IOException {
		when(inMock.readLine()).thenReturn("l");
		ticTacToeMenu.showMenu();
		verify(outMock, times(9)).println(any(String.class));

	}

	@Test
	public final void testIfShowJoinGame() throws IOException {
		when(inMock.readLine()).thenReturn("j").thenReturn(id).thenReturn(name);
		ticTacToeMenu.showMenu();
		verify(outMock, times(13)).println(any(String.class));
	}

	@Test
	public final void testJoinGameWrongParameter() throws IOException {
		when(inMock.readLine()).thenReturn("k").thenReturn("s").thenReturn(name).thenReturn(description)
		.thenReturn(letter).thenReturn(isPrivate);
		ticTacToeMenu.showMenu();
		verify(outMock).println("kis not a option, please try again");
		verify(outMock, times(2)).println("Which operation do you want?");
		
	}

	@Test
	public final void testIfShowNextMenu() throws IOException {
		when(inMock.readLine()).thenReturn("n").thenReturn(secret);
		ticTacToeMenu.showMenu();
		verify(outMock).println("enter your secret game");
	}

	@Test
	public final void testMoveMenu() throws IOException {
		when(inMock.readLine()).thenReturn("m").thenReturn(secret).thenReturn(position);
		ticTacToeMenu.showMenu();
		verify(outMock, times(12)).println(any(String.class));
	}

	@Test
	public final void testIfShowEndGame() throws IOException {
		when(inMock.readLine()).thenReturn("e").thenReturn(secret);
		ticTacToeMenu.showMenu();
		verify(outMock).println("endGame?");
	}

}
