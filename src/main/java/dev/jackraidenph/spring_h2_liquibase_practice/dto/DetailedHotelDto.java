package dev.jackraidenph.spring_h2_liquibase_practice.dto;

import java.util.Set;

public class DetailedHotelDto {

    private long id;
    private String name;
    private String brand;
    private AddressDto address;
    private ContactsDto contacts;
    private ArrivalTimeDto arrivalTime;
    private Set<String> amenities;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBrand() {
        return brand;
    }

    public AddressDto getAddress() {
        return address;
    }

    public ContactsDto getContacts() {
        return contacts;
    }

    public ArrivalTimeDto getArrivalTime() {
        return arrivalTime;
    }

    public Set<String> getAmenities() {
        return amenities;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setAddress(AddressDto addressDto) {
        this.address = addressDto;
    }

    public void setContacts(ContactsDto contactsDto) {
        this.contacts = contactsDto;
    }

    public void setArrivalTime(ArrivalTimeDto arrivalTimeDto) {
        this.arrivalTime = arrivalTimeDto;
    }

    public void setAmenities(Set<String> amenities) {
        this.amenities = amenities;
    }
}
