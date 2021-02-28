package play.mongo.embedded.repository;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class TemplateVariable {
    private final String code;
    private final String description;

    public TemplateVariable(String code, String description) {
        this.code = code;
        this.description = description;
    }
}
