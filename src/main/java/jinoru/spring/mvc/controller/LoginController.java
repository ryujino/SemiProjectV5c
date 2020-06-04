package jinoru.spring.mvc.controller;

import jinoru.spring.mvc.service.LoginService;
import jinoru.spring.mvc.vo.MemberVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    private LoginService lsrv;

    @Autowired
    public LoginController(LoginService lsrv) {
        this.lsrv = lsrv;
    }

    // 로그인 체크 기능
    // 입력한 아이디/비밀번호로 로그인 가능 여부 확인
    // 로그인 성공시 로그인 여부를 시스템에 저장하기 위해
    // HttpSession 객체를 이용한다.
    // 즉, 서버가 생성한 정보를 일정기간 동안
    // WAS에 저장해두고 필요할때 마다 이것을 활용함으로써
    // 로그인한 사용자를 식별할 수 있다.
    @PostMapping(value = "login/login")
    public String login(MemberVO mvo, HttpSession sess) {
        String returnPage = "redirect:/login/fail";

        if (lsrv.checkLogin(mvo, sess)) {     // 로그인 성공시
            returnPage = "redirect:/index";
        } else {

        }


        return "redirect:/index";
    }

    // 로그아웃
    // 로그인 성공시 로그인 여부를 세션객체로 만들어 두었음
    // 로그아웃시에는 이 세션 객체를 지워버리면 된다.
    @RequestMapping(value = "login/logout", method = RequestMethod.GET)
    public String logout(HttpSession sess) {

        // 세션객체를 서버에서 제거한다.
        sess.invalidate();

        return "redirect:/index";
    }

    // 로그인 실패 또는 로그인이 필요한 페이지에
    // 무단으로 접근했을시
    @RequestMapping(value = "login/fail")
    public ModelAndView loginfail() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("layout/layout");
        mv.addObject("action", "../loginfail.jsp");

        return mv;
    }
}
