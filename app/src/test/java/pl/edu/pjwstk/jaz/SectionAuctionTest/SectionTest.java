package pl.edu.pjwstk.jaz.SectionAuctionTest;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import pl.edu.pjwstk.jaz.domain.LoginRequest;
import pl.edu.pjwstk.jaz.domain.RegisterRequest;
import pl.edu.pjwstk.jaz.IntegrationTest;
import pl.edu.pjwstk.jaz.request.CategoryRequest;
import pl.edu.pjwstk.jaz.request.SectionNameRequest;
import pl.edu.pjwstk.jaz.request.SectionRequest;


import java.util.Arrays;

import static io.restassured.RestAssured.given;
@RunWith(SpringRunner.class)
@IntegrationTest
public class SectionTest {

    @BeforeClass
    public static void beforeClassRegisterAdmin(){
        given()
                .body(new RegisterRequest("Admin","Admin"))
                .contentType(ContentType.JSON)
                .post("/api/register");
        given()
                .body(new RegisterRequest("User","User"))
                .contentType(ContentType.JSON)
                .post("/api/register");
    }

    public Response adminLogging(){
        return given()
                .body(new LoginRequest("Admin","Admin"))
                .contentType(ContentType.JSON)
                .post("/api/login")
                .thenReturn();
    }

    public Response userLogging(){
        return given()
                .body(new LoginRequest("User","User"))
                .contentType(ContentType.JSON)
                .post("/api/login")
                .thenReturn();
    }
//    public void sectionCreated(Response){
//    given()
//                .body(new SectionRequest("House",Arrays.asList("Kitchen","Bathroom")))
//            .contentType(ContentType.JSON)
//                .cookies(adminLogging().getCookies())
//            .post("/api/addSection");
//    }

    @Test
    public void adminAddSectionTest200() {
        // @formatter:off
        given()
                .body(new SectionRequest("House",Arrays.asList("Kitchen","Bathroom")))
                .contentType(ContentType.JSON)
                .cookies(adminLogging().getCookies())
                .post("/api/addSection")
                .then()
                .statusCode(org.springframework.http.HttpStatus.OK.value());
        // @formatter:on
    }

    @Test
    public void userAddSectionTest403() {
        // @formatter:off
        given()
                .body(new SectionRequest("House",Arrays.asList("Kitchen","Bathroom")))
                .contentType(ContentType.JSON)
                .cookies(userLogging().getCookies())
                .post("/api/addSection")
                .then()
                .statusCode(HttpStatus.FORBIDDEN.value());
        // @formatter:on
    }

    @Test
    public void notLoggedPersonAddSectionTest403() {
        // @formatter:off
        given()
                .body(new SectionRequest("House",Arrays.asList("Kitchen","Bathroom")))
                .contentType(ContentType.JSON)
                .post("/api/addSection")
                .then()
                .statusCode(HttpStatus.FORBIDDEN.value());
        // @formatter:on
    }

    @Test
    public void adminAddSectionTestWithNoData204() {
        // @formatter:off
        given()
                .body(new SectionRequest("",Arrays.asList("Kitchen","Bathroom")))
                .contentType(ContentType.JSON)
                .cookies(adminLogging().getCookies())
                .post("/api/addSection")
                .then()
                .statusCode(HttpStatus.NO_CONTENT.value());
        // @formatter:on
    }


    @Test
    public void adminEditSectionTest200() {
        // @formatter:off
        given()
                .body(new SectionRequest("A",Arrays.asList("Kitchen","Bathroom")))
                .contentType(ContentType.JSON)
                .cookies(adminLogging().getCookies())
                .post("/api/addSection");
        given()
                .body(new SectionNameRequest("A","B"))
                .contentType(ContentType.JSON)
                .cookies(adminLogging().getCookies())
                .post("/api/editSection")
                .then()
                .statusCode(org.springframework.http.HttpStatus.OK.value());
        // @formatter:on
    }

    @Test
    public void userEditSectionTest403() {
        // @formatter:off
        given()
                .body(new SectionNameRequest("House","Garden"))
                .contentType(ContentType.JSON)
                .cookies(userLogging().getCookies())
                .post("/api/editSection")
                .then()
                .statusCode(HttpStatus.FORBIDDEN.value());
        // @formatter:on
    }

    @Test
    public void notLoggedPersonEditSectionTest403() {
        // @formatter:off
        given()
                .body(new SectionNameRequest("House","Garden"))
                .contentType(ContentType.JSON)
                .post("/api/editSection")
                .then()
                .statusCode(HttpStatus.FORBIDDEN.value());
        // @formatter:on
    }

    @Test
    public void adminEditSectionTestWithBadSectionName400() {
        // @formatter:off
        given()
                .body(new SectionRequest("House",Arrays.asList("Kitchen","Bathroom")))
                .contentType(ContentType.JSON)
                .cookies(adminLogging().getCookies())
                .post("/api/addSection");
        given()
                .body(new SectionNameRequest("Garden","House"))
                .contentType(ContentType.JSON)
                .cookies(adminLogging().getCookies())
                .post("/api/editSection")
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());
        // @formatter:on
    }

    @Test
    public void adminEditSectionTestWithEmptyNewName204() {
        // @formatter:off
        given()
                .body(new SectionRequest("House",Arrays.asList("Kitchen","Bathroom")))
                .contentType(ContentType.JSON)
                .cookies(adminLogging().getCookies())
                .post("/api/addSection");
        given()
                .body(new SectionNameRequest("House",""))
                .contentType(ContentType.JSON)
                .cookies(adminLogging().getCookies())
                .post("/api/editSection")
                .then()
                .statusCode(HttpStatus.NO_CONTENT.value());
        // @formatter:on
    }

    @Test
    public void adminAddCategoryTest200() {
        // @formatter:off
        given()
                .body(new SectionRequest("C",Arrays.asList("Kitchen","Bathroom")))
                .contentType(ContentType.JSON)
                .cookies(adminLogging().getCookies())
                .post("/api/addSection");
        given()
                .body(new SectionRequest("C",Arrays.asList("Kitchen","Bathroom")))
                .contentType(ContentType.JSON)
                .cookies(adminLogging().getCookies())
                .post("/api/addCategory")
                .then()
                .statusCode(org.springframework.http.HttpStatus.OK.value());
        // @formatter:on
    }

    @Test
    public void userAddCategoryTest403() {
        // @formatter:off
        given()
                .body(new SectionRequest("House",Arrays.asList("Kitchen","Bathroom")))
                .contentType(ContentType.JSON)
                .cookies(userLogging().getCookies())
                .post("/api/addCategory")
                .then()
                .statusCode(HttpStatus.FORBIDDEN.value());
        // @formatter:on
    }

    @Test
    public void notLoggedPersonAddCategoryTest403() {
        // @formatter:off
        given()
                .body(new SectionRequest("House",Arrays.asList("Kitchen","Bathroom")))
                .contentType(ContentType.JSON)
                .post("/api/addCategory")
                .then()
                .statusCode(HttpStatus.FORBIDDEN.value());
        // @formatter:on
    }

    @Test
    public void adminAddCategoryTestWithBadSectionName400() {
        // @formatter:off
        given()
                .body(new SectionRequest("House",Arrays.asList("Kitchen","Bathroom")))
                .contentType(ContentType.JSON)
                .cookies(adminLogging().getCookies())
                .post("/api/addSection");
        given()
                .body(new SectionRequest("Garden",Arrays.asList("Kitchen","Bathroom")))
                .contentType(ContentType.JSON)
                .cookies(adminLogging().getCookies())
                .post("/api/addCategory")
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());
        // @formatter:on
    }

    @Test
    public void adminAddCategoryTestWithEmptyCategoryName204() {
        // @formatter:off
        given()
                .body(new SectionRequest("D",Arrays.asList("Kitchen","Bathroom")))
                .contentType(ContentType.JSON)
                .cookies(adminLogging().getCookies())
                .post("/api/addSection");
        given()
                .body(new SectionRequest("D",Arrays.asList("Kitchen","")))
                .contentType(ContentType.JSON)
                .cookies(adminLogging().getCookies())
                .post("/api/addCategory")
                .then()
                .statusCode(HttpStatus.NO_CONTENT.value());
        // @formatter:on
    }


    @Test
    public void adminEditCategoryTest200() {
        // @formatter:off
        given()
                .body(new CategoryRequest("Kitchen","Office"))
                .contentType(ContentType.JSON)
                .cookies(adminLogging().getCookies())
                .post("/api/editCategory")
                .then()
                .statusCode(org.springframework.http.HttpStatus.OK.value());
        // @formatter:on
    }

    @Test
    public void adminEditCategoryTestWithBadCategoryName400() {
        // @formatter:off
        given()
                .body(new CategoryRequest("LivingRoom","Office"))
                .contentType(ContentType.JSON)
                .cookies(adminLogging().getCookies())
                .post("/api/editCategory")
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());
        // @formatter:on
    }

    @Test
    public void adminEditCategoryTestWithEmptyCategoryNewName204() {
        // @formatter:off
        given()
                .body(new SectionRequest("G",Arrays.asList("Kitchen","Bathroom")))
                .contentType(ContentType.JSON)
                .cookies(adminLogging().getCookies())
                .post("/api/addSection");
        given()
                .body(new SectionRequest("G",Arrays.asList("K","F")))
                .contentType(ContentType.JSON)
                .cookies(adminLogging().getCookies())
                .post("/api/addCategory");
        given()
                .body(new CategoryRequest("K",""))
                .contentType(ContentType.JSON)
                .cookies(adminLogging().getCookies())
                .post("/api/editCategory")
                .then()
                .statusCode(HttpStatus.NO_CONTENT.value());
        // @formatter:on
    }

    @Test
    public void userEditCategoryTest403() {
        // @formatter:off
        given()
                .body(new SectionRequest("House",Arrays.asList("Kitchen","Bathroom")))
                .contentType(ContentType.JSON)
                .cookies(userLogging().getCookies())
                .post("/api/addCategory")
                .then()
                .statusCode(HttpStatus.FORBIDDEN.value());
        // @formatter:on
    }

    @Test
    public void notLoggedPersonEditCategoryTest403() {
        // @formatter:off
        given()
                .body(new SectionRequest("House",Arrays.asList("Kitchen","Bathroom")))
                .contentType(ContentType.JSON)
                .post("/api/addCategory")
                .then()
                .statusCode(HttpStatus.FORBIDDEN.value());
        // @formatter:on
    }

}