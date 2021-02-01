package pl.edu.pjwstk.jaz.average;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import pl.edu.pjwstk.jaz.IntegrationTest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@RunWith(SpringRunner.class)
@IntegrationTest
public class AverageControllerTestIT {

    @Test
    public void should_calculate_simple_average_first() {
        given()
                .when()
                .param("numbers", "4,1,7,4")
                .get("/api/average")
                .then()
                .statusCode(200)
                .body("message",equalTo("Average equals: 4"));
    }
    @Test
    public void should_calculate_simple_average_second() {
        given()
                .when()
                .param("numbers", "2,1,1")
                .get("/api/average")
                .then()
                .statusCode(200)
                .body("message",equalTo("Average equals: 1.33"));
    }
    @Test
    public void should_calculate_simple_average_third() {
        given()
                .when()
                .param("numbers", "5,2,1,1")
                .get("/api/average")
                .then()
                .statusCode(200)
                .body("message",equalTo("Average equals: 2.25"));
    }
    @Test
    public void when_no_parameter_supplied_should_print_a_message() {
        given()
                .when()
                .get("/api/average")
                .then()
                .statusCode(200)
                .body("message", equalTo("Please put parameters."));
    }
}
