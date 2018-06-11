package hello;

import hello.models.AsyncDemo;
import hello.models.AsyncDemoDelayed;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/asyncrestdemo")
public class AsyncDemoController {

    private final WebClient client;

    AsyncDemoController(){
        //WebClient with spring is inherently Async
        client = WebClient.create("https://reqres.in");
    }

    /**
     * //outputs single users (without delay)
     * @return single user
     */
    @RequestMapping(value = { "", "/"} )
    public Mono<AsyncDemo>  demoAsyncRest() {

        return client.get()
                .uri("/api/users/2").accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(AsyncDemo.class);
    }

    /**
     * outputs list of multiple users with delayed response
     * @param delay
     * @return Multiple Users
     */
    @RequestMapping(value = "/delayed")
    public Mono<AsyncDemoDelayed>  demoAsyncRestDelayedResp(@RequestParam(name = "delay", defaultValue="10") int delay) {

        return client.get()
                .uri("/api/users?delay={delay}", delay).accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(AsyncDemoDelayed.class);
    }

    /**
     * Calls two rest APIs Asynchronously
     * First to list single user (without delay)
     * Second to list multiple users (with dalay)
     *        (Notice the order of responses for Asynchronous calls)
     * @param delay
     * @return combined object users
     */
    @RequestMapping(value = "/multiple")
    public Flux demoAsyncRestMultipleCalls(@RequestParam(name = "delay", defaultValue="5") int delay) {

        Mono<AsyncDemoDelayed> firstRequest = demoAsyncRestDelayedResp(10);//outputs list of multiple users
        Mono<AsyncDemo> secondRequest  = demoAsyncRest();//outputs single users
        return Flux.merge(firstRequest, secondRequest);
    }

}
