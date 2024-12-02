package com.combatcritters.critterspring.battle.request;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * CritterRequest.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     11/28/24
 *
 * @PURPOSE:    define that a method is a critterRequest handler and what resource it handles
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface CritterRequest {
    String value() default "";
}
