package com.railway.Railway.services;

import com.railway.Railway.model.Train;
import com.railway.Railway.repositories.TrainRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrainService {
    private final TrainRepository trainRepository;

    public TrainService(TrainRepository trainRepository) {
        this.trainRepository = trainRepository;
    }

    // Fetch all trains
    public List<Train> getAllTrains() {
        return trainRepository.findAll();
    }

    // Fetch a train by ID
    public Optional<Train> getTrainById(Long id) {
        return trainRepository.findById(id);
    }

    // Save a new train
    public Train createTrain(Train train) {
        return trainRepository.save(train);
    }

    // Update existing train
    public Train updateTrain(Train train) {
        return trainRepository.save(train);
    }

    // Delete train by ID
    public void deleteTrain(Long id) {
        trainRepository.deleteById(id);
    }
}
