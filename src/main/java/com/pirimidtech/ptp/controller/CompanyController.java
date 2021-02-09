package com.pirimidtech.ptp.controller;
import com.pirimidtech.ptp.entity.AssetDetail;
import com.pirimidtech.ptp.service.asset.AssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
@RestController
public class CompanyController {
    @Autowired
    private AssetService assetService;
    @RequestMapping(method = RequestMethod.GET, value = "/company/{id}")
    public Optional<AssetDetail> getAssetDetail(@PathVariable UUID id){
        return assetService.getAssetDetail(id);
    }
    @RequestMapping(method = RequestMethod.POST, value = "/company")
    public void addAssetDetail(@RequestBody AssetDetail assetDetail){
        assetService.addCompany(assetDetail);
    }
    @GetMapping(value = "/company")
    public List<AssetDetail> assetDetailList(){
        return assetService.getAllAssetDetail();
    }
    @GetMapping(value = "/search/{infix}")
    public List<AssetDetail> searchAssetList(@PathVariable String infix){
        return assetService.searchAssetDetail(infix);
    }
}