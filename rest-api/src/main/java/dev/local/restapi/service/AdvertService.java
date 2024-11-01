package dev.local.restapi.service;

import dev.local.restapi.model.Advert;
import dev.local.restapi.model.dto.AdvertRequestDto;
import dev.local.restapi.model.enums.AdvertStatus;
import dev.local.restapi.respository.AdvertRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdvertService {

    private final AdvertRepository advertRepository;

    public Advert saveAdvert(AdvertRequestDto dto) {
        Advert advert = mapToAdvert(dto);
        advert.setCreatedAt(LocalDateTime.now());
        advert.setValidTo(LocalDateTime.now().plusMonths(1));
        advert.setStatus(AdvertStatus.ACTIVE);
        return advertRepository.save(advert);
    }

    public List<Advert> getAllAdverts(Pageable pageable) {
        return advertRepository.findAll(pageable).getContent();
    }

    public Optional<Advert> getAdvertById(Long id) {
        return advertRepository.findById(id);
    }

    public void deleteAdvert(Long id) {
        advertRepository.deleteById(id);
    }

    //TODO add some mapper class with two ways mapping
    private Advert mapToAdvert(AdvertRequestDto dto) {
        Advert advert = new Advert();
        advert.setTitle(dto.getTitle());
        advert.setDescription(dto.getDescription());
        advert.setAdvertiserType(dto.getAdvertiserType());
        advert.setContact(dto.getContact());
        advert.setParams(dto.getParams());
        return advert;
    }
}