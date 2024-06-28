package ru.itis.auctionmarketplace.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.itis.auctionmarketplace.dto.request.RegistrationForm;
import ru.itis.auctionmarketplace.exception.AccountAlreadyExistsException;
import ru.itis.auctionmarketplace.service.AuthService;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @GetMapping("/sign-in")
    public String signInView(@RequestParam(required = false) String error, Model model) {
        model.addAttribute("error", error);
        return "sign_in_form";
    }

    @GetMapping("/sign-up")
    public String signUpView() {
        return "sign_up_form";
    }

    @PostMapping("/sign-up")
    String signUp(@ModelAttribute @Validated RegistrationForm form) {
        log.info("sign up form: {}", form.toString());
        authService.register(form);
        return "redirect:/welcome";
    }

}
