package hfe;

import org.h2.tools.Server;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

@Named
public class Standalone {

    @PersistenceContext
    private EntityManager manager;

    public EntityManager getManager() {
        return manager;
    }

    @Transactional
    public void runQuery(String sql) {
        getManager().createNativeQuery(sql).executeUpdate();
    }

    @Transactional
    public <T> List<T> result(String sql, Class<T> clazz) {
        return (List<T>)getManager().createNativeQuery(sql).getResultList();
    }

    public static void main(String[] args) {

        Server h2Server = null;
        try {
            h2Server = startH2();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan("hfe");
        context.refresh();
        //Arrays.stream(context.getBeanDefinitionNames()).forEach(name -> Logger.getLogger(Standalone.class.getSimpleName()).info("name: " + name));
        //Logger.getLogger(Standalone.class.getSimpleName()).info("count: " + context.getBeanDefinitionCount());

        try {
            Standalone standalone = context.getBean(Standalone.class);
            standalone.createDatabase();
            List<String> tables = standalone.result("SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA='PUBLIC' ORDER BY TABLE_NAME", String.class);
            tables.forEach(name -> Logger.getLogger(Standalone.class.getSimpleName()).info(name));
            Logger.getLogger(Standalone.class.getSimpleName()).info("--------------------");
        } catch (Exception e) {
            stopH2(h2Server);
            throw new RuntimeException(e);
        }
        stopH2(h2Server);
    }

    void createDatabase() {
        runQuery("DROP ALL OBJECTS");
        runQuery("create table WHFE (id number(19,0) not null, number number(19,0), fid varchar2(10 char) not null, primary key (id))");
    }

    static Server startH2() throws SQLException, ClassNotFoundException {
        Server h2Server = org.h2.tools.Server.createTcpServer().start();
        return h2Server;
    }

    static void stopH2(Server h2Server) {
        if(h2Server != null) {
            h2Server.shutdown();
            h2Server.stop();
        }
    }
}

