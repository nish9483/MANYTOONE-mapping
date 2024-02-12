package com.example.demo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController

public class StudentController {
	@Autowired
	private StudentRepo studentRepo;
	
	
	@PostMapping("/api/student")
	public ResponseEntity<Student>saveStudent(@RequestBody Student student)
	{
		Student a=studentRepo.save(student);
		return new ResponseEntity<Student>(a,HttpStatus.CREATED);
	}
	
	@GetMapping("/api/student")
	public ResponseEntity<List<Student>>getAllstudents()
	{
		List<Student>ad=studentRepo.findAll();
		return new ResponseEntity<List<Student>>(ad,HttpStatus.OK);
	}
	
	@GetMapping("/api/student/{id}")
	public ResponseEntity<Student>getStudentById(@PathVariable int id)
	{
		Optional<Student>ads=studentRepo.findById(id);
		if(ads.isPresent())
		{
			return new ResponseEntity<Student>(ads.get(),HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<Student>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("/api/astudent/{id}")
	public ResponseEntity<Student>updateStudent(@PathVariable int id,@RequestBody Student updatedStudent)
	{
		Optional<Student>ads=studentRepo.findById(id);
		
		if(ads.isPresent())
		{
			Student student=ads.get();
			student.setName(updatedStudent.getName());
			student.setDob(updatedStudent.getDob());
			student.setGender(updatedStudent.getGender());
			
			Student adss=studentRepo.save(student);
			return new ResponseEntity<Student>(adss,HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<Student>(HttpStatus.NOT_FOUND);
		}
	}

	
	@DeleteMapping("/api/student/{id}")
	public ResponseEntity<Void>deleteStudent(@PathVariable int id)
	{
		Optional<Student> address=studentRepo.findById(id);
		
		if(address.isPresent())
		{
			studentRepo.deleteById(id);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}
}


