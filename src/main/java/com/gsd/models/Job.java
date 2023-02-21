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
    String discription;
    String type;
    String expireDate;
    String companyName;
    String location;
    String employeementType;
    String employeementPeriod;
    String jobUrgency;
    String companyLogo;
    String listingSite;

}
