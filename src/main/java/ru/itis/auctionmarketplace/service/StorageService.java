package ru.itis.auctionmarketplace.service;

import org.springframework.web.multipart.MultipartFile;

public interface StorageService {
    void store(MultipartFile file, String fileName, String username);

    String findAvatarByUsername(String username);
}
