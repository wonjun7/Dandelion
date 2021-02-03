package com.dandelion.backend.service;

import com.dandelion.backend.entity.Locker;
import com.dandelion.backend.repository.LockerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class LockerService {
    @Autowired
    LockerRepository lockerRepository;

    public Locker bringUnusedLocker() {
        return lockerRepository.unUsedLocker().get(0);
    }

    public Locker save (Locker locker) {
        return lockerRepository.save(locker);
    }

    public Locker getOne(int locker_number) {
        return lockerRepository.getOne(locker_number);
    }
}
