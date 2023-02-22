package com.gsd.models;

import java.net.URL;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter @Setter @NoArgsConstructor @Accessors(fluent = true) @ToString
public class Job {
    String id;
    String title;
    URL url;
    String description;
    String type;
    String expireDate;
    String companyName;
    String location;
    String employmentType;
    String employmentPeriod;
    String jobUrgency;
    String companyLogo;
    String listingSite;

}
