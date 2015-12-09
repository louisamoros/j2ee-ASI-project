package servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.gson.Gson;

/**
 * Servlet implementation class WatcherAuthServlet
 */
@WebServlet("/watcher-auth-servlet")
public class WatcherAuthServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

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
		// TODO Auto-generated method stub
		System.out.println("coucou");

		List<String> list = new ArrayList<String>();
		list.add("item1");
		list.add("item2");
		list.add("item3");
		String json = new Gson().toJson(list);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		System.out.println("yoyo");

		// List<String> list = new ArrayList<String>();
		// list.add("meti1");
		// list.add("meti2");
		// list.add("meti3");
		// String json = new Gson().toJson(list);
		// // response.setContentType("application/json");
		// // response.setCharacterEncoding("UTF-8");
		// // response.getWriter().write(json);
		// response.setContentType("application/json");
		// PrintWriter out = response.getWriter();
		// out.write(json);
		// out.flush();

		// String jsonAuth ="{"+"\"login\"unsure emoticon"tp\","+
		// "\"pwd\"unsure emoticon"tp\"}";

		StringBuffer sb = new StringBuffer();

		try {
			BufferedReader reader = request.getReader();
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		JSONParser parser = new JSONParser();
		JSONObject req = null;
		try {
			req = (JSONObject) parser.parse(sb.toString());
		} catch (ParseException e) {
			e.printStackTrace();
		}

		String login = (String) req.get("login");
		String pwd = (String) req.get("pwd");

		System.out.println("login : " + login);
		System.out.println("pwd : " + pwd);

		JSONObject responseJson = new JSONObject();
		responseJson.put("login", "tp");
		responseJson.put("validAuth", true);
		responseJson.put("role", "admin");

		response.setContentType("application/json");
		response.getOutputStream().print(responseJson.toJSONString());
		response.getOutputStream().flush();
		
	}

}
