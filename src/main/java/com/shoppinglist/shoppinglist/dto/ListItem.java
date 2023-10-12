package com.shoppinglist.shoppinglist.dto;

import jakarta.persistence.*;
import jdk.jfr.Unsigned;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;
import static jakarta.persistence.TemporalType.TIMESTAMP;

@Data
@Entity
@Table
public class ListItem {
    @Id
    @GeneratedValue(strategy=IDENTITY)
    private Integer itemId;

    @Column(nullable = false)
    private String itemName;

    @Column(nullable = false)
    private Double itemPrice;

    @Column(nullable = false)
    private Integer quantity;

    @Column
    @CreationTimestamp
    @Temporal(TIMESTAMP)
    private LocalDateTime created;

    @Column
    @UpdateTimestamp
    @Temporal(TIMESTAMP)
    private LocalDateTime lastUpdated;
}