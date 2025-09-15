package com.railway.Railway.config;

import com.railway.Railway.model.Block;
import com.railway.Railway.model.Signal;
import com.railway.Railway.model.Train;
import com.railway.Railway.repositories.BlockRepository;
import com.railway.Railway.repositories.SignalRepository;
import com.railway.Railway.repositories.TrainRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseInitializer implements CommandLineRunner {
    private final BlockRepository blockRepository;
    private final SignalRepository signalRepository;
    private final TrainRepository trainRepository;

    public DatabaseInitializer(BlockRepository blockRepository,
                               SignalRepository signalRepository,
                               TrainRepository trainRepository) {
        this.blockRepository = blockRepository;
        this.signalRepository = signalRepository;
        this.trainRepository = trainRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (blockRepository.count() == 0 && signalRepository.count() == 0 && trainRepository.count() == 0) {
            System.out.println("🚀 Initializing sample database...");

            // Create Blocks
            Block block1 = new Block();
            block1.setBlockName("A-B");
            block1.setOccupied(false);
            blockRepository.save(block1);

            Block block2 = new Block();
            block2.setBlockName("B-C");
            block2.setOccupied(false);
            blockRepository.save(block2);

            // Create Signals
            Signal signal1 = new Signal();
            signal1.setSignalName("Signal-AB");
            signal1.setGreen(true); // initially green
            signal1.setBlock(block1);
            signalRepository.save(signal1);

            Signal signal2 = new Signal();
            signal2.setSignalName("Signal-BC");
            signal2.setGreen(false); // initially red
            signal2.setBlock(block2);
            signalRepository.save(signal2);

            // Create Trains
            Train train1 = new Train();
            train1.setTrainName("Express-101");
            train1.setCurrentBlock("Depot");
            trainRepository.save(train1);

            Train train2 = new Train();
            train2.setTrainName("Passenger-202");
            train2.setCurrentBlock("Depot");
            trainRepository.save(train2);

            System.out.println("Sample data inserted into database.");
        } else {
            System.out.println("Database already contains data. Skipping initialization.");
        }
    }
}
