package dev.jackraidenph.spring_h2_liquibase_practice.controller;

import dev.jackraidenph.spring_h2_liquibase_practice.dto.DetailedHotelDto;
import dev.jackraidenph.spring_h2_liquibase_practice.dto.ShortHotelDto;
import dev.jackraidenph.spring_h2_liquibase_practice.entity.HotelEntity;
import dev.jackraidenph.spring_h2_liquibase_practice.service.HotelService;
import dev.jackraidenph.spring_h2_liquibase_practice.service.Mapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/property-view")
@Tag(name = "Hotel", description = "The Hotel API")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @GetMapping("/hotels")
    @Operation(summary = "Retrieve a list of all hotels with shortened information")
    public ResponseEntity<List<ShortHotelDto>> getAllHotels() {
        List<ShortHotelDto> shortHotelDtos = hotelService.getAllHotels().stream().
                map(Mapper::hotelEntityToDtoShort)
                .toList();

        return ResponseEntity.ok(shortHotelDtos);
    }

    @GetMapping("/hotels/{id}")
    @Operation(summary = "Retrieve detailed information about a hotel")
    public ResponseEntity<DetailedHotelDto> getHotelById(@PathVariable Long id) {
        HotelEntity hotel = hotelService.getHotelById(id);
        if (hotel != null) {
            return ResponseEntity.ok(Mapper.hotelEntityToDtoDetailed(hotel));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/hotels")
    @Operation(summary = "Add a new hotel")
    public ResponseEntity<ShortHotelDto> createHotel(@RequestBody HotelEntity hotel) {
        HotelEntity hotelEntity = hotelService.createHotel(hotel);
        if (hotelEntity != null) {
            return ResponseEntity.ok(Mapper.hotelEntityToDtoShort(hotelEntity));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/hotels/{id}/amenities")
    @Operation(summary = "Add amenities to a hotel. If no matching amenities exists, new will be created")
    public ResponseEntity<Void> addAmenities(@PathVariable Long id, @RequestBody List<String> amenities) {
        HotelEntity hotelEntity = hotelService.addAmenities(id, amenities);
        if (hotelEntity != null) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/search")
    @Operation(summary = "Search hotels by parameters. Available parameters: name, brand, city, country, amenities")
    public ResponseEntity<List<DetailedHotelDto>> searchHotels(@RequestParam(required = false) String name,
                                                               @RequestParam(required = false) String brand,
                                                               @RequestParam(required = false) String city,
                                                               @RequestParam(required = false) String country,
                                                               @RequestParam(required = false) List<String> amenities) {

        List<DetailedHotelDto> hotelDtos = hotelService.searchHotels(name, brand, city, country, amenities)
                .stream()
                .map(Mapper::hotelEntityToDtoDetailed)
                .toList();

        return ResponseEntity.ok(hotelDtos);
    }

    @GetMapping("/histogram/{param}")
    @Operation(summary = "Retrieve histograms. Available: brand, city, country, amenities")
    public ResponseEntity<Map<String, Long>> getHistogram(@PathVariable String param) {
        Map<String, Long> histogram = hotelService.getHistogram(param);

        if (histogram != null) {
            return ResponseEntity.ok(histogram);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}