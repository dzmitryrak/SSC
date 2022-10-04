package com.itechart.adapters;

import com.google.gson.Gson;
import com.itechart.utils.PropertyReader;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import static io.restassured.RestAssured.given;

@Log4j2
public class BaseAdapter {
    protected Gson jsonReader = new Gson();
    protected PropertyReader propertyReader = new PropertyReader("src/test/resources/configuration.properties");
    private final String ACCESS_TOKEN = propertyReader.getPropertyValueByKey("access_token");
    protected final String API_BASE_URL = propertyReader.getPropertyValueByKey("api_base_url");

    @Step("Make post request")
    public String post(String url, String body, int status) {
        log.debug("Sending post request with {} url and {} body", url, body);
        return
                given().
                        header("Authorization","Bearer " + ACCESS_TOKEN).
                        header("Content-type", "application/json").
                        body(body).
                        log().all().
                when().
                        post(url).
                then().
                        log().all().
                        statusCode(status).
                        extract().body().asString();
    }

    @Step("Make patch request")
    public String patch(String url, String body, int status) {
        log.debug("Sending patch request with {} url and {} body", url, body);
        return
                given().
                        header("Authorization","Bearer " + ACCESS_TOKEN).
                        header("Content-type", "application/json").
                        body(body).
                        log().all().
                when().
                        patch(url).
                then().
                        log().all().
                        statusCode(status).
                        extract().body().asString();
    }

    @Step("Make get request")
    public String get(String url, int status) {
        log.debug("Sending get request with {} url", url);
        return
                given().
                        header("Authorization","Bearer " + ACCESS_TOKEN).
                        header("Content-type", "application/json").
                        log().all().
                when().
                        get(url).
                then().
                        log().all().
                        statusCode(status).
                        extract().body().asString();
    }

    @Step("Make delete request")
    public String delete(String url, int status) {
        log.debug("Sending delete request with {} url", url);
        return
                given().
                        header("Authorization","Bearer " + ACCESS_TOKEN).
                        header("Content-type", "application/json").
                        log().all().
                when().
                        delete(url).
                then().
                        log().all().
                        statusCode(status).
                        extract().body().asString();
    }
}
