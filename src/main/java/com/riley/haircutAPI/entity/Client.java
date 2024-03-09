package com.riley.haircutAPI.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Client {

    @Id
    @SequenceGenerator(
            name = "courseId_sequence",
            sequenceName = "courseId_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "courseId_sequence"
    )
    private Long clientId;
    
    private String firstName;
    private String lastName;
    private Integer mobile;
    private String email;
    private String password;

    @OneToOne(mappedBy = "client")
    @JsonBackReference
    private Appointment appointment;
}
