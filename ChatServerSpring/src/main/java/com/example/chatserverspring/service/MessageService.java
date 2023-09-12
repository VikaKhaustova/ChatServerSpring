package com.example.chatserverspring.service;

import com.example.chatserverspring.model.FileDTO;
import com.example.chatserverspring.model.Message;
import com.example.chatserverspring.model.MessageDTO;
import com.example.chatserverspring.repo.MessageRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
public class MessageService {

    private final MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Transactional
    public void add(MessageDTO messageDTO) {
        var message = Message.fromDTO(messageDTO);
        message.setFileDataList(messageDTO.getFileDataList());
        message.setFileNames(messageDTO.getFileNames());
        messageRepository.save(message);
    }

    @Transactional(readOnly = true)
    public List<MessageDTO> get(long id) {
        var messages = messageRepository.findNew(id);
        var result = new ArrayList<MessageDTO>();

        messages.forEach(message -> result.add(message.toDTO()));
        return result;
    }

    @Transactional(readOnly = true)
    public List<FileDTO> getFileByMessageId(Long[] ids) throws UnsupportedEncodingException {

        var result = new ArrayList<FileDTO>();

        for (long id : ids){
            var message = messageRepository.findFile(id);
            var temp = new FileDTO();
            temp.setFileData(message.getFileData());
            temp.setFileName(message.getFileName());
            temp.setId(message.getId());
            byte[] fileData = Base64.getEncoder().encode(message.getFileData().getBytes("UTF-8"));
            temp.setFileDataByte(fileData);
            result.add(temp);
        }
        return result;
    }

    @Transactional(readOnly = true)
    public List<MessageDTO> getPrivateMessage(String to) {
        var messages = messageRepository.findPrivate(to);
        var result = new ArrayList<MessageDTO>();

        messages.forEach(message -> result.add(message.toDTO()));
        return result;
    }

    @Transactional(readOnly = true)
    public List<String> getAllUsers() {
        var messages = messageRepository.getUsers();
        var result = new ArrayList<String>();

        messages.forEach(message -> result.add(message));
        return result;
    }


}
