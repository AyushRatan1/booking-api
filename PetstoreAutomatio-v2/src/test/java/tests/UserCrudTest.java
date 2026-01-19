package tests;

import api.UserApi;
import models.User;
import models.UserResponse;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.response.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import utils.TestDataFactory;

public class UserCrudTest {

    private User user;

    @BeforeClass
    public void setupTestData() {
        user = TestDataFactory.createRandomUser();

    }

    @Test(priority = 1)
    public void createUserTest() {
        Response response = UserApi.createUser(user);
        Assert.assertEquals(response.getStatusCode(), 200);

        UserResponse createResponse = response.as(UserResponse.class);
        Assert.assertEquals(createResponse.getCode(), 200);
    }

    @Test(priority = 2, dependsOnMethods = "createUserTest")
    public void getUserTest() {
        Response response = UserApi.getUser(user.getUsername());
        Assert.assertEquals(response.getStatusCode(), 200);

        // safer assertion
        Assert.assertEquals(
                response.jsonPath().getString("username"),
                user.getUsername()
        );
    }

    @Test(priority = 3, dependsOnMethods = "getUserTest")
    public void loginUserTest() {
        Response response = UserApi.loginUser(user.getUsername(), user.getPassword());
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(priority = 4, dependsOnMethods = "loginUserTest")
    public void logoutUserTest() {
        Response response = UserApi.logoutUser();
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(priority = 5, dependsOnMethods = "logoutUserTest")
    public void updateUserTest() {
        user.setFirstName("UpdatedName");

        Response response = UserApi.updateUser(user.getUsername(), user);
        Assert.assertEquals(response.getStatusCode(), 200);

        UserResponse updateResponse = response.as(UserResponse.class);
        Assert.assertEquals(updateResponse.getCode(), 200);
    }

    @Test(priority = 6, dependsOnMethods = "updateUserTest")
    public void deleteUserTest() {
        Response response = UserApi.deleteUser(user.getUsername());
        Assert.assertEquals(response.getStatusCode(), 200);

        UserResponse deleteResponse = response.as(UserResponse.class);
        Assert.assertEquals(deleteResponse.getCode(), 200);
    }

    @Test(priority = 7)
    public void createUsersWithArrayTest() {
        List<User> users = TestDataFactory.createUserList(10);

        Response response = UserApi.createUsersWithArray(users);
        Assert.assertEquals(response.getStatusCode(), 200);
    }
}
