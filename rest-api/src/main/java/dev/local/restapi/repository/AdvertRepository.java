package dev.local.restapi.repository;

import dev.local.restapi.model.Advert;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AdvertRepository extends JpaRepository<Advert, Long>, JpaSpecificationExecutor<Advert> {

    Page<Advert> findAll(Pageable pageable);
}
