package net.chinhung.core.component.order;

public interface CoreOrderComponent {

    GetAllResult getAll(GetAll query);

    FindByIdResult findById(FindById query);

    CreateResult create(Create command);

    DeleteResult delete(Delete command);

    UpdateResult update(Update command);
}
