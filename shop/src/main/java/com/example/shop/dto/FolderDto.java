package com.example.shop.dto;

import com.example.shop.domain.Folder;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public class FolderDto {

    @Getter
    public static class AddRequest {
        private List<String> folderNames;
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class AddResponse {
        private String folderName;

        @Builder
        public AddResponse(String folderName) {
            this.folderName = folderName;
        }
    }

    @Getter
    @NoArgsConstructor
    public static class ReadResponse {
        private Long id;
        private Long userId;
        private String name;

        public ReadResponse(Folder folder) {
            id = folder.getId();
            userId = folder.getUser().getId();
            name = folder.getName();
        }
    }
}
