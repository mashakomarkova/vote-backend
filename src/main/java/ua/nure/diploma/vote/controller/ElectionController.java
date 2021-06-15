package ua.nure.diploma.vote.controller;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.nure.diploma.vote.dto.ChoiceDto;
import ua.nure.diploma.vote.dto.ElectionDto;
import ua.nure.diploma.vote.dto.ElectionWithUserDto;
import ua.nure.diploma.vote.dto.UserDto;
import ua.nure.diploma.vote.mapper.ElectionMapper;
import ua.nure.diploma.vote.service.ChoiceService;
import ua.nure.diploma.vote.service.ElectionService;
import ua.nure.diploma.vote.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ElectionController {

    private final ElectionService electionService;
    private final UserService userService;
    private final ChoiceService choiceService;
    private final ElectionMapper electionMapper;

    @GetMapping(value = "elections",  produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ElectionDto>> findAllElections() {
        return ResponseEntity.ok(electionService.findAllElections());
    }

    @PostMapping("elections/create")
    public ResponseEntity<ElectionDto> createElection(@RequestBody ElectionDto electionDto) {
        return ResponseEntity.ok(electionService.saveElection(electionDto));
    }

    @DeleteMapping("elections/remove/{electionId}")
    public ResponseEntity<String> deleteElection(@PathVariable String electionId) {
        electionService.deleteElection(electionId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("elections/own/{userId}")
    public List<ElectionDto> findOwnElections(@PathVariable String userId) {
        return electionService.findOwnElections(userId);
    }

    @GetMapping("elections/{electionId}/{userId}")
    public ElectionWithUserDto findElection(@PathVariable String electionId, @PathVariable String userId) {
        ElectionDto election = electionService.findElection(electionId);
        boolean hasCurrentUserPArticipatedInElection = election.getChoices().stream()
                .anyMatch(choiceDto ->
                        choiceDto.getUserRates().stream().anyMatch(userId::equals)
        );
        ElectionWithUserDto electionWithUserDto = electionMapper.mapToElectionWithUserDto(election);
        electionWithUserDto.setHasUserParticipated(hasCurrentUserPArticipatedInElection);
        return electionWithUserDto;
    }

    @GetMapping("private/{electionId}/{userId}")
    public ElectionDto findOwnElection(@PathVariable String electionId, @PathVariable String userId) {
        return electionService.findOwnElection(electionId, userId);
    }

    @PostMapping("choose/{electionId}/{choiceId}/{userId}")
    public ResponseEntity<String> makeChoiceInElection(@PathVariable String electionId, @PathVariable String choiceId,
                                            @PathVariable String userId) {
        ElectionDto electionDto = electionService.findElection(electionId);
        List<ChoiceDto> choiceDtos = electionDto.getChoices();
        ChoiceDto choice = choiceDtos.stream()
                .filter(choiceDto -> choiceId.equals(choiceDto.getId())).findFirst().orElseGet(ChoiceDto::new);
        int indexDto = choiceDtos.indexOf(choice);
        List<String> userRates = Optional.ofNullable(choice.getUserRates()).orElse(new ArrayList<>());
        userRates.add(userId);
        choice.setUserRates(userRates);
        choiceDtos.set(indexDto, choice);
        electionDto.setChoices(choiceDtos);
        electionService.updateElection(electionDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/deactivate/{electionId}")
    public ResponseEntity<String> deactivateElection(@PathVariable String electionId) {
        ElectionDto electionDto = electionService.findElection(electionId);
        electionDto.setStatus("INACTIVE");
        electionService.updateElection(electionDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/recommended/{email}")
    public List<ElectionDto> getRecommendedElections(@PathVariable String email) {
        UserDto userDto = userService.getUser(email);
        List<String> topics = Optional.ofNullable(userDto.getTopics()).orElseGet(ArrayList::new);
       return electionService.findByTopics(topics).stream()
               .filter(electionDto -> StringUtils.equalsIgnoreCase("ACTIVE",electionDto.getStatus()))
               .filter(electionDto -> StringUtils.equalsIgnoreCase("public", electionDto.getAccess()))
               .collect(Collectors.toList());
    }

    @PostMapping("/topics/{email}/{topic}")
    public ResponseEntity<String> countTopics(@PathVariable String email, @PathVariable String topic) {
        UserDto userDto = userService.getUser(email);
        List<String> topics = Optional.ofNullable(userDto.getTopics()).orElseGet(ArrayList::new);
        topics.add(topic);
        userDto.setTopics(topics);
        userService.updateUser(userDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/filter")
    public List<ElectionDto> filterElections(@RequestParam(required = false) String questionText,
                                             @RequestParam(required = false) String country,
                                             @RequestParam(required = false) String city) {
        if (questionText != null) {
            return electionService.findAllElections().stream().filter(electionDto ->
                    StringUtils.containsIgnoreCase(electionDto.getQuestionText(),questionText))
                    .collect(Collectors.toList());
        }
        if (country != null) {
            return electionService.findAllElections().stream().filter(electionDto ->
                    StringUtils.containsIgnoreCase(electionDto.getCountry(),country))
                    .collect(Collectors.toList());
        }
        if (city != null) {
            return electionService.findAllElections().stream().filter(electionDto ->
                    StringUtils.containsIgnoreCase(electionDto.getCity(), city))
                    .collect(Collectors.toList());
        }
        return electionService.findAllElections();
    }
}
