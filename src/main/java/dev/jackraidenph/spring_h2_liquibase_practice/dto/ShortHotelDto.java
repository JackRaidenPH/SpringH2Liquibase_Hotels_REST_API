package dev.jackraidenph.spring_h2_liquibase_practice.dto;

public class ShortHotelDto {

    private long id;
    private String name;
    private String description;
    private String address;
    private String phone;


    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAddress(String shortAddress) {
        this.address = shortAddress;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
