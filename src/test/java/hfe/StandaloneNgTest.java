package hfe;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import javax.inject.Inject;

@RunWith(SpringRunner.class)
@ContextConfiguration
public class StandaloneNgTest {

    @Inject
    private Standalone standalone;

    @BeforeTest
    public void setup() {

        standalone.createDatabase();
    }

    @Test
    public void insertData() {

    }
}
