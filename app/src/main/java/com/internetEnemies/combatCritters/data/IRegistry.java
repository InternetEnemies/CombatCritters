/**
 * IRegistry.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     2024-02-03
 *
 * @PURPOSE:    interface for registry
 */

package com.internetEnemies.combatCritters.data;


import java.util.List;

public interface IRegistry<T>{

     /**
      * get the object with given id
      * @param id id of object to be found
      * @return Object with id
      */
     T getSingle(int id);
     /**
      * get list of objects given a list of ids
      * @param ids list of ids of objects needed
      * @return list of objects
      */
     List<T> getListOf(List<Integer> ids);

     /**
      * get list of all objects stored in this registry
      * @return list of objects
      */
     List<T> getAll();

     /**
      * add object to the registry
      * @param obj object to add
      * @return created object
      */
     T add(T obj);

}
