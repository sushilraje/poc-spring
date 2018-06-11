package hello;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/asyncrestdemo")
public class AsyncDemoController {

    private final WebClient client;

    AsyncDemoController(){

        client = WebClient.create("https://reqres.in");
    }

    @RequestMapping(value = "/")
    public Mono<AsyncDemo>  demoAsyncRestResponse(@RequestParam(name = "delay", defaultValue="5") int delay) {

        return client.get()
                .uri("/api/users?delay={delay}", delay).accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(AsyncDemo.class);
    }
}
