package hello;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping(value = "/greeting")
public class GreetingController {

    private static final String template = "Hello, %s!";
    private static final String admintemplate = "Redirecting to admin page....";
    private static final String usertemplate = "Welcome User %s ....";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping(value="/")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new Greeting(counter.incrementAndGet(),
                            String.format(template, name));
    }

    @RequestMapping(params = {"name=admin"}) // for request like /greeting?name=admin
    public Greeting greetingAdmin() {
        return new Greeting(counter.incrementAndGet(),
                String.format(admintemplate));
    }

    @RequestMapping(params = "user")// for request like /greeting?user&name=sushil
    public Callable<ResponseEntity<Greeting>> greetingUser(@RequestParam(value="name", defaultValue="user1") String name) throws InterruptedException {
        Thread.sleep(10000);
        return () -> ResponseEntity.ok(new Greeting(counter.incrementAndGet(),
                String.format(usertemplate, name)));
    }

}
