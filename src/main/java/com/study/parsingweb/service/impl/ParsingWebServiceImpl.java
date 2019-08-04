package com.study.parsingweb.service.impl;

import com.study.parsingweb.constants.ParsingOption;
import com.study.parsingweb.service.ParsingWebService;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

@Slf4j
@Service
public class ParsingWebServiceImpl implements ParsingWebService {

    private int charactors[];

    private URLConnection connection;

    //    public ParsingWebServiceImpl(){
//        charactors = new int[128];
//    }


    @Override
    public String urlParsing(String urlPath, String parsingOption, int wrapUnit) {
        String tagPattern = "<.*?>(.*?)</.*>";
        StringBuilder stringBuilder = new StringBuilder();
        try{
            InputStreamReader inputStreamReader = new InputStreamReader (connection.getInputStream(), "utf-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String content = "";
            while((content = bufferedReader.readLine())!=null){
                content = StringUtils.trimAllWhitespace(content);
                if(!StringUtils.isEmpty(content)) {
                    stringBuilder.append(content);
                }
            }
            bufferedReader.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        log.info(mixAndSort(stringBuilder.toString()));
        return null;
    }

    @Override
    public boolean connectWeb(String urlPath) {
        try {
            URL url = new URL(urlPath);
            connection = url.openConnection();
        } catch (IOException e) {
            e.getStackTrace();
        }
        return ObjectUtils.isEmpty(connection);
    }

    @Override
    public String extractContent(ParsingOption parsingOption) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(connection.getInputStream(),
                StandardCharsets.UTF_8);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String content;
            while ((content = bufferedReader.readLine()) != null) {
                content = StringUtils.trimAllWhitespace(content);
                if (!StringUtils.isEmpty(content)) {
                    if (parsingOption.equals(ParsingOption.WHOLE_HTML)) {
//                        content.
                    }
                    stringBuilder.append(content);
                }
            }
            inputStreamReader.close();
            bufferedReader.close();
        } catch (IOException e) {
            e.getStackTrace();
        }
        return stringBuilder.toString();
    }

    @Override
    public boolean sortContent(String content) {
        return false;
    }

    @Override
    public void diviedContent(String content) {

    }

    @Override
    public void printResultAndRest(String content) {

    }

    private String mixAndSort(String html){
        Arrays.fill(charactors, 0);
        for (char c: html.toCharArray()) {
            if((int)c >= 0 && (int)c < 128)
                charactors[(int)c]++;
        }

        StringBuilder result = new StringBuilder();

        int numberStart = 48, numberEnd = 59;
        int upperStart = 65, upperEnd = 90;
        int lowerStart = 97, lowerEnd = 122;
        while(numberStart > numberEnd && upperStart > upperEnd && lowerStart > lowerEnd){
            while(charactors[numberStart] != 0)
                numberStart++;
            while(charactors[upperStart] != 0)
                upperStart++;
            while(charactors[lowerStart] != 0)
                lowerStart++;

            if(upperStart > upperEnd){
                result.append((char)lowerStart);
                charactors[lowerStart]--;
            } else if(lowerStart > lowerEnd){
                result.append((char)upperStart);
                charactors[upperStart]--;
            }
            if(numberStart <= numberEnd){
                result.append((char)numberStart);
                charactors[numberStart]--;
            }
        }
        return result.toString();
    }
}
