package com.example.Newsline.aop;

import com.example.Newsline.model.User;
import com.example.Newsline.service.impl.DatabaseCommentService;
import com.example.Newsline.service.impl.DatabaseNewsService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.HandlerMapping;

import java.text.MessageFormat;
import java.util.Map;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    /** Вы можете реализовать аспект, который будет выполняться перед методом, помеченным вашей аннотацией.
     * Например, @Before("@annotation(myAnnotation)") означает, что этот аспект будет выполняться перед методом, который помечен аннотацией myAnnotation.
     Вы можете получить HttpServletRequest за пределами контроллера, используя контекст:
     RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
     HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
     С помощью этого объекта вы можете извлечь переменные пути запроса:
     var pathVariables = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
     Также вы можете извлечь и GET-параметры запроса:
     request.getParameter(“paramName”) **/

    @Autowired
    DatabaseNewsService databaseNewsService;

    @Autowired
    DatabaseCommentService databaseCommentService;

    @Before("@annotation(Loggable)")
    public void logBefore(JoinPoint joinPoint) {

        log.info("Before execution of {}", joinPoint.getSignature().getName());

        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        var pathVariables = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);

//        log.info("userId " + request.getParameter("userId"));
//
//        log.info("pathVariables");
//        for (String name: pathVariables.keySet()) {
//            String key = name.toString();
//            String value = pathVariables.get(name);
//            log.info(key + " " + value);
//        }

        Long userId = Long.valueOf(request.getParameter("userId"));

        if (pathVariables.containsKey("commentId")) {
            ifUserIsCommentAuthor(userId, Long.valueOf(pathVariables.get("commentId")));
        }

        if (pathVariables.containsKey("newsId")) {
            ifUserIsNewsAuthor(userId, Long.valueOf(pathVariables.get("newsId")));
        }

    }

    private void ifUserIsNewsAuthor(Long userId, Long newsId) {

        User newsAuthor = databaseNewsService.findById(newsId).getNewsAuthor();
        if (newsAuthor.getId() != userId) {
            throw new IllegalArgumentException(MessageFormat.format(
                    "Автор новости: {0}. Нельзя изменять и удалять чужие новости.", newsAuthor.getFullName()
            ));
        }
    }

    private void ifUserIsCommentAuthor(Long userId, Long commentId) {

        User commentAuthor = databaseCommentService.findById(commentId).getCommentAuthor();
        if (commentAuthor.getId() != userId) {
            throw new IllegalArgumentException(MessageFormat.format(
                    "Автор комментария: {0}. Нельзя изменять и удалять чужие комментарии.", commentAuthor.getFullName()
            ));
        }
    }

//    @After("@annotation(Loggable)")
//    public void logAfter(JoinPoint joinPoint){
//        log.info("After execution of {}", joinPoint.getSignature().getName());
//    }
//
//    @AfterReturning("@annotation(Loggable)")
//    public void logAfterReturning(JoinPoint joinPoint, Object result){
//        log.info("After returning from {}, with result {}", joinPoint.getSignature().getName(), result);
//    }
}
