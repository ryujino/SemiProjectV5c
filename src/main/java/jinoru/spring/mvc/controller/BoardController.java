package jinoru.spring.mvc.controller;

import jinoru.spring.mvc.service.BDReplyService;
import jinoru.spring.mvc.service.GoogleCaptchaUtil;
import jinoru.spring.mvc.vo.ReplyVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import jinoru.spring.mvc.service.BoardService;
import jinoru.spring.mvc.vo.BoardVO;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class BoardController {

    private BoardService bsrv;
    private BDReplyService brsrv;
    private GoogleCaptchaUtil gcutil;

    @Autowired
    // 세개의 멤버변수를 생성자를 통해 DI받음
    public BoardController(BoardService bsrv, BDReplyService brsrv, GoogleCaptchaUtil gcutil) {

        this.bsrv = bsrv;
        this.brsrv = brsrv;
        this.gcutil = gcutil;
    }

    // 목록보기
    @RequestMapping(value = "/board/list")
    public ModelAndView list(String cp) {

        ModelAndView mv = new ModelAndView();

        mv.setViewName("layout/layout"); // 뷰이름 지정
        mv.addObject("action", "../board/list.jsp");

        // 목록 불러오기
        ArrayList<BoardVO> bdlist = bsrv.showBoard(cp);
        mv.addObject("bdlist", bdlist);

        // 총 게시물 수 불러오기
        int bdcnt = bsrv.countBoard();
        mv.addObject("bdcnt", bdcnt);

        return mv;
    }

    // 새글쓰기
    @RequestMapping(value = "/board/write")
    public ModelAndView write() {

        ModelAndView mv = new ModelAndView();
        mv.setViewName("layout/layout"); // 뷰이름 지정

        mv.addObject("action", "../board/write.jsp");

        return mv;
    }

    // 새글쓰기
    @RequestMapping(value = "/board/write", method = RequestMethod.POST)
    public String writeok(BoardVO bd, HttpServletRequest req, RedirectAttributes rda) {
        String returnPage = "redirect:/board/write";
        String gCaptcha = req.getParameter("g-recaptcha");

        if (gcutil.checkCaptcha(gCaptcha)) {
            bsrv.newBoard(bd);
            returnPage = "redirect:/board/list?cp=1";
        } else {
            rda.addFlashAttribute("checkFail","자동가입방지 확인이 실패했습니다.");
        }
        return returnPage;
    }

    // 본문보기
    @RequestMapping(value = "/board/view")
    public ModelAndView view(String bno) {

        ModelAndView mv = new ModelAndView();

        mv.setViewName("layout/layout"); // 뷰이름 지정
        mv.addObject("action", "../board/view.jsp");

        // 본문글
        BoardVO b = bsrv.showOneBoard(bno);
        mv.addObject("b", b);

        // 본문글에 대한 댓글과 대댓글
        List<ReplyVO> r = brsrv.showReply(bno);
        mv.addObject("r",r);

        return mv;
    }

    // 수정하기
    @RequestMapping(value = "/board/update")
    public ModelAndView update() {

        ModelAndView mv = new ModelAndView();
        mv.setViewName("layout/layout"); // 뷰이름 지정

        mv.addObject("action", "../board/modify.jsp");

        return mv;
    }

    // 삭제하기
        @RequestMapping(value = "/board/delete")
        public String delete(String bno) {

        bsrv.removeBoard(bno);

        return "redirect:/board/list?cp=1";
    }

}
