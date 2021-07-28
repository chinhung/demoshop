package net.chinhung.core.component.product;

public interface CoreProductComponent {

    GetAllResult getAll(GetAll query);

    CreateResult create(Create command);

    DeleteResult delete(Delete command);

    UpdateResult update(Update command);
}
