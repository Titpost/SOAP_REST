import core.YandexSpellerSOAP;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.Arrays;

import static com.sun.jmx.snmp.ThreadContext.contains;
import static constants.YandexSpellerConstants.*;
import static constants.YandexSpellerConstants.Format.html;

/**
 * try to test SOAP via RestAssured
 */
public class TestYandexSpellerSOAP {

    @Test
    public void wrongWordEn(){
        YandexSpellerSOAP
                .with()
                    .text(WRONG_WORD_EN)
                .callSOAP()
                .then()
                    .body(Matchers.stringContainsInOrder(
                        Arrays.asList(
                                WRONG_WORD_EN,
                                RIGHT_WORD_EN
                        )
                    ));
    }

    @Test
    public void wrongWordRu(){
        YandexSpellerSOAP
                .with()
                    .language(Languages.RU)
                    .text(WRONG_WORD_RU)
                .callSOAP()
                .then()
                    .body(Matchers.stringContainsInOrder(
                        Arrays.asList(
                                WRONG_WORD_RU,
                                RIGHT_WORD_RU
                        )
                    ));
    }

    @Test
    public void useRequestBuilderToChangeParams(){
        YandexSpellerSOAP
                .with()
                    .language(Languages.EN)
                    .text(WRONG_WORD_EN)
                    .options("6")
                .callSOAP()
                .then()
                    .body(Matchers.stringContainsInOrder(
                        Arrays.asList(
                                WRONG_WORD_EN,
                                RIGHT_WORD_EN
                        )
                    ));
    }

    @Test
    public void noSuggestionsRu(){
        YandexSpellerSOAP
                .with()
                    .language(Languages.RU)
                    .text(BAD_WORD_RU)
                .callSOAP()
                .then()
                    .body(Matchers.not(contains(
                            BAD_WORD_RU
                    )));
    }

    @Test
    public void noSuggestionsEn(){
        YandexSpellerSOAP
                .with()
                    .language(Languages.EN)
                    .text(BAD_WORD_EN)
                .callSOAP()
                .then()
                    .body(Matchers.not(contains(
                            BAD_WORD_EN
                    )));
    }

    @Test
    public void wrongFormat(){
        YandexSpellerSOAP
                .with()
                    .format(html)
                    .text(WRONG_WORD_EN)
                    .callSOAP()
                .then()
                .body(Matchers.stringContainsInOrder(
                        Arrays.asList(
                                WRONG_WORD_EN,
                                RIGHT_WORD_EN
                        )
                ));
    }
}