package com.example.shop.service;

import com.example.shop.domain.Folder;
import com.example.shop.domain.User;
import com.example.shop.dto.FolderDto;
import com.example.shop.exception.UserException;
import com.example.shop.exception.status.UserStatus;
import com.example.shop.repository.FolderRepository;
import com.example.shop.repository.UserRepository;
import com.example.shop.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FolderService {

    private final FolderRepository folderRepository;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    @Transactional
    public List<FolderDto.AddResponse> addFolders(FolderDto.AddRequest requestDto, HttpServletRequest request) {
        String token = jwtUtil.resolveToken(request);
        Claims claims;

        if (token != null) {
            if (jwtUtil.validateToken(token)) {
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new UserException(UserStatus.INVALID_ACCESS_TOKEN);
            }

            // 로그인한 유저
            User findUser = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new UserException(UserStatus.NOT_EXIST_USER)
            );

            List<Folder> folders = new ArrayList<>();
            for (String folderName : requestDto.getFolderNames()) {
                Folder folder = Folder.builder()
                        .name(folderName)
                        .build();
                folder.registerUser(findUser);  // 연관관계 세팅
                folders.add(folder);
            }
            folderRepository.saveAll(folders);

            List<FolderDto.AddResponse> response = new ArrayList<>();
            for (Folder folder : folders) {
                response.add(FolderDto.AddResponse.builder()
                        .folderName(folder.getName())
                        .build());
            }
            return response;
        } else {
            return null;
        }
    }
}
