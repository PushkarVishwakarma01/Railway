package com.railway.Railway.controllers;

import com.railway.Railway.model.Train;
import com.railway.Railway.services.TrainService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/trains")
@CrossOrigin(origins = "http://localhost:3000")
public class TrainController {
    private final TrainService trainService;

    public TrainController(TrainService trainService) {
        this.trainService = trainService;
    }

    // GET all trains
    @GetMapping
    public List<Train> getAllTrains() {
        return trainService.getAllTrains();
    }

    // GET train by ID
    @GetMapping("/{id}")
    public ResponseEntity<Train> getTrainById(@PathVariable Long id) {
        Optional<Train> train = trainService.getTrainById(id);
        return train.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // POST create new train
    @PostMapping
    public Train createTrain(@RequestBody Train train) {
        return trainService.createTrain(train);
    }

    // PUT update train
    @PutMapping("/{id}")
    public ResponseEntity<Train> updateTrain(@PathVariable Long id, @RequestBody Train trainDetails) {
        Optional<Train> existingTrain = trainService.getTrainById(id);
        if (existingTrain.isPresent()) {
            Train train = existingTrain.get();
            train.setTrainName(trainDetails.getTrainName());
            train.setCurrentBlock(trainDetails.getCurrentBlock());
            train.setDepartureTime(trainDetails.getDepartureTime());
            train.setArrivalTime(trainDetails.getArrivalTime());
            train.setDelayMinutes(trainDetails.getDelayMinutes());

            Train updatedTrain = trainService.updateTrain(train);
            return ResponseEntity.ok(updatedTrain);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE train
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrain(@PathVariable Long id) {
        Optional<Train> existingTrain = trainService.getTrainById(id);
        if (existingTrain.isPresent()) {
            trainService.deleteTrain(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
