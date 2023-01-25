package no.eg.nexstephub.server.demoserver.controller;

import no.eg.nexstephub.server.demoserver.model.ByggOfficeDto;
import no.eg.nexstephub.server.demoserver.model.GeneralResponseDto;
import no.eg.nexstephub.server.demoserver.model.MessageDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "byggoffice", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*")
public class ByggOfficeController {

    @PostMapping("/export")
    public GeneralResponseDto<MessageDto> export(@RequestBody ByggOfficeDto byggOfficeDto) {
        GeneralResponseDto<MessageDto> generalResponse= new GeneralResponseDto<>();
        generalResponse.setHttpStatusCode(200);
        generalResponse.setData( new MessageDto("ByggOffice received "));
        return generalResponse;
    }
}
