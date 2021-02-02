package com.pirimidtech.ptp.controller;

import com.pirimidtech.ptp.entity.Position;
import com.pirimidtech.ptp.exception.ErrorHandler;
import com.pirimidtech.ptp.service.position.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
public class PositionController {
    @Autowired
    private PositionService positionService;

    @GetMapping("position/{userId}")
    List<Position> getPosition(@PathVariable("userId") UUID userId)
    {
        List<Position> positionList = new ArrayList<>();
        try {
            positionList = positionService.getAllPosition(userId);
        } catch (Exception exception) {
            throw new ErrorHandler(exception.getCause());
        }
        return positionList;
    }
}
