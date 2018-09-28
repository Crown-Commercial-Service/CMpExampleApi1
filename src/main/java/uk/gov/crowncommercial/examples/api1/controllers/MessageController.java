package uk.gov.crowncommercial.examples.api1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import uk.gov.crowncommercial.examples.api1.models.Message;
import uk.gov.crowncommercial.examples.api1.models.MessageRepository;

/**
 * Simple controller class for dealing with a class that can be persisted to a
 * JDBC based repository.
 *
 * Only two methods are supported:
 * /add?name=hello&message=Hello        To add a new entry
 * /                                    To list all entires
 */
@Controller
@RequestMapping(path="/messages") // This means URL's start with /demo (after Application path)
public class MessageController {
    @Autowired
    private MessageRepository messageRepository;

    @GetMapping(path="")
    public @ResponseBody Iterable<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    @GetMapping(path="/add")
    public @ResponseBody Message addNewMessage (@RequestParam String name, @RequestParam String message) {

        Message n = new Message();
        n.setName(name);
        n.setMessage(message);
        Message newMessage = messageRepository.save(n);

        return newMessage;
    }
}
