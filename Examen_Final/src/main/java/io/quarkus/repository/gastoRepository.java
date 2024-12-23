package io.quarkus.repository;
import io.quarkus.model.Gasto;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GastoRepository {
    private static final String FILE_PATH = "src/main/resources/data.json";
    private ObjectMapper objectMapper = new ObjectMapper();

    // Leer gastos
    public List<Gasto> getAllGastos() throws IOException {
        return Arrays.asList(objectMapper.readValue(new File(FILE_PATH), Gasto[].class));
    }

    // Gasto por ID
    public Gasto getGastoById(int id) throws IOException {
        List<Gasto> gastos = getAllGastos();
        return gastos.stream().filter(gasto -> gasto.getId() == id).findFirst().orElse(null);
    }

    // Agregar un nuevo gasto
    public void addGasto(Gasto gasto) throws IOException {
        List<Gasto> gastos = getAllGastos();
        gastos.add(gasto);
        saveGastos(gastos);
    }

    // Actualizar gasto existente
    public void updateGasto(int id, Gasto updatedGasto) throws IOException {
        List<Gasto> gastos = getAllGastos();
        for (Gasto gasto : gastos) {
            if (gasto.getId() == id) {
                gasto.setDescripcion(updatedGasto.getDescripcion());
                gasto.setFecha(updatedGasto.getFecha());
                gasto.setMonto(updatedGasto.getMonto());
                saveGastos(gastos);
                return;
            }
        }
    }

    // Eliminar un gasto por ID
    public void deleteGasto(int id) throws IOException {
        List<Gasto> gastos = getAllGastos();
        gastos.removeIf(gasto -> gasto.getId() == id);
        saveGastos(gastos);
    }

    // Calcular  gastos
    public double getPromedio() throws IOException {
        List<Gasto> gastos = getAllGastos();
        return gastos.stream().mapToDouble(Gasto::getMonto).average().orElse(0.0);
    }

    
    public List<Gasto> filterGastosByDateRange(long inicio, long fin) throws IOException {
        List<Gasto> gastos = getAllGastos();
        List<Gasto> filtered = new ArrayList<>();
        for (Gasto gasto : gastos) {
            if (gasto.getFecha() >= inicio && gasto.getFecha() <= fin) {
                filtered.add(gasto);
            }
        }
        return filtered;
    }

    // Guardar gastos actualizados en el archivo 
    private void saveGastos(List<Gasto> gastos) throws IOException {
        objectMapper.writeValue(new File(FILE_PATH), gastos);
    }
}