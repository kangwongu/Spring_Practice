package com.example.shop.controller;

import com.example.shop.dto.FolderDto;
import com.example.shop.service.FolderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/folders")
    public ResponseEntity<List<FolderDto.ReadResponse>> getFolders(HttpServletRequest request) {
        List<FolderDto.ReadResponse> response = folderService.getFolders(request);
        return ResponseEntity.ok().body(response);
    }
}
