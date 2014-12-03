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

@WebServlet(name = "next", urlPatterns = { "/next" })
public class Next extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Next() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			String secret = request.getParameter("secret");

			Game game = Game.getGameBySecret(secret);
			if (game == null) {
				throw new GameException(ErrorType.OTHER_ERROR);
			}

			JSONObject responseObject = new JSONObject().put("board", game.getBoard()).put("turn", game.currentTurn);
			if (game.getWinner() != -1) {
				responseObject.put("winner", game.getWinner());
			}
			writeResponse(response, responseObject);

		} catch (GameException ge) {
			System.out.println(ge.getMessage());
			ge.printStackTrace();
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
