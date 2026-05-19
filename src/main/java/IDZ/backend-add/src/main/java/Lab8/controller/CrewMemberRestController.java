package Lab8.controller;

import Lab8.entity.CrewMember;
import Lab8.service.CrewMemberService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// REST-контролер для сутності Член екіпажу (CrewMember)
@RestController
@RequestMapping("/api/crew")
public class CrewMemberRestController {

    private final CrewMemberService crewMemberService;

    public CrewMemberRestController(CrewMemberService crewMemberService) {
        this.crewMemberService = crewMemberService;
    }

    // GET /api/crew - всі члени екіпажу
    @GetMapping
    public ResponseEntity<List<CrewMember>> getAll() {
        return ResponseEntity.ok(crewMemberService.findAll());
    }

    // GET /api/crew/{id}
    @GetMapping("/{id}")
    public ResponseEntity<CrewMember> getById(@PathVariable Long id) {
        return ResponseEntity.ok(crewMemberService.findById(id));
    }

    // POST /api/crew - створити нового
    @PostMapping
    public ResponseEntity<CrewMember> create(@Valid @RequestBody CrewMember crewMember) {
        return ResponseEntity.status(201).body(crewMemberService.create(crewMember));
    }

    // PUT /api/crew/{id}
    @PutMapping("/{id}")
    public ResponseEntity<CrewMember> update(@PathVariable Long id, @Valid @RequestBody CrewMember crewMember) {
        return ResponseEntity.ok(crewMemberService.update(id, crewMember));
    }

    // DELETE /api/crew/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        crewMemberService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
