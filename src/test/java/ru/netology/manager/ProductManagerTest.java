package ru.netology.manager;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;
import ru.netology.repository.ProductRepository;

import static org.junit.jupiter.api.Assertions.*;

class ProductManagerTest {

    private ProductRepository repository = new ProductRepository();
    private ProductManager manager = new ProductManager (repository);
    private Product first = new Book(1, "Summer", 155, "Abc");
    private Product second = new Book(2, "Winter", 200, "Abc");
    private Product third = new Book(3, "Spring", 70000, "Abc");
    private Product forth = new Book(4, "Autumn", 30, "Xyz");
    private Product fifth = new Smartphone(5, "Iphone12", 80000, "Apple");
    private Product sixth = new Smartphone(6, "Iphone11", 60000, "Apple");
    private Product seventh = new Smartphone(7, "Honor", 30000, "Huawei");

    @BeforeEach
    public void setting() {
        manager.addProduct(first);
        manager.addProduct(second);
        manager.addProduct(third);
        manager.addProduct(forth);
        manager.addProduct(fifth);
        manager.addProduct(sixth);
        manager.addProduct(seventh);
    }

    @Test
    public void shouldSearchByTextNameBook() {

        Product[] actual = manager.searchBy("Winter");
        Product[] expected = new Product[]{second};

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchByTextAuthor() {

        Product[] actual = manager.searchBy("Xyz");
        Product[] expected = new Product[]{forth};

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchByTextAuthorIfMany() {

        Product[] actual = manager.searchBy("Abc");
        Product[] expected = new Product[]{first, second, third};

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchByTextNameSmartphone() {

        Product[] actual = manager.searchBy("Iphone12");
        Product[] expected = new Product[]{fifth};

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchByTextProducer() {

        Product[] actual = manager.searchBy("Huawei");
        Product[] expected = new Product[]{seventh};

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFailSearchByTextIfNotExists() {

        Product[] actual = manager.searchBy("Sample");
        Product[] expected = new Product[]{};

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchByTextProducerIfMany() {

        Product[] actual = manager.searchBy("Apple");
        Product[] expected = new Product[]{fifth, sixth};

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldAddNewProductNotBookOrSmartphone() {

        Product eighth = new Product(8, "Nothing", 30);

        boolean actual = manager.matches(eighth, "Something");
        boolean expected = false;

        assertEquals(actual, expected);
    }
}