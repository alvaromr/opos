package com.alvaro.opos.domain.model;

import java.util.List;

public interface Repository<Model, GetParams, ListParams> {
    List<Model> list(ListParams params);
    Model get(GetParams params);
    Model save(Model model);
    void delete(GetParams params);
}
