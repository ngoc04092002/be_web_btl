package ttcs.btl.controller.clients;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ttcs.btl.dto.clients.ClientResponse;
import ttcs.btl.dto.clients.PasswordRequest;
import ttcs.btl.model.client.ClientEntity;
import ttcs.btl.service.clients.IClientService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/")
public class ClientController {

    private final IClientService iClientService;

    @GetMapping("getAll-clients")
    public List<ClientResponse> getAllNews(){
        List<ClientEntity> allCLients = iClientService.getAllCLients();

        return allCLients.stream().map(this::clientResponse).toList();
    }

    @PostMapping("save-client")
    public ClientResponse saveClient(@RequestBody ClientEntity clientEntity){
        return iClientService.saveClient(clientEntity);
    }

    @PutMapping("update-password")
    public String updatePassword(@RequestBody @Valid PasswordRequest passwordRequest){
        return iClientService.updatePassword(passwordRequest);
    }

    @DeleteMapping("/delete-client/{id}")
    public String deleteClient(@PathVariable("id") Long id){
        return iClientService.deleteClient(id);
    }

    private ClientResponse clientResponse(ClientEntity clientEntity){
        return new ClientResponse(clientEntity);
    }
}
