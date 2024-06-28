package ru.itis.auctionmarketplace.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.auctionmarketplace.config.properties.StorageProperties;
import ru.itis.auctionmarketplace.service.StorageService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/image")
public class ImageController {

    private final StorageService storageService;
    private final StorageProperties storageProperties;

    @PostMapping("/upload")
    @ResponseStatus(HttpStatus.OK)
    public void upload(@RequestParam("file") MultipartFile file, Principal principal) {
        String uuid = UUID.randomUUID().toString();
        log.info("uuid: {}", uuid);
        storageService.store(file, uuid, principal.getName());
    }

    @GetMapping
    public ResponseEntity<byte[]> get(Principal principal){
        log.info("path: {}", Path.of(storageProperties.getLocation()).toAbsolutePath().toString());
        String absPath = Path.of(storageProperties.getLocation()).toAbsolutePath().toString() + File.separator;
        String fileName = storageService.findAvatarByUsername(principal.getName());
        log.info("fileName: {}", fileName);
        try {
            byte[] bytes = Files.readAllBytes(Paths.get(absPath + fileName));
            return ResponseEntity.ok().contentType(MediaType.MULTIPART_FORM_DATA).body(bytes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
