package maps.core;

import maps.beans.YandexMapsAnswer;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import maps.constants.YandexMapsConstants;
import org.apache.http.HttpStatus;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

import static org.hamcrest.Matchers.lessThan;

/**
 * Describes Yandex Maps REST API
 */
public class YandexMapsApi {


    // builder pattern
    private YandexMapsApi() {
    }
    private HashMap<String, String> params = new HashMap<>();

    public static class ApiBuilder {
        YandexMapsApi mapsApi;

        private ApiBuilder(YandexMapsApi gcApi) {
            mapsApi = gcApi;
        }

        public ApiBuilder geocode(String text) {
            mapsApi.params.put(YandexMapsConstants.PARAM_GEOCODE, text);
            return this;
        }

        public Response callApi() {
            return RestAssured.with()
                    .queryParams(mapsApi.params)
                    .log().all()
                    .get(YandexMapsConstants.YANDEX_MAPS_URL).prettyPeek();
        }
    }

    public static ApiBuilder with() {
        YandexMapsApi api = new YandexMapsApi();
        return new ApiBuilder(api);
    }


    //get ready Speller answers list form api response
    public static List<YandexMapsAnswer> getYandexMapsAnswers(Response response){
        return new Gson().fromJson(
                response.asString().trim(),
                new TypeToken<List<YandexMapsAnswer>>() {}.getType()
        );
    }


    //set base request and response specifications tu use in tests
    public static ResponseSpecification successResponse(){
        return new ResponseSpecBuilder()
                .expectContentType(ContentType.JSON)
                .expectHeader("Connection", "keep-alive")
                .expectResponseTime(lessThan(20000L))
                .expectStatusCode(HttpStatus.SC_OK)
                .build();
    }

    public static RequestSpecification baseRequestConfiguration(){
        return new RequestSpecBuilder()
                .setAccept(ContentType.XML)
                .addHeader("custom header2", "header2.value")
                .addQueryParam("requestID", new Random().nextLong())
                .setBaseUri(YandexMapsConstants.YANDEX_MAPS_URL)
                .build();
    }
}
