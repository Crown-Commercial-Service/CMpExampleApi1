package uk.gov.crowncommercial.examples.api1;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Spring supplied example that returns a simple request counter.
 * It's purpose in the example is to server as a destination for an api 'health check'.
 */
@RestController
public class RootController {

    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/")
    public Root root() {
        return new Root(counter.incrementAndGet() );
    }
}