package com.dandelion.backend.repository;

import com.dandelion.backend.entity.ItemImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<ItemImage, Integer> {
}
