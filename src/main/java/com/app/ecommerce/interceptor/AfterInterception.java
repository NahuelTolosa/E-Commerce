package com.app.ecommerce.interceptor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AfterInterception {

    private static final Logger logger = LogManager.getLogger(AfterInterception.class);

    @Pointcut("@annotation(com.app.ecommerce.annotation.ServiceMethodAnnotation)")
    void serviceMethods() {}

    @After("serviceMethods()")
    void beforeServiceMethods() {
    }


    /**************************************************************************************/


    @Pointcut("execution(* com.app.ecommerce.controller.ProductController.findAll(..))")
    void finAll(){}

    @After("finAll()")
    void afterFindAll() {
        logger.info("Se solicito ver un listado de todos los productos.");
    }


    /**************************************************************************************/


    @Pointcut("execution(* com.app.ecommerce.controller.ProductController.findByName(..))")
    void findByName(){}

    @After("findByName()")
    void afterFindByName() {
        logger.info("Se solicito ver los datos de un producto a partir del nombre del mismo.");
    }


    /**************************************************************************************/


    @Pointcut("execution(* com.app.ecommerce.controller.ProductController.findByCategory(..))")
    void findByCategory(){}

    @After("findByCategory()")
    void afterFindByCategory() {
        logger.info("Se solicito ver una lista de productos que pertenezcan a una categoria pasada por parametro.");
    }


    /**************************************************************************************/



}
