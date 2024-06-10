package dev.jackraidenph.spring_h2_liquibase_practice.service;

import dev.jackraidenph.spring_h2_liquibase_practice.entity.HotelAmenityEntity;
import dev.jackraidenph.spring_h2_liquibase_practice.entity.HotelEntity;
import dev.jackraidenph.spring_h2_liquibase_practice.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class HotelService {
    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private AmenityService amenityService;

    public List<HotelEntity> getAllHotels() {
        return hotelRepository.findAll();
    }

    public HotelEntity getHotelById(Long id) {
        return hotelRepository.findById(id).orElse(null);
    }

    public List<HotelEntity> searchHotels(String name,
                                          String brand,
                                          String city,
                                          String country,
                                          List<String> amenities
    ) {
        return hotelRepository.searchParams(name, brand, city, country, amenities);
    }

    public HotelEntity createHotel(HotelEntity hotel) {
        return hotelRepository.save(hotel);
    }

    public HotelEntity addAmenities(Long id, List<String> amenities) {
        HotelEntity hotel = hotelRepository.findById(id).orElse(null);
        if (hotel != null) {

            List<HotelAmenityEntity> amenityEntities = amenityService.getOrCreateFromStringCollection(amenities);

            hotel.getAmenities().addAll(amenityEntities);
            hotelRepository.save(hotel);
        }
        return hotel;
    }

    public Map<String, Long> getHistogram(String param) {
        List<HotelEntity> hotels = hotelRepository.findAll();

        return switch (param.toLowerCase()) {
            case "brand" -> countParameter(hotels, HotelEntity::getBrand);
            case "city" -> countParameter(hotels, h -> h.getAddress().getCity());
            case "country" -> countParameter(hotels, h -> h.getAddress().getCountry());

            case "amenities" -> hotels.stream()
                    .flatMap(h -> h.getAmenities().stream())
                    .collect(
                            Collectors.groupingBy(
                                    HotelAmenityEntity::getName,
                                    Collectors.counting()
                            )
                    );
            default -> null;
        };
    }

    private static <T> Map<String, Long> countParameter(
            Collection<T> entities,
            Function<T, String> parameterFunction
    ) {
        return entities.stream().collect(Collectors.groupingBy(parameterFunction, Collectors.counting()));
    }
}