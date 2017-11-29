package com.example.guestbook;

import com.jayway.restassured.builder.RequestSpecBuilder;
import io.fabric8.kubernetes.api.model.v3_1.Service;
import org.arquillian.cube.kubernetes.annotations.Named;
import org.arquillian.cube.openshift.impl.enricher.AwaitRoute;
import org.arquillian.cube.openshift.impl.enricher.RouteURL;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;

import static com.jayway.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.containsString;

@RunWith(Arquillian.class)
public class GuestBookIT {

    @RouteURL("guestbook")
    @AwaitRoute
    private URL route;

    @Named("guestbook")
    @ArquillianResource
    Service guestbook;

    @Test
    public void should_verify_guestbook_service_should_not_be_null() throws IOException {
        assertThat(guestbook).isNotNull();
        assertThat(guestbook.getSpec()).isNotNull();
        assertThat(guestbook.getSpec().getPorts()).isNotNull();
        assertThat(guestbook.getSpec().getPorts()).isNotEmpty();
    }

    @Test
    public void should_test_guestbook_entry_endpoint_is_reachable() {
        RequestSpecBuilder requestSpecBuilder = getRequestSpecBuilder();

        given(requestSpecBuilder.build())
                .when().get()
                .then()
                .statusCode(200)
                .body(containsString("Arni"));
    }

    @Test
    public void should_test_guestbook_entry_endpoint_with_id_parameter_is_reachable() {
        RequestSpecBuilder requestSpecBuilder = getRequestSpecBuilder();

        given(requestSpecBuilder.build())
                .param("id", "1000")
                .when().get()
                .then()
                .statusCode(200)
                .body(containsString("Duke Nukem"));
    }

    private RequestSpecBuilder getRequestSpecBuilder() {
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.setBaseUri(String.format("http://%s/guestbook/", Objects.requireNonNull(route).getHost()));
        return requestSpecBuilder;
    }
}
