package com.railway.Railway.services;

import com.railway.Railway.model.Block;
import com.railway.Railway.repositories.BlockRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BlockService {
    private final BlockRepository blockRepository;

    public BlockService(BlockRepository blockRepository) {
        this.blockRepository = blockRepository;
    }

    // Fetch all blocks
    public List<Block> getAllBlocks() {
        return blockRepository.findAll();
    }

    // Fetch block by ID
    public Optional<Block> getBlockById(Long id) {
        return blockRepository.findById(id);
    }

    // Create new block
    public Block createBlock(Block block) {
        return blockRepository.save(block);
    }

    // Update block (e.g., assign train or free block)
    public Block updateBlock(Block block) {
        return blockRepository.save(block);
    }

    // Delete block
    public void deleteBlock(Long id) {
        blockRepository.deleteById(id);
    }

    // Custom logic: check if block is free
    public boolean isBlockFree(Long id) {
        Optional<Block> block = blockRepository.findById(id);
        return block.map(b -> !b.isOccupied()).orElse(false);
    }

    // Assign train to block
    public Block assignTrainToBlock(Long blockId, String trainId) {
        Optional<Block> block = blockRepository.findById(blockId);
        if (block.isPresent()) {
            Block b = block.get();
            b.setOccupied(true);
            b.setTrainId(trainId);
            return blockRepository.save(b);
        } else {
            throw new RuntimeException("Block not found");
        }
    }

    // Free a block
    public Block freeBlock(Long blockId) {
        Optional<Block> block = blockRepository.findById(blockId);
        if (block.isPresent()) {
            Block b = block.get();
            b.setOccupied(false);
            b.setTrainId(null);
            return blockRepository.save(b);
        } else {
            throw new RuntimeException("Block not found");
        }
    }
}
