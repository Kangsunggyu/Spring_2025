package org.example.memo.contoller;

import lombok.RequiredArgsConstructor;
import org.example.memo.dto.MemoRequestDto;
import org.example.memo.dto.MemoResponseDto;
import org.example.memo.repository.MemoRepository;
import org.example.memo.service.MemoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemoController {
    private final MemoService memoService;
    private MemoRepository memoRepository;

    @PostMapping("/memos")
    public MemoResponseDto createMemo(@RequestBody MemoRequestDto memoRequestDto){
        return memoService.createMemoRepository(memoRequestDto);
    }
    @GetMapping("/memos")
    public List<MemoResponseDto> readAllMemo(){
        return memoService.readAllMemoRepository();
    }
    @PutMapping("/memos/{memoId}")
    public MemoResponseDto updateMemo(
            @PathVariable("memoId") Long memberId,
            @RequestBody MemoRequestDto memoRequestDto){
        return memoService.updateMemoRepository(memberId, memoRequestDto);
    }
    @DeleteMapping("/memos/{memoId}")
    public void deleteMemo(@PathVariable("memoId") Long memoId){
        memoService.deleteMemoRepository(memoId);
    }
}
