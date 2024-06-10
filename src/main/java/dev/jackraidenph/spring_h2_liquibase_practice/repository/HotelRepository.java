package dev.jackraidenph.spring_h2_liquibase_practice.repository;

import dev.jackraidenph.spring_h2_liquibase_practice.entity.HotelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

public interface HotelRepository extends JpaRepository<HotelEntity, Long> {

    @Query("""
            SELECT h
            FROM HotelEntity h
            JOIN h.amenities a
            WHERE (:name IS NULL OR LOWER(h.name) LIKE LOWER(CONCAT('%', :name, '%')))
            AND (:brand IS NULL OR LOWER(h.brand) LIKE LOWER(CONCAT('%', :brand, '%')))
            AND (:city IS NULL OR LOWER(h.address.city) LIKE LOWER(CONCAT('%', :city, '%')))
            AND (:country IS NULL OR LOWER(h.address.country) LIKE LOWER(CONCAT('%', :country, '%')))
            AND (:amenities IS NULL OR a.name IN :amenities)
            """
    )
    List<HotelEntity> searchParams(
            @Param("name") String name,
            @Param("brand") String brand,
            @Param("city") String city,
            @Param("country") String country,
            @Param("amenities") Collection<String> amenities
    );
}