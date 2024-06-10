package dev.jackraidenph.spring_h2_liquibase_practice.repository;

import dev.jackraidenph.spring_h2_liquibase_practice.entity.HotelAmenityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

public interface AmenityRepository extends JpaRepository<HotelAmenityEntity, Long> {
    @Query("""
        SELECT a
        FROM HotelAmenityEntity a
        WHERE LOWER(a.name) in :names
    """)
    List<HotelAmenityEntity> findByNameInLowerCase(@Param("names") Collection<String> names);
}