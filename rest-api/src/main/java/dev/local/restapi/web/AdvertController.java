package dev.local.restapi.web;

import dev.local.restapi.model.Advert;
import dev.local.restapi.model.dto.AdvertRequestDto;
import dev.local.restapi.service.AdvertService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
@RequestMapping("/api/adverts")
@RequiredArgsConstructor
public class AdvertController {

    private final AdvertService advertService;

    @PostMapping
    @Operation(summary = "Create a new advert", description = "Creates a new advert based on the provided data and returns the saved advert.")
    @ApiResponse(responseCode = "201", description = "Advert created successfully", content = @Content(schema = @Schema(implementation = Advert.class)))
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Advert data to create a new advert", required = true, content = @Content(schema = @Schema(implementation = AdvertRequestDto.class)))
    public ResponseEntity<Advert> createAdvert(@RequestBody AdvertRequestDto dto) {
        Advert savedAdvert = advertService.saveAdvert(dto);
        URI location = URI.create("/adverts/" + savedAdvert.getId());
        return ResponseEntity.created(location).body(savedAdvert);
    }

    @GetMapping
    @Operation(summary = "Get all adverts", description = "Fetches a paginated list of adverts based on the specified page and limit.")
    @ApiResponse(responseCode = "200", description = "List of adverts retrieved successfully", content = @Content(schema = @Schema(implementation = Advert.class)))
    public List<Advert> getAdverts(@RequestParam(defaultValue = "0") int page,
                                   @RequestParam(defaultValue = "10") int limit) {
        Pageable pageable = PageRequest.of(page, limit);
        return advertService.getAllAdverts(pageable);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get advert by ID", description = "Fetches an advert by its unique ID.")
    @ApiResponse(responseCode = "200", description = "Advert retrieved successfully", content = @Content(schema = @Schema(implementation = Advert.class)))
    @ApiResponse(responseCode = "404", description = "Advert not found")
    public ResponseEntity<Advert> getAdvert(@PathVariable Long id) {
        return advertService.getAdvertById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete advert by ID", description = "Deletes an advert by its unique ID.")
    @ApiResponse(responseCode = "204", description = "Advert deleted successfully")
    @ApiResponse(responseCode = "404", description = "Advert not found")
    public ResponseEntity<Void> deleteAdvert(@PathVariable Long id) {
        advertService.deleteAdvert(id);
        return ResponseEntity.noContent().build();
    }
}
