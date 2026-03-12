package com.stemlink.skillmentor.controllers;

import com.stemlink.skillmentor.dto.MentorDTO;
import com.stemlink.skillmentor.entities.Mentor;
import com.stemlink.skillmentor.services.MentorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping(path = "/api/v1/mentors")
@RequiredArgsConstructor
@Validated

public class MentorController extends AbstractController{

    private final MentorService mentorService;
    private final ModelMapper modelMapper;

    @GetMapping
    public Page<Mentor> getAllMentors(Pageable pageable) {
        return mentorService.getAllMentors(pageable);
    }

    @GetMapping("{id}")
    public Mentor getMentorById(@PathVariable Long id) {
        return mentorService.getMentorById(id);
    }

    @PostMapping
    public Mentor createMentor(@Valid @RequestBody MentorDTO mentorDTO) {
        Mentor mentor = modelMapper.map(mentorDTO, Mentor.class);
        return mentorService.createNewMentor(mentor);
    }

    @PutMapping("{id}")
    public Mentor updateMentor(@PathVariable Long id, @Valid @RequestBody MentorDTO updatedMentorDTO) {
        Mentor mentor = modelMapper.map(updatedMentorDTO, Mentor.class);
        return mentorService.updateMentorById(id, mentor);
    }

    @DeleteMapping("{id}")
    public void deleteMentor(@PathVariable Long id) {
        mentorService.deleteMentor(id);
    }

}
