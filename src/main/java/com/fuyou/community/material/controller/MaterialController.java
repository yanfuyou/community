package com.fuyou.community.material.controller;

import com.fuyou.community.common.ResultVo;
import com.fuyou.community.material.model.Material;
import com.fuyou.community.material.service.MaterialService;
import com.fuyou.community.sys.model.dto.PageQueryDto;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "资料操作")
@RequiredArgsConstructor
@RequestMapping("/material")
public class MaterialController {
    private final MaterialService materialService;

    @PostMapping("/upload")
    public ResultVo upload(@RequestBody Material material){
        return materialService.upload(material);
    }

    @GetMapping("/remove/{id}")
    public ResultVo remove(@PathVariable String id){
        return materialService.remove(id);
    }

    @GetMapping("/myMaterial/{userName}/{flag}")
    public ResultVo<List<Material>> myMaterial(@PathVariable String userName, @PathVariable String flag){
        return materialService.myMaterial(userName, flag);
    }

    public ResultVo list(PageQueryDto<Material> maDto){
        return null;
    }

}
