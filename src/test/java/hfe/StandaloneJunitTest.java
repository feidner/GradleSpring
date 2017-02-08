package hfe;

import hfe.config.DbTestConfig;
import hfe.config.HfeTransaction;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { DbTestConfig.class, HfeTransaction.class, Standalone.class})
@Transactional
public class StandaloneJunitTest {

    @Inject
    private Standalone standalone;

    @Before
    public void setup() {
        standalone.createDatabase();
    }

    @Test
    public void insertData() {

    }
}
