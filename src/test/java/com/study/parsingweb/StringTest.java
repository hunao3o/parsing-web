package com.study.parsingweb;

import com.study.parsingweb.constants.ParsingOption;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class StringTest {

  @Test
  public void string_without_tag_braces_test() {
    String input = "<html class='happy'>content??</html>";
    log.debug(input.replaceAll(ParsingOption.WHOLE_HTML.matcher, ""));
  }

  @Test
  public void string_without_tag_contents_test() {
    String input = "<html class='happy'>content??</html>";
    log.debug(input.replaceAll(ParsingOption.EXCEPT_TAG.matcher, ""));
  }
}
