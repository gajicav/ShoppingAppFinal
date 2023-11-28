package com.shoppinglist.rest.dto;

import com.shoppinglist.rest.persistence.SLEntity;
import com.shoppinglist.rest.persistence.SLRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class SLService {
    private final SLRepository lists;

    public SLService(final SLRepository lists) {
        this.lists = lists;
    }

    public void delete(final Long id) {
        lists.deleteById(id);
    }

    public SLRepresentation post(final SLInput input) {
        return createRepresentation(lists.save(new SLEntity(input)));
    }

    public SLRepresentation put(final SLInput input, final Long id) throws NoSuchElementException {
        return createRepresentation(lists.findById(id)
            .map(list -> lists.save(list.merge(input)))
            .orElseThrow());
    }
    public SLRepresentation one(final Long id) throws NoSuchElementException {
        return createRepresentation(lists.findById(id).orElseThrow());
    }

    public List<SLRepresentation> all() {
        return createCollection(lists.findAll());
    }

    public SLRepresentation find(final Long itemId) {
        return createRepresentation(lists.findByItemId(itemId).orElseThrow());
    }

    public SLRepresentation createRepresentation(final SLEntity entity) {
        return new SLRepresentation(
            entity,
            lists.countItemsById(entity.getId()),
            null
        );
    }

    public List<SLRepresentation> createCollection(@NonNull final List<SLEntity> entities) {
         return entities.stream().map(
            entity -> new SLRepresentation(
                entity,
                lists.countItemsById(entity.getId()),
                lists.getTotalById(entity.getId())
            )
        ).collect(Collectors.toList());
    }
}
