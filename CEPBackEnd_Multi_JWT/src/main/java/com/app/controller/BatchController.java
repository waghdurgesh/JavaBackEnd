package com.app.controller;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.ApiResponse;
import com.app.dto.CreateBatchDto;
import com.app.dto.UpdateBatchDto;
import com.app.entity.Batch;
import com.app.service.IBatchService;

@RestController
@RequestMapping("/batch")
public class BatchController {

	@Autowired
	IBatchService batchService;

	@Autowired
	private ModelMapper mapper;

	public BatchController() {
	}

	@GetMapping
	public List<Batch> getAll() {
		return batchService.getAll();
	}

	@GetMapping("/findbyname/{batchName}")
	public List<Optional<Batch>> getDetailsByName(@PathVariable String batchName) {
		return batchService.getByName(batchName);
	}

	@GetMapping("/findbyid/{batchId}")
	public ResponseEntity<?> getDetailsById(@PathVariable long batchId) {
		try {
			return new ResponseEntity<>(batchService.getByBatchId(batchId), HttpStatus.OK);
		} catch (RuntimeException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/create")
	public Batch addBatch(@RequestBody CreateBatchDto traBatch) {
		Batch newBatch = mapper.map(traBatch, Batch.class);
		return batchService.insertBatch(newBatch);
	}

	@DeleteMapping("/remove/{batchId}")
	public ApiResponse removeBatch(@PathVariable Long batchId) {
		return new ApiResponse(batchService.deleteBatch(batchId));
	}

	@PatchMapping("/update/{batchId}")
	public Batch updateBatch(@PathVariable Long batchId, @RequestBody UpdateBatchDto detBatch) {
		return batchService.editBatch(batchId, detBatch);
	}

}
