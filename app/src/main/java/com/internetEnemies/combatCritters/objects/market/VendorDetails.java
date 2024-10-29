package com.internetEnemies.combatCritters.objects.market;

/**
 * VendorDetails.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     10/29/24
 * 
 * @PURPOSE:    represent a vendors details
 * 
 * @param id id of the vendor
 * @param image image used by the vendor
 * @param name name of the vendor
 * @param refreshInterval refresh time interval in seconds
 */
public record VendorDetails(int id, String image, String name, long refreshInterval) { }
