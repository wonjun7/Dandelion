package com.dandelion.backend.repository;

import com.dandelion.backend.entity.Locker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LockerRepository extends JpaRepository<Locker, Integer> {

    @Query(value = "select * from locker where is_use = 0", nativeQuery = true)
    List<Locker> unUsedLocker();

}
