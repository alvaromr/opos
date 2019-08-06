package com.alvaro.opos.data.source.local;

import java.util.List;

public interface LocalDataSource<Entity> {
    List<Entity> getAll();

    Entity get(Long id);

    Entity save(Entity entity);

    void deleteAll();

    void delete(Entity exerciseEntity);
}
