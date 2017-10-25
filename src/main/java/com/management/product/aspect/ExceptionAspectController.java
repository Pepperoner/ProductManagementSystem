package com.management.product.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ExceptionAspectController {

    private static final Logger LOGGER = Logger.getLogger(ExceptionAspectController.class);

    @AfterThrowing(
            pointcut = "execution(* com.management.product..controller..*(..))",
            throwing = "ex"
    )
    public void afterThrowingAdvice(Exception ex) {
        LOGGER.error("EXCEPTION IN METHOD " + ex.getClass());
    }
}
