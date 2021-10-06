package web.controller;


import core.service.SportiveService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web.converter.SportiveConverter;
import web.dto.SportiveDto;
import web.dto.SportivesDto;

import java.util.Set;

@RestController
public class SportiveController {
    public static final Logger log = LoggerFactory.getLogger(SportiveController.class);

    @Autowired
    private SportiveService sportiveService;

    @Autowired
    private SportiveConverter sportiveConverter;


    @RequestMapping(value = "/sportives", method = RequestMethod.GET)
    Set<SportiveDto> getSportives() {
        log.trace("SportiveController - getSportive - enter method");
        return sportiveConverter.convertModelsToDtos(
                sportiveService.getAllSportives());
    }


    @RequestMapping(value = "/sportives", method = RequestMethod.POST)
    SportiveDto saveTeam(@RequestBody SportiveDto teamDto) {
        log.trace("TeamController - saveTeam - enter method");
        return sportiveConverter.convertModelToDto(sportiveService.saveSportive(
                sportiveConverter.convertDtoToModel(teamDto)
        ));
    }

    @RequestMapping(value = "/sportives/{id}", method = RequestMethod.PUT)
    SportiveDto updateClient(@PathVariable Long id,
                         @RequestBody SportiveDto teamDto) {
        log.trace("TeamController - updateTeam - enter method");
        return sportiveConverter.convertModelToDto( sportiveService.updateSportive(id,
                sportiveConverter.convertDtoToModel(teamDto)));
    }

    @RequestMapping(value = "/sportives/{id}", method = RequestMethod.DELETE)
    ResponseEntity<?> deleteClient(@PathVariable Long id){
        log.trace("TeamController - deleteTeam - enter method");

        sportiveService.deleteSportive(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/sportives/filterAge/{age}", method = RequestMethod.GET)
    SportivesDto getSportivesByAge(@PathVariable int age) {
        log.trace("begin filter sportives name={}",age);
        SportivesDto cpy = new SportivesDto(sportiveConverter
                .convertModelsToDtos(sportiveService.filterSportivesByAge(age)));
        log.trace("end filter sportives={}",cpy);
        return cpy;
    }

    @RequestMapping(value = "/sportives/filterFirstName/{firstName}", method = RequestMethod.GET)
    SportivesDto getSportivesByFirstName(@PathVariable String firstName) {
        log.trace("begin filter sportives name={}",firstName);
        SportivesDto cpy = new SportivesDto(sportiveConverter
                .convertModelsToDtos(sportiveService.filterSportivesByFirstName(firstName)));
        log.trace("end filter sportives={}",cpy);
        return cpy;
    }

    @RequestMapping(value = "/sportives/filterTeamId/{teamId}", method = RequestMethod.GET)
    SportivesDto getSportivesByTeamId(@PathVariable int teamId) {
        log.trace("begin filter sportives name={}",teamId);
        SportivesDto cpy = new SportivesDto(sportiveConverter
                .convertModelsToDtos(sportiveService.filterSportivesByTeamId(teamId)));
        log.trace("end filter sportives={}",cpy);
        return cpy;
    }

}
