package ma.projet.controller;

import java.util.List;

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

import ma.projet.entities.Filiere;
import ma.projet.entities.Student;
import ma.projet.service.StudentService;

@RestController
@RequestMapping("/api/v1/Students")
public class StudentController {

	@Autowired
	private StudentService service;

	@GetMapping
	public List<Student> findAllStudent() {
		return service.findAll();
	}

	@PostMapping
	public Student createStudent(@RequestBody Student Student) {
		Student.setId(0);
		return service.create(Student);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> findById(@PathVariable int id) {
		Student Student = service.findById(id);
		if (Student == null) {
			return new ResponseEntity<Object>("Student avec ID = " + id + " n'existe pas", HttpStatus.BAD_REQUEST);
		} else {
			return ResponseEntity.ok(Student);
		}
	}
	@GetMapping("/filieres")
	public List<Student> findById(@RequestBody Filiere filiere ) {
		return service.findbyFiliere(filiere);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Object> updateStudent(@PathVariable int id, @RequestBody Student newStudent) {
		Student oldStudent = service.findById(id);
		if (oldStudent == null) {
			return new ResponseEntity<Object>("Student avec ID = " + id + " n'existe pas", HttpStatus.BAD_REQUEST);
		} else {
			newStudent.setId(id);
			return ResponseEntity.ok(service.update(newStudent));
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteStudent(@PathVariable int id) {
		Student Student = service.findById(id);
		if (Student == null) {
			return new ResponseEntity<Object>("Student avec ID = " + id + " n'existe pas", HttpStatus.BAD_REQUEST);
		} else {
			service.delete(Student);
			return ResponseEntity.ok("etudiant supprimée");
		}
	}
	
}
