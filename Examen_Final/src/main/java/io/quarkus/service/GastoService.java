package main.java.com.example.service;

import com.example.model.Gasto;
import com.example.repository.GastoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/gastos")
public class GastoService {

    @Autowired
    private GastoRepository gastoRepository;

    @GetMapping
    public ResponseEntity<List<Gasto>> getAllGastos() {
        return ResponseEntity.ok(gastoRepository.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Gasto> getGastoById(@PathVariable int id) {
        Gasto gasto = gastoRepository.read(id);
        return gasto != null ? ResponseEntity.ok(gasto) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Gasto> createGasto(@RequestBody Gasto gasto) {
        gastoRepository.create(gasto);
        return ResponseEntity.status(HttpStatus.CREATED).body(gasto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Gasto> updateGasto(@PathVariable int id, @RequestBody Gasto gasto) {
        gasto.setId(id);
        gastoRepository.update(gasto);
        return ResponseEntity.ok(gasto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGasto(@PathVariable int id) {
        gastoRepository.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/promedio")
    public ResponseEntity<Double> getPromedio() {
        return ResponseEntity.ok(gastoRepository.calcularPromedio());
    }

    @GetMapping("/rango")
    public ResponseEntity<List<Gasto>> getGastosPorRango(
            @RequestParam(value = "inicio", required = false) Long inicio,
            @RequestParam(value = "fin", required = false) Long fin) {
        // Convertir fechas en formato "yyyy-MM-dd" a timestamp si es necesario
        // ...

        return ResponseEntity.ok(gastoRepository.filtrarPorFecha(inicio, fin));
    }
}