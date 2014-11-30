package edu.dcu.cpssd.tictactoe.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import edu.dcu.cpssd.tictactoe.core.ErrorType;
import edu.dcu.cpssd.tictactoe.core.Game;
import edu.dcu.cpssd.tictactoe.core.exceptions.GameException;

@WebServlet(name = "joinGame", urlPatterns = { "/joinGame" })
public class JoinGame extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public JoinGame() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String gameId = request.getParameter("id");
			String name = request.getParameter("name");
			String pin = request.getParameter("pin");

			Game game = Game.getGameById(gameId);
			System.out.println(gameId);
			if (game == null) {
				throw new GameException(ErrorType.OTHER_ERROR);
			}
			
			if (game.isPrivate && !game.isFull) {
				if (pin != null && game.getPin().equals(pin)) {
					game.addPlayer(name, Game.generateSecret());
				} else {
					throw new GameException(ErrorType.MISSING_PARAMETER_IN_REQUEST);
				}
			} else if(!game.isFull){
				game.addPlayer(name, Game.generateSecret());
			} else {
				throw new GameException(ErrorType.GAME_FULL);
			}
			
			JSONObject responseObject = new JSONObject().put("letter", game.getOtherLetter()).put("status", "okay").put("secret", game.secret[game.getOtherLetter() - 1]);
			writeResponse(response, responseObject);

		} catch (GameException ge) {
			writeResponse(response, ge.getErrorType());
		}
	}

	private void writeResponse(HttpServletResponse response, JSONObject responseObject) throws IOException {
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		responseObject.write(out);
		out.flush();
		out.close();
	}
}
