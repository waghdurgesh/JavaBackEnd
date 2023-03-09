package com.app.service;

import java.util.List;
import java.util.Optional;

import com.app.dto.UpdateBatchDto;
import com.app.entity.Batch;

public interface IBatchService {

	List<Batch> getAll();

	List<Optional<Batch>> getByName(String batchName);

	Batch getByBatchId(Long batchId);

	Batch insertBatch(Batch transientBatch);

	String deleteBatch(Long id);

	Batch editBatch(Long batchId, UpdateBatchDto detachedBatch);
	
	

	
}
