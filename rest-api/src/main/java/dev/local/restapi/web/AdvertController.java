package dev.local.restapi.web;

import dev.local.restapi.model.Advert;
import dev.local.restapi.model.dto.AdvertRequestDto;
import dev.local.restapi.service.AdvertService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/adverts")
@RequiredArgsConstructor
public class AdvertController {

    private final AdvertService advertService;

    @PostMapping
    public ResponseEntity<Advert> createAdvert(@RequestBody AdvertRequestDto dto) {
        Advert savedAdvert = advertService.saveAdvert(dto);
        URI location = URI.create("/adverts/" + savedAdvert.getId());
        return ResponseEntity.created(location).body(savedAdvert);
    }

    @GetMapping
    public List<Advert> getAdverts(@RequestParam(defaultValue = "0") int page,
                                   @RequestParam(defaultValue = "10") int limit) {
        Pageable pageable = PageRequest.of(page, limit);
        return advertService.getAllAdverts(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Advert> getAdvert(@PathVariable Long id) {
        return advertService.getAdvertById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdvert(@PathVariable Long id) {
        advertService.deleteAdvert(id);
        return ResponseEntity.noContent().build();
    }
}
