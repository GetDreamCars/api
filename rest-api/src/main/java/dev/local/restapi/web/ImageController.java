package dev.local.restapi.web;

import dev.local.restapi.model.Image;
import dev.local.restapi.model.ImageCollection;
import dev.local.restapi.repository.ImageCollectionRepository;
import dev.local.restapi.service.CloudinaryService;
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
@RequestMapping("/images")
@RequiredArgsConstructor
public class ImageController {

    private final CloudinaryService cloudinaryService;
    private final ImageCollectionRepository imageCollectionRepository;

    @PostMapping("/collections")
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
    public ResponseEntity<ImageCollection> getImageCollection(@PathVariable Long id) {
        return imageCollectionRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/collections/{id}")
    public ResponseEntity<Void> deleteImageCollection(@PathVariable Long id) {
        if (imageCollectionRepository.existsById(id)) {
            imageCollectionRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
