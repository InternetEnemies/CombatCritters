package com.combatcritters.critterspring.battle.request;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * CritterController.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     11/28/24
 *
 * @PURPOSE:    defines a class a critter request controller
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface CritterController {
}
