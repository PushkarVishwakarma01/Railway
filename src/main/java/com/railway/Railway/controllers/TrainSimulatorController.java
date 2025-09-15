package com.railway.Railway.controllers;

import com.railway.Railway.model.Train;
import com.railway.Railway.services.TrainService;
import com.railway.Railway.simulator.TrainSimulator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/simulator")
@CrossOrigin(origins = "http://localhost:3000")
public class TrainSimulatorController {
//    private final TrainService trainService;
//    private final TrainSimulator trainSimulator;
//
//    public TrainSimulatorController(TrainService trainService, TrainSimulator trainSimulator) {
//        this.trainService = trainService;
//        this.trainSimulator = trainSimulator;
//    }
//
//    // Start simulation for a train with a route
//    @PostMapping("/start/{trainId}")
//    public ResponseEntity<String> startSimulation(
//            @PathVariable Long trainId,
//            @RequestBody List<Long> routeBlockIds) {
//
//        Optional<Train> trainOpt = trainService.getTrainById(trainId);
//
//        if (trainOpt.isEmpty()) {
//            return ResponseEntity.notFound().build();
//        }
//
//        Train train = trainOpt.get();
//
//        // Run simulation in a new thread to avoid blocking
//        new Thread(() -> {
//            try {
//                trainSimulator.moveTrainAlongRoute(train, routeBlockIds);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }).start();
//
//        return ResponseEntity.ok("Simulation started for train " + train.getTrainName());
//    }

}
