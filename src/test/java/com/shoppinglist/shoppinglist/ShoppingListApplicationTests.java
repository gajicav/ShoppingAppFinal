package com.shoppinglist.shoppinglist;
 
import com.shoppinglist.shoppinglist.service.IShoppingListService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = ShoppingListApplication.class)
public class ShoppingListApplicationTests {

    @Autowired
    private IShoppingListService shoppingListService;

    @Test
    public void contextLoads() {
    }

    @Test
    public void testAddingAndUpdatingItem() {
        ListItem item = new ListItem();

        item.setName("Banana");
        item.setPrice(1.25);
        item.setQuantity(5);

        item = shoppingListService.save(item);

        assertNotNull(item, "Mock item not created");

        System.out.println("Created mock item");
        System.out.println(item);

        try {
            ListItem firstItem = shoppingListService.fetchAll().get(0);
            assertEquals(item, firstItem, "Saved mock item is not equal to first item in table");
        } catch (Exception e) {
            System.out.println("Could not retrieve first item in table");
        }// setName() 方法更新保存的项目的名称为 “Apple”。然后再次使用 save() 方法保存它。然后通过比较它们的时间戳来检查更新后的项目是否等于原始项目。

        item.setName("Apple");

        item = shoppingListService.save(item);

        assertNotNull(item, "Mock item not updated");

        System.out.println("Updated mock item");
        System.out.println(item);

        assertNotEquals(item.getCreated(), item.getUpdated(), "Updated timestamp has not changed");
    }
}
