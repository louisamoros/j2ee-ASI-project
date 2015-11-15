package servlet;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ejb.MessageReceiverSyncLocal;
import ejb.MessageSenderLocal;

/**
 * Servlet implementation class WatcherAuthServlet
 */
@WebServlet("/watcher-auth")
public class WatcherAuthServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	MessageSenderLocal sender;

	@EJB
	MessageReceiverSyncLocal receiver;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public WatcherAuthServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("yoyo");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("yoyo");

	}

}
