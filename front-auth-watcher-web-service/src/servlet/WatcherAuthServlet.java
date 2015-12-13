package servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.jms.JMSException;
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
import ejb.MessagesLocal;

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

		UserModel user = new UserModel(login, pwd);

		// sender.sendMessage(user);
		// user = receiver.receiveMessageUser();

		if ("tp".equals(user.getLogin()) && "tp".equals(user.getPwd()))
			user.setRole(Role.ADMIN);
		else if ("pt".equals(user.getLogin()) && "pt".equals(user.getPwd()))
			user.setRole(Role.WATCHER);
		else
			user.setRole(Role.NONE);

		JSONObject responseJson = new JSONObject();

		responseJson.put("login", user.getLogin());

		if (user.getRole().equals(Role.ADMIN)) {
			responseJson.put("role", "admin");
			responseJson.put("validAuth", "true");
		} else if (user.getRole().equals(Role.WATCHER)) {
			responseJson.put("role", "watcher");
			responseJson.put("validAuth", "true");
		} else {
			responseJson.put("role", "none");
			responseJson.put("validAuth", false);
		}

		System.out.println(responseJson);

		response.setContentType("application/json");
		response.getOutputStream().print(responseJson.toJSONString());
		response.getOutputStream().flush();

	}

}
