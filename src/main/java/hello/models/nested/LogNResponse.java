package hello.models.nested;

import hello.models.AsyncDemoDelayed;
import reactor.core.CoreSubscriber;
import reactor.core.publisher.Mono;

import java.util.LinkedList;

public class LogNResponse {
    public LinkedList<String> logs = new LinkedList<>();
    public Mono<AsyncDemoDelayed> responses = new Mono<AsyncDemoDelayed>() {
        @Override
        public void subscribe(CoreSubscriber<? super AsyncDemoDelayed> actual) {

        }
    };
}
