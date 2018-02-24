package constants;

/**
 * Constants of YandexSpeller
 */
public class YandexSpellerConstants {

    public static final String YANDEX_SPELLER_HOST = "speller.yandex.net";

    public static final String YANDEX_SPELLER_BASE = "https://" + YANDEX_SPELLER_HOST;

    public static final String YANDEX_SPELLER_URL
            = YANDEX_SPELLER_BASE + "/services/spellservice";

    public static final String YANDEX_SPELLER_API_URI =
            YANDEX_SPELLER_URL + ".json/checkText";

    public static final String YANDEX_SPELLER_API_METHOD_checkText = "/checkText";


    public static final String PARAM_TEXT = "text";
    public static final String PARAM_OPTIONS = "options";
    public static final String PARAM_LANG = "lang";
    public static final String PARAM_FORMAT = "format";
    public static final String WRONG_WORD_EN = "requisitee";
    public static final String RIGHT_WORD_EN = "requisite";
    public static final String WRONG_WORD_UK = "питаня";
    public static final String WRONG_WORD_RU = "паехали";
    public static final String RIGHT_WORD_RU = "поехали";
    public static final String BAD_WORD_RU = "понаехали";
    public static final String BAD_WORD_EN = "casualitinessity";
    public static final String WORD_WITH_WRONG_CAPITAL = "moscow";
    public static final String WORD_WITH_LEADING_DIGITS = "11" + RIGHT_WORD_EN;

    public static final String delimeter = "\n=====================================================================";

    public enum Languages {
        RU("ru"),
        UK("uk"),
        EN("en");

        public String languageCode;

        Languages(String lang) {
            this.languageCode = lang;
        }
    }

    public enum Format {
        plain,
        html
    }
}
