package maps;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.Arrays;

import static maps.constants.YandexMapsConstants.*;
import static org.hamcrest.Matchers.lessThan;


public class TestYandexMapsJSON {

    // simple usage of RestAssured library: direct request call and response validations in test.
    @Test
    public void simpleMapsApiCall() {
        RestAssured
                .given()
                    .queryParam(PARAM_GEOCODE, "Москва,+Тверская+улица,+дом+7")
                    .accept(ContentType.JSON)
                    .and()
                    .body("some body payroll")
                    .log().everything()
                .when()
                    .get(YANDEX_MAPS_URL)
                    .prettyPeek()
                .then()

                    .assertThat()
                    .statusCode(HttpStatus.SC_OK)
                    .body(Matchers.allOf(
                            Matchers.stringContainsInOrder(Arrays.asList(
                                    PRECISION,
                                    ADDRESS
                            )),
                            Matchers.containsString("Москва, Тверская улица, 7")))
                    .contentType(ContentType.JSON)
                    .time(lessThan(20000L)); // Milliseconds
    }
}
