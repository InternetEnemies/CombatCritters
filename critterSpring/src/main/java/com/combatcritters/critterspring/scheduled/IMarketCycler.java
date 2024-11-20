package com.combatcritters.critterspring.scheduled;

import java.util.Date;

/**
 * IMarketCycler.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     11/17/24
 * 
 * @PURPOSE:    provide market cycle related queries
 */
public interface IMarketCycler {
    /**
     * get the refesh time of the vendor with the given id
     * @param vendorId id of the vendor to get refresh time of
     * @return datetime of the refresh
     */
    Date getRefresh(int vendorId);
}
