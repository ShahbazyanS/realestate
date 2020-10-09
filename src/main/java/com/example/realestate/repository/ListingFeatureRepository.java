package com.example.realestate.repository;

import com.example.realestate.model.ListingFeatures;
import com.example.realestate.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ListingFeatureRepository extends JpaRepository<ListingFeatures,Long> {


}
