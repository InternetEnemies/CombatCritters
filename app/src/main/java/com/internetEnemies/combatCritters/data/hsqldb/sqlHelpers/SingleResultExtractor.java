package com.internetEnemies.combatCritters.data.hsqldb.sqlHelpers;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * SingleResultExtractor.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     10/24/24
 * 
 * @PURPOSE:    provide extractors for a single result from sql
 */
public class SingleResultExtractor {
    public static <T> T extractSingleResult(ResultSet rs, IResultExtractor<T> extractor) throws SQLException {
        T result = null;
        if (rs.next()) {
            result = extractor.getResult(rs);
        }
        return result;
    }
    
    public static <T> IResultExtractor<T> getSingleResultExtractor(IResultExtractor<T> extractor) {
        return rs -> extractSingleResult(rs, extractor);
    }
}
