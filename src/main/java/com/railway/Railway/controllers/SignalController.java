package com.railway.Railway.controllers;

import com.railway.Railway.model.Signal;
import com.railway.Railway.services.SignalService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/signals")
public class SignalController {
    private final SignalService signalService;

    public SignalController(SignalService signalService) {
        this.signalService = signalService;
    }

    // Get all signals
    @GetMapping
    public List<Signal> getAllSignals() {
        return signalService.getAllSignals();
    }

    // Get signal by ID
    @GetMapping("/{id}")
    public Signal getSignalById(@PathVariable Long id) {
        return signalService.getSignalById(id)
                .orElseThrow(() -> new RuntimeException("Signal not found with id " + id));
    }

    // Create new signal
    @PostMapping
    public Signal createSignal(@RequestBody Signal signal) {
        return signalService.createSignal(signal);
    }

    // Update signal
    @PutMapping("/{id}")
    public Signal updateSignal(@PathVariable Long id, @RequestBody Signal signal) {
        signal.setId(id);
        return signalService.updateSignal(signal);
    }

    // Delete signal
    @DeleteMapping("/{id}")
    public void deleteSignal(@PathVariable Long id) {
        signalService.deleteSignal(id);
    }

    // ✅ Get signal by Block ID
    @GetMapping("/block/{blockId}")
    public Signal getSignalByBlock(@PathVariable Long blockId) {
        return signalService.getSignalByBlock(blockId);
    }

    // ✅ Change signal state (set to green/red)
    @PutMapping("/{id}/state")
    public Signal setSignalState(@PathVariable Long id, @RequestParam boolean isGreen) {
        return signalService.setSignalState(id, isGreen);
    }
}
