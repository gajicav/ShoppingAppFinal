package com.shoppinglist.shoppinglist.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;
import static jakarta.persistence.TemporalType.TIMESTAMP;

@Data
@Entity
public class ListItem {
    // Todo: Actually use validation in REST api and frontend
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer itemId;

    @NotBlank(message = "Name must not be blank.")
    @Size(min = 1, max = 100, message = "Name must be less than 100 characters.")
    private String name;

    @Min(value = 0, message = "Price must be at least 0.")
    @Max(value = 5000, message = "Price must be less than 5000.")
    private Double price;

    @Min(value = 1, message = "Quantity must be at least one.")
    @Max(value = 1000, message = "Quantity must be less than 1000.")
    private Integer quantity = 1;

    @CreationTimestamp
    @Temporal(TIMESTAMP)
    private LocalDateTime created;

    @UpdateTimestamp
    @Temporal(TIMESTAMP)
    private LocalDateTime updated;

    public String getLabel() {
        return String.format("%s (%s)", name, quantity);
    }

    public Double getTotal() {
        return price * quantity;
    }
}