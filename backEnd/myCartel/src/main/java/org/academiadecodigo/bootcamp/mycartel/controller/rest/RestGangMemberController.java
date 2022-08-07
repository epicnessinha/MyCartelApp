package org.academiadecodigo.bootcamp.mycartel.controller.rest;

import org.academiadecodigo.bootcamp.mycartel.converters.GangMemberDtoToGangMember;
import org.academiadecodigo.bootcamp.mycartel.converters.GangMemberToGangMemberDto;
import org.academiadecodigo.bootcamp.mycartel.exceptions.GangMemberNotFoundException;
import org.academiadecodigo.bootcamp.mycartel.command.GangMemberDto;
import org.academiadecodigo.bootcamp.mycartel.persistence.model.GangMember;
import org.academiadecodigo.bootcamp.mycartel.services.GangMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/customer")
public class RestGangMemberController {

        private GangMemberService gangMemberService;
        private GangMemberDtoToGangMember gangMemberDtoToGangMember;
        private GangMemberToGangMemberDto gangMemberToGangMemberDto;

    @Autowired
    public void setGangMemberService(GangMemberService gangMemberService) {
        this.gangMemberService = gangMemberService;
    }
    @Autowired
    public void setGangMemberDtoToGangMember(GangMemberDtoToGangMember gangMemberDtoToGangMember) {
        this.gangMemberDtoToGangMember = gangMemberDtoToGangMember;
    }
    @Autowired
    public void setGangMemberToGangMemberDto(GangMemberToGangMemberDto gangMemberDto) {
        this.gangMemberToGangMemberDto = gangMemberDto;
    }

    @RequestMapping(method = RequestMethod.GET, path = {"/", ""})
        public ResponseEntity<List<GangMemberDto>> listGangMembers() {

            List<GangMemberDto> gangMemberDtos = gangMemberService.list().stream()
                    .map(gangMember -> gangMemberToGangMemberDto.convert(gangMember))
                    .collect(Collectors.toList());

            return new ResponseEntity<>(gangMemberDtos, HttpStatus.OK);
        }

        @RequestMapping(method = RequestMethod.GET, path = "/{id}")
        public ResponseEntity<GangMemberDto> showGangMember(@PathVariable Integer id) {

            GangMember gangMember = gangMemberService.get(id);

            if (gangMember == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity<>(gangMemberToGangMemberDto.convert(gangMember), HttpStatus.OK);
        }

        @RequestMapping(method = RequestMethod.POST, path = {"/", ""})
        public ResponseEntity<?> addGangMember(@Valid @RequestBody GangMemberDto gangMemberDto, BindingResult bindingResult, UriComponentsBuilder uriComponentsBuilder) {

            if (bindingResult.hasErrors() || gangMemberDto.getId() != null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            GangMember savedGangMember = gangMemberService.save(gangMemberDtoToGangMember.convert(gangMemberDto));

            // get help from the framework building the path for the newly created resource
            UriComponents uriComponents = uriComponentsBuilder.path("/api/customer/" + savedGangMember.getId()).build();

            // set headers with the created path
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(uriComponents.toUri());

            return new ResponseEntity<>(headers, HttpStatus.CREATED);
        }
        @RequestMapping(method = RequestMethod.PUT, path = "/{id}")
        public ResponseEntity<GangMemberDto> editGangMember(@Valid @RequestBody GangMemberDto gangMemberDto, BindingResult bindingResult, @PathVariable Integer id) {

            if (bindingResult.hasErrors()) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            if (gangMemberDto.getId() != null && !gangMemberDto.getId().equals(id)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            if (gangMemberService.get(id) == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            gangMemberDto.setId(id);

            gangMemberService.save(gangMemberDtoToGangMember.convert(gangMemberDto));
            return new ResponseEntity<>(HttpStatus.OK);
        }

        @RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
        public ResponseEntity<GangMemberDto> deleteGangMember(@PathVariable Integer id) {

            try {

                gangMemberService.delete(id);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);


            } catch (GangMemberNotFoundException e) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
    }

