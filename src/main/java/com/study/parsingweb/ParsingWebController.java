package com.study.parsingweb;

import com.study.parsingweb.service.ParsingWebService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ParsingWebController {

    private final ParsingWebService parsingWebService;

    @PostMapping("/parsing.json")
    public Map<String, Object> main(@RequestParam String urlPath,
                                    @RequestParam String parsingOption,
                                    @RequestParam int wrapUnit){
        String content = parsingWebService.urlParsing(urlPath, parsingOption, wrapUnit);
        Map<String, Object> response = new HashMap<String, Object>();
        return response;
    }
}
