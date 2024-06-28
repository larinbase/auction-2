package ru.itis.auctionmarketplace.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record RegistrationForm(
        @NotBlank(message = "Имя не должно быть пустым")
        @Size(min = 3, max = 20, message = "Имя должно содержать от 3 до 20 символов")
        String username,

        @NotBlank(message = "Пароль не должен быть пустым")
        @Size(min = 6, max = 20, message = "Пароль должен содержать от 6 до 50 символов")
        String password) {
}
