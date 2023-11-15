package com.solvd.hospital_project.hospital.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Printers {
    public static final Logger PRINT;
    public static final Logger PRINTLN;
    public static final Logger PRINT2LN;

    static {
        PRINT = LogManager.getLogger("InsteadOfSOUT");
        PRINTLN = LogManager.getLogger("InsteadOfSOUT_ln");
        PRINT2LN = LogManager.getLogger("ln_InsteadOfSOUT_ln");
    }
}
