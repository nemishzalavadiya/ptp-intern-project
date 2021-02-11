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
public class PositionService implements PositionServiceInterface{

    @Autowired
    private PositionRepository positionRepository;

    @Override
    public List<Position> getAllPosition(UUID userId,int pageNo,int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Position> pageResult=positionRepository.findAllByUserId(userId,pageable);

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
                Position oldPosition=positionOptional.get();
                if(position.getAssetClass()==AssetClass.STOCK)
                {
                    if (action.equals(Action.BUY)) {
                        oldPosition.setVolume(oldPosition.getVolume() + position.getVolume());
                    } else {
                        oldPosition.setVolume(oldPosition.getVolume() - position.getVolume());
                        if (oldPosition.getVolume() <= 0) {
                            deleteFromPosition(oldPosition.getId());
                            return;
                        }

                    }
                }
                else
                {
                    oldPosition.setPrice(oldPosition.getPrice() + position.getPrice());
                }
                positionRepository.save(oldPosition);
            } else {
                positionRepository.save(position);
            }
        }

        public Position getPositionByUserIdAndAssetDetailId(UUID userId,UUID assetDetailId)
        {
            Optional<Position> position=positionRepository.findAllByUserIdAndAssetDetailId(userId,assetDetailId);
            return position.isPresent()?position.get():null;
        }

}
