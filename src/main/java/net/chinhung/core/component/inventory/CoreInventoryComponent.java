package net.chinhung.core.component.inventory;

public interface CoreInventoryComponent {

    GetAllResult getAll(GetAll query);

    FindByProductIdResult findByProductId(FindByProductId query);

    CreateResult create(Create command);

    DeleteResult delete(Delete command);

    UpdateResult update(Update command);
}
