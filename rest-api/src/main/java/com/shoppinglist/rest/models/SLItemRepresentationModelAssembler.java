package com.shoppinglist.rest.models;

import com.shoppinglist.rest.controllers.SLController;
import com.shoppinglist.rest.persistence.SLItemEntity;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.BasicLinkBuilder;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class SLItemRepresentationModelAssembler extends RepresentationModelAssemblerSupport<SLItemEntity, SLItemModel> {
    SLItemRepresentationModelAssembler() {
        super(SLController.class, SLItemModel.class);
    }

    @Override
    @NonNull
    public SLItemModel toModel(@NonNull final SLItemEntity entity) {
        final String self = BasicLinkBuilder.linkToCurrentMapping()
            + "/api/items/"
            + entity.getId();

        return instantiateModel(entity).add(
            Link.of(self, IanaLinkRelations.SELF),
            Link.of(self + "/list", "list")
        );
    }

    @Override
    @NonNull
    protected SLItemModel instantiateModel(@NonNull final SLItemEntity entity) {
        return new SLItemModel(entity);
    }
}
