package sanjiaotie.com;

import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;

// AnnotatedMethod表示一个加了注解的方法
public class AnnotatedMethod {

    private ExecutableElement annotatedMethodElement;
    private String simpleMethodName;
    private String simpleClassName;

    public AnnotatedMethod(ExecutableElement annotatedMethodElement) {
        this.annotatedMethodElement = annotatedMethodElement;
        simpleMethodName = annotatedMethodElement.getSimpleName().toString();
        /*
         * getEnclosingElement() 可以理解为该标签的父标签.
         * {@see Class.getEnclosingClass}
         */
        TypeElement parent = (TypeElement) annotatedMethodElement.getEnclosingElement();
        /*
         * Return the fully qualified name of this class or interface.
         * {@code java.util.Set<E>} is "java.util.Set"
         * {@code java.util.Map.Entry}is "java.util.Map.Entry"
         */
        simpleClassName = parent.getQualifiedName().toString();
    }

    public ExecutableElement getAnnotatedMethodElement() {
        return annotatedMethodElement;
    }

    public String getSimpleMethodName() {
        return simpleMethodName;
    }

    public String getSimpleClassName() {
        return simpleClassName;
    }

}
