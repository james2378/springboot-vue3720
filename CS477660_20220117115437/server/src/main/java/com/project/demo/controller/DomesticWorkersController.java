package com.project.demo.controller;

import com.project.demo.entity.DomesticWorkers;
import com.project.demo.service.DomesticWorkersService;
import com.project.demo.controller.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 *家政人员：(DomesticWorkers)表控制层
 *
 */
@RestController
@RequestMapping("/domestic_workers")
public class DomesticWorkersController extends BaseController<DomesticWorkers,DomesticWorkersService> {

    /**
     *家政人员对象
     */
    @Autowired
    public DomesticWorkersController(DomesticWorkersService service) {
        setService(service);
    }

    @PostMapping("/add")
    @Transactional
    public Map<String, Object> add(HttpServletRequest request) throws IOException {
        Map<String,Object> paramMap = service.readBody(request.getReader());
        this.addMap(paramMap);
        return success(1);
    }

}
