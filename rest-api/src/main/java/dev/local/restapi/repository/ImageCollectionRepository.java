package dev.local.restapi.repository;

import dev.local.restapi.model.ImageCollection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageCollectionRepository extends JpaRepository<ImageCollection, Long> {
}
