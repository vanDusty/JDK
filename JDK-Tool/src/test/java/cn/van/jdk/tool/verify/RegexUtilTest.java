package cn.van.jdk.tool.verify;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class RegexUtilTest {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Test
    public void isMatch() {
        String input = "0571-69123456";
        boolean isTel = RegexUtil.isMatch(RegexConstant.REGEX_TEL, input);
        logger.info("result:{}", isTel);

        boolean isPhone = RegexUtil.isMatch(RegexConstant.REGEX_MOBILE_SIMPLE, input);
        logger.info("result:{}", isPhone);

    }

    @Test
    public void getMatches() {
        String input = "0571-69123456";
        String result = RegexUtil.getMatches(RegexConstant.REGEX_NUMBER, input);
        logger.info("result:{}", result);
        List<Object> list = RegexUtil.getMatchesList(RegexConstant.REGEX_NUMBER, input);
        logger.info("result:{}", list);
    }

    @Test
    public void count() {
        String input = "0571-69123456";
        int result = RegexUtil.count(RegexConstant.REGEX_NUMBER, input);
        logger.info("result:{}", result);
    }

    @Test
    public void replace() {
        String input = "0571-69123456-hz";
        String result = RegexUtil.replaceAll("-", input, "_");
        logger.info("result:{}", result);
        result = RegexUtil.replaceFirst("-", input, "_");
        logger.info("result:{}", result);
        result = RegexUtil.delPre("-", input);
        logger.info("result:{}", result);
    }

    @Test
    public void escape() {
        String input = "$123.45";
        String result = RegexUtil.escape(input);
        logger.info("result:{}", result);
    }

}
