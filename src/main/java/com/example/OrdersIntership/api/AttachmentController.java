package com.example.OrdersIntership.api;

import com.example.OrdersIntership.dto.AttachmentDto;
import com.example.OrdersIntership.service.interfaces.AttachmentServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AttachmentController {

    private final AttachmentServiceInterface attachmentService;

    @GetMapping("/attachment")
    public ResponseEntity<List<AttachmentDto>> allAttachments(){

        return new ResponseEntity<>(attachmentService.findAllAttachments(), HttpStatus.OK);
    }

    @GetMapping("/attachment/{id}")
    public ResponseEntity<AttachmentDto> getAttachmentById(@PathVariable("id") Long id){

        return new ResponseEntity<>(attachmentService.findAttachmentById(id), HttpStatus.OK);
    }

    @PostMapping("/attachment")
    public ResponseEntity<HttpStatus> saveAttachment(@RequestBody AttachmentDto attachment){

        attachmentService.createAttachment(attachment);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @PutMapping("/attachment")
    public ResponseEntity<HttpStatus> updateAttachment(@RequestBody AttachmentDto attachment){

        attachmentService.updateAttachment(attachment);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("attachment/{id}")
    public ResponseEntity<HttpStatus> deleteAttachment(@PathVariable("id") Long id){

        attachmentService.deleteAttachment(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
