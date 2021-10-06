package web.converter;

import core.model.Sportive;
import org.springframework.stereotype.Component;
import web.dto.SportiveDto;

@Component
public class SportiveConverter extends BaseConverter<Sportive, SportiveDto>{
    public Sportive convertDtoToModel(SportiveDto dto) {
        Sportive sportive = Sportive.builder()
                .age(dto.getAge())
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .tid(dto.getTid())
                .build();
        sportive.setId(dto.getId());
        return sportive;
    }

    @Override
    public SportiveDto convertModelToDto(Sportive sportive) {
        SportiveDto dto = new SportiveDto(sportive.getFirstName(), sportive.getLastName(),sportive.getAge(),sportive.getTid());
        dto.setId(sportive.getId());
        return dto;
    }
}
