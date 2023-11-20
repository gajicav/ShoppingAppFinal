package com.shoppinglist.rest.models;

import com.shoppinglist.rest.controllers.SLController;
import com.shoppinglist.rest.persistence.SLEntity;
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

        return instantiateModel(entity).add(
            Link.of(self, IanaLinkRelations.SELF),
            Link.of(self + "/items", "items")
        );
    }

    @Override
    @NonNull
    protected SLModel instantiateModel(@NonNull final SLEntity entity) {
        final var query = entityManager.createQuery("select count(*) from SLItemEntity i where i.list.id = ?1");
        query.setParameter(1, entity.getId());
        return new SLModel(entity, (Long) query.getSingleResult());
    }
}
