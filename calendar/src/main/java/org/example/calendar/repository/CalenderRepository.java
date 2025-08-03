package org.example.calendar.repository;

import org.example.calendar.entity.CalenderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CalenderRepository extends JpaRepository<CalenderEntity, Integer> {
}
