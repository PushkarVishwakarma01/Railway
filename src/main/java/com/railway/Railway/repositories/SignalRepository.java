package com.railway.Railway.repositories;

import com.railway.Railway.model.Signal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SignalRepository extends JpaRepository<Signal, Long> {

    @Query(value = "SELECT * FROM signal WHERE block_id = :blockId", nativeQuery = true)
    Signal getSignalByBlock(@Param("blockId") Long blockId);

}
