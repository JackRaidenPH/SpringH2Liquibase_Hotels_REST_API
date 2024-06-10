package dev.jackraidenph.spring_h2_liquibase_practice.service;

import dev.jackraidenph.spring_h2_liquibase_practice.entity.HotelAmenityEntity;
import dev.jackraidenph.spring_h2_liquibase_practice.repository.AmenityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AmenityService {

    @Autowired
    private AmenityRepository amenityRepository;

    public List<HotelAmenityEntity> getAllAmenities() {
        return amenityRepository.findAll();
    }

    public List<HotelAmenityEntity> addAllAmenities(Iterable<HotelAmenityEntity> amenityEntities) {
        return amenityRepository.saveAll(amenityEntities);
    }

    public List<HotelAmenityEntity> getOrCreateFromStringCollection(Collection<String> names) {
        Set<String> amenityParams = names.stream()
                .map(String::toLowerCase)
                .collect(Collectors.toSet());
        List<HotelAmenityEntity> amenityEntities = amenityRepository.findByNameInLowerCase(amenityParams);

        if (amenityEntities.size() != names.size()) {

            Set<String> existingAmenitiesLowercase = amenityEntities.stream()
                    .map(HotelAmenityEntity::getName)
                    .map(String::toLowerCase)
                    .collect(Collectors.toSet());

            Set<HotelAmenityEntity> newAmenities = new HashSet<>();

            for (String name : amenityParams) {
                if (!existingAmenitiesLowercase.contains(name)) {
                    HotelAmenityEntity newAmenity = new HotelAmenityEntity();
                    newAmenity.setName(name);
                    newAmenities.add(newAmenity);
                }
            }

            amenityEntities.addAll(
                    amenityRepository.saveAll(newAmenities)
            );
        }

        return amenityEntities;
    }

}
