package com.study.parsingweb.service.impl;

import com.study.parsingweb.service.ParsingWebService;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ParsingWebServiceImpl implements ParsingWebService {

    enum PARSING_OPTION{
        WHOLE_HTML,
        EXCEPT_TAG
    }

    private int charactors[];

    public ParsingWebServiceImpl(){
        charactors = new int[128];
    }

    @Override
    public String urlParsing(String urlPath, String parsingOption, int wrapUnit) {
        String tagPattern = "<.*?>(.*?)</.*>";
        StringBuilder stringBuilder = new StringBuilder();
        try{
            URL url = new URL(urlPath);
            URLConnection connection = url.openConnection();
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
