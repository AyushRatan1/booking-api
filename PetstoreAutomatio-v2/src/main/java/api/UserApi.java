package api;

import constants.Routes;
import io.restassured.response.Response;
import models.User;
import utils.RestUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserApi {

    private UserApi() {
        // prevent object creation
    }

    public static Response createUser(User user) {
        return RestUtils.post(Routes.CREATE_USER, user);
    }

    public static Response getUser(String username) {
        Map<String, String> pathParams = new HashMap<>();
        pathParams.put("username", username);

        return RestUtils.get(Routes.GET_USER, pathParams);
    }

    public static Response updateUser(String username, User user) {
        Map<String, String> pathParams = new HashMap<>();
        pathParams.put("username", username);

        return RestUtils.put(Routes.UPDATE_USER, pathParams, user);
    }

    public static Response deleteUser(String username) {
        Map<String, String> pathParams = new HashMap<>();
        pathParams.put("username", username);

        return RestUtils.delete(Routes.DELETE_USER, pathParams);
    }

    public static Response loginUser(String username, String password) {
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("username", username);
        queryParams.put("password", password);

        return RestUtils.getWithQueryParams(Routes.LOGIN_USER, queryParams);
    }

    public static Response logoutUser() {
        return RestUtils.get(Routes.LOGOUT_USER, Map.of());
    }

    public static Response createUsersWithArray(List<User> users) {
        return RestUtils.post(Routes.CREATE_USERS_WITH_ARRAY, users);
    }
}
