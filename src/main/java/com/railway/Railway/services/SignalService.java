package com.railway.Railway.services;

import com.railway.Railway.model.Signal;
import com.railway.Railway.repositories.SignalRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SignalService {
    private final SignalRepository signalRepository;

    public SignalService(SignalRepository signalRepository) {
        this.signalRepository = signalRepository;
    }

    // CRUD operations
    public List<Signal> getAllSignals() {
        return signalRepository.findAll();
    }

    public Optional<Signal> getSignalById(Long id) {
        return signalRepository.findById(id);
    }

    public Signal createSignal(Signal signal) {
        return signalRepository.save(signal);
    }

    public Signal updateSignal(Signal signal) {
        return signalRepository.save(signal);
    }

    public void deleteSignal(Long id) {
        signalRepository.deleteById(id);
    }

    // Change signal state
    public Signal setSignalState(Long id, boolean isGreen) {
        Signal signal = signalRepository.findById(id).orElseThrow(() -> new RuntimeException("Signal not found with id " + id));
        signal.setGreen(isGreen);
        return signalRepository.save(signal);
    }

    public Signal getSignalByBlock(Long blockId) {
        return signalRepository.getSignalByBlock(blockId);
    }
}
