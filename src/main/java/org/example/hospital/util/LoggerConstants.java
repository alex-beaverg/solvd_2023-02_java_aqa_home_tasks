package org.example.hospital.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class LoggerConstants {
    public static final Logger LOGGER;
    public static final Logger LOGGER_LN;
    public static final Logger LN_LOGGER_LN;

    static {
        LOGGER = LogManager.getLogger("InsteadOfSOUT");
        LOGGER_LN = LogManager.getLogger("InsteadOfSOUT_ln");
        LN_LOGGER_LN = LogManager.getLogger("ln_InsteadOfSOUT_ln");
    }
}
