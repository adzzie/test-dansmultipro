package dev.dansmultipro.test.controller;

import dev.dansmultipro.test.dto.MapLocation;
import dev.dansmultipro.test.dto.Posistions;
import dev.dansmultipro.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/recruitment/positions")
public class RecruitmentController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UserService userService;


    @GetMapping
    public ResponseEntity<List<Posistions>> positions(Principal principal){

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<?> entity = new HttpEntity<>(headers);
        UriComponents uriComponents = UriComponentsBuilder.fromHttpUrl("http://dev3.dansmultipro.co.id/api/recruitment/positions.json").build().encode();
        ResponseEntity<List<Posistions>> responseEntity = restTemplate.exchange(uriComponents.toUri(), HttpMethod.GET,entity, new ParameterizedTypeReference<List<Posistions>>(){});

//        List<Posistions> list = (List<Posistions>) new Gson().fromJson(String.valueOf(responseEntity),Posistions.class);
//        Optional<Posistions> posistion = responseEntity.getBody().stream().filter(posistions -> posistions.getId().equals("32bf67e5-4971-47ce-985c-44b6b3860cdb")).findFirst();
//        return responseEntity.ok(maps);
        return responseEntity;
    }

    @GetMapping("/filter-by-id")
    public ResponseEntity<Posistions> positionsFilterById(Principal principal){

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<?> entity = new HttpEntity<>(headers);
        UriComponents uriComponents = UriComponentsBuilder.fromHttpUrl("http://dev3.dansmultipro.co.id/api/recruitment/positions.json").build().encode();
        ResponseEntity<List<Posistions>> responseEntity = restTemplate.exchange(uriComponents.toUri(), HttpMethod.GET,entity, new ParameterizedTypeReference<List<Posistions>>(){});

//        List<Posistions> list = (List<Posistions>) new Gson().fromJson(String.valueOf(responseEntity),Posistions.class);

        Optional<Posistions> posistion = responseEntity.getBody().stream().filter(posistions -> posistions.getId().equals("32bf67e5-4971-47ce-985c-44b6b3860cdb")).findFirst();


        return responseEntity.ok(posistion.get());
//        return responseEntity;
    }

    @GetMapping("/group-location")
    public ResponseEntity<List<MapLocation>> positionsGroup(Principal principal){

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<?> entity = new HttpEntity<>(headers);
        UriComponents uriComponents = UriComponentsBuilder.fromHttpUrl("http://dev3.dansmultipro.co.id/api/recruitment/positions.json").build().encode();
        ResponseEntity<List<Posistions>> responseEntity = restTemplate.exchange(uriComponents.toUri(), HttpMethod.GET,entity, new ParameterizedTypeReference<List<Posistions>>(){});

        List<MapLocation> maps = new ArrayList<>();
        for (Posistions posistion: responseEntity.getBody()
        ) {
            boolean tisakAda = true;
            if(maps.size()>0) {
                for (MapLocation loc : maps
                ) {
                    if (loc.getLocation().equals(posistion.getLocation())) {
                        List<Posistions> pos = new ArrayList<>();
                        pos.addAll(loc.getData());
                        pos.add(posistion);
                        loc.setData(pos);
                        tisakAda = false;
                    }
                }
            }
            if(tisakAda)maps.add(MapLocation.builder().location(posistion.getLocation()).data(List.of(posistion)).build());

        }
        return responseEntity.ok(maps);
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> positions(@PathVariable("id") String id, Principal principal){
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<?> entity = new HttpEntity<>(headers);
        UriComponents uriComponents = UriComponentsBuilder.fromHttpUrl("http://dev3.dansmultipro.co.id/api/recruitment/positions/"+id).build().encode();
        ResponseEntity<String> responseEntity = restTemplate.exchange(uriComponents.toUri(), HttpMethod.GET,entity, String.class);

        return responseEntity;
    }


}
