package com.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entity.Batch;

public interface IBatchRepository extends JpaRepository<Batch, Long> {

	List<Optional<Batch>>findByBatchName(String batchName);
}
