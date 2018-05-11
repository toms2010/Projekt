package pl.toms.aplisens.service;

import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;

import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import pl.toms.aplisens.domain.Category;
import pl.toms.aplisens.repository.CategoryRepository;
import pl.toms.aplisens.util.AppMessage;
import pl.toms.aplisens.util.ApplicationException;

/**
 * Testy implementacji metody {@link CategoryServiceImpl#saveCategory(Category)}.
 */
@RunWith(MockitoJUnitRunner.class)
public class CategoryServiceImpl_saveCategoryTest 
{
    @Rule
    public ExpectedException thrown = ExpectedException.none();
    
    @InjectMocks
    private final CategoryService testedService = new CategoryServiceImpl();
    
    @Mock
    private AppMessage appMessage;
    @Mock
    private CategoryRepository repo;
    @Mock
    private Category category;
    
    @After
    public void tearDown() {
        reset(appMessage, repo, category);
    }
    
    /**
     * Test weryfikujący zwrócenie błędu gdy nie przekazano kategorii.
     */
    @Test
    public void categoryIsNull_throwException() {
        thrown.expect(ApplicationException.class);
        // TODO sprawdzić kod wyjątku
        testedService.saveCategory(null);
    }

    /**
     * Test weryfikujący zapisane kategorii gdy został przekazany obiekt klategorii.
     */
    @Test
    public void categoryIsPassed_save() {
        testedService.saveCategory(category);
        verify(repo).save(category);
    }
}
