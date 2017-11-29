package com.example.student;

import com.jayway.restassured.builder.RequestSpecBuilder;
import org.arquillian.cube.openshift.impl.enricher.AwaitRoute;
import org.arquillian.cube.openshift.impl.enricher.RouteURL;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.net.URL;
import java.util.Objects;

import static com.jayway.restassured.RestAssured.given;


@RunWith(Arquillian.class)
public class OpenShiftIT {


    @RouteURL("student")
    @AwaitRoute
    private URL route;

    private RequestSpecBuilder requestSpecBuilder;

    @Before
    public void setupRestAssured() {
        this.requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.setBaseUri(String.format("http://%s/rest/student/", Objects.requireNonNull(route).getHost()));
    }

    @Test
    public void testGreetingEndpoint() {
        given(requestSpecBuilder.build())
                .when().get()
                .then()
                .statusCode(200);
    }
}
