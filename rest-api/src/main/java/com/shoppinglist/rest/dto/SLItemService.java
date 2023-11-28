package com.shoppinglist.rest.dto;

import com.shoppinglist.rest.persistence.SLItemEntity;
import com.shoppinglist.rest.persistence.SLItemRepository;
import com.shoppinglist.rest.persistence.SLRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class SLItemService {
    private final SLRepository lists;
    private final SLItemRepository items;

    public SLItemService(SLItemRepository items, SLRepository lists) {
        this.items = items;
        this.lists = lists;
    }

    public void delete(final Long id) {
        items.deleteById(id);
    }

    public SLItemRepresentation post(final SLItemInput input) {
        return createRepresentation(items.save(new SLItemEntity(input)));
    }

    public SLItemRepresentation put(final SLItemInput input, final Long id) throws NoSuchElementException {
        return createRepresentation(items.findById(id)
            .map(list -> items.save(list.merge(input)))
            .orElseThrow());
    }
    public SLItemRepresentation one(final Long id) throws NoSuchElementException {
        return createRepresentation(items.findById(id).orElseThrow());
    }

    public SLItemRepresentation move(Long listId, Long id) throws NoSuchElementException {
        final var list = lists.findById(listId).orElseThrow();
        return createRepresentation(items.findById(id).map(item -> {
            item.setList(list);
            return items.save(item);
        }).orElseThrow());
    }

    public List<SLItemRepresentation> all() {
        return createCollection(items.findAll());
    }

    public List<SLItemRepresentation> find(Long listId) {
        return createCollection(items.findByListId(listId).orElseThrow());
    }

    public SLItemRepresentation createRepresentation(final SLItemEntity entity) {
        return new SLItemRepresentation(
            entity,
            null
        );
    }

    public List<SLItemRepresentation> createCollection(@NonNull final List<SLItemEntity> entities) {
         return entities.stream().map(
            entity -> new SLItemRepresentation(
                entity,
                items.getTotalById(entity.getId())
            )
        ).collect(Collectors.toList());
    }
}
