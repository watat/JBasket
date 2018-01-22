package jp.wat.basket.controller;

import java.sql.Timestamp;

import jp.wat.basket.entity.Member;
import jp.wat.basket.form.MemberForm;
import jp.wat.basket.service.MemberService;

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

@Controller
@SessionAttributes(value={"userInfo"})
@EnableWebSecurity
public class MemberEditController {
	
	@ModelAttribute("userInfo")
	UserInfo userInfo() {
	    return new UserInfo();
	}
	
	@Autowired
	MemberService memberService;

	/********************************************************
	 * メンバー登録
	 *******************************************************/
	// 登録画面　表示
	@RequestMapping(value="/member/regist", method=RequestMethod.GET)
	public String memberRegist(UserInfo userInfo, Model model){
		
		// TODO 丸数字はどうしよう？　https://ja.wikipedia.org/wiki/%E4%B8%B8%E6%95%B0%E5%AD%97
		userInfo.setStartViewName("/member/regist");
		model.addAttribute("memberForm", new MemberForm());
		
		System.out.println("test");
		
		return "/member/edit";

	}
	
	// 登録画面　キャンセル
	@RequestMapping(value = {"/member/confirm"}, method = RequestMethod.POST, params = "cancel")
	public String registCancel(Model model) {
		return "redirect:/member";
	}
	
	// 登録確認画面　表示
	@RequestMapping(value = {"/member/confirm"}, method = RequestMethod.POST, params = "confirm")
	public String registConfirm(
			@Validated MemberForm memberForm,
			BindingResult result,
			Model model) {
					
		System.out.println("debug");
		if(result.hasErrors()){
			System.out.println("エラー：" + result.getAllErrors());
			//TODO URLが変わってしまい、キャンセルボタンでエラーが発生する
			return "/member/edit";
		}
		
//		//重複チェック
//		Member befMember = memberService.findByNo(memberForm.getNo());
//		
//		//TODO 背番号の重複チェック
//		if(befMember != null){
//			model.addAttribute("message", "登録済みの背番号です");
//			return "/member/edit";
//		}
		
		//TODO 変更時の変更有無チェック
		//TODO Formに変更有無を追加して、チェック結果を格納
		
		model.addAttribute("memberForm", memberForm);
		return "/member/registConfirm";
	}
	
	// 登録／変更処理（共通）
	@RequestMapping(value={"/member/submit", "/member/edit/submit"}, method=RequestMethod.POST, params="submit")
	public String registComplete(@Validated MemberForm form, UserInfo userInfo,BindingResult result, SessionStatus sessionStatus, Model model,
			RedirectAttributes redirectAttributes){

		ModelMapper modelMapper = new ModelMapper();
		Member member = modelMapper.map(form, Member.class);
				
		// 共通項目の設定
		member.setDeleteFlg(0);
		member.setRegistUser(1); //TODO ログインユーザーに変更
		member.setRegistTime(new Timestamp(System.currentTimeMillis()));
		member.setUpdateUser(1); //TODO ログインユーザーに変更
		member.setUpdateTime(new Timestamp(System.currentTimeMillis()));
		
		// DB更新処理 
		memberService.registMember(member);
		
		String nextViewName = userInfo.getStartViewName();
		sessionStatus.setComplete();
		
		// 登録／編集判定
		if(nextViewName.equals("/member/regist")){
			redirectAttributes.addFlashAttribute("message","登録が完了しました");
			return "redirect:/member/regist";
			
		} else if(nextViewName.equals("/member/edit")){
			redirectAttributes.addFlashAttribute("message","更新が完了しました");
			return "redirect:/member";
			
		} else {
			//異常値の場合はトップに遷移
			return "redirect:/";
		}
	}	

	/********************************************************
	 * メンバー変更
	 *******************************************************/
	// 変更画面　表示
	@RequestMapping(value="/member/edit/{mid}", method=RequestMethod.GET)
	public String memberEdit(@PathVariable("mid") Integer mid, UserInfo userInfo,  Model model){
		
		//セッションに遷移元画面を格納
		userInfo.setStartViewName("/member/edit");
		
		//変更前情報取得
		Member member = memberService.findById(mid);
		ModelMapper modelMapper = new ModelMapper();
		MemberForm befMemberForm = modelMapper.map(member, MemberForm.class);

		model.addAttribute("memberForm", befMemberForm);
		return "/member/edit";
	}
	
	// 変更画面　キャンセル
	@RequestMapping(value = {"/member/edit/confirm"}, method = RequestMethod.POST, params = "cancel")
	public String editCancel(Model model) {
		return "redirect:/member";
	}
	
	// 変更確認画面　表示
	@RequestMapping(value = {"/member/edit/confirm"}, method = RequestMethod.POST, params = "confirm")
	public String editConfirm(
			@Validated MemberForm form,
			BindingResult result,
			Model model) {
					
		//変更前情報取得
		Member member = memberService.findById(form.getMemberId());
		ModelMapper modelMapper = new ModelMapper();
		
		MemberForm befMemberForm = modelMapper.map(member, MemberForm.class);
		
		//TODO バリデーションチェック
		model.addAttribute("befMemberF", befMemberForm);
		model.addAttribute("memberForm", form);
		
		return "/member/confirm";
	}
	
	// ブラウザバック対策
	@RequestMapping(value={"/member/edit/confirm", "/member/confirm"}, method=RequestMethod.GET)
	public String registConfirmBack(Model model){
		return "redirect:/member";
	}
	
	/********************************************************
	 * メンバー削除
	 *******************************************************/
	// 削除確認画面　表示
	@RequestMapping(value = {"/member/delete/{mid}"}, method = RequestMethod.GET)
	public String deleteConfirm(@PathVariable("mid") Integer mid, Model model) {
					
		//登録情報取得
		Member member = memberService.findById(mid);
		ModelMapper modelMapper = new ModelMapper();
		MemberForm form = modelMapper.map(member, MemberForm.class);
		
		//TODO バリデーションチェック
		model.addAttribute("memberForm", form);
		
		return "/member/deleteConfirm";
	}
	
	// 削除完了画面
	@RequestMapping(value={"/member/delete/submit"}, method=RequestMethod.POST, params="submit")
	public String deleteComplete(@Validated MemberForm form, UserInfo userInfo,BindingResult result, SessionStatus sessionStatus, Model model){

		ModelMapper modelMapper = new ModelMapper();
		Member member = modelMapper.map(form, Member.class);
		
		// 共通項目の設定
		member.setDeleteFlg(0);
		member.setRegistUser(1); //TODO ログインユーザーに変更
		member.setRegistTime(new Timestamp(System.currentTimeMillis()));
		member.setUpdateUser(1); //TODO ログインユーザーに変更
		member.setUpdateTime(new Timestamp(System.currentTimeMillis()));
		
		// DB更新処理 
		memberService.deleteMember(member);
		
		String nextViewName = userInfo.getStartViewName();
		sessionStatus.setComplete();
		
		return "redirect:/member";
	}	
	
}