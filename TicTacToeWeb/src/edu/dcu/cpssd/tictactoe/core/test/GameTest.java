package edu.dcu.cpssd.tictactoe.core.test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.dcu.cpssd.tictactoe.core.Board;
import edu.dcu.cpssd.tictactoe.core.Game;
import edu.dcu.cpssd.tictactoe.core.User;
import edu.dcu.cpssd.tictactoe.core.exceptions.GameException;

public class GameTest {
	private ArrayList<User> users;
	private Board board;
	private User jenny;
	private User norma;
	private Game game;

	@Before
	public void setUp() throws Exception {
		users = new ArrayList<>(2);
		jenny = new User("jenny");
		norma = new User("norma");
		users.add(jenny);
		users.add(norma);
		board = new Board();
		game = new Game(users, board);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void test() {

	}

	// test return letter
	@Test
	public final void testIfReturnLetterX() {
		assertEquals(1, game.getUserTurn(jenny));
	}

	@Test
	public final void testIfReturnLetterO() {
		assertEquals(2, game.getUserTurn(norma));
	}

	@Test
	public final void testIfReturnLetterError() throws Exception {
		assertEquals(0, game.getUserTurn(new User("no valid")));
	}

	@Test
	public final void testIfGetReturnPositions() {
		int[] positions = game.getBoard().getPositions();

		assertArrayEquals(new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, positions);
	}

	@Test
	public final void testIfGetReturnBoardPositions() throws Exception {
		int position = 1;
		int turn = 2;
		game.setTurn(turn);
		int[] positions = game.move(position, norma);
		assertArrayEquals(new int[] { 0, 2, 0, 0, 0, 0, 0, 0, 0 }, positions);
	}

	@Test
	public final void testIfGetReturnBoardPositionswothPosition14() throws Exception {
		User user = new User("jenny");
		int position = 14;
		int turn = 0;
		game.setTurn(turn);
		int[] positions = game.move(position, user);
		assertArrayEquals(new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, positions);
	}

	/*@Test
	public final void testIfGetReturnBoardAllPositions() throws Exception {
		User user = new User("jen");
		game.setTurn(0);
		int[] positions = game.move(7, user);
		positions = game.move(2, user);
		positions = game.move(0, user);
		assertArrayEquals(new int[] { 1, 0, 1, 0, 0, 0, 0, 1, 0 }, positions);
	}
*/
	// test return id

	// test return winner

	@Test
	public final void testIfIsWinner() throws Exception {
		int turn = 2;
		int[] boardPositions = new int[] { 2, 2, 2, 0, 0, 0, 0, 0, 0 };
		Board board = new Board(boardPositions);
		Game game = new Game("23", board);
		game.setTurn(turn);

		assertEquals(true, game.isWinner(turn));
		// como testear
	}
}
