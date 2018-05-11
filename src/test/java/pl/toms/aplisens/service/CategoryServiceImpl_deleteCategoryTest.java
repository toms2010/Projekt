package pl.toms.aplisens.service;

import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;

import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import junit.framework.TestCase;
import pl.toms.aplisens.domain.Category;
import pl.toms.aplisens.repository.CategoryRepository;
import pl.toms.aplisens.util.AppMessage;
import pl.toms.aplisens.util.ApplicationException;

/**
 * Testy implementacji metody {@link CategoryServiceImpl#deleteCategory(Category)}.
 */
@RunWith(MockitoJUnitRunner.class)
public class CategoryServiceImpl_deleteCategoryTest extends TestCase
{
    @Rule
    public ExpectedException thrown = ExpectedException.none();
    
    @InjectMocks
    private final CategoryService testedService = new CategoryServiceImpl();
    
    @Mock
    private AppMessage appMessage;
    @Mock
    private CategoryRepository repo;
    
    private Long categoryId;
    
    /**
     * Przygotowuje dane do testów
     */
    @Before
    public void setUp() {
        categoryId = new Random().nextLong();
    }
    
    @After
    public void tearDown() {
        reset(appMessage, repo);
    }
    
    /**
     * Test weryfikujący zwrócenie błędu gdy nie przekazano kategorii.
     */
    @Test
    public void categoryIdIsNull_throwException() {
        thrown.expect(ApplicationException.class);
        // TODO sprawdzić kod wyjątku
        testedService.deleteCategory(null);
    }

    /**
     * Test weryfikujący zapisane kategorii gdy został przekazany obiekt klategorii.
     */
    @Test
    public void categoryIdIsPassed_delete() {
        testedService.deleteCategory(categoryId);
        verify(repo).deleteById(categoryId);
    }
}
