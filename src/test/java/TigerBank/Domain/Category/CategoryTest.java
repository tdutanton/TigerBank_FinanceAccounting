package TigerBank.Domain.Category;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import TigerBank.Domain.TxType.TxType;
import org.junit.jupiter.api.Test;

class CategoryTest {

  @Test
  void shouldCreateCategoryWithValidData() {
    Category category = new Category("cat_1", TxType.INCOME, "Зарплата");

    assertEquals("cat_1", category.getId());
    assertEquals(TxType.INCOME, category.getType());
    assertEquals("Зарплата", category.getName());
  }

  @Test
  void shouldThrowExceptionWhenIdIsNull() {
    assertThrows(IllegalArgumentException.class, () ->
        new Category(null, TxType.INCOME, "Зарплата")
    );
  }

  @Test
  void shouldThrowExceptionWhenIdIsEmpty() {
    assertThrows(IllegalArgumentException.class, () ->
        new Category("", TxType.INCOME, "Зарплата")
    );
  }

  @Test
  void shouldThrowExceptionWhenTypeIsNull() {
    assertThrows(IllegalArgumentException.class, () ->
        new Category("cat_1", null, "Зарплата")
    );
  }

  @Test
  void shouldThrowExceptionWhenNameIsNull() {
    assertThrows(IllegalArgumentException.class, () ->
        new Category("cat_1", TxType.INCOME, null)
    );
  }

  @Test
  void shouldThrowExceptionWhenNameIsEmpty() {
    assertThrows(IllegalArgumentException.class, () ->
        new Category("cat_1", TxType.INCOME, "   ")
    );
  }

  @Test
  void shouldHaveCorrectToString() {
    Category category = new Category("cat_1", TxType.EXPENSE, "Продукты");
    assertTrue(category.toString().contains("id=cat_1"));
    assertTrue(category.toString().contains("name=Продукты"));
  }
}