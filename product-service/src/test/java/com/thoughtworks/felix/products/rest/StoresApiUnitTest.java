package com.thoughtworks.felix.products.rest;

import com.thoughtworks.felix.products.domain.Store;
import com.thoughtworks.felix.products.domain.StoreRepository;
import com.thoughtworks.felix.products.support.ApiUnitTest;
import io.restassured.module.mockmvc.response.MockMvcResponse;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.thoughtworks.felix.products.support.TestHelper.outToLog;
import static com.thoughtworks.felix.products.support.TestHelper.readJsonFrom;
import static io.restassured.http.ContentType.JSON;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.core.IsCollectionContaining.hasItems;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

public class StoresApiUnitTest extends ApiUnitTest{

    private static final String CREATE_STORE_URL = "/stores";
    private static final Logger LOGGER = LoggerFactory.getLogger(StoresApiUnitTest.class);


    @Mock
    private StoreRepository repository;

    @InjectMocks
    private StoresApi storesApi;

    @Before
    public void setUp() {
        setUpApi(storesApi);
        final Store fixtureStore = new Store("pet store", 999L, "felix pet store");
        when(repository.save(any(Store.class))).thenReturn(fixtureStore);
    }

    @Test
    public void should_400_when_create_store_without_name_or_customer_id() {
        final String body = readJsonFrom("request/create-store-empty-name-non-owner-id-400.json");
        final MockMvcResponse response = given()
                .contentType(JSON)
                .body(body)
                .when()
                .post(CREATE_STORE_URL)
                .then()
                .contentType(JSON)
                .statusCode(400)
                .body("field", hasItems("name", "owner_id"))
                .body("message", hasItems("must not be blank", "must not be null"))
                .extract()
                .response();
        outToLog(LOGGER, response);
    }

    @Test
    public void should_400_when_create_store_with_invalid_customer_id() {
        final String body = readJsonFrom("request/create-store-invalid-owner-id.json");
        final MockMvcResponse response = given()
                .contentType(JSON)
                .body(body)
                .when()
                .post(CREATE_STORE_URL)
                .then()
                .contentType(JSON)
                .statusCode(400)
                .extract()
                .response();
        outToLog(LOGGER, response);
    }

    @Test
    public void should_201_when_create_store_success() {
        String body = readJsonFrom("request/create-store-201.json");
        final MockMvcResponse response = given()
                .contentType(JSON)
                .body(body)
                .when()
                .post(CREATE_STORE_URL)
                .then()
                .contentType(JSON)
                .statusCode(201)
                .extract()
                .response();
        outToLog(LOGGER, response);
    }
}