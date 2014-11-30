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

@WebServlet(name = "endGame", urlPatterns = { "/endGame" })
public class EndGame extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public EndGame() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String secret = request.getParameter("secret");

			if (secret == null || secret.equals("")) {
				throw new GameException(ErrorType.MISSING_PARAMETER_IN_REQUEST);
			}

			Game game = Game.getGameBySecret(secret);
			if (game == null || game.isOver) {
				throw new GameException(ErrorType.NO_SUCH_GAME);
			}
			game.exitGame(secret);

			JSONObject responseObject = new JSONObject().put("status", "okay");
			writeResponse(response, responseObject);
		} catch (GameException e) {
			writeResponse(response, e.getErrorType());
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
