package com.ruoyi.hachimiCli.controller;

import com.ruoyi.hachimiCli.domaindto.CaseAndImgDto;
import com.ruoyi.hachimiCli.domaindto.PersonalDto;
import com.ruoyi.hachimiCli.domaindto.PublicDto;
import com.ruoyi.hachimiCli.service.AdoptFeedbackService_Cli;
import com.ruoyi.hachimiCli.service.AdoptionApplyService_Cli;
import com.ruoyi.hachimiCli.service.PersonalService_Cli;
import com.ruoyi.hachimiCli.service.RescueCaseService_Cli;
import com.ruoyi.hachimiSys.domain.AdoptFeedback;
import com.ruoyi.hachimiSys.domain.AdoptionApply;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/personal")
public class PersonalController_Cli {

    @Autowired
    private PersonalService_Cli personalService;

    @Autowired
    private AdoptFeedbackService_Cli adoptFeedbackServiceCli;
    @Autowired
    private AdoptionApplyService_Cli adoptionApplyServiceCli;

    @Autowired
    private RescueCaseService_Cli rescueCaseServiceCli;

    // 查询公开信息
    @GetMapping("/getPublicInfo")
    public PublicDto getPublicInfo(HttpServletRequest request) {

        System.out.println(">>> Controller URI: " + request.getRequestURI());
        Long userId = (Long) request.getAttribute("userId");
        String name = (String) request.getAttribute("username");
        System.out.println(userId);
        System.out.println(name);
        return personalService.getPublicInfo(userId);
    }

    // 查询隐私信息
    @GetMapping("/getPersonalInfo")
    public PersonalDto getPersonalInfo(HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        PersonalDto personalDto=personalService.getPersonalInfo(userId);
        System.out.println(personalDto);
        System.out.println(userId);
        return personalDto;
    }

    // 更新公开信息
    @PutMapping("/updatePublicInfo")
    public int updatePublicInfo(@RequestBody PublicDto publicDto,
                                   HttpServletRequest request) {
        try {
            Long userId = (Long) request.getAttribute("userId");
            String username = (String) request.getAttribute("username");
            publicDto.setUsername(username);

            return personalService.updatePublicInfo(userId, publicDto);
        } catch (Exception e) {
            return 0;
        }
    }

    // 更新隐私信息
    @PutMapping("/updatePersonalInfo")
    public int updatePersonalInfo(@RequestBody PersonalDto personalDto,
                                     HttpServletRequest request) {
        try {
            Long userId = (Long) request.getAttribute("userId");
            return personalService.updatePersonalInfo(userId, personalDto);
        } catch (Exception e) {
            return 0;
        }
    }

    // 查询收养反馈信息
    @GetMapping("/getAdoptFeedback")
    public List<AdoptFeedback> getAdoptFeedback(HttpServletRequest request){
        Long userId = (Long) request.getAttribute("userId");
        return adoptFeedbackServiceCli.selectAdoptFeedbackByUserId(userId);
    }

    //更新收养反馈信息
    @PostMapping("/updateAdoptFeedback")
    public int updateAdoptFeedbackByFeedbackId(@RequestBody AdoptFeedback adoptFeedback){
        try {
            return adoptFeedbackServiceCli.updateAdoptFeedbackByFeedbackId(adoptFeedback);
        } catch (Exception e) {
            return 0;
        }
    }

    //删除收养反馈信息和关联的图片信息
    @PutMapping("/deleteAdoptFeedback")
    public int deleteAdoptFeedbackAndCaseImageByFeedbackId(@Parameter Long feedbackId){
        try {
            return adoptFeedbackServiceCli.deleteAdoptFeedbackByFeedbackId(feedbackId);
        } catch (Exception e) {
            return 0;
        }
    }


    //查询收养申请信息
    @GetMapping("/getAdoptionApply")
    public List<AdoptionApply> getAdoptionApply(HttpServletRequest request){
        Long userId = (Long) request.getAttribute("userId");
        return adoptionApplyServiceCli.selectAdoptionApplyByUserId(userId);
    }

    //更新收养申请信息
    @PostMapping("/updateAdoptionApply")
    public int updateAdoptionApplyByApplyId(@RequestBody AdoptionApply adoptionApply){
        return adoptionApplyServiceCli.updateAdoptionApplyByApplyId(adoptionApply);
    }

    //删除收养申请消息
    @PutMapping("/deleteAdoptionApply")
    public int deleteAdoptionApplyByApplyId(@Parameter Long applyId){
        return adoptionApplyServiceCli.deleteAdoptionApplyByApplyId(applyId);
    }

    //查询救助信息
    @GetMapping("/getRescueCase")
    public List<CaseAndImgDto> getRescueCase(HttpServletRequest request){
        Long userId = (Long) request.getAttribute("userId");
        return rescueCaseServiceCli.selectRescueCaseByUserId(userId);
    }

    //更新救助信息
    @PostMapping("/updateRescueCase")
    public int updateRescueCaseAndRescuePlaceByCaseId(@RequestBody CaseAndImgDto caseAndImgDto){
        return rescueCaseServiceCli.updateRescueCaseAndRescuePlaceByCaseId(caseAndImgDto);
    }

    //删除救助信息
    @PutMapping("/deleteRescueCase")
    public int deleteRescueCaseByCaseId(@Parameter Long caseId){
        return rescueCaseServiceCli.deleteRescueCaseByCaseId(caseId);
    }
}