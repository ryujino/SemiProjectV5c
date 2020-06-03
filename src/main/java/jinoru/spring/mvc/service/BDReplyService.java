package jinoru.spring.mvc.service;

import jinoru.spring.mvc.dao.BDReplyDAO;
import jinoru.spring.mvc.vo.ReplyVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * Created by jinoru on 2020-06-02.
 */

@Service("brsrv")
public class BDReplyService {

    private BDReplyDAO brdao;

    @Autowired
    public BDReplyService(BDReplyDAO brdao) {
        this.brdao = brdao;
    }

    // 댓글 쓰기
    public void makeReply(ReplyVO rvo) {
        brdao.insertReply(rvo);
    }

    // 댓글과 대댓글 가져오기
    public ArrayList<ReplyVO> showReply(String bno) {
        return (ArrayList<ReplyVO>)brdao.selectReply(bno);
    }

    // 대댓글쓰기
    public void makeComment(ReplyVO rvo) {
        brdao.insertComment(rvo);
    }
}
