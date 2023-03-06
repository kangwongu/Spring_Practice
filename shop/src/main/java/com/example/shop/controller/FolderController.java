package com.example.shop.controller;

import com.example.shop.dto.FolderDto;
import com.example.shop.service.FolderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class FolderController {

    private final FolderService folderService;

    // 폴더 생성
    @PostMapping("/folders")
    public ResponseEntity<List<FolderDto.AddResponse>> addFolders(@RequestBody FolderDto.AddRequest requestDto,
                                                                  HttpServletRequest request) {
        List<FolderDto.AddResponse> response = folderService.addFolders(requestDto, request);
        return ResponseEntity.ok().body(response);
    }
}
