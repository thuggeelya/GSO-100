import com.example.task3.dto.Item;
import com.example.task3.services.ItemService;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SqlTest {

    private final Item item = new Item(1, "testName", 1);
    private final ItemService service = ItemService.getInstance();

    @Test
    public void add() {
        assertTrue(service.addItem(item));
        assertEquals(item, service.findByCode(1));
    }

    @Test
    public void edit() {
        item.setCode(2);
        item.setName("edit");
        item.setPrice(2);
        assertTrue(service.editItem(1, item));
        assertEquals(item, service.findByCode(item.getCode()));
    }

    @Test
    public void firstRemove() {
        assertTrue(service.removeItem(2));
    }

    @Test
    public void secondRemove() {
        assertFalse(service.removeItem(2));
    }
}
