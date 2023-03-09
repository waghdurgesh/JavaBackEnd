package com.app.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.custom_exception.ResourceNotFoundException;
import com.app.dto.UpdateBatchDto;
import com.app.entity.Batch;
import com.app.repository.IBatchRepository;

@Service
@Transactional
public class BatchServiceImpl implements IBatchService {
	@Autowired
	private IBatchRepository batchRepo;

	@Override
	public List<Batch> getAll() {
		return batchRepo.findAll();
	}

	@Override
	public Batch getByBatchId(Long batchId) {
		if (batchRepo.existsById(batchId)) {
			return batchRepo.findById(batchId).get();
		}
		throw new ResourceNotFoundException("Invalid Batch Id : Batch Not Found");
	}

	@Override
	public List<Optional<Batch>> getByName(String batchName) {
		if ((batchRepo.findByBatchName(batchName)).isEmpty()) {
			throw new ResourceNotFoundException("Invalid Batch Name : Batch Not Found");
		}
		return batchRepo.findByBatchName(batchName);

	}

	@Override
	public Batch insertBatch(Batch traBatch) {
		if (batchRepo.existsById(traBatch.getBatchId())) {
			throw new ResourceNotFoundException("Batch Id Already Used: Change Batch Id");
		}
		return batchRepo.save(traBatch);
	}

	@Override
	public String deleteBatch(Long id) {
		if (batchRepo.existsById(id)) {
			batchRepo.deleteById(id);
			return "Batch Succesfully Deleted";
		}
		return "Batch Deletion Failed : Invalid Id";
	}

	@Override
	public Batch editBatch(Long batchId, UpdateBatchDto detachedBatch) {
		if (batchRepo.existsById(batchId)) {
			Batch upBatch = getByBatchId(batchId);
			upBatch.setBatchName(detachedBatch.getBatchName());
			upBatch.setBatchDescription(detachedBatch.getBatchDescription());
			return batchRepo.save(upBatch);
		} else
			throw new ResourceNotFoundException("Invalid Batch Id : Updation Failed");
	}
}
