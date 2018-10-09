package sanjiaotie.com;

import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.processing.Filer;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.VariableElement;
import javax.lang.model.util.Elements;

/**
 * 一个Annotate类，里面存储了各种AnnotatedMethod
 */
public class AnnotatedClass {

    private String className;
    private String packageName;
    private List<AnnotatedMethod> methods = new LinkedList<>();


    /**
     * @param packageName       将要生成的类的包名
     * @param generateClassName 将要生成的类的类名
     */
    public AnnotatedClass(String packageName, String generateClassName) {
        this.className = generateClassName;
        this.packageName = packageName;
    }

    // 生成Java代码部分，现在可以不看
    public void generateCode(Elements elementUtils, Filer filer) {

        TypeSpec.Builder typeBuilder = TypeSpec.interfaceBuilder(className).addModifiers(Modifier.PUBLIC);

        for (AnnotatedMethod m : methods) {
            MethodSpec.Builder methodBuilder =
                    MethodSpec.methodBuilder(m.getSimpleMethodName())
                            .addModifiers(Modifier.ABSTRACT, Modifier.PUBLIC)
                            .returns(TypeName.get(m.getAnnotatedMethodElement().getReturnType()));

            int i = 1;
            for (VariableElement e : m.getAnnotatedMethodElement().getParameters()) {
                methodBuilder.addParameter(TypeName.get(e.asType()), "param" + String.valueOf(i));
                ++i;
            }
            typeBuilder.addMethod(methodBuilder.build());
        }
        JavaFile javaFile = JavaFile.builder(packageName, typeBuilder.build()).build();


        try {
            javaFile.writeTo(filer);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

    }

    public void addMethod(AnnotatedMethod annotatedMethod) {
        methods.add(annotatedMethod);
    }
}
