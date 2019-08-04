package com.study.parsingweb.service;

import com.study.parsingweb.constants.ParsingOption;

public interface ParsingWebService {

    public String urlParsing(String urlPath, String parsingOption, int wrapUnit);

    /**
     * 1. 웹에 연결 2. 웹 html 내용을 가져오기 (옵션 적용하여) 3. 가져온 내용을 정렬하기 4. 정렬한 내용을 출력단위만큼 분할하고 나머지 계산하기 5. 몫과 나머지
     * 출력하기
     */
    public boolean connectWeb(String urlPath);

    public String extractContent(ParsingOption parsingOption);

    public boolean sortContent(String content);

    public void diviedContent(String content);

    public void printResultAndRest(String content);

}
