package org.example.memo.repository;

import org.example.memo.entity.MemoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemoRepository extends JpaRepository<MemoEntity,Long> {
}
