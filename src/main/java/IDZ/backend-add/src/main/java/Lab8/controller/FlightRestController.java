package Lab8.controller;

import Lab8.dto.FlightDto;
import Lab8.service.FlightService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// REST-контролер для сутності Рейс (Flight)
@RestController
@RequestMapping("/api/flights")
public class FlightRestController {

    private final FlightService flightService;

    public FlightRestController(FlightService flightService) {
        this.flightService = flightService;
    }

    // отримання списку всіх рейсів - GET /api/flights
    @GetMapping
    public ResponseEntity<List<FlightDto>> getAll() {
        return ResponseEntity.ok(flightService.findAll());
    }

    // отримання одного рейсу - GET /api/flights/{id}
    @GetMapping("/{id}")
    public ResponseEntity<FlightDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(flightService.findById(id));
    }

    // створення нового рейсу - POST /api/flights
    @PostMapping
    public ResponseEntity<FlightDto> create(@Valid @RequestBody FlightDto dto) {
        FlightDto created = flightService.create(dto);
        return ResponseEntity.status(201).body(created);
    }

    // оновлення рейсу - PUT /api/flights/{id}
    @PutMapping("/{id}")
    public ResponseEntity<FlightDto> update(@PathVariable Long id, @Valid @RequestBody FlightDto dto) {
        return ResponseEntity.ok(flightService.update(id, dto));
    }

    // видалення рейсу - DELETE /api/flights/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        flightService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
