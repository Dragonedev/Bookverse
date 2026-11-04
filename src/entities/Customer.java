
package entities;

public class Customer {

    private String customerCode;
    private String name;
    private String cpf;
    private String email;

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
            return new Customer(this);
        }
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
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
