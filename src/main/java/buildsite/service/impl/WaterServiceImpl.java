package buildsite.service.impl;

import buildsite.service.WaterService;
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

@Service
public class WaterServiceImpl implements WaterService {
    private static final Logger LOG = LoggerFactory.getLogger(MapServiceImpl.class);

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public Water add(Water water) {
        if (water== null){
            LOG.error("input water is null");
            return null;
        }

        return mongoTemplate.insert(water);
    }
    @Override
    public Water get(String id) {
        if (!StringUtils.hasText(id)){
            LOG.error("input water id is blank");
            return null;
        }
        Water water= mongoTemplate.findById(id, Water.class);
        return water;
    }

    @Override
    public boolean delete(String id) {
        if (!StringUtils.hasText(id)){
            LOG.error("input water id is blank");
            return false;
        }

        Water water = new Water();
        water.setId(id);

        DeleteResult result = mongoTemplate.remove(water);
        return result != null && result.getDeletedCount() > 0;
    }

}
