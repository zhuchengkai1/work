package buildsite.service.impl;

import buildsite.model.Environment;
import buildsite.service.EnvironmentService;
import org.springframework.stereotype.Service;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class EnvironmentServiceImpl implements EnvironmentService {

    private static final Logger LOG = LoggerFactory.getLogger(MapServiceImpl.class);

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public Environment add(Environment environment) {
        if ( environment == null){
            LOG.error("input environment is null");
            return null;
        }

        return mongoTemplate.insert(environment);
    }

    @Override
    public Environment get(String id) {
        if (!StringUtils.hasText(id)){
            LOG.error("input environment id is blank");
            return null;
        }
        Environment environment= mongoTemplate.findById(id, Environment.class);
        return environment;
    }

    @Override
    public List<Environment> getAll() {

        return null;
    }

    @Override
    public boolean delete(String id) {
        if (!StringUtils.hasText(id)){
            LOG.error("input environment id is blank");
            return false;
        }

        Environment environment = new Environment();
        environment.setId(id);

        DeleteResult result = mongoTemplate.remove(environment);
        return result != null && result.getDeletedCount() > 0;
    }
}
