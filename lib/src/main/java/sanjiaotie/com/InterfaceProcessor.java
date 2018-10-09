package sanjiaotie.com;

import com.google.auto.service.AutoService;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Completion;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import javax.tools.Diagnostic;

import sanjiaotie.com.interface_apt.AutoInterface;

@AutoService(Processor.class)
public class InterfaceProcessor extends AbstractProcessor {

    private Types typeUtils;
    // Element代表程序的元素，例如包、类或者方法。每个Element代表一个静态的、语言级别的构件
    private Elements elementUtils;

    private Filer filer;
    private Messager messager;


    @Override
    public Set<String> getSupportedOptions() {
        return super.getSupportedOptions();
    }

    /**
     * 返回我们定义的Interface注解
     * @return
     */
    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> types = new TreeSet<>();
        types.add(AutoInterface.class.getCanonicalName());
        return types;
    }

    /**
     * 返回最近的版本
     * @return
     */
    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    /**
     * 对一些工具进行初始化
     * @param processingEnvironment
     */
    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        elementUtils = processingEnvironment.getElementUtils();
        filer = processingEnvironment.getFiler();
        typeUtils = processingEnvironment.getTypeUtils();
        messager = processingEnvironment.getMessager();
    }

    /**
     * 真正生成java代码的地方
     * @param set
     * @param roundEnvironment
     * @return
     */
    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        Map<String, AnnotatedClass> classMap = new HashMap<>();
        // 得到所有注解@Interface的Element集合
        Set<? extends Element> elementSet = roundEnvironment.getElementsAnnotatedWith(AutoInterface.class);

        // 解析数据
        for (Element e: elementSet) {
            if (e.getKind() != ElementKind.METHOD) {
                error(e, "错误的注解类型,只有函数能够处理 @%s 该注解", AutoInterface.class.getSimpleName());
                return true;
            }
            ExecutableElement element = (ExecutableElement) e;
            AnnotatedMethod annotatedMethod = new AnnotatedMethod(element);
            String classname = annotatedMethod.getSimpleClassName();
            AnnotatedClass annotatedClass = classMap.get(classname);
            if (annotatedClass == null) {
                PackageElement pkg = elementUtils.getPackageOf(element);
                annotatedClass = new AnnotatedClass(pkg.getQualifiedName().toString(),
                        element.getAnnotation(AutoInterface.class).value().toString());
                annotatedClass.addMethod(annotatedMethod);
                classMap.put(classname, annotatedClass);
            } else {
                annotatedClass.addMethod(annotatedMethod);
            }
        }
//
        // 生成代码
        for (AnnotatedClass annotatedClass : classMap.values()) {
            annotatedClass.generateCode(elementUtils, filer);
        }
        return false;
        // return false;
    }

    @Override
    public Iterable<? extends Completion> getCompletions(Element element, AnnotationMirror annotationMirror,
                                                         ExecutableElement executableElement, String s) {
        return super.getCompletions(element, annotationMirror, executableElement, s);
    }

    @Override
    protected synchronized boolean isInitialized() {
        return super.isInitialized();
    }

    // 注解的错误输出
    private void error(Element e, String msg, Object... args) {
        messager.printMessage(Diagnostic.Kind.ERROR, String.format(msg, args), e);
    }


}
