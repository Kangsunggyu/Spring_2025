package org.example.memo.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.memo.dto.MemoRequestDto;
import org.example.memo.dto.MemoResponseDto;
import org.example.memo.entity.MemoEntity;
import org.example.memo.repository.MemoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemoService {
    private final MemoRepository memoRepository;

    // Create 메서드
    @Transactional
    public MemoResponseDto createMemoRepository(MemoRequestDto memoRequestDto) {
        MemoEntity memoEntity = new MemoEntity(memoRequestDto.getContent());
        MemoEntity savedMemoEntity = memoRepository.save(memoEntity);

        return new MemoResponseDto(
                savedMemoEntity.getId(),
                savedMemoEntity.getContent(),
                savedMemoEntity.getCreateTime()
        );
    }
    @Transactional
    public List<MemoResponseDto> readAllMemoRepository() {
        List<MemoEntity> memoEntities = memoRepository.findAll();
        List<MemoResponseDto> list = new ArrayList<>();
        for (MemoEntity memoEntity : memoEntities) {
            MemoResponseDto memoResponseDto = new MemoResponseDto(
                    memoEntity.getId(),
                    memoEntity.getContent(),
                    memoEntity.getCreateTime()
            );
            list.add(memoResponseDto);
        }
        return list;
    }
    @Transactional
    public MemoResponseDto updateMemoRepository(Long id, MemoRequestDto memoRequestDto) {
        MemoEntity memoEntity = memoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("id가 없어"));
        memoEntity.updateMemo(memoRequestDto.getContent());
        return new MemoResponseDto(memoEntity.getId(), memoEntity.getContent(), memoEntity.getCreateTime());
    }
    @Transactional
    public void deleteMemoRepository(Long id) {
        MemoEntity memo = memoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("id 없음"));
        memoRepository.deleteById(id);
    }
}
