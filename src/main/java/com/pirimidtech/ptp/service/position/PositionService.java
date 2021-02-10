package com.pirimidtech.ptp.service.position;

import com.pirimidtech.ptp.entity.Action;
import com.pirimidtech.ptp.entity.Position;
import com.pirimidtech.ptp.repository.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PositionService implements PositionServiceInterface {

    @Autowired
    private PositionRepository positionRepository;

    @Override
    public List<Position> getAllPosition(UUID userId, int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Position> pageResult = positionRepository.findAllByUserId(userId, pageable);
        return pageResult.toList();
    }

    @Override
    public void deleteFromPosition(UUID positionId) {
        positionRepository.deleteById(positionId);
    }

    @Override
    public void addToPosition(Position position, Action action) {
        Optional<Position> positionOptional = positionRepository.findAllByUserIdAndAssetDetailId(position.getUser().getId(), position.getAssetDetail().getId());
        if (positionOptional.isPresent()) {
            if (action.equals(Action.BUY)) {
                positionOptional.get().setVolume(positionOptional.get().getVolume() + position.getVolume());
            } else {
                positionOptional.get().setVolume(positionOptional.get().getVolume() - position.getVolume());
                if (positionOptional.get().getVolume() == 0) {
                    deleteFromPosition(positionOptional.get().getId());
                }
            }
            positionRepository.save(positionOptional.get());
        } else {
            positionRepository.save(position);
        }

    }


}
