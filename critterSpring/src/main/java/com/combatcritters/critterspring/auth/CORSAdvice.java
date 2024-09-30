package com.combatcritters.critterspring.auth;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@ControllerAdvice
public class CORSAdvice implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    /*
    A note to anyone looking at this code, I spent 8+ hours trying to resolve spring not sending cors headers, several of
    which were spent trying to get spring to set them statically for all responses (as it turns out @RestControllers can't 
    use Interceptors which is several of the top stack overflow results). I can guarantee that if you search how to setup
    cors in spring you will find things that look like they should work (including the spring docs) and are better solutions than
    this. I probably tried them, and they didn't work for me. Don't waste time here.
    - jkl
     */
    /**
     * adds static headers to all (non error) responses
     */
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, 
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType, 
                                  ServerHttpRequest request, ServerHttpResponse response) {
        
        response.getHeaders().add("Access-Control-Allow-Origin", "*");
        return body;
    }
}
