package uk.gov.crowncommercial.examples.api1.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uk.gov.crowncommercial.examples.api1.models.Root;

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