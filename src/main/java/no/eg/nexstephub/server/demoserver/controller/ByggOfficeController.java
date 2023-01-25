package no.eg.nexstephub.server.demoserver.controller;

import no.eg.nexstephub.server.demoserver.model.ByggOfficeDto;
import no.eg.nexstephub.server.demoserver.model.GeneralResponseDto;
import no.eg.nexstephub.server.demoserver.model.MessageDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

import static no.eg.nexstephub.server.demoserver.util.DateTimeUtil.dateTimeFormatter;


@RestController
@RequestMapping(path = "byggoffice", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*")
public class ByggOfficeController {

    @PostMapping("/export")
    public ResponseEntity<GeneralResponseDto> export(@RequestBody ByggOfficeDto byggOfficeDto) {
        if (byggOfficeDto == null) {
            return createResponse(HttpStatus.BAD_REQUEST.value(),"No input, parameter 'byggOfficeDto' is null", HttpStatus.BAD_REQUEST);
        }

        if (byggOfficeDto.getTransactions() == null || byggOfficeDto.getTransactions().size() == 0) {
            return createResponse(HttpStatus.BAD_REQUEST.value(),"No transactions in 'byggOfficeDto', parameter 'byggOfficeDto.transactions' is null or empty", HttpStatus.BAD_REQUEST);
        }


        GeneralResponseDto generalResponse= new GeneralResponseDto<>();
        generalResponse.setHttpStatusCode(200);
        generalResponse.setData( new MessageDto("ByggOffice received " + byggOfficeDto.getTransactions().size() + " transactions"));
        return new ResponseEntity<>(generalResponse, HttpStatus.OK);
    }

    public static ResponseEntity<GeneralResponseDto> createResponse(int statusCode, String message, HttpStatus httpStatus) {
        GeneralResponseDto generalResponseDto = new GeneralResponseDto();
        generalResponseDto.setHttpStatusCode(statusCode);
        generalResponseDto.setMessage(message);
        generalResponseDto.setOccurred_at(LocalDateTime.now().format(dateTimeFormatter));
        return new ResponseEntity<>(generalResponseDto, httpStatus);
    }
}

