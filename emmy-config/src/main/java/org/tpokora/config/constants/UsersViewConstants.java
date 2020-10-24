package org.tpokora.config.constants;

import static org.tpokora.config.constants.CommonConstants.SLASH;

public class UsersViewConstants {

    public static final String USERS_VIEW = "users";
    public static final String USERS_VIEW_URL = SLASH + USERS_VIEW;
    public static final String USERS_VIEW_TEMPLATE = USERS_VIEW + SLASH + USERS_VIEW;

    public static final String ROLES_VIEW = "roles";
    public static final String ROLES_VIEW_URL = USERS_VIEW_URL + SLASH + ROLES_VIEW;
    public static final String ROLES_VIEW_TEMPLATE =  USERS_VIEW + SLASH + ROLES_VIEW;

    public static final String PROFILE_VIEW = "profile";
    public static final String PROFILE_VIEW_URL = USERS_VIEW_URL + SLASH + PROFILE_VIEW;
    public static final String PROFILE_VIEW_TEMPLATE =  USERS_VIEW + SLASH + PROFILE_VIEW;

    public static final String PROFILE_CHANGE_USERNAME = USERS_VIEW_URL + SLASH + PROFILE_VIEW + "/changeUsername";
    public static final String PROFILE_CHANGE_EMAIL = USERS_VIEW_URL + SLASH + PROFILE_VIEW + "/changeEmail";
    public static final String PROFILE_CHANGE_PASSWORD = USERS_VIEW_URL + SLASH + PROFILE_VIEW + "/changePassword";

    /**
     * Class is providing static constant strings for HomeViewController
     */
    private UsersViewConstants() {}
}
