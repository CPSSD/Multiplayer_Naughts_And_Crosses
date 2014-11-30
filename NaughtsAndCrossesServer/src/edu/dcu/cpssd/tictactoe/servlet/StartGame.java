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

@WebServlet(name = "startGame", urlPatterns = { "/startGame" })
public class StartGame extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public StartGame() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {

			String name = request.getParameter("name");
			String description = request.getParameter("description");
			String letter = request.getParameter("letter");
			String isPrivate = request.getParameter("private");
			String pin;
			
			if (letter == null || description == null || name == null || isPrivate == null) {
				throw (new GameException(ErrorType.MISSING_PARAMETER_IN_REQUEST));
			}
			Game game;
			
			if(Integer.parseInt(isPrivate) == 1){
				pin = request.getParameter("pin");
				if(pin == null){
					throw (new GameException(ErrorType.MISSING_PARAMETER_IN_REQUEST));
				}
				game = new Game(name, description, Integer.parseInt(letter), pin);
			} else {
				game = new Game(name, description, Integer.parseInt(letter));
			}

			JSONObject responseObject = new JSONObject()
			.put("status", "okay")
			.put("letter", game.hostLetter)
			.put("id", game.getId())
			.put("secret", game.secret[game.hostLetter-1]);
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
