package com.example.tasklist.web.security.expression;

import com.example.tasklist.service.UserService;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.context.ApplicationContext;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.core.Authentication;

public class CustomSecurityExceptionHandler extends DefaultMethodSecurityExpressionHandler {

    private final AuthenticationTrustResolver trustResolver = new AuthenticationTrustResolverImpl();
    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        super.setApplicationContext(applicationContext);
        this.applicationContext = applicationContext;
    }

    @Override
    protected MethodSecurityExpressionOperations createSecurityExpressionRoot(Authentication authentication, MethodInvocation invocation) {
        CustomMethodSecurityExpression root = new CustomMethodSecurityExpression(authentication);
        root.setTrustResolver(trustResolver);
        root.setPermissionEvaluator(getPermissionEvaluator());
        root.setRoleHierarchy(getRoleHierarchy());
        root.setUserService(this.applicationContext.getBean(UserService.class));
        return root;
    }
}
