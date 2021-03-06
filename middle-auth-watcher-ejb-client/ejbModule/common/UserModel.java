package common;

import java.io.Serializable;

public class UserModel implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String login;
	private String pwd;
	private Role role;
	
	public UserModel(String username, String pwd) {
		this.login = username;
		this.pwd = pwd;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}
