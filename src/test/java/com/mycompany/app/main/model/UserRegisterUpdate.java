package com.mycompany.app.main.model;

import com.mycompany.app.main.task.RegisterUser;
import net.serenitybdd.screenplay.Performable;

public class UserRegisterUpdate {
	
	private String name;
	private String job;
	private String email;
	private String password;

	public UserRegisterUpdate(String name, String job, String email, String password) {
		this.name = name;
		this.job = job;
		this.email = email;
		this.password = password;
	}

	public UserRegisterUpdate(String name, String job) {
		this.name = name;
		this.job = job;
	}

	public UserRegisterUpdate() {

	}

	public Performable rememberMe(){
		return new RegisterUser(new UserRegisterUpdate(name, job, email, password));
	}

	public UserRegisterUpdate withEmail(String email){
		this.email = email;
		return this;
	}

	public UserRegisterUpdate withPassword(String password){
		this.password = password;
		return this;
	}
	public UserRegisterUpdate withJob(String job){
		this.job = job;
		return this;
	}

	public UserRegisterUpdate withName(String name){
		this.name = name;
		return this;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

}
