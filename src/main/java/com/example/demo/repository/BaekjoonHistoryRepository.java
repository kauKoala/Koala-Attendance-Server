package com.example.demo.repository;

import com.example.demo.domain.BaekjoonHistory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BaekjoonHistoryRepository extends MongoRepository<BaekjoonHistory, Long> {
}
