package edu.city.web;

import edu.city.dao.PersonCheckDao;
import edu.city.dao.PoolConnectionBuilder;
import edu.city.domain.PersonRequest;
import edu.city.domain.PersonResponse;
import edu.city.exception.PersonCheckException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.time.LocalDate;

@Path("/check")
@Singleton
public class CheckPersonService {
    private static final Logger logger = LoggerFactory.getLogger(CheckPersonService.class);

    PersonCheckDao dao;


    @PostConstruct
    public void init() {
        logger.info("Start");
        dao = new PersonCheckDao();
        dao.setConnectionBuilder(new PoolConnectionBuilder());
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public PersonResponse checkPerson(PersonRequest request) throws PersonCheckException {
        logger.info(request.toString());

        return dao.checkPerson(request);
    }


}
