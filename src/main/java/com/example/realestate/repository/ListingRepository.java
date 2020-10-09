package com.example.realestate.repository;

import com.example.realestate.model.Listing;
import com.example.realestate.model.ListingStatus;
import com.example.realestate.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ListingRepository extends JpaRepository<Listing,Long> {

    List<Listing> findAllByListingStatus(ListingStatus listingstatus);

}
