package com.internetEnemies.combatCritters.data.hsqldb.DSOHelpers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * extract lists of items from a sql result set
 */
public class ResultListExtractor {
    /**
     * get list of results from a result set
     * @param rs result set to process
     * @param extractor callback to process the individual results
     * @return list of objects from the result set
     */
    public static <T> List<T> getResults(ResultSet rs, IResultExtractor<T> extractor) throws SQLException {
        List<T> results = new ArrayList<>();
        // extract until there are no results left
        while(rs.next()) {
            results.add(extractor.getResult(rs));
        }
        return results;
    }
}

