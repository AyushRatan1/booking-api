package tests;

import api.UserApi;
import models.User;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import utils.TestDataFactory;

public class UserNegativeTest {
    @Test
    public void getUser_notFoundTest() {
        Response response = UserApi.getUser("non_existing_user_123");
        Assert.assertEquals(response.getStatusCode(), 404);
    }
    @Test
    public void deleteUser_notFoundTest() {
        Response response = UserApi.deleteUser("already_deleted_user");
        Assert.assertEquals(response.getStatusCode(), 404);
    }

}
