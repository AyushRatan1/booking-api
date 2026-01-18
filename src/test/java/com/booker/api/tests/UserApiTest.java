package com.booker.api.tests;

import com.booker.api.client.UserClient;
import com.booker.api.models.Address;
import com.booker.api.models.Company;
import com.booker.api.models.Geo;
import com.booker.api.models.User;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * API Tests for Users endpoint with complex nested objects
 * 
 * Tests cover 4 HTTP operations:
 * 1. GET - Read user with nested Address, Geo, Company
 * 2. POST - Create user with all nested objects
 * 3. PUT - Update user including nested objects
 * 4. DELETE - Remove a user
 */
public class UserApiTest {

    private UserClient client;

    @BeforeClass
    public void setup() {
        client = new UserClient();
    }

    // ===================================================================
    // TEST 1: GET - Retrieve user with nested objects
    // ===================================================================
    @Test(description = "GET /users/1 - Retrieve user with nested Address & Company")
    public void testGetUser() {
        Response response = client.getUser(1);

        // Verify status
        assertThat("Status should be 200", response.getStatusCode(), equalTo(200));

        // Verify main user fields
        assertThat("User ID should be 1",
                response.jsonPath().getInt("id"), equalTo(1));
        assertThat("Name should exist",
                response.jsonPath().getString("name"), equalTo("Leanne Graham"));
        assertThat("Username should exist",
                response.jsonPath().getString("username"), equalTo("Bret"));
        assertThat("Email should exist",
                response.jsonPath().getString("email"), not(emptyString()));

        // Verify nested Address object
        assertThat("Address street should exist",
                response.jsonPath().getString("address.street"), not(emptyString()));
        assertThat("Address city should exist",
                response.jsonPath().getString("address.city"), equalTo("Gwenborough"));

        // Verify deeply nested Geo object (inside Address)
        assertThat("Geo lat should exist",
                response.jsonPath().getString("address.geo.lat"), not(emptyString()));
        assertThat("Geo lng should exist",
                response.jsonPath().getString("address.geo.lng"), not(emptyString()));

        // Verify nested Company object
        assertThat("Company name should exist",
                response.jsonPath().getString("company.name"), not(emptyString()));
        assertThat("Company catchPhrase should exist",
                response.jsonPath().getString("company.catchPhrase"), not(emptyString()));

        System.out.println("GET: Retrieved user '" + response.jsonPath().getString("name") +
                "' from city '" + response.jsonPath().getString("address.city") + "'");
    }

    // ===================================================================
    // TEST 2: POST - Create user with all nested objects
    // ===================================================================
    @Test(description = "POST /users - Create user with nested Address, Geo, Company")
    public void testCreateUser() {
        // Build nested Geo object
        Geo geo = new Geo("40.7128", "-74.0060");

        // Build nested Address object (contains Geo)
        Address address = Address.builder()
                .street("123 Main Street")
                .suite("Suite 100")
                .city("New York")
                .zipcode("10001")
                .geo(geo)
                .build();

        // Build nested Company object
        Company company = new Company(
                "TechCorp Inc",
                "Innovation through technology",
                "enterprise solutions");

        // Build main User object (contains Address and Company)
        User newUser = User.builder()
                .name("John Smith")
                .username("jsmith")
                .email("john.smith@techcorp.com")
                .phone("555-123-4567")
                .website("techcorp.com")
                .address(address)
                .company(company)
                .build();

        // Make API call
        Response response = client.createUser(newUser);

        // Verify status
        assertThat("Status should be 201 Created",
                response.getStatusCode(), equalTo(201));

        // Verify response contains our data
        assertThat("ID should be 11 (next after existing 10)",
                response.jsonPath().getInt("id"), equalTo(11));
        assertThat("Name should match",
                response.jsonPath().getString("name"), equalTo("John Smith"));
        assertThat("Email should match",
                response.jsonPath().getString("email"), equalTo("john.smith@techcorp.com"));

        // Verify nested objects were sent
        assertThat("Address city should match",
                response.jsonPath().getString("address.city"), equalTo("New York"));
        assertThat("Geo lat should match",
                response.jsonPath().getString("address.geo.lat"), equalTo("40.7128"));
        assertThat("Company name should match",
                response.jsonPath().getString("company.name"), equalTo("TechCorp Inc"));

        System.out.println("POST: Created user '" + response.jsonPath().getString("name") +
                "' with ID " + response.jsonPath().getInt("id"));
    }

    // ===================================================================
    // TEST 3: PUT - Update user including nested objects
    // ===================================================================
    @Test(description = "PUT /users/1 - Update user with new nested data")
    public void testUpdateUser() {
        // Build updated nested objects
        Geo updatedGeo = new Geo("51.5074", "-0.1278");

        Address updatedAddress = Address.builder()
                .street("221B Baker Street")
                .suite("Flat B")
                .city("London")
                .zipcode("NW1 6XE")
                .geo(updatedGeo)
                .build();

        Company updatedCompany = new Company(
                "Updated Corp",
                "New and improved",
                "innovative solutions");

        User updatedUser = User.builder()
                .name("Updated Name")
                .username("updateduser")
                .email("updated@example.com")
                .phone("999-999-9999")
                .website("updated.com")
                .address(updatedAddress)
                .company(updatedCompany)
                .build();

        // Make API call
        Response response = client.updateUser(1, updatedUser);

        // Verify status
        assertThat("Status should be 200", response.getStatusCode(), equalTo(200));

        // Verify updates
        assertThat("ID should still be 1",
                response.jsonPath().getInt("id"), equalTo(1));
        assertThat("Name should be updated",
                response.jsonPath().getString("name"), equalTo("Updated Name"));
        assertThat("Address city should be updated",
                response.jsonPath().getString("address.city"), equalTo("London"));
        assertThat("Geo lat should be updated",
                response.jsonPath().getString("address.geo.lat"), equalTo("51.5074"));
        assertThat("Company should be updated",
                response.jsonPath().getString("company.name"), equalTo("Updated Corp"));

        System.out.println("PUT: Updated user to '" + response.jsonPath().getString("name") +
                "' in city '" + response.jsonPath().getString("address.city") + "'");
    }

    // ===================================================================
    // TEST 4: DELETE - Remove a user
    // ===================================================================
    @Test(description = "DELETE /users/1 - Delete a user")
    public void testDeleteUser() {
        Response response = client.deleteUser(1);

        assertThat("Status should be 200", response.getStatusCode(), equalTo(200));
        assertThat("Response should be empty object", response.getBody().asString(), equalTo("{}"));

        System.out.println("DELETE: User deleted successfully");
    }
}
