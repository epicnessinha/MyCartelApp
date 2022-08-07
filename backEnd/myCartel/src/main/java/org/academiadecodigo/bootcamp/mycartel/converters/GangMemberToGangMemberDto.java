package org.academiadecodigo.bootcamp.mycartel.converters;

import org.academiadecodigo.bootcamp.mycartel.command.GangMemberDto;
import org.academiadecodigo.bootcamp.mycartel.persistence.model.GangMember;
import org.springframework.stereotype.Component;

@Component
public class GangMemberToGangMemberDto extends AbstractConverter<GangMember, GangMemberDto> {

    @Override
    public GangMemberDto convert(GangMember gangMember) {

        GangMemberDto GangMemberDto = new GangMemberDto();
        GangMemberDto.setId(gangMember.getId());
        GangMemberDto.setFirstName(gangMember.getFirstName());
        GangMemberDto.setLastName(gangMember.getLastName());
        GangMemberDto.setEmail(gangMember.getEmail());
        GangMemberDto.setPhone(gangMember.getPhone());

        return GangMemberDto;
    }
}
