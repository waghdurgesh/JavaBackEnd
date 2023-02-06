package com.app.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.custom_exceptions.ResourceNotFoundException;
import com.app.dto.EmployeeResponse;
import com.app.dto.EmployeeSpecificResp;
import com.app.dto.LoginRequestDto;
import com.app.pojos.Employee;
import com.app.repository.EmployeeRepository;

@Service
@Transactional
public class EmployeeServiceImpl implements IEmployeeService {
	// dependency DAO layer interface
	@Autowired
	private EmployeeRepository empRepository;
	// dependency Model mapper
	@Autowired
	private ModelMapper mapper;

	@PostConstruct
	public void init() {
		System.out.println("***in init***" + mapper);
	}

	@Override
	public List<Employee> getAllEmployee() {
		return empRepository.findAll();
	}

	@Override
	public Employee addNewEmployee(Employee emp) {
		return empRepository.save(emp);
	}

	@Override
	public String deleteEmployee(Long id) {
		if (empRepository.existsById(id)) {
			empRepository.deleteById(id);
			return "Emp details deleted ....";
		}
		return "Deletion Failed : Invalid Emp Id !!!!!!!!!!!";
	}
	// update method using response entity
//	@Override
//	public ResponseEntity<Employee> editEmployee(@PathVariable Long id, @RequestBody Employee emp) {
//		// create employee instance to save data gain by find id
//		Employee updtemp = empRepository.findById(id).orElseThrow();
//		// update gain data by suing setters supplied by getter from parameter
//		updtemp.setFirstname(emp.getFirstname());
//		updtemp.setLastname(emp.getLastname());
//		updtemp.setSalary(emp.getSalary());
//		updtemp.setDepartment(emp.getDepartment());
//		updtemp.setWorkLocation(emp.getWorkLocation());
//		updtemp.setEmail(emp.getEmail());
//		updtemp.setPassword(emp.getPassword());
//		// save data
//		empRepository.save(updtemp);
//		// return to
//		return ResponseEntity.ok(updtemp);
//	}

	@Override
	public Employee updateEmployeeDetails(Employee detachedEmp) {

		if (empRepository.existsById(detachedEmp.getId())) {
			return empRepository.save(detachedEmp);
		}
		throw new ResourceNotFoundException("Invalid Emp Id : Updation Failed!!!!!!!!");
	}

	@Override
	public List<EmployeeResponse> getEmpsBySalary(double minSal, double maxSal) {
		// entity to dto conversion is needed

//		return empRepository.fetchEmpNamesBySalaryRange(minSal, maxSal).//Stream<Emp>
//				map(e -> new EmployeeResponse(e.getFirstname(),e.getLastname())) //Stream<EmpResp>
//				.collect(Collectors.toList());
		return empRepository.fetchEmpNamesBySalaryRange(minSal, maxSal). // stream of emp
				map(e -> mapper.map(e, EmployeeResponse.class)) // Entity --> DTO
				.collect(Collectors.toList());
	}

	@Override
	public Optional<Employee> getEmpById(Long id) {
		if (empRepository.existsById(id)) {
			return empRepository.findById(id);
		}
		throw new ResourceNotFoundException("Invalid Emp Id : Search Failed!!!!!!!!");
	}

	@Override
	public List<EmployeeResponse> getEmpsByFirstName(String firstname) {

		return empRepository.fetchEmpByFirstname(firstname).map(e -> mapper.map(e, EmployeeResponse.class))
				.collect(Collectors.toList());
	}

	@Override
	public List<Employee> getAllDetailsByFirstName(String firstname) {

		return empRepository.fetchEmpAllDetailsByFirstname(firstname);
	}

	@Override
	public EmployeeSpecificResp authenticateEmp(LoginRequestDto dto) {
		// TODO Auto-generated method stub
		Employee employee = empRepository.findByEmailAndPassword(dto.getEmail(), dto.getPassword())
				.orElseThrow(() -> new ResourceNotFoundException("Bad Credentials !!!!!"));
		// => success
		return mapper.map(employee, EmployeeSpecificResp.class);

	}
}
