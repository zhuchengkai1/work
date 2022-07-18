package buildsite.service;
import buildsite.model.Water;

import java.util.List;1


public interface WaterService {

    Water add(Water water);

    Water get(String id);

    boolean delete(String id);
}
