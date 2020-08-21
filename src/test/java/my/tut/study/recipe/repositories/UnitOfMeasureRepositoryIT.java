package my.tut.study.recipe.repositories;

import my.tut.study.recipe.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UnitOfMeasureRepositoryIT {

    @Autowired
    private UnitOfMeasureRepository repository;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void findByDescription() {
        UnitOfMeasure unitOfMeasure = repository.findByDescription("Teaspoon").get();
        assertEquals("Teaspoon", unitOfMeasure.getDescription());
    }

    @Test
    public void findCupByDescription() {
        String cupDescription = repository.findByDescription("Cup").orElse(new UnitOfMeasure()).getDescription();
        assertEquals("Cup", cupDescription);
    }
}