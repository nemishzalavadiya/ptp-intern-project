package com.pirimidtech.ptp.controller;

import com.pirimidtech.ptp.entity.Position;
import com.pirimidtech.ptp.exception.ErrorHandler;
import com.pirimidtech.ptp.service.position.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
public class PositionController {

    @Autowired
    private PositionService positionService;

    @GetMapping("position/users/{id}")
    public ResponseEntity<List<Position>> getPosition(@PathVariable("id") UUID userId, @RequestParam(name = "page") int page, @RequestParam(name = "size") int size) {
        List<Position> positionList = new ArrayList<>();
        try {
            positionList = positionService.getAllPosition(userId, page, size);
        } catch (Exception exception) {
            throw new ErrorHandler(exception.getCause());
        }
        return positionList.size() != 0 ? ResponseEntity.ok().body(positionList) : ResponseEntity.notFound().build();
    }
}
