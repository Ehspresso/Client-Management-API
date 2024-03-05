package com.riley.haircutAPI.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Appointment {

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
    private Long id;
    private Date date;
    private Integer type;
    private Integer price;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "client_id",
            referencedColumnName = "clientId"
    )
    private Client client;
}
