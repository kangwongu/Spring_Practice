package com.example.memo.service;

import com.example.memo.domain.Memo;
import com.example.memo.dto.request.MemoCreateRequestDto;
import com.example.memo.dto.request.MemoUpdateRequestDto;
import com.example.memo.dto.response.MemoCreateResponseDto;
import com.example.memo.dto.response.MemoReadResponseDto;
import com.example.memo.dto.response.MemoUpdateResponseDto;
import com.example.memo.exception.CustomException;
import com.example.memo.repository.MemoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.example.memo.exception.StatusCode.NOT_EXISTING_MEMO;
import static com.example.memo.exception.StatusCode.WRITE_MEMO_ERROR;

@Service
@RequiredArgsConstructor
@Transactional
public class MemoService {

    private final MemoRepository memoRepository;

    // 메모 생성
    public MemoCreateResponseDto createMemo(MemoCreateRequestDto requestDto) {
        if (requestDto.getContents()==null || requestDto.getContents().isEmpty()) throw new CustomException(WRITE_MEMO_ERROR);

        Memo memo = Memo.builder()
                .username(requestDto.getUsername())
                .content(requestDto.getContents())
                .build();

        memoRepository.save(memo);

        return MemoCreateResponseDto.builder()
                .id(memo.getId())
                .build();
    }

    // 모든 메모 반환
    public List<MemoReadResponseDto> getMemos() {
        List<Memo> memos = memoRepository.findAll();
        List<MemoReadResponseDto> responseDtos = new ArrayList<>();
        for (Memo memo : memos) {
            responseDtos.add(MemoReadResponseDto.builder()
                    .username(memo.getUsername())
                    .contents(memo.getContent())
                    .build());
        }
        return responseDtos;
    }

    // 메모 수정
    public MemoUpdateResponseDto updateMemo(Long id, MemoUpdateRequestDto requestDto) {
        if (requestDto.getContents()==null || requestDto.getContents().isEmpty()) throw new CustomException(WRITE_MEMO_ERROR);
        Memo findMemo = memoRepository.findById(id).orElseThrow(
                () -> new CustomException(NOT_EXISTING_MEMO)
        );
        // 수정
        findMemo.updateMemo(requestDto);
        return MemoUpdateResponseDto.builder()
                .id(findMemo.getId())
                .build();
    }
}
