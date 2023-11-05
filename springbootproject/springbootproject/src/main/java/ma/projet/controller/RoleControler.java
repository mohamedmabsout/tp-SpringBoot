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

import ma.projet.entities.Role;
import ma.projet.service.RoleService;

@RestController
@RequestMapping("/api/v1/Roles")
public class RoleControler {

	@Autowired
	private RoleService service;

	@GetMapping
	public List<Role> findAllRole() {
		return service.findAll();
	}

	@PostMapping
	public Role createRole(@RequestBody Role Role) {
		Role.setId(0);
		return service.create(Role);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> findById(@PathVariable int id) {
		Role Role = service.findById(id);
		if (Role == null) {
			return new ResponseEntity<Object>("Role avec ID = " + id + " n'existe pas", HttpStatus.BAD_REQUEST);
		} else {
			return ResponseEntity.ok(Role);
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<Object> updateRole(@PathVariable int id, @RequestBody Role newRole) {
		Role oldRole = service.findById(id);
		if (oldRole == null) {
			return new ResponseEntity<Object>("Role avec ID = " + id + " n'existe pas", HttpStatus.BAD_REQUEST);
		} else {
			newRole.setId(id);
			return ResponseEntity.ok(service.update(newRole));
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteRole(@PathVariable int id) {
		Role Role = service.findById(id);
		if (Role == null) {
			return new ResponseEntity<Object>("Role avec ID = " + id + " n'existe pas", HttpStatus.BAD_REQUEST);
		} else {
			service.delete(Role);
			return ResponseEntity.ok("filière supprimée");
		}
	}
	
}
