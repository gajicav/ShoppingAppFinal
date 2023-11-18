package com.shoppinglist.shoppinglist.models;

import com.shoppinglist.shoppinglist.controllers.SLController;
import com.shoppinglist.shoppinglist.persistence.SLEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.BasicLinkBuilder;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class SLRepresentationModelAssembler extends RepresentationModelAssemblerSupport<SLEntity, SLModel> {
    @PersistenceContext
    private EntityManager entityManager;

    SLRepresentationModelAssembler() {
        super(SLController.class, SLModel.class);
    }

    @Override
    @NonNull
    public SLModel toModel(@NonNull final SLEntity entity) {

        final String self = BasicLinkBuilder.linkToCurrentMapping()
            + "/api/lists/"
            + entity.getId();

        final String items = self + "/items";

        return instantiateModel(entity).add(
            Link.of(self, IanaLinkRelations.SELF),
            Link.of(items, "items")
        );
    }

    @Override
    @NonNull
    protected SLModel instantiateModel(@NonNull final SLEntity entity) {
        final var query = entityManager.createQuery("select count(i.id) from SLItemEntity i where i.list.id = ?1");
        query.setParameter(1, entity.getId());
        return new SLModel(entity, (Long) query.getSingleResult());
    }
}
