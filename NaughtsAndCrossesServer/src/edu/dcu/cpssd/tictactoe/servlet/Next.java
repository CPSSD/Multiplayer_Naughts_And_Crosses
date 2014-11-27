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

import edu.dcu.cpssd.tictactoe.core.Board;
import edu.dcu.cpssd.tictactoe.core.GameFactory;
import edu.dcu.cpssd.tictactoe.core.Game;
import edu.dcu.cpssd.tictactoe.core.exceptions.GameException;

/**
 * Servlet implementation class Next
 */
@WebServlet(name = "next", urlPatterns = { "/next" })
public class Next extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Next() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			ServletContext servletContext = getServletConfig().getServletContext();

			GameFactory gameFactory = (GameFactory) servletContext.getAttribute("gameFactory");
			String id = request.getParameter("id");
			Game game = gameFactory.getGameWithId(id);

			Board board = game.getBoard();
			int turn = game.getTurn();
			int[] positions = board.getPositions();
			int winner = game.getWinner();

			JSONObject responseObject = new JSONObject().put("board", positions).put("turn", turn)
					.put("winner", winner);
			writeResponse(response, responseObject);

		} catch (GameException ge) {
			System.out.println(ge.getMessage());
			ge.printStackTrace();
			writeResponse(response, ge.getErrorType());
		}

	}

	private void writeResponse(HttpServletResponse response, JSONObject responseObject)
			throws IOException {
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		responseObject.write(out);
		out.flush();
		out.close();
	}
}
