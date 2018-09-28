package uk.gov.crowncommercial.examples.api1.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import uk.gov.crowncommercial.examples.api1.models.SystemInfo;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Simple example controller that can be used to obtained a list of system properties
 * and environment variable values relating to the environment in which the API is running.
 *
 * if no parameter is supplied only an id and the current date/time will be returned.
 *
 * A 'detail' parameter can be supplied that should be
 *
 * detail=properties            Return all Java system properties
 * detail=environment           Return all environment variables
 * detail=all                   Return everything
 */
@RestController
public class SystemInfoController {

    // Simple counter incremented for each request
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/systeminfo")
    public SystemInfo systeminfo(@RequestParam(value="detail", defaultValue="false") String detail) {

        return new SystemInfo(counter.incrementAndGet(), detail );
    }
}