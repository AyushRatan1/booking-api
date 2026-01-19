package constants;

public final class Routes {
    // User APIs constants
    private static final String USER = "/user";

    public static final String CREATE_USER = USER;
    public static final String GET_USER = USER + "/{username}";
    public static final String UPDATE_USER = USER + "/{username}";
    public static final String DELETE_USER = USER + "/{username}";

    public static final String LOGIN_USER = USER + "/login";
    public static final String LOGOUT_USER = USER + "/logout";
    public static final String CREATE_USERS_WITH_ARRAY = USER + "/createWithArray";

    // Store Apis constant

    public static final String GET_INVENTORY = "/store/inventory";
    public static final String PLACE_ORDER = "/store/order";
    public static final String GET_ORDER_BY_ID = "/store/order/{orderId}";
    public static final String DELETE_ORDER = "/store/order/{orderId}";

    private Routes() {}
}
