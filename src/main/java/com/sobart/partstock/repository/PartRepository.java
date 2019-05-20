package com.sobart.partstock.repository;

import com.sobart.partstock.domain.Part;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface PartRepository extends CrudRepository<Part, Long> {

    Page<Part> findByNeed(boolean value, Pageable pageable);

    Page<Part> findAll(Pageable pageable);

    Part findById(long id);

}
