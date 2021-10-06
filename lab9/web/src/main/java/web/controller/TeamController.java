package web.controller;

import core.service.TeamService;
import web.converter.TeamConverter;
import web.dto.TeamDto;
import web.dto.TeamsDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
public class TeamController {
    public static final Logger log = LoggerFactory.getLogger(TeamController.class);

    @Autowired
    private TeamService teamService;

    @Autowired
    private TeamConverter teamConverter;


    @RequestMapping(value = "/teams", method = RequestMethod.GET)
    Set<TeamDto> getTeams() {
        log.trace("TeamController - getTeams - enter method");
        return teamConverter.convertModelsToDtos(teamService.getAllTeams());
    }

    @RequestMapping(value = "/teams/filter/{name}", method = RequestMethod.GET)
    TeamsDto getTrainersFiltered(@PathVariable String name) {
        log.trace("begin filter teams teamName={}",name);
        TeamsDto cpy = new TeamsDto(teamConverter
                .convertModelsToDtos(teamService.filterTeamsByTeamname(name)));
        log.trace("end filter trainers={}",cpy);
        return cpy;
    }

    @RequestMapping(value = "/teams", method = RequestMethod.POST)
    TeamDto saveTeam(@RequestBody TeamDto teamDto) {
        log.trace("TeamController - saveTeam - enter method");
        return teamConverter.convertModelToDto(teamService.saveTeam(
                teamConverter.convertDtoToModel(teamDto)
        ));
    }

    @RequestMapping(value = "/teams/{id}", method = RequestMethod.PUT)
    TeamDto updateTeam(@PathVariable Long id,
                           @RequestBody TeamDto teamDto) {
        log.trace("TeamController - updateTeam - enter method");
        return teamConverter.convertModelToDto( teamService.updateTeam(id,
                teamConverter.convertDtoToModel(teamDto)));
    }

    @RequestMapping(value = "/teams/{id}", method = RequestMethod.DELETE)
    ResponseEntity<?> deleteTeam(@PathVariable Long id){
        log.trace("TeamController - deleteTeam - enter method");

        teamService.deleteTeamById(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
