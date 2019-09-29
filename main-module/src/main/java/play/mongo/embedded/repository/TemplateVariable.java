package play.mongo.embedded.repository;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class TemplateVariable {
    private String code;
    private String description;

    public TemplateVariable(String code, String description) {
        this.code = code;
        this.description = description;
    }
}
