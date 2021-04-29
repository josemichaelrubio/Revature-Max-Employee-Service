package com.revaturemax.repositories;

import com.revaturemax.models.QuizScore;
import com.revaturemax.models.QuizScoreId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizScoreRepository extends JpaRepository<QuizScore, QuizScoreId> {
}
