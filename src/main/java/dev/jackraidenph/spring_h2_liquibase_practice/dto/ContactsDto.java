package dev.jackraidenph.spring_h2_liquibase_practice.dto;

public class ContactsDto {

    private String phone;
    private String email;

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
