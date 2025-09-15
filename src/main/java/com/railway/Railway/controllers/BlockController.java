package com.railway.Railway.controllers;

import com.railway.Railway.model.Block;
import com.railway.Railway.services.BlockService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/blocks")
@CrossOrigin(origins = "http://localhost:3000")
public class BlockController {
    private final BlockService blockService;

    public BlockController(BlockService blockService) {
        this.blockService = blockService;
    }

    // GET all blocks
    @GetMapping
    public List<Block> getAllBlocks() {
        return blockService.getAllBlocks();
    }

    // GET block by ID
    @GetMapping("/{id}")
    public ResponseEntity<Block> getBlockById(@PathVariable Long id) {
        Optional<Block> block = blockService.getBlockById(id);
        return block.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // POST create new block
    @PostMapping
    public Block createBlock(@RequestBody Block block) {
        return blockService.createBlock(block);
    }

    // PUT assign train to block
    @PutMapping("/{id}/assign")
    public ResponseEntity<Block> assignTrain(@PathVariable Long id, @RequestParam String trainId) {
        try {
            Block updatedBlock = blockService.assignTrainToBlock(id, trainId);
            return ResponseEntity.ok(updatedBlock);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // PUT free block
    @PutMapping("/{id}/free")
    public ResponseEntity<Block> freeBlock(@PathVariable Long id) {
        try {
            Block updatedBlock = blockService.freeBlock(id);
            return ResponseEntity.ok(updatedBlock);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE block
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBlock(@PathVariable Long id) {
        Optional<Block> existingBlock = blockService.getBlockById(id);
        if (existingBlock.isPresent()) {
            blockService.deleteBlock(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
