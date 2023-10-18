package com.vikash.stockAPP.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "stocks")
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //unique
    private Long stockId;

    private String stockName;

    private double stockPrice;

    private int stockOwnerCount;

    private Type stockType;

    private LocalDateTime stockCreationTimeStamp;
}
