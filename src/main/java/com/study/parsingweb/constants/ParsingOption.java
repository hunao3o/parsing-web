package com.study.parsingweb.constants;

public enum ParsingOption {

  WHOLE_HTML("[^(가-힣ㄱ-ㅎㅏ-ㅣa-zA-Z0-9)|</>]"),
  EXCEPT_TAG("^(가-힣ㄱ-ㅎㅏ-ㅣa-zA-Z0-9)(\\<.?[a-zA-Z]+[^>]+[\\>])|(\\<\\/[a-zA-Z]+\\>)");

  public String matcher;

  ParsingOption(String matcher) {
    this.matcher = matcher;
  }
}
