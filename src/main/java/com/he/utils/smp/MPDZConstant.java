package com.he.utils.smp;

public class MPDZConstant {

    //public static final String SPDZ_PATH = "/home/ty/Workspace/smpc/MP-SPDZ";
    public static final String SPDZ_PATH = "/opt/he_project/backend/mpc/mp-spdz-0.3.0";

    public static final String PROGRAM_NAME = "testgp.mpc";

    public static final String PROGRAM_PATH = SPDZ_PATH + "/Programs/Source/" + PROGRAM_NAME;

    public static final String COMILER = SPDZ_PATH + "/compile.py";

    public static final String KEY_GENERATION = SPDZ_PATH + "/Scripts/setup-ssl.sh";

    public static final Integer JOINER_COUNT = 2;

    public static final String[] USER_INPUTS = {SPDZ_PATH + "/Player-Data/Input-P0-0", SPDZ_PATH + "/Player-Data/Input-P1-0"};

    // public static final String EXECUTOR = SPDZ_PATH + "/semi-bmr-party.x";

    public static final String EXECUTOR = SPDZ_PATH + "/semi-bmr-party.x";
    
}
