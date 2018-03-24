package pl.jakubpiecuch.accounts.model;

/**
 * Created by Rico on 2015-03-27.
 */
public class Permissions {

    public static final String ROLE_PREFIX = "ROLE_";

    public static final String PLAN_CREATOR = "PLAN_C";
    public static final String PLAN_UPDATER = "PLAN_U";
    public static final String PLAN_DELETER = "PLAN_D";
    public static final String PLAN_VIEWER = "PLAN_V";
    public static final String PLAN_APPROVER = "PLAN_A";
    public static final String DESCRIPTION_CREATOR = "DESCRIPTION_C";
    public static final String DESCRIPTION_UPDATER = "DESCRIPTION_U";
    public static final String DESCRIPTION_DELETER = "DESCRIPTION_D";
    public static final String DESCRIPTION_VIEWER = "DESCRIPTION_V";
    public static final String DESCRIPTION_APPROVER = "DESCRIPTION_A";
    public static final String EQUIPMENT_CREATOR = "EQUIPMENT_C";
    public static final String EQUIPMENT_UPDATER = "EQUIPMENT_U";
    public static final String EQUIPMENT_DELETER = "EQUIPMENT_D";
    public static final String EQUIPMENT_VIEWER = "EQUIPMENT_V";
    public static final String EQUIPMENT_APPROVER = "EQUIPMENT_A";
    public static final String EXECUTION_UPDATER = "EXECUTION_U";
    public static final String ROLE_VIEWER = "ROLE_V";
    public static final String ROLE_CREATOR = "ROLE_C";
    public static final String ROLE_UPDATER = "ROLE_U";
    public static final String ROLE_DELETER = "ROLE_D";
    public static final String WORKOUT_VIEWER = "WORKOUT_V";
    public static final String WORKOUT_UPDATER = "WORKOUT_U";
    public static final String ACCOUNT_RECORD_VIEWER = "ACCOUNT_RECORD_V";
    public static final String ACCOUNT_RECORD_CREATOR = "ACCOUNT_RECORD_C";
    public static final String ACCOUNT_RECORD_UPDATER = "ACCOUNT_RECORD_U";
    public static final String ACCOUNT_RECORD_DELETER = "ACCOUNT_RECORD_D";
    public static final String PLAN_STARTER_CREATOR = "PLAN_STARTER_C";
    public static final String USER_RECORD_VIEWER = "USER_RECORD_V";
    public static final String USER_RECORD_CREATOR = "USER_RECORD_C";
    public static final String USER_RECORD_UPDATER = "USER_RECORD_U";
    public static final String USER_RECORD_DELETER = "USER_RECORD_D";

    private static final String[] ALL = new String[]{
            PLAN_CREATOR, PLAN_UPDATER, PLAN_DELETER, PLAN_VIEWER, PLAN_APPROVER,
            EQUIPMENT_CREATOR, EQUIPMENT_UPDATER, EQUIPMENT_DELETER, EQUIPMENT_VIEWER, EQUIPMENT_APPROVER,
            DESCRIPTION_CREATOR, DESCRIPTION_UPDATER, DESCRIPTION_DELETER, DESCRIPTION_VIEWER, DESCRIPTION_APPROVER,
            ROLE_CREATOR, ROLE_UPDATER, ROLE_DELETER, ROLE_VIEWER,
            WORKOUT_VIEWER, WORKOUT_UPDATER,
            EXECUTION_UPDATER,
            ACCOUNT_RECORD_VIEWER, ACCOUNT_RECORD_CREATOR, ACCOUNT_RECORD_UPDATER, ACCOUNT_RECORD_DELETER,
            USER_RECORD_VIEWER, USER_RECORD_CREATOR, USER_RECORD_UPDATER, USER_RECORD_DELETER,
            PLAN_STARTER_CREATOR};

    private static final String[] USER_ROLE_PERMISSIONS = new String[]{
            PLAN_VIEWER,
            EQUIPMENT_VIEWER,
            DESCRIPTION_VIEWER,
            WORKOUT_VIEWER, WORKOUT_UPDATER,
            EXECUTION_UPDATER,
            USER_RECORD_VIEWER,
            PLAN_STARTER_CREATOR};

    private Permissions() {
    }

    public static String[] getAllPermissions() {
        return ALL;
    }

    public static String[] getUserPermissions() {
        return USER_ROLE_PERMISSIONS;
    }
}
