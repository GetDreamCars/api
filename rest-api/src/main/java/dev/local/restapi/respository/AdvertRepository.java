package dev.local.restapi.respository;

import dev.local.restapi.model.Advert;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdvertRepository extends JpaRepository<Advert, Long> {

    Page<Advert> findAll(Pageable pageable);
}
