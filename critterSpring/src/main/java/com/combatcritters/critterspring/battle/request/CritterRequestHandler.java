package com.combatcritters.critterspring.battle.request;

import com.combatcritters.critterspring.battle.payloads.BattleRequest;
import com.combatcritters.critterspring.battle.playerSession.IPlayerSession;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * CritterRequestHandler.java
 * COMP 4350
 * @Project     Combat Critters 2.0
 * @created     11/28/24
 *
 * @PURPOSE:    handle critter requests by forwarding them to their respective controller
 */
public class CritterRequestHandler implements ICritterRequestHandler{
    private final Map<String, Method> methods;
    private final Map<String, Object> handlers;

    public CritterRequestHandler(){
        methods = new HashMap<>();
        handlers = new HashMap<>();
    }

    @Override
    public void register(Object requestHandler) {
        Class<?> clazz = requestHandler.getClass();
        if(clazz.getAnnotation(CritterController.class) == null){
            // this check isn't strictly necessary but if I wanted to do something more generic it might be nice to have
            throw new RuntimeException("Cannot register instance of class not annotated with @CritterController");
        }

        //register methods and their handlers
        for (Method method : clazz.getDeclaredMethods()) {
            CritterRequest reqDef = method.getDeclaredAnnotation(CritterRequest.class);
            if(reqDef != null){
                methods.put(reqDef.value(), method); // todo check that the method params are setup correctly
                handlers.put(reqDef.value(),requestHandler);
            }
        }
    }

    @Override
    public void handle(IPlayerSession session, BattleRequest battleRequest) {// todo error handling done here
        Method method = methods.get(battleRequest.resource());
        if(method == null) return; //todo throw error / return error response
        Class<?> paramType= method.getParameterTypes()[1];
        ObjectMapper mapper = new ObjectMapper();

        try {
            Object obj = mapper.readValue(battleRequest.payload(),paramType); // get payload object
            method.setAccessible(true);
            method.invoke(handlers.get(battleRequest.resource()),session,obj);
        } catch (JsonProcessingException e) { // bad request
            //todo handle
        } catch (InvocationTargetException e) { // error thrown by method
            Throwable cause = e.getCause();
            //todo handle cases (such as BattleInputException
        } catch (IllegalAccessException e) { // server error
            throw new RuntimeException(e);
        }
    }
}
