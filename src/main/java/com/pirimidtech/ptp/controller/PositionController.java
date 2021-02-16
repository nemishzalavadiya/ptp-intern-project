package com.pirimidtech.ptp.controller;

import com.pirimidtech.ptp.entity.Position;
import com.pirimidtech.ptp.service.position.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
public class PositionController {

    @Autowired
    private PositionService positionService;

    @GetMapping("/position/users/{id}")
    public ResponseEntity<List<Position>> getPosition(@PathVariable("id") UUID userId, @RequestParam(name = "page", defaultValue = "0") int page, @RequestParam(name = "size", defaultValue = "10") int size) {
        List<Position> positionList = positionService.getAllPosition(userId, page, size);
        return ResponseEntity.ok().body(positionList);
    }
}
