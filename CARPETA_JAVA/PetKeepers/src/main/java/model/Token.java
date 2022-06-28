package model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Token {
	@JsonProperty("valor")
	private String valor;
	
	// CONSTRUCTORES
		@JsonCreator
		public Token(@JsonProperty("valor") final String valor) {
			this.valor = valor;
		}

		@JsonCreator
		public Token() {
		}

		// ENCAPSULACION
		@JsonProperty("valor")
		public String getValor() {
			return valor;
		}
}
