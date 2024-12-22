package main.java.com.example.repository;

import com.example.model.Gasto;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GastoRepository {

    private static final String ARCHIVO_JSON = "data.json";
    private final ObjectMapper objectMapper = new ObjectMapper();
    private List<Gasto> gastos = new ArrayList<>();

    public GastoRepository() {
        try {
            File file = new File(ARCHIVO_JSON);
            if (file.exists()) {
                gastos = objectMapper.readValue(file, new TypeReference<List<Gasto>>() {});
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // CRUD
    public void create(Gasto gasto) {
        gastos.add(gasto);
        guardarCambios();
    }

    public Gasto read(int id) {
        return gastos.stream().filter(g -> g.getId() == id).findFirst().orElse(null);
    }

    public void update(Gasto gasto) {
        Gasto gastoExistente = read(gasto.getId());
        if (gastoExistente != null) {
            gastos.remove(gastoExistente);
            gastos.add(gasto);
            guardarCambios();
        }
    }

    public void delete(int id) {
        gastos.removeIf(g -> g.getId() == id);
        guardarCambios();
    }

    public List<Gasto> getAll() {
        return new ArrayList<>(gastos);
    }

    // Operaciones adicionales
    public double calcularPromedio() {
        if (gastos.isEmpty()) {
            return 0.0;
        }
        return gastos.stream().mapToDouble(Gasto::getMonto).average().getAsDouble();
    }

    public List<Gasto> filtrarPorFecha(long fechaInicio, long fechaFin) {
        return gastos.stream()
                .filter(g -> g.getFecha() >= fechaInicio && g.getFecha() <= fechaFin)
                .collect(Collectors.toList());
    }

    private void guardarCambios() {
        try {
            objectMapper.writeValue(new File(ARCHIVO_JSON), gastos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}