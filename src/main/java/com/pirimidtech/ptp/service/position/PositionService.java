package com.pirimidtech.ptp.service.position;

import com.pirimidtech.ptp.entity.Action;
import com.pirimidtech.ptp.entity.AssetClass;
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
    public void addStockToPosition(Position position, Action action) {

        Optional<Position> positionOptional = positionRepository.findAllByUserIdAndAssetDetailId(position.getUser().getId(), position.getAssetDetail().getId());
        if (positionOptional.isPresent()) {
            Position oldPosition = positionOptional.get();
            if (action.equals(Action.BUY)) {
                float currentPrice = oldPosition.getPrice();
                float currentVolume = oldPosition.getVolume();
                float price = currentPrice*currentVolume;
                float average = (price + position.getVolume()*position.getPrice())/(currentVolume + position.getVolume());
                oldPosition.setPrice(average);
                oldPosition.setVolume(oldPosition.getVolume() + position.getVolume());
            } else {
                if (oldPosition.getVolume() - position.getVolume()<= 0) {
                    deleteFromPosition(oldPosition.getId());
                    return;
                }
                float currentPrice = oldPosition.getPrice();
                float currentVolume = oldPosition.getVolume();
                float price = currentPrice*currentVolume;
                float average = (price - position.getVolume()*position.getPrice())/(oldPosition.getVolume()-position.getVolume());
                oldPosition.setPrice(average);
                oldPosition.setVolume(oldPosition.getVolume() - position.getVolume());
            }
            positionRepository.save(oldPosition);

        }else
        {
            positionRepository.save(position);
        }
    }

    public void addMutualFundToPosition(Position position) {
        Optional<Position> positionOptional = positionRepository.findAllByUserIdAndAssetDetailId(position.getUser().getId(), position.getAssetDetail().getId());
        if (positionOptional.isPresent()) {
            Position oldPosition = positionOptional.get();
            float currentPrice = oldPosition.getPrice(); // total
            float currentVolume = oldPosition.getVolume(); // total unit
            oldPosition.setPrice(currentPrice + position.getPrice());
            oldPosition.setVolume(oldPosition.getVolume() + position.getVolume());
            positionRepository.save(oldPosition);
        } else {
            positionRepository.save(position);
        }
    }
    public Position getPositionByUserIdAndAssetDetailId(UUID userId, UUID assetDetailId) {
        Optional<Position> position = positionRepository.findAllByUserIdAndAssetDetailId(userId, assetDetailId);
        return position.isPresent() ? position.get() : null;
    }

}
