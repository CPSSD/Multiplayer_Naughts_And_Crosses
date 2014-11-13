package edu.dcu.cpssd.tictactoe.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import edu.dcu.cpssd.tictactoe.core.ErrorType;
import edu.dcu.cpssd.tictactoe.core.Game;
import edu.dcu.cpssd.tictactoe.core.GameFactory;
import edu.dcu.cpssd.tictactoe.core.User;
import edu.dcu.cpssd.tictactoe.core.exceptions.GameException;

/**
 * Servlet implementation class Move
 */
@WebServlet(name = "move", urlPatterns = { "/move" })
public class Move extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Move() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		JSONObject responseObject;
		try {
			String status = performMove(request);
			responseObject = new JSONObject().put("status", status);
		} catch (GameException e) {
			e.printStackTrace();
			responseObject = e.getErrorType();
			// responseObject = new JSONObject().put("status", "error")
			// .put("message", e.getMessage())
			// .put("code", e.getCode());
		}
		writeResponse(response, responseObject);
	}

	private void writeResponse(HttpServletResponse response, JSONObject responseObject)
			throws IOException {
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		responseObject.write(out);
		out.flush();
		out.close();
	}

	private String performMove(HttpServletRequest request) throws GameException {

		try {
			String id = request.getParameter("id");
			int position = Integer.valueOf(request.getParameter("position"));
			ServletContext servletContext = getServletContext();
			User user = (User) request.getSession().getAttribute("user");
			GameFactory gameFactory = (GameFactory) servletContext.getAttribute("gameFactory");
			Game game = gameFactory.getGameWithId(id);

			game.move(position, user);

			servletContext.setAttribute("gameFactory", gameFactory);

			return "okay";

		} catch (NumberFormatException e) {

			throw new GameException(ErrorType.OTHER_ERROR);
		}

	}
}