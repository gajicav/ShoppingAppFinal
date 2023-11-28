package com.shoppinglist.rest.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface SLItemRepository extends JpaRepository<SLItemEntity, Long> {
    @Query("select i.items from SLEntity i where i.id = ?1")
    Optional<List<SLItemEntity>> findByListId(Long id);

    @Query("select i.quantity * i.price from SLItemEntity i where i.id = ?1")
    BigDecimal getTotalById(Long id);
}
