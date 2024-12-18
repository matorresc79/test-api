package com.idi.backend.adpters.output;


import org.springframework.data.repository.CrudRepository;

import com.idi.backend.adpters.output.entity.LibroEntity;

public interface LibroJpaRepository extends CrudRepository<LibroEntity, Long> {
}
