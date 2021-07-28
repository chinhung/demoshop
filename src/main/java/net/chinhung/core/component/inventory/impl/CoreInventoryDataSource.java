package net.chinhung.core.component.inventory.impl;

import net.chinhung.core.component.inventory.*;

public interface CoreInventoryDataSource {

    CreateResult create(Create command);

    DeleteResult delete(Delete command);

    FindByProductIdResult findByProductId(FindByProductId command);

    GetAllResult getAll(GetAll command);

    UpdateResult update(Update command);
}
