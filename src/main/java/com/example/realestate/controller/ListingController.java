package com.example.realestate.controller;

import com.example.realestate.model.Listing;
import com.example.realestate.model.ListingFeatures;
import com.example.realestate.repository.ListingFeatureRepository;
import com.example.realestate.repository.ListingRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ListingController {

    private final ListingRepository listingRepository;
    private final ListingFeatureRepository listingFeatureRepository;

    @GetMapping("/listing/add")
    public String addListingGet(ModelMap modelMap){
        List<ListingFeatures> features = listingFeatureRepository.findAll();
        modelMap.addAttribute("features", features);
        return "addListing";
    }

    @PostMapping("/listing/add")
    public String addListing(@ModelAttribute Listing listing,
                             @RequestParam("features") List<Long> features,
                             @RequestParam("img")MultipartFile multipartFile) throws IOException {
        List<ListingFeatures> allById = listingFeatureRepository.findAllById(features);
        listing.setListingFeatures(allById);
        String picName = System.currentTimeMillis() + "_" +multipartFile.getOriginalFilename();
        File file = new File("C:\\Users\\User\\Documents\\upload" + picName);
        multipartFile.transferTo(file);
        listing.setPicUrl(picName);
        listingRepository.save(listing);
        return "redirect:/listing/add";
    }

    @GetMapping("listing/image")
    public @ResponseBody
    byte[] getImage(@RequestParam("name") String imageName) throws IOException {
        InputStream in = new FileInputStream("C:\\Users\\User\\Documents\\upload\\" + File.separator + imageName);
        return IOUtils.toByteArray(in);
    }
}
