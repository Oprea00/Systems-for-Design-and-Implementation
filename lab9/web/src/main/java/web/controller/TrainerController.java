package web.controller;

import core.service.TrainerService;
import web.converter.TrainerConverter;
import web.dto.TrainerDto;
import web.dto.TrainersDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
public class TrainerController {
    public static final Logger log = LoggerFactory.getLogger(TrainerController.class);

    @Autowired
    private TrainerService trainerService;

    @Autowired
    private TrainerConverter trainerConverter;


    @RequestMapping(value = "/trainers", method = RequestMethod.GET)
    Set<TrainerDto> getTrainers() {
        log.trace("TrainerController - getTrainers - enter method");
        return trainerConverter.convertModelsToDtos(trainerService.getAllTrainers());
    }

    @RequestMapping(value = "/trainers", method = RequestMethod.POST)
    TrainerDto saveTrainer(@RequestBody TrainerDto trainerDto) {
        log.trace("TrainerController - saveTrainer - enter method");
        return trainerConverter.convertModelToDto(trainerService.saveTrainer(
                trainerConverter.convertDtoToModel(trainerDto)
        ));
    }

    @RequestMapping(value = "/trainers/{id}", method = RequestMethod.PUT)
    TrainerDto updateClient(@PathVariable Long id,
                         @RequestBody TrainerDto trainerDto) {
        log.trace("TrainerController - updateTrainer - enter method");
        return trainerConverter.convertModelToDto( trainerService.updateTrainer(id,
                trainerConverter.convertDtoToModel(trainerDto)));
    }

    @RequestMapping(value = "/trainers/{id}", method = RequestMethod.DELETE)
    ResponseEntity<?> deleteClient(@PathVariable Long id){
        log.trace("TrainerController - deleteTrainer - enter method");
        trainerService.deleteTrainerById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/trainers/filter/{name}", method = RequestMethod.GET)
    TrainersDto getTrainersFiltered(@PathVariable String name) {
        log.trace("begin filter trainers name={}",name);
        TrainersDto cpy = new TrainersDto(trainerConverter
                .convertModelsToDtos(trainerService.filterTrainersByName(name)));
        log.trace("end filter trainers={}",cpy);
        return cpy;
    }
}
