package play.mongo.embedded;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import play.mongo.embedded.repository.TemplateVariable;
import play.mongo.embedded.repository.TemplateVariableRepository;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MongoTest {
    @Autowired
    private TemplateVariableRepository templateVariableRepository;

    @Test
    public void testMongo() {
        templateVariableRepository.save(new TemplateVariable("code", "desc"));
        List<TemplateVariable> templateVariableList = templateVariableRepository.findAll();
        System.out.println("Hello there");
    }
}
