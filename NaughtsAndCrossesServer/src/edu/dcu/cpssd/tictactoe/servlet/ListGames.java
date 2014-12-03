package edu.dcu.cpssd.tictactoe.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import edu.dcu.cpssd.tictactoe.core.Game;
import edu.dcu.cpssd.tictactoe.core.exceptions.GameException;

@WebServlet(name = "listGames", urlPatterns = { "/listGames" })
public class ListGames extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ListGames() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			JSONObject responseObject = new JSONObject();
			responseObject.put("status", "okay");
			JSONArray jsonArray = new JSONArray();

			for (int i = 0; i < Game.games.size(); i++) {
				Game g = Game.games.get(i);
				if (g != null && (!g.isFull && !g.isOver)) {
					jsonArray.put(g.getInfo());
				}
			}
			responseObject.put("games", jsonArray);

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
