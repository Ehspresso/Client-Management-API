package com.riley.haircutAPI.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Appointment {

    @Id
    @SequenceGenerator(
            name = "appointment_sequence",
            sequenceName = "appointment_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "appointment_sequence"
    )
    private Long id;

    @DateTimeFormat(pattern = "mm/dd/yyyy")
    private LocalDate date;
    private Integer type;
    private Integer price;

    @OneToOne
    @JoinColumn(
            name = "client_id",
            referencedColumnName = "clientId",
            nullable = false
    )
    @JsonManagedReference
    private Client client;
}
