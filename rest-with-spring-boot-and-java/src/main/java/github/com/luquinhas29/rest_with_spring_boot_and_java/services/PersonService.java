package github.com.luquinhas29.rest_with_spring_boot_and_java.services;

import github.com.luquinhas29.rest_with_spring_boot_and_java.exception.ResourceNotFoundException;
import github.com.luquinhas29.rest_with_spring_boot_and_java.model.Person;
import github.com.luquinhas29.rest_with_spring_boot_and_java.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    private Logger logger = LoggerFactory.getLogger(PersonService.class.getName());

    public List<Person> findAll() {
        logger.info("Finding all Person");

        return personRepository.findAll();
    }

    public Person findById(Long id) {
        logger.info("Finding one Person!");

        return personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
    }

    public Person create(Person person) {
        logger.info("Creating one Person");

    return personRepository.save(person);
    }

    public Person update(Person person){
        logger.info("Updating one Person");

        Person entity = personRepository.findById(person.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

    return personRepository.save(person);
    }

    public void delete(long id){
        logger.info("Deleting one Person!");

        Person entity = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        personRepository.delete(entity);


    }

}
