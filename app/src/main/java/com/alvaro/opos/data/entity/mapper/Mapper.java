package com.alvaro.opos.data.entity.mapper;

import java.util.List;

// Used to transform data from Entity (data layer), to Model (domain layer), and vice versa
public interface Mapper<Model, Entity> {
    List<Model> from(List<Entity> list);

    Model from(Entity entity);

    Entity transform(Model exercise);
}
