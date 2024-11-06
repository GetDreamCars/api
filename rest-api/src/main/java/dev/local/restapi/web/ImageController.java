package dev.local.restapi.web;

import dev.local.restapi.model.Image;
import dev.local.restapi.model.ImageCollection;
import dev.local.restapi.repository.ImageCollectionRepository;
import dev.local.restapi.service.CloudinaryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/images")
@RequiredArgsConstructor
public class ImageController {

    private final CloudinaryService cloudinaryService;
    private final ImageCollectionRepository imageCollectionRepository;

    @PostMapping("/collections")
    @Operation(summary = "Create an image collection", description = "Uploads images to Cloudinary and creates an image collection.")
    @ApiResponse(responseCode = "200", description = "Image collection created successfully", content = @Content(schema = @Schema(implementation = ImageCollection.class)))
    @RequestBody(description = "List of image files to upload", required = true, content = @Content(mediaType = "multipart/form-data", schema = @Schema(type = "array", implementation = MultipartFile.class)))
    public ResponseEntity<ImageCollection> createImageCollection(@RequestParam("files") List<MultipartFile> files) throws IOException {
        ImageCollection imageCollection = new ImageCollection();
        List<Image> images = new ArrayList<>();

        for (MultipartFile file : files) {
            String fileUrl = cloudinaryService.uploadImage(file);
            Image image = new Image();
            image.setUrl(fileUrl);
            image.setImageCollection(imageCollection);
            images.add(image);
        }

        imageCollection.setImages(images);
        ImageCollection savedCollection = imageCollectionRepository.save(imageCollection);
        return ResponseEntity.ok(savedCollection);
    }

    @GetMapping("/collections/{id}")
    @Operation(summary = "Get image collection by ID", description = "Fetches an image collection by its unique ID.")
    @ApiResponse(responseCode = "200", description = "Image collection retrieved successfully", content = @Content(schema = @Schema(implementation = ImageCollection.class)))
    @ApiResponse(responseCode = "404", description = "Image collection not found")
    public ResponseEntity<ImageCollection> getImageCollection(@PathVariable Long id) {
        return imageCollectionRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/collections/{id}")
    @Operation(summary = "Delete image collection by ID", description = "Deletes an image collection by its unique ID.")
    @ApiResponse(responseCode = "204", description = "Image collection deleted successfully")
    @ApiResponse(responseCode = "404", description = "Image collection not found")
    public ResponseEntity<Void> deleteImageCollection(@PathVariable Long id) {
        if (imageCollectionRepository.existsById(id)) {
            imageCollectionRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
