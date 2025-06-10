package com.project.demo.controller;

import com.project.demo.entity.CleaningKnowledge;
import com.project.demo.service.CleaningKnowledgeService;
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
 *保洁常识：(CleaningKnowledge)表控制层
 *
 */
@RestController
@RequestMapping("/cleaning_knowledge")
public class CleaningKnowledgeController extends BaseController<CleaningKnowledge,CleaningKnowledgeService> {

    /**
     *保洁常识对象
     */
    @Autowired
    public CleaningKnowledgeController(CleaningKnowledgeService service) {
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
