package com.example.chatserverspring.controler;

import com.example.chatserverspring.model.FileDTO;
import com.example.chatserverspring.model.MessageDTO;
import com.example.chatserverspring.service.MessageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.List;

@RestController
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping("add")
    public ResponseEntity<Void> add(@RequestBody MessageDTO messageDTO) {
        messageService.add(messageDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @GetMapping("get")
    public List<MessageDTO> get(@RequestParam(required = false, defaultValue = "0") Long from) {
        return messageService.get(from);
    }



    @GetMapping("file")
    public List<FileDTO> file(@RequestParam Long... messageId) throws UnsupportedEncodingException {
        Long[] ids = messageId;
        return messageService.getFileByMessageId(ids);
    }

    @GetMapping("private")
    public List<MessageDTO> getPrivateMessage(@RequestParam(required = false) String to) {
        return messageService.getPrivateMessage(to);
    }

    @GetMapping("users")
    public List<String> getAllUsers() {
        return messageService.getAllUsers();
    }



    @GetMapping("test")
    public String test() {
        return "It works!";
    }

}
