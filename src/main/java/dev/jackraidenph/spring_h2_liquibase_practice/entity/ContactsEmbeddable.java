package dev.jackraidenph.spring_h2_liquibase_practice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;

@Embeddable
public class ContactsEmbeddable {
    @Column(nullable = false)
    @NotBlank(message = "Phone is required")
    private String phone;
    @Column(nullable = false)
    @NotBlank(message = "E-Mail is required")
    private String email;

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}