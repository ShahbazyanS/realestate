package com.example.realestate.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "listing")
public class Listing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    @Enumerated(value = EnumType.STRING)
    private ListingType listingType;
    private double price;
    private int bedrooms;
    private int bathrooms;
    private double area;
    private String mlsNo;
    private String location;
    @ManyToMany
    @JoinTable(name = "listing_feature",
            joinColumns = {@JoinColumn(name = "listing_id")},
            inverseJoinColumns = {@JoinColumn(name = "feature_id")})
    private List<ListingFeatures> listingFeatures;
    private String picUrl;
    @Enumerated(value = EnumType.STRING)
    private ListingStatus listingStatus = ListingStatus.NORMAL;


}
