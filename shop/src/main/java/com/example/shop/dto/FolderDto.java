package com.example.shop.dto;

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
}
