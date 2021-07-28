package net.chinhung.application.component.inventory.impl;

import net.chinhung.application.inventory.InventoryRecord;
import net.chinhung.application.component.inventory.GetAll;
import net.chinhung.application.component.inventory.GetAllResult;
import net.chinhung.application.component.inventory.InventoryQuery;
import net.chinhung.application.component.inventory.InventoryQueryResult;
import net.chinhung.application.component.inventory.Update;
import net.chinhung.application.component.inventory.UpdateResult;
import net.chinhung.core.inventory.CoreInventoryRecord;
import net.chinhung.fundamental.aspect.CommandService;
import net.chinhung.fundamental.aspect.QueryService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Stream;

import static util.TestUtil.ANY_INT;
import static util.TestUtil.ANY_STRING;

public class InventoryComponentImplTest {

    @Mock private QueryService<GetAll, GetAllResult> getAllService;
    @Mock private CommandService<Update, UpdateResult> updateService;
    private InventoryComponentImpl instance;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        instance = new InventoryComponentImpl(getAllService, updateService);
    }

    @Test
    public void getAllService() {
        Mockito.when(getAllService.execute(Mockito.any())).thenReturn(new GetAllResult(new ArrayList<>()));

        instance.getInventoryRecords(new ArrayList<>());

        Mockito.verify(getAllService).execute(Mockito.any());
    }

    @Test
    public void updateService() {
        final UpdateResult anyResult = new UpdateResult(new InventoryRecord(new CoreInventoryRecord(ANY_STRING, ANY_STRING, ANY_STRING, ANY_INT)));
        Mockito.when(updateService.execute(Mockito.any())).thenReturn(anyResult);

        instance.update(ANY_STRING, ANY_INT);

        Mockito.verify(updateService).execute(Mockito.any());
    }

    private static Stream<Arguments> testQuery_InStock() {
        return Stream.of(
                Arguments.of(1, 1),
                Arguments.of(2, 1),
                Arguments.of(2, 2),
                Arguments.of(3, 1),
                Arguments.of(3, 2),
                Arguments.of(3, 3)
        );
    }

    @ParameterizedTest
    @MethodSource
    public void testQuery_InStock(final Integer someStockQuantity, final Integer queryQuantity) {
        final GetAllResult someResult = new GetAllResult(Collections.singletonList(new InventoryRecord(new CoreInventoryRecord(ANY_STRING, ANY_STRING, "someProductId", someStockQuantity))));
        Mockito.when(getAllService.execute(Mockito.any())).thenReturn(someResult);

        final InventoryQueryResult result = instance.query(Collections.singletonList(new InventoryQuery("someProductId", ANY_STRING, queryQuantity)));

        Assertions.assertEquals(1, result.getInStockQueries().size());
        Assertions.assertEquals(0, result.getOutOfStockQueries().size());
        Assertions.assertEquals("someProductId", result.getInStockQueries().get(0).getProductId());
    }

    private static Stream<Arguments> testQuery_OutOfStock() {
        return Stream.of(
                Arguments.of(0, 1),
                Arguments.of(1, 2),
                Arguments.of(2, 3)
        );
    }

    @ParameterizedTest
    @MethodSource
    public void testQuery_OutOfStock(final Integer someStockQuantity, final Integer queryQuantity) {
        final GetAllResult someResult = new GetAllResult(Collections.singletonList(new InventoryRecord(new CoreInventoryRecord(ANY_STRING, ANY_STRING, "someProductId", someStockQuantity))));
        Mockito.when(getAllService.execute(Mockito.any())).thenReturn(someResult);

        final InventoryQueryResult result = instance.query(Collections.singletonList(new InventoryQuery("someProductId", ANY_STRING, queryQuantity)));

        Assertions.assertEquals(1, result.getOutOfStockQueries().size());
        Assertions.assertEquals(0, result.getInStockQueries().size());
        Assertions.assertEquals("someProductId", result.getOutOfStockQueries().get(0).getProductId());
    }
}
