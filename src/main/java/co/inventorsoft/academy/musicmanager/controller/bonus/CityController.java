package co.inventorsoft.academy.musicmanager.controller.bonus;

import co.inventorsoft.academy.musicmanager.controller.bonus.entity.Countries;
import co.inventorsoft.academy.musicmanager.controller.bonus.entity.DataResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/cities")
public class CityController {

    @GetMapping
    public List<Countries> getCities() {
        String url = "https://countriesnow.space/api/v0.1/countries";

        RestTemplate template = new RestTemplate();
        Object[] objects = new Object[]{template.getForObject(url, Object.class)};

        ObjectMapper objectMapper = new ObjectMapper();

        return Arrays.stream(objects)
                .map(o -> objectMapper.convertValue(o, DataResponse.class))
                .map(DataResponse::getData)
                .toList().get(0);
    }
}
