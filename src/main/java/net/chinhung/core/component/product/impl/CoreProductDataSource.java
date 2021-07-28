package net.chinhung.core.component.product.impl;

import net.chinhung.core.component.product.*;

public interface CoreProductDataSource {

    CreateResult create(Create command);

    DeleteResult delete(Delete command);

    GetAllResult getAll(GetAll command);

    UpdateResult update(Update command);
}
