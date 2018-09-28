package uk.gov.crowncommercial.examples.api1.models;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface that provides JPA access to a collection of Message objects.
 */
public interface MessageRepository extends JpaRepository<Message, Long> {

}
