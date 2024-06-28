package ru.itis.auctionmarketplace.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.auctionmarketplace.config.properties.StorageProperties;
import ru.itis.auctionmarketplace.exception.AccountNotFoundException;
import ru.itis.auctionmarketplace.exception.StorageException;
import ru.itis.auctionmarketplace.exception.StorageFileNotFoundException;
import ru.itis.auctionmarketplace.model.AccountEntity;
import ru.itis.auctionmarketplace.model.StorageEntity;
import ru.itis.auctionmarketplace.repository.AccountRepository;
import ru.itis.auctionmarketplace.repository.StorageRepository;
import ru.itis.auctionmarketplace.service.StorageService;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class StorageServiceImpl implements StorageService {

    private final StorageProperties storageProperties;
    private final StorageRepository storageRepository;
    private final AccountRepository accountRepository;

    @Override
    public void store(MultipartFile file, String fileName, String username) {

        AccountEntity account = accountRepository.findByUsername(username)
                .orElseThrow(() -> new AccountNotFoundException(username));

        String location = storageProperties.getLocation();
        log.info("location: {}, contentType: {}, origFileName: {}", location, file.getContentType(), file.getOriginalFilename());
        try {
            if (file.isEmpty()) {
                throw new StorageException("Failed to store empty file");
            }
            log.info("location 1: {}", location);
            Path destinationFile = Path.of(location).resolve(
                            Paths.get(fileName + ".png"))
                    .normalize().toAbsolutePath();

            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, destinationFile,
                        StandardCopyOption.REPLACE_EXISTING);
            }

            storageRepository.findByAccountUsername(username).ifPresent(storageRepository::delete);

            storageRepository.save(StorageEntity.builder()
                            .account(account)
                            .fileName(fileName)
                    .build());
        } catch (IOException e) {
            throw new StorageException("Failed to store file", e);
        }
    }

    @Override
    public String findAvatarByUsername(String username) {
        if(storageRepository.findByAccountUsername(username).isPresent()){
            return storageRepository.findByAccountUsername(username).get().getFileName() + ".png";
        }
        return "base-avatar.png";
    }
}

