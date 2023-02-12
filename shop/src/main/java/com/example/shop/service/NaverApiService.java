package com.example.shop.service;

import com.example.shop.dto.NaverApiDto;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class NaverApiService {

    @Value("${spring.naver.accessKey}")
    private String accessKey;

    @Value("${spring.naver.secretKey}")
    private String secretKey;

    // 네이버 API를 사용해 검색
    public List<NaverApiDto.ResponseDto> searchItems(String query) {
        RestTemplate rest = new RestTemplate();
        HttpHeaders header = new HttpHeaders();
        header.add("X-Naver-Client-Id", accessKey);
        header.add("X-Naver-Client-Secret", secretKey);
        String body = "";

        HttpEntity<String> requestEntity = new HttpEntity<String>(body, header);
        ResponseEntity<String> responseEntity = rest.exchange("https://openapi.naver.com/v1/search/shop.json?display=15&query=" + query , HttpMethod.GET, requestEntity, String.class);

        HttpStatus httpStatus = responseEntity.getStatusCode();
        int status = httpStatus.value();
        log.info("NAVER API Status Code : " + status);

        // 결과를 JSON으로 응답받음
        String response = responseEntity.getBody();

        // 응답받은 JSON중에 필요한 데이터를 추출
        return fromJSONtoDto(response);
    }

    // JSON -> Dto
    private List<NaverApiDto.ResponseDto> fromJSONtoDto(String response) {
        JSONObject object = new JSONObject(response);
        JSONArray items = object.getJSONArray("items");// items에 검색 결과값 리스트가 있다
        List<NaverApiDto.ResponseDto> dtoList = new ArrayList<>();
        for (Object item : items) {
            JSONObject jsonItem = (JSONObject) item;
            NaverApiDto.ResponseDto responseDto = new NaverApiDto.ResponseDto(jsonItem);
            dtoList.add(responseDto);
        }

        return dtoList;

    }
}
