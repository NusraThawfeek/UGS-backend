package com.example.demo.entity;

import lombok.Data;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;

import javax.persistence.*;


@Data
@Entity
@Table(name = "locations")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String locationName;

    public Location() {
    }

    public Location(String locationName){
        this.locationName = locationName;
    }

}
