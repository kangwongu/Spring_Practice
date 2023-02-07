package com.example.memo.service;

import com.example.memo.domain.Memo;
import com.example.memo.dto.request.MemoCreateRequestDto;
import com.example.memo.exception.CustomException;
import com.example.memo.exception.StatusCode;
import com.example.memo.repository.MemoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

@Service
@RequiredArgsConstructor
@Transactional
public class MemoService {

    private final MemoRepository memoRepository;

    // 메모 생성
    public Long createMemo(MemoCreateRequestDto requestDto) {
        if (requestDto.getContent()==null || requestDto.getContent().isEmpty()) throw new CustomException(StatusCode.CREATE_MEMO_ERROR);

        Memo memo = Memo.builder()
                .username(requestDto.getUsername())
                .content(requestDto.getContent())
                .build();

        memoRepository.save(memo);

        return memo.getId();
    }

}
