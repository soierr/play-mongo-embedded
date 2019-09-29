package play.mongo.embedded.repository;


import org.springframework.data.mongodb.repository.MongoRepository;

public interface TemplateVariableRepository extends MongoRepository<TemplateVariable, String> {
}
