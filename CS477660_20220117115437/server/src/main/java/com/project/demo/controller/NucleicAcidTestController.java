package com.project.demo.controller;

import com.project.demo.entity.NucleicAcidTest;
import com.project.demo.service.NucleicAcidTestService;
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
 *核酸检测：(NucleicAcidTest)表控制层
 *
 */
@RestController
@RequestMapping("/nucleic_acid_test")
public class NucleicAcidTestController extends BaseController<NucleicAcidTest,NucleicAcidTestService> {

    /**
     *核酸检测对象
     */
    @Autowired
    public NucleicAcidTestController(NucleicAcidTestService service) {
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
