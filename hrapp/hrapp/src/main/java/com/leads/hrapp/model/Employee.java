package com.leads.hrapp.model;

import jakarta.persistence.*;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.*;
@Data
@JacksonXmlRootElement(localName = "employee")
@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @JacksonXmlProperty(isAttribute = true)
    private Long id;

    private String firstname;
    private String lastname;
    private String title;
    private String division;
    private String building;
    private String room;

}
