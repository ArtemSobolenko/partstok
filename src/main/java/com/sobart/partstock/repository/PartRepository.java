package com.sobart.partstock.repository;

import com.sobart.partstock.domain.Part;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PartRepository extends CrudRepository<Part, Long> {
    List<Part> findByNeed(boolean value);
}
