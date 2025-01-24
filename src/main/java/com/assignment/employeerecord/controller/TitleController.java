package com.assignment.employeerecord.controller;

import com.assignment.employeerecord.model.DetailTitle;
import com.assignment.employeerecord.model.WebResponse;
import com.assignment.employeerecord.service.TitleService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class TitleController {
   
   private TitleService titleService;
   
   public TitleController(TitleService titleService) {
      this.titleService = titleService;
   }
   
   @GetMapping("/titles/{empNo}")
   public WebResponse<List<DetailTitle>> getTitleHistoryByEmpNo(Integer empNo) {
      List<DetailTitle> titles = titleService.getHistoryByEmpNo(empNo);
      return WebResponse.<List<DetailTitle>>builder().status(HttpStatus.OK.value()).data(titles).build();
   }
   
   @PostMapping("/titles")
   public WebResponse<DetailTitle> addNewTitle(DetailTitle request) {
      DetailTitle title = titleService.addNewTitle(request);
      return WebResponse.<DetailTitle>builder().status(HttpStatus.CREATED.value()).data(title).build();
   }
   
   @PutMapping("/titles/update")
   public WebResponse<DetailTitle> updateTitle(@RequestBody DetailTitle existing ,@RequestBody DetailTitle request) {
      DetailTitle title = titleService.updateExistingTitle(existing, request);
      return WebResponse.<DetailTitle>builder().status(HttpStatus.OK.value()).data(title).build();
   }
   
   @DeleteMapping("/titles/delete")
   public WebResponse<String> deleteTitle(@RequestBody DetailTitle request) {
      String deleted = titleService.deleteTitle(request);
      return WebResponse.<String>builder().status(HttpStatus.OK.value()).data(deleted).build();
   }
}
