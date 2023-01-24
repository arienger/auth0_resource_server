package no.eg.nexstephub.server.demoserver.controller;
import no.eg.nexstephub.server.demoserver.model.GeneralResponseDto;
import no.eg.nexstephub.server.demoserver.model.MessageDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Handles requests to "/api" endpoints.
 * @see no.eg.nexstephub.server.demoserver.controller.config.SecurityConfig to see how these endpoints are protected.
 */
@RestController
@RequestMapping(path = "api", produces = MediaType.APPLICATION_JSON_VALUE)
// For simplicity of this sample, allow all origins. Real applications should configure CORS for their use case.
@CrossOrigin(origins = "*")
public class APIController {

//    @GetMapping(value = "/public")
//    public GeneralResponse<Message> publicEndpoint() {
//        GeneralResponse<Message> generalResponse= new GeneralResponse<>();
//        generalResponse.setHttpStatusCode(200);
//        generalResponse.setData(new Message("All good. You DO NOT need to be authenticated to call /api/public."));
//        return generalResponse;
//    }

    @GetMapping(value = "/private/ping")
    public GeneralResponseDto<MessageDto> ping() {
        GeneralResponseDto<MessageDto> generalResponse= new GeneralResponseDto<>();
        generalResponse.setHttpStatusCode(200);
        generalResponse.setData( new MessageDto("All good. You can see this because you are Authenticated."));
        return generalResponse;
    }

    @PostMapping("/private/message")
    public GeneralResponseDto<MessageDto> message(@RequestBody MessageDto messageDto) {
        GeneralResponseDto<MessageDto> generalResponse= new GeneralResponseDto<>();
        generalResponse.setHttpStatusCode(200);
        generalResponse.setData( new MessageDto("Received message: " + (messageDto==null?null:messageDto.getMessage())));
        return generalResponse;
    }

//    @GetMapping(value = "/private-scoped")
//    public GeneralResponse<Message> privateScopedEndpoint() {
//        GeneralResponse<Message> generalResponse= new GeneralResponse<>();
//        generalResponse.setHttpStatusCode(200);
//        generalResponse.setData( new Message("All good. You can see this because you are Authenticated with a Token granted the 'read:messages' scope"));
//        return generalResponse;
//    }
}
