package com.shoppinglist.rest.persistence;

import com.shoppinglist.rest.input.SLInput;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class SLEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "list", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SLItemEntity> items = new ArrayList<>();

    public SLEntity(String description, SLItemEntity... items) {
        this.description = description;
        this.items = List.of(items);
        this.items.forEach(item -> item.setList(this));
    }

    public SLEntity(SLInput input) {
        merge(input);
    }

    /*
    Need to fix this later for lazy loading on the items collection

    private BigDecimal getTotal() {
        return items.stream()
            .map(SLItemEntity::getTotal)
            .reduce(BigDecimal::add)
            .orElse(BigDecimal.ZERO);
    } */

    public SLEntity merge(@NonNull SLInput input) {
        this.description = input.description();
        return this;
    }
}
