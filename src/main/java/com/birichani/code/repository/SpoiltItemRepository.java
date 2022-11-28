package com.birichani.code.repository;

import com.birichani.code.model.SpoiltItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpoiltItemRepository extends JpaRepository<SpoiltItem,Long> {
}
