package core;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;

import static constants.YandexSpellerConstants.*;

/**
 * Describes Yandex Speller SOAP request.
 */
public class YandexSpellerSOAP {

    static RequestSpecification spellerSOAPreqSpec = new RequestSpecBuilder()
            .addHeader(
                    "Accept-Encoding",
                    "gzip,deflate")
            .setContentType("text/xml;charset=UTF-8")
            .addHeader(
                    "SOAPAction",
                    YANDEX_SPELLER_API_METHOD_checkText)
            .addHeader(
                    "Host",
                    YANDEX_SPELLER_HOST)
            .setBaseUri(
                    YANDEX_SPELLER_URL)
            .build();

    private YandexSpellerSOAP(){}

    private HashMap<String, String> params = new HashMap<>();

    public static class SOAPBuilder {
        final YandexSpellerSOAP spellerSOAP;

        private SOAPBuilder(YandexSpellerSOAP soap) {
            spellerSOAP = soap;
        }

        public YandexSpellerSOAP.SOAPBuilder text(String text) {
            spellerSOAP.params.put(PARAM_TEXT, text);
            return this;
        }

        public YandexSpellerSOAP.SOAPBuilder options(String options) {
            spellerSOAP.params.put(PARAM_OPTIONS, "'" + options + "'");
            return this;
        }

        public YandexSpellerSOAP.SOAPBuilder language(Languages language) {
            spellerSOAP.params.put(PARAM_LANG,   "'" + language.languageCode + "'");
            return this;
        }

        public YandexSpellerSOAP.SOAPBuilder format(Format format) {
            spellerSOAP.params.put(PARAM_FORMAT,   "'" + format.name() + "'");
            return this;
        }

        public Response callSOAP() {
            String soapBody =
                      "<soapenv:Envelope xmlns:soapenv='http://schemas.xmlsoap.org/soap/envelope/' "
                    + "  xmlns:spel='" + YANDEX_SPELLER_URL + "'>\n"
                    + "  <soapenv:Header/>\n"
                    + "  <soapenv:Body>\n"
                    + "    <spel:CheckTextRequest "
                    + "        lang=" + (spellerSOAP.params.getOrDefault(PARAM_LANG, "'en'"))
                    + "        options=" + (spellerSOAP.params.getOrDefault(PARAM_OPTIONS, "'0'"))
                    + "        format=" + (spellerSOAP.params.getOrDefault(PARAM_FORMAT, "''")) + ">\n"
                    + "      <spel:text>"+ (spellerSOAP.params.getOrDefault(PARAM_TEXT, WRONG_WORD_EN)) + "</spel:text>\n"
                    + "    </spel:CheckTextRequest>\n"
                    + "  </soapenv:Body>\n"
                    + "</soapenv:Envelope>";


            return RestAssured.with()
                    .spec(spellerSOAPreqSpec)
                    .body(soapBody)
                    .log().all()
                    .with().post()
                    .prettyPeek();
        }
    }


    public static SOAPBuilder with() {
        core.YandexSpellerSOAP soap = new YandexSpellerSOAP();
        return new SOAPBuilder(soap);
    }
}
