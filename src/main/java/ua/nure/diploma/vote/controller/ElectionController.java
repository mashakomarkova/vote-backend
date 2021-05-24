package ua.nure.diploma.vote.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.nure.diploma.vote.dto.ElectionDto;
import ua.nure.diploma.vote.service.ElectionService;

import java.util.List;

@RestController
@RequestMapping("elections")
@RequiredArgsConstructor
public class ElectionController {

    private final ElectionService electionService;

    @GetMapping
    public List<ElectionDto> findAllElections() {
        return electionService.findAllElections();
    }

    @PostMapping("/create")
    public ResponseEntity<ElectionDto> createElection(@RequestBody ElectionDto electionDto) {
       return ResponseEntity.ok(electionService.saveElection(electionDto));
    }

    @GetMapping
    public void someOther() {

    }

    @PostMapping("/delete")
    public ResponseEntity<String> deleteElection(@PathVariable String electionId) {
        electionService.deleteElection(electionId);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public void some() {

    }

    @GetMapping
    public void somee() {

    }
}
