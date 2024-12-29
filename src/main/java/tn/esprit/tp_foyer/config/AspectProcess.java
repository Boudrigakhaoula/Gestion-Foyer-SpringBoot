package tn.esprit.tp_foyer.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;


@Component
@Aspect
@Slf4j
public class AspectProcess {
    @Before("execution(* tn.esprit.tp_foyer.service.FoyerServicelmpl.*(..))")
    public void logMethodEntry(JoinPoint joinPoint) {
        String name = joinPoint.getSignature().getName();
        log.info("**** In method "+ name + ":");
    }
    //log apres l'execution de chaque méthode du service Foyerserviceimlp

    @After("execution(* tn.esprit.tp_foyer.service.FoyerServicelmpl.*(..))")
    public void logMethodExit(JoinPoint joinPoint) {
        String name = joinPoint.getSignature().getName();
        log.info("**** Exiting method "+ name );
    }
}
