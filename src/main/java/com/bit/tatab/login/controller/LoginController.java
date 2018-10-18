package com.bit.tatab.login.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bit.tatab.login.service.LoginService;
import com.bit.tatab.login.vo.LoginVO;
import com.bit.tatab.main.vo.CommentVO;
import com.bit.tatab.main.vo.MyPageVO;

@Controller
public class LoginController {

   @Inject
   LoginService loginService;

   // google login
/*   
    @RequestMapping(value="/googleLogin.do", method=RequestMethod.GET)
    public ModelAndView gooleLogin() throws Exception {
      
       System.out.println("구글 로그인 GET");
     
        ModelAndView mav = new ModelAndView("userMain");
      
        return mav;
    }
*/
   
   // google login
    @RequestMapping(value="/googleLogin.do", method=RequestMethod.POST)
    public ModelAndView gooleLogin(HttpServletRequest request, HttpServletResponse response) throws Exception {
      
       System.out.println("구글 로그인 POST");
     
        String googleId = request.getParameter("googleId");
        String name = request.getParameter("name");
      
        LoginVO loginVO = new LoginVO(googleId, name);
        
        System.out.println(loginVO.toString());
        
       
        //////
       List<LoginVO> checkLoginVOList = loginService.memberInfoFind(loginVO);

        // 세션객체 얻어오기
        HttpSession session = request.getSession();
        session.setAttribute("login_email", loginVO.getLoginEmail());
        System.out.println("login_email: " + session.getAttribute("login_email"));

        // 코멘트 관련 세션 활용하는 작업 (원석)
        CommentVO commentVO = loginService.mainCommentFind(loginVO);
        MyPageVO myPageVO = loginService.myPageInfoFind(loginVO);
        
        System.out.println("수정된 commentVO : " + commentVO);
        System.out.println("수정된 myPageVO : " + myPageVO);
        
       if(checkLoginVOList.size() == 0) {
          System.out.println("로그인vo 객체 db에 없음!");
            // 로그인 정보 db에 추가
            loginService.memberInfoInsert(loginVO);
            // 코멘트 관련 기본 내용 삽입
            CommentVO commentVO3 = new CommentVO(session.getAttribute("login_email").toString(),
            		"TATAB에 오신 것을 환영합니다!", 
            		"회원님의 코멘트는 이 곳에 생성됩니다.", 
            		"코멘트 설정을 위해 야구공 버튼을 눌러주세요 →→→→→→→→→→→→→→→→→→→→→→→→→→→");
           
            // 코멘트 관련 정보 db에 추가
            loginService.mainCommentInsert(commentVO3);
            session.setAttribute("mainTitle", commentVO3.getMain_title());
            session.setAttribute("subTitle", commentVO3.getSub_title());
            session.setAttribute("subComment", commentVO3.getSub_comment());
            
            // 마이페이지 관련 기본 내용 삽입
            MyPageVO myPageVO2 = new MyPageVO(session.getAttribute("login_email").toString(),
            		session.getAttribute("login_name").toString(),
            		"Nickname",
            		"Date of Birth",
            		"Motto");
            
            // 마이페이지 관련 정보 db에 추가
            loginService.myPageInfoInsert(myPageVO2);
            session.setAttribute("myPageVO", myPageVO2);
            session.setAttribute("login_name", myPageVO2.getLogin_name());
            session.setAttribute("nickname", myPageVO2.getNickname());
            session.setAttribute("dob", myPageVO2.getDob());
            session.setAttribute("motto", myPageVO2.getMotto());
            
            System.out.println("db에 로그인 정보 등록 완료");
            
       } else {
          System.out.println("vo 객체 db에 있음!");

          // 코멘트 관련 세션 활용하는 작업 (원석) - 정보 있을 때!
          CommentVO commentVO2 = loginService.mainCommentFind(loginVO);
          session.setAttribute("myPageVO", commentVO2);
          /*session.setAttribute("mainTitle", commentVO2.getMain_title());
          session.setAttribute("subTitle", commentVO2.getSub_title());
          session.setAttribute("subComment", commentVO2.getSub_comment());*/
          System.out.println("코멘트야 올라갔니? : " + commentVO2.toString());
          
          // 마이페이지 관련 세션 활용하는 작업 (원석) - 정보 있을 떄!
          MyPageVO myPageVO3 = loginService.myPageInfoFind(loginVO);
          session.setAttribute("myPageVO", myPageVO3);
          System.out.println("내 정보야 올라갔니? : " + myPageVO3.toString());
       } 
       
       // MyPage 관련 정보 
       
        ModelAndView mav = new ModelAndView("userMain");
      
        return mav;
    }
    
    
    // google logout
    @RequestMapping("/googleLogout.do")
    public ModelAndView googleLogout(HttpServletRequest request, HttpServletResponse response) throws Exception {
       
       // 세션객체 얻어오기
       HttpSession session = request.getSession();
       session.invalidate();
       
       ModelAndView mav = new ModelAndView("redirect:/");
       System.out.println("viewName 확인: " + mav.getViewName());
       
       System.out.println("구글 로그아웃 성공");
       return mav;
    }
    
    
}