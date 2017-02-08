package hfe;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

public class SpringItTest {

    @Test
    public void rest() throws IOException {
        System.out.println(IOUtils.toString(getStream("http://localhost:8080/springit/nop"), Charset.defaultCharset()));
        System.out.println(IOUtils.toString(getStream("http://localhost:8080/nop"), Charset.defaultCharset()));
        System.out.println(IOUtils.toString(getStream("http://localhost:8080/hello"), Charset.defaultCharset()));
        System.out.println(IOUtils.toString(getStream("http://localhost:8080/springit/hello"), Charset.defaultCharset()));
    }

    private static InputStream getStream(String url) throws IOException {
        System.out.println("-------------" + url + "--------------");
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = httpClient.execute(new HttpGet(url));
        HttpEntity entity = response.getEntity();
        return entity.getContent();
    }

}