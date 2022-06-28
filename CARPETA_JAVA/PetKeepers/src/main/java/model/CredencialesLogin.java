package model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CredencialesLogin {
	// ATRIBUTOS
	@JsonProperty("email")
	private String email;

	@JsonProperty("password")
	private String password;

	// CONSTRUCTORES
	@JsonCreator
	public CredencialesLogin(
			@JsonProperty("email") final String email,
			@JsonProperty("password") final String password) {
		this.email = email;
		this.password = password;
	}

	// ENCAPSULACION
	@JsonProperty("email")
	public String getEmail() {
		return email;
	}

	@JsonProperty("password")
	public String getPassword() {
		return password;
	}
}
