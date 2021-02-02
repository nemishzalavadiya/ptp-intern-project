package com.pirimidtech.ptp.service.position;

import com.pirimidtech.ptp.entity.Position;
import com.pirimidtech.ptp.repository.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class PositionService implements PositionServiceInterface{

    @Autowired
    private PositionRepository positionRepository;

    @Override
    public List<Position> getAllPosition(UUID userId) {
        return positionRepository.findAllByUserId(userId);
    }

    @Override
    public void deleteFromPosition(UUID positionId) {
        positionRepository.deleteById(positionId);
    }

    @Override
    public void addToPosition(Position position) {
            positionRepository.save(position);
    }
}
