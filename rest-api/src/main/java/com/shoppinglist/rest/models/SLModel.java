package com.shoppinglist.rest.models;

import com.shoppinglist.rest.persistence.SLEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;
import org.springframework.lang.NonNull;

@Data
@EqualsAndHashCode(callSuper = false)
@Relation(collectionRelation = "lists", itemRelation = "list")
public class SLModel extends RepresentationModel<SLModel> {
    private final Long id;
    private final String description;
    private final Long count;

    SLModel(@NonNull SLEntity list, Long count) {
        this.id = list.getId();
        this.description = list.getDescription();
        this.count = count;
    }
}
