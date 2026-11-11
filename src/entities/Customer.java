
package entities;

import exceptions.InvalidDataException;

public class Customer {

	private final String customerCode;
	private final String name;
	private final String cpf;
	private final String email;

	private Customer(Builder builder) {
		this.customerCode = builder.customerCode;
		this.name = builder.name;
		this.cpf = builder.cpf;
		this.email = builder.email;
	}

	public static class Builder {

		private String customerCode;
		private String name;
		private String cpf;
		private String email;

		public Builder customerCode(String customerCode) {
			this.customerCode = customerCode;
			return this;
		}

		public Builder name(String name) {
			this.name = name;
			return this;
		}

		public Builder cpf(String cpf) {
			this.cpf = cpf;
			return this;
		}

		public Builder email(String email) {
			this.email = email;
			return this;
		}

		public Customer build() {

			if (customerCode == null || customerCode.isBlank()) {
				throw new InvalidDataException("Customer code is required.");
			}

			if (name == null || name.isBlank()) {
				throw new InvalidDataException("Customer name is required.");
			}

			if (cpf == null || !cpf.matches("\\d{11}")) {
				throw new InvalidDataException("CPF must contain 11 digits.");
			}

			if (email == null || !email.matches("^[\\w.-]+@[\\w.-]+\\.\\w+$")) {
				throw new InvalidDataException("Invalid email.");
			}

			return new Customer(this);
		}
	}

	public String getCustomerCode() {
		return customerCode;
	}

	public String getName() {
		return name;
	}

	public String getCpf() {
		return cpf;
	}

	public String getEmail() {
		return email;
	}

}
