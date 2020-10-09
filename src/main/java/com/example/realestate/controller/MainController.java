package com.example.realestate.controller;

import com.example.realestate.model.CurrentUser;
import com.example.realestate.model.Listing;
import com.example.realestate.model.ListingStatus;
import com.example.realestate.model.User;
import com.example.realestate.repository.ListingRepository;
import com.example.realestate.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ListingRepository listingRepository;

    @GetMapping("/")
    public String main(Model model, @AuthenticationPrincipal CurrentUser currentUser) {
        if (currentUser != null){
            model.addAttribute("currentUser",currentUser.getUser());
        }
        List<Listing> featuredList = listingRepository.findAllByListingStatus(ListingStatus.FEATURED);
        model.addAttribute("feturedList", featuredList);
        return "index";
    }

    @PostMapping("/register")
    public String registerPost(@ModelAttribute User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "redirect:/signIn";
    }


    @GetMapping("/signIn")
    public String signIn(Model model) {
        return "signin";

}

    @GetMapping("/register")
    public String registerGet() {
        return "register";
    }
}
