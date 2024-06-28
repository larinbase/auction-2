package ru.itis.auctionmarketplace;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import ru.itis.auctionmarketplace.config.properties.StorageProperties;

@SpringBootApplication
@EnableConfigurationProperties({StorageProperties.class})
public class AuctionMarketplaceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuctionMarketplaceApplication.class, args);
    }

}
