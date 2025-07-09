package com.yotion.document;

import com.aerospike.client.AerospikeClient;
import com.aerospike.client.Bin;
import com.aerospike.client.Key;
import com.aerospike.client.policy.Policy;
import com.yotion.document.dto.DocumentDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

/**
 * File is created by andreychernenko at 08.07.2025
 */

@RestController
@RequestMapping
public class Controller {

    private AerospikeClient client;
    private String namespace;

    public Controller(AerospikeClient client, String namespace) {
        this.client = client;
        this.namespace = namespace;
    }

    @GetMapping("/add-new-object")
    public ResponseEntity<?> addNewObject() {
        Bin greeting = new Bin("Greeting", Map.of("Hello", "World"));
        Bin added = new Bin("Added", String.valueOf(new Date()));
        Key testKey = new Key(this.namespace, "TestKey", "Key");
        client.put(client.getWritePolicyDefault(), testKey, greeting, added);
        return ResponseEntity.ok(client.get(new Policy(), testKey));
    }

    @PostMapping("/addNewDocument")
    public ResponseEntity<UUID> newDocument(@Valid DocumentDTO documentDTO) {
        return null;
    }
}
