package dev.jackraidenph.spring_h2_liquibase_practice.service;

import dev.jackraidenph.spring_h2_liquibase_practice.dto.*;
import dev.jackraidenph.spring_h2_liquibase_practice.entity.*;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.StringJoiner;
import java.util.stream.Collectors;

@Service
public class Mapper {

    public static AddressDto addressEmbeddableToDto(AddressEmbeddable addressEmbeddable) {
        AddressDto addressDto = new AddressDto();
        addressDto.setHouseNumber(addressEmbeddable.getHouseNumber());
        addressDto.setStreet(addressEmbeddable.getStreet());
        addressDto.setCity(addressEmbeddable.getCity());
        addressDto.setCountry(addressEmbeddable.getCountry());
        addressDto.setPostCode(addressEmbeddable.getPostCode());

        return addressDto;
    }

    public static ContactsDto contactsEmbeddableToDto(ContactsEmbeddable contactsEmbeddable) {
        ContactsDto contactsDto = new ContactsDto();
        contactsDto.setEmail(contactsDto.getEmail());
        contactsDto.setPhone(contactsDto.getPhone());

        return contactsDto;
    }

    public static ArrivalTimeDto arrivalTimeEmbeddableToDto(ArrivalTimeEmbeddable arrivalTimeEmbeddable) {
        ArrivalTimeDto arrivalTimeDto = new ArrivalTimeDto();
        arrivalTimeDto.setCheckIn(arrivalTimeEmbeddable.getCheckIn());
        arrivalTimeDto.setCheckOut(arrivalTimeEmbeddable.getCheckOut());

        return arrivalTimeDto;
    }

    public static ShortHotelDto hotelEntityToDtoShort(HotelEntity hotelEntity) {
        ShortHotelDto shortHotelDto = new ShortHotelDto();
        shortHotelDto.setId(hotelEntity.getId());
        shortHotelDto.setName(hotelEntity.getName());
        shortHotelDto.setDescription(hotelEntity.getDescription());
        shortHotelDto.setPhone(hotelEntity.getContacts().getPhone());

        StringJoiner joiner = new StringJoiner(", ");

        AddressEmbeddable addressEmbeddable = hotelEntity.getAddress();

        joiner.add(addressEmbeddable.getHouseNumber() + " " + addressEmbeddable.getStreet());
        joiner.add(addressEmbeddable.getCity());
        joiner.add(addressEmbeddable.getPostCode());
        joiner.add(addressEmbeddable.getCountry());

        shortHotelDto.setAddress(joiner.toString());

        return shortHotelDto;
    }

    public static DetailedHotelDto hotelEntityToDtoDetailed(HotelEntity hotelEntity) {
        DetailedHotelDto detailedHotelDto = new DetailedHotelDto();
        detailedHotelDto.setId(hotelEntity.getId());
        detailedHotelDto.setName(hotelEntity.getName());
        detailedHotelDto.setBrand(hotelEntity.getBrand());
        detailedHotelDto.setAddress(addressEmbeddableToDto(hotelEntity.getAddress()));
        detailedHotelDto.setContacts(contactsEmbeddableToDto(hotelEntity.getContacts()));
        detailedHotelDto.setArrivalTime(arrivalTimeEmbeddableToDto(hotelEntity.getArrivalTime()));

        Set<String> amenities = hotelEntity.getAmenities().stream()
                .map(HotelAmenityEntity::getName)
                .collect(Collectors.toSet());

        detailedHotelDto.setAmenities(amenities);

        return detailedHotelDto;
    }

}
