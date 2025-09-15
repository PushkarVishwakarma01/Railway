package com.railway.Railway.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Signal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String signalName; // e.g., "Signal-A"

    private boolean isGreen; // true if train can enter the block

    @OneToOne
    private Block block; // the block this signal controls

}
