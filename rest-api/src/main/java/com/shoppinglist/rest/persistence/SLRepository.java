package com.shoppinglist.rest.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SLRepository extends JpaRepository<SLEntity, Long> {
    @Query("select i.list from SLItemEntity i where i.id = ?1")
    Optional<SLEntity> findByItemId(Long id);
}
