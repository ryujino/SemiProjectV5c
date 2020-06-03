package jinoru.spring.mvc.controller;

import jinoru.spring.mvc.service.BDReplyService;
import jinoru.spring.mvc.vo.ReplyVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BDReplyController {

    private BDReplyService brsrv;

    @Autowired
    public BDReplyController(BDReplyService brsrv) {
        this.brsrv = brsrv;
    }

    // 대글쓰기 완료
    // PostMapping자동으로 post
    @PostMapping(value = "/reply/bdrpywrite")
    public String bdrpywrite(ReplyVO rvo) {

        brsrv.makeReply(rvo);

        return "redirect:/board/view.do?bno=" + rvo.getBno();
    }

    // 대대글쓰기 완료
    // PostMapping자동으로 post
    @PostMapping(value = "/reply/bdcmtwrite")
    public String bdcmtwrite(ReplyVO rvo) {

        brsrv.makeComment(rvo);

        return "redirect:/board/view.do?bno=" + rvo.getBno();
    }
}
