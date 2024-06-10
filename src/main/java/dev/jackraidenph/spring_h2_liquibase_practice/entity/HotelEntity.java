package dev.jackraidenph.spring_h2_liquibase_practice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.Set;

@Entity
@Table(name = "hotel")
public class HotelEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = "Name is required")
    private String name;

    @Column(nullable = false)
    @NotBlank(message = "Brand is required")
    private String brand;

    @Column(nullable = false)
    @NotBlank(message = "Description is required")
    private String description;

    @Embedded
    private AddressEmbeddable address;

    @Embedded
    private ContactsEmbeddable contacts;

    @Embedded
    private ArrivalTimeEmbeddable arrivalTime;

    @ManyToMany
    @JoinTable(
            name = "hotel_amenities",
            joinColumns = @JoinColumn(name = "hotel_id"),
            inverseJoinColumns = @JoinColumn(name = "amenity_id"))
    private Set<HotelAmenityEntity> amenities;

    public String getName() {
        return name;
    }

    public AddressEmbeddable getAddress() {
        return address;
    }

    public ContactsEmbeddable getContacts() {
        return contacts;
    }

    public ArrivalTimeEmbeddable getArrivalTime() {
        return arrivalTime;
    }

    public Set<HotelAmenityEntity> getAmenities() {
        return amenities;
    }

    public String getDescription() {
        return description;
    }

    public Long getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAddress(AddressEmbeddable address) {
        this.address = address;
    }

    public void setContacts(ContactsEmbeddable contacts) {
        this.contacts = contacts;
    }

    public void setArrivalTime(ArrivalTimeEmbeddable arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public void setAmenities(Set<HotelAmenityEntity> amenities) {
        this.amenities = amenities;
    }
}