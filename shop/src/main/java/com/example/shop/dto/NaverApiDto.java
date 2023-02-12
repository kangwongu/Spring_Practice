package com.example.shop.dto;

import lombok.Getter;
import org.json.JSONObject;


public class NaverApiDto {

    @Getter
    public static class ResponseDto {
        private String title;
        private String link;
        private String image;
        private int lprice;

        // JSON -> ResponseDto
        public ResponseDto(JSONObject object) {
            title = object.getString("title");
            link = object.getString("link");
            image = object.getString("image");
            lprice = object.getInt("lprice");
        }
    }
}
