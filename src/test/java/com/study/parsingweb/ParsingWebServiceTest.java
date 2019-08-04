package com.study.parsingweb;

import static org.assertj.core.api.Assertions.assertThat;

import com.study.parsingweb.constants.ParsingOption;
import com.study.parsingweb.service.ParsingWebService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class ParsingWebServiceTest {

  @Autowired
  private ParsingWebService parsingWebService;

  @Test
  public void connect_webpage_test() {
    assertThat(parsingWebService.connectWeb("https://www.naver.com")).isTrue();
    assertThat(parsingWebService.connectWeb("https://yah.ac/acc")).isTrue();
    assertThat(parsingWebService.connectWeb("https://everytime.kr/")).isTrue();
  }

  @Test
  public void extract_content_test() {
    parsingWebService.connectWeb("https://yah.ac/acc");
    assertThat(parsingWebService.extractContent(ParsingOption.WHOLE_HTML).isEmpty()).isFalse();
    assertThat(parsingWebService.extractContent(ParsingOption.EXCEPT_TAG).isEmpty()).isFalse();
  }

  @Test
  public void extract_content_whole_html_test() {
    parsingWebService.connectWeb("https://yah.ac/acc");
    assertThat(parsingWebService.extractContent(ParsingOption.WHOLE_HTML).contains("html"))
        .isTrue();
  }

  @Test
  public void extract_content_except_tag_test() {
    parsingWebService.connectWeb("https://yah.ac/acc");
    assertThat(parsingWebService.extractContent(ParsingOption.EXCEPT_TAG).contains("html"))
        .isFalse();
  }
}
