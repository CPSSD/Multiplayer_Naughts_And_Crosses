package edu.dcu.ticTacToeApi2.test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import edu.dcu.ticTacToeApi2.ServerConnector;
import edu.dcu.ticTacToeApi2.TicTacToeClient;
import edu.dcu.ticTacToeApi2.ui.TicTacToeMenu;

public class EndToEndTest {
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
	private TicTacToeClient clientMock;
	private TicTacToeMenu ticTacToeMenu;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		ticTacToeMenu = new TicTacToeMenu(inMock);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testEndToEnd() throws IOException {
		when(inMock.readLine()).thenReturn("s").thenReturn(name).thenReturn(description).thenReturn(letter).thenReturn(secret);
		ticTacToeMenu.showMenu();
	}
	
	

}
