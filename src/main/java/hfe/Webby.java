package hfe;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.WebApplicationInitializer;

import javax.servlet.ServletContext;
import java.util.Arrays;
import java.util.logging.Logger;

public class Webby implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext container) {
        Logger.getLogger(getClass().getSimpleName()).info("###############################");
        /*AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.setConfigLocation("hfe.config");
        context.setServletContext(container);
        context.close();

        //container.addListener(new ContextLoaderListener(context));

        ServletRegistration.Dynamic dispatcher = container.addServlet("dispatcher", new DispatcherServlet(context));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");*/

        Logger.getLogger(getClass().getSimpleName()).info(container.getServerInfo());

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan("hfe");
        context.refresh();

        Arrays.stream(context.getBeanDefinitionNames()).forEach(name -> Logger.getLogger(getClass().getSimpleName()).info("name: " + name));
        Logger.getLogger(getClass().getSimpleName()).info("count: " + context.getBeanDefinitionCount());

        //SpringIt it = context.getBean("springIt", SpringIt.class);
        //Logger.getLogger(getClass().getSimpleName()).info("xxxx : " + it.muffe());

        Logger.getLogger(getClass().getSimpleName()).info("###############################-finished");
    }
}
