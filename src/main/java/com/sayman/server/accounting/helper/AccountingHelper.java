package com.sayman.server.accounting.helper;

import java.time.LocalDateTime;

public class AccountingHelper {
    /**
     * Returns true if startDate is later then endDate, otherwise returns false
     * @param startDate - start of the accounting item's expected date range.
     * @param endDate - end of the accounting item's expected date range.
     * @return boolean
     */
    public static boolean accountItemDateRangeValidator(LocalDateTime startDate, LocalDateTime endDate) {
        return startDate.compareTo(endDate) < 0;
    }
}
