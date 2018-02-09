package jp.wat.basket.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import jp.wat.basket.entity.Schedule;
import jp.wat.basket.form.ScheduleForm;
import jp.wat.basket.service.ScheduleService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
@SessionAttributes(value={"userInfo"})
@EnableWebSecurity
public class SchduleEditController {
	private static final Logger logger = LoggerFactory.getLogger(SchduleEditController.class);
	
	@ModelAttribute("userInfo")
	UserInfo userInfo() {
	    return new UserInfo();
	}
	
	@Autowired
	ScheduleService scheduleService;

	/********************************************************
	 * スケジュール編集
	 *******************************************************/
	// スケジュール編集画面　表示
	@RequestMapping(value="/schedule/edit/{month}", method=RequestMethod.GET)
	public String memberEdit(@PathVariable("month") Integer month, UserInfo userInfo,  Model model){
				
		// TODO 年度を設定する仕組みを検討。暫定で2017を設定
		Integer nendo = Integer.valueOf(2017);
		
		//スケジュール一覧取得
		List<Schedule> scheduleList= scheduleService.getScheduleData(nendo, month);
		ModelMapper modelMapper = new ModelMapper();
		
		List<ScheduleForm> scheduleFormList = new ArrayList<ScheduleForm>();
		
		for(Schedule schedule: scheduleList){
			ScheduleForm scheduleForm = modelMapper.map(schedule, ScheduleForm.class);
			scheduleFormList.add(scheduleForm);
		}
		
		model.addAttribute("scheduleFormList", scheduleFormList);
		return "/schedule/edit/scheduleEdit";
	}
	
//	// 変更画面　キャンセル
//	@RequestMapping(value = {"/member/edit/confirm"}, method = RequestMethod.POST, params = "cancel")
//	public String editCancel(Model model) {
//		return "redirect:/member";
//	}
	
//	// 変更確認画面　表示
//	@RequestMapping(value = {"/member/edit/confirm"}, method = RequestMethod.POST, params = "confirm")
//	public String editConfirm(
//			@Validated MemberForm form,
//			BindingResult result,
//			Model model) {
//					
//		//変更前情報取得
//		Member member = memberService.findById(form.getMemberId());
//		ModelMapper modelMapper = new ModelMapper();
//		
//		MemberForm befMemberForm = modelMapper.map(member, MemberForm.class);
//		
//		//TODO バリデーションチェック
//		model.addAttribute("befMemberF", befMemberForm);
//		model.addAttribute("memberForm", form);
//		
//		return "/member/edit/editConfirm";
//	}
}
