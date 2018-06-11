package hello;

import hello.models.RequestMappingDemo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping(value = "/greeting")
public class RequestMappingDemoController {

    private static final String template = "Hello, %s!";
    private static final String admintemplate = "Redirecting to admin page....";
    private static final String usertemplate = "Welcome User %s ....";
    private final AtomicLong counter = new AtomicLong();

    /**
     * RequestMapping default
     *
     * @param name
     * @return RequestMappingDemo
     */
    @RequestMapping(value="/")
    public RequestMappingDemo greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new RequestMappingDemo(counter.incrementAndGet(),
                            String.format(template, name));
    }

    /**
     * Request Mapping by query parameter with a value
     *
     * localhost:8080/greeting?name=admin
     *
     * @return RequestMappingDemo
     */
    @RequestMapping(params = {"name=admin"}) //
    public RequestMappingDemo greetingAdmin() {
        return new RequestMappingDemo(counter.incrementAndGet(),
                String.format(admintemplate));
    }


    /**
     * Request Mapping with a query parameter
     *
     * localhost:8080/greeting?user&name=sushil
     *
     * @param name
     * @return RequestMappingDemo
     * @throws InterruptedException
     */
    @RequestMapping(params = "user")
    public Callable<ResponseEntity<RequestMappingDemo>> greetingUser(@RequestParam(value="name", defaultValue="user1") String name) throws InterruptedException {
        Thread.sleep(10000);
        return () -> ResponseEntity.ok(new RequestMappingDemo(counter.incrementAndGet(),
                String.format(usertemplate, name)));
    }

}
