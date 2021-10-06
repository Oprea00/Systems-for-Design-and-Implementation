package core.service;



import core.model.Sportive;

import java.util.List;

public interface SportiveService {
    Sportive saveSportive(Sportive sportive);

    void deleteSportive(Long id);

    Sportive updateSportive(Long id,Sportive sportive);

    List<Sportive> getAllSportives();

    List<Sportive> filterSportivesByAge(int age);

    List<Sportive> filterSportivesByFirstName(String firstName);

    List<Sportive> filterSportivesByTeamId(int teamId);


}
