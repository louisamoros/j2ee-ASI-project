package servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.gson.Gson;

import common.Role;
import common.UserModel;
import ejb.MessageReceiverSyncLocal;
import ejb.MessageSenderLocal;

@WebServlet("/watcher-auth-servlet")
public class WatcherAuthServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@EJB
	 MessageSenderLocal sender;
	 @EJB
	 MessageReceiverSyncLocal receiver;

	public WatcherAuthServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		List<String> list = new ArrayList<String>();
		list.add("doGet");
		list.add("is");
		list.add("working");
		String json = new Gson().toJson(list);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

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

		UserModel userModel = new UserModel(login, pwd);
		
		System.out.println("0000000000000000000000000000000000000000000000");
		System.out.println("0000000000000000000000000000000000000000000000");
		System.out.println("0000000000000000000000000000000000000000000000");
		System.out.println("0000000000000000000000000000000000000000000000");
		System.out.println("0000000000000000000000000000000000000000000000");
		System.out.println("0000000000000000000000000000000000000000000000");
		
		sender.sendMessage("yoyo");
		
		System.out.println(receiver.receiveMessage());
		
		userModel.setLogin("tp");
		userModel.setRole(Role.ADMIN);
		System.out.println(userModel.getLogin());
		System.out.println(userModel.getRole());

		JSONObject responseJson = new JSONObject();

		responseJson.put("login", userModel.getLogin());
		if (userModel.getRole().equals("NONE")) {
			responseJson.put("validAuth", false);
		} else {
			responseJson.put("validAuth", "true");
		}

//		responseJson.put("login", "tp");
//		responseJson.put("validAuth", true);
		responseJson.put("role", "admin");

		response.setContentType("application/json");
		response.getOutputStream().print(responseJson.toJSONString());
		response.getOutputStream().flush();

	}

}
