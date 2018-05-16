package com.example.demo.annotation;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.example.demo.annotation.CheckCase.List;
import com.example.demo.model.CaseMode;
import com.example.demo.validator.CheckCaseValidator;

@Target({ FIELD, METHOD, PARAMETER, ANNOTATION_TYPE, TYPE_USE })
@Retention(RUNTIME)
@Constraint(validatedBy = CheckCaseValidator.class)
@Documented
@Repeatable(List.class)
public @interface CheckCase {

    String message() default "Upper / Lower case only.";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

    CaseMode value();

    @Target({ FIELD, METHOD, PARAMETER, ANNOTATION_TYPE })
    @Retention(RUNTIME)
    @Documented
    @interface List {
        CheckCase[] value();
    }
}






























//Customized Hibernate Validator:
//-------------------------------
//http://docs.jboss.org/hibernate/stable/validator/reference/en-US/html_single/#validator-customconstraints-simple
//https://www.sitepoint.com/effective-domain-model-validation-with-hibernate-validator/
//http://in.relation.to/2017/03/02/adding-custom-constraint-definitions-via-the-java-service-loader/
//https://github.com/infobip/infobip-bean-validation
//https://github.com/nomemory/java-bean-validation-extension


//Customized Annotation:
//---------------------
//https://blog.csdn.net/javazejian/article/details/71860633

//https://dzone.com/articles/creating-custom-annotations-in-java
//https://beginnersbook.com/2014/09/java-annotations/
//http://www.cnblogs.com/peida/archive/2013/04/24/3036689.html















