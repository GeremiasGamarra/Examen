package io.quarkus.service;

import io.quarkus.model.Gasto;
import io.quarkus.repository.GastoRepository;

import java.io.IOException;
import java.util.List;

public class GastoService {
    private GastoRepository gastoRepository = new GastoRepository();

    
    public List<Gasto> getAllGastos() throws IOException {
        return gastoRepository.getAllGastos();
    }

    
    public Gasto getGastoById(int id) throws IOException {
        return gastoRepository.getGastoById(id);
    }

   
    public void addGasto(Gasto gasto) throws IOException {
        gastoRepository.addGasto(gasto);
    }

    public void updateGasto(int id, Gasto updatedGasto) throws IOException {
        gastoRepository.updateGasto(id, updatedGasto);
    }

    
    public void deleteGasto(int id) throws IOException {
        gastoRepository.deleteGasto(id);
    }

  
    public double getPromedio() throws IOException {
        return gastoRepository.getPromedio();
    }


    public List<Gasto> filterGastosByDateRange(long inicio, long fin) throws IOException {
        return gastoRepository.filterGastosByDateRange(inicio, fin);
    }
}
