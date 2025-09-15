package com.railway.Railway.simulator;

import com.railway.Railway.model.Train;
import com.railway.Railway.services.BlockService;
import com.railway.Railway.services.SignalService;
import com.railway.Railway.services.TrainService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TrainSimulator {
    private final TrainService trainService;
    private final BlockService blockService;
    private final SignalService signalService;

    public TrainSimulator(TrainService trainService, BlockService blockService, SignalService signalService) {
        this.trainService = trainService;
        this.blockService = blockService;
        this.signalService = signalService;
    }

    // Simulate train movement along a route
    public void moveTrainAlongRoute(Train train, List<Long> routeBlockIds) throws InterruptedException {
        for (Long blockId : routeBlockIds) {

            // Wait until block is free
            // Wait until block is free and signal is green
            while (!blockService.isBlockFree(blockId) && !signalService.getSignalByBlock(blockId).isGreen()) {
                System.out.println("Train " + train.getTrainName() + " waiting for block " + blockId + " or signal");
                Thread.sleep(1000);
            }


            // Enter the block
            blockService.assignTrainToBlock(blockId, train.getId().toString());
            train.setCurrentBlock("Block-" + blockId);
            trainService.updateTrain(train);
            System.out.println("Train " + train.getTrainName() + " ENTERS block " + blockId);

            // Simulate train travel time
            Thread.sleep(5000); // 5 seconds per block (adjust as needed)

            // Leave the block
            blockService.freeBlock(blockId);
            System.out.println("Train " + train.getTrainName() + " LEAVES block " + blockId);
        }

        System.out.println("Train " + train.getTrainName() + " completed its route!");
    }
}
