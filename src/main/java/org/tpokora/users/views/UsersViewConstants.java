package org.tpokora.users.views;

import static org.tpokora.common.CommonConstants.SLASH;

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

    /**
     * Class is providing static constant strings for HomeViewController
     */
    private UsersViewConstants() {}
}
