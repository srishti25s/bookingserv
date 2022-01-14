package annotators;

import com.fasterxml.jackson.databind.JsonNode;
import com.sun.codemodel.JAnnotationUse;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JFieldVar;
import org.jsonschema2pojo.AbstractAnnotator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

public class AnnotationEntity extends AbstractAnnotator {
    private JAnnotationUse annotation;

    @Override
    public void propertyField(JFieldVar field, JDefinedClass clazz, String propertyName, JsonNode propertyNode) {
        super.propertyField(field, clazz, propertyName, propertyNode);
        if (propertyNode.has("id_field")) {
            JAnnotationUse annotation;
            field.annotate(Id.class);
            clazz.annotate(Entity.class);
            annotation = field.annotate(GeneratedValue.class);
            annotation.param("strategy",GenerationType.IDENTITY);
        }

        if(propertyNode.has("oneToOne")) {
            JAnnotationUse annotation;
            annotation = field.annotate(OneToOne.class);
            annotation.param("cascade", CascadeType.ALL);
            annotation = field.annotate(JoinColumn.class);
            annotation.param("name", propertyNode.get("join_column").textValue());
            annotation.param("referencedColumnName", propertyNode.get("reference_id").textValue());
        }
        if(propertyNode.has("notnull") && propertyNode.get("notnull").booleanValue()) {
            field.annotate(NotNull.class);
        }
    }
}
