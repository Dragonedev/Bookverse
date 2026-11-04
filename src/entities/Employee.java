package entities;

public class Employee {

	private String employeeCode;
	private String name;
	private String cpf;
	private String email;

	private Employee(Builder builder) {
		this.employeeCode = builder.employeeCode;
		this.name = builder.name;
		this.cpf = builder.cpf;
		this.email = builder.email;
	}

	public static class Builder {
		private String employeeCode;
		private String name;
		private String cpf;
		private String email;

		public Builder employeeCode(String employeeCode) {
			this.employeeCode = employeeCode;
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

		public Employee build() {
			return new Employee(this);
		}

	}

	public String getEmployeeCode() {
		return employeeCode;
	}

	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
