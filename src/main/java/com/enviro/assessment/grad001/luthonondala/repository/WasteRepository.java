package com.enviro.assessment.grad001.luthonondala.repository;

import com.enviro.assessment.grad001.luthonondala.entity.Waste;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WasteRepository extends JpaRepository<Waste, Long> {
}
