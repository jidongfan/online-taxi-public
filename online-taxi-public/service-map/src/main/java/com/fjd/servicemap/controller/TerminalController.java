package com.fjd.servicemap.controller;

import com.fjd.internalcommon.dto.ResponseResult;
import com.fjd.internalcommon.response.TerminalResponse;
import com.fjd.servicemap.service.TerminalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: fanjidong R22496
 * @version: 1.0
 * @Date: 2023/3/19 8:39
 * @desc:
 */
@RestController
@RequestMapping("/terminal")
public class TerminalController {

    @Autowired
    private TerminalService terminalService;

    /**
     * 创建终端
     * @param name
     * @param desc 终端描述
     * @return
     */
    @RequestMapping("/add")
    public ResponseResult<TerminalResponse> add(@RequestParam String name, @RequestParam String desc){

        return terminalService.add(name, desc);
    }

    /**
     * 周边搜索
     * @param center
     * @param radius
     * @return
     */
    @PostMapping("/aroundsearch")
    public  ResponseResult<List<TerminalResponse>> aroundsearch(@RequestParam String center, @RequestParam Integer radius){

        return terminalService.aroudsearch(center, radius);
    }

}
