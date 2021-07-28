package net.chinhung.core.component.order.impl;

import net.chinhung.core.component.order.*;

public interface CoreOrderDataSource {

    CreateResult create(Create command);

    DeleteResult delete(Delete command);

    FindByIdResult findById(FindById command);

    GetAllResult getAll(GetAll command);

    UpdateResult update(Update command);
}
