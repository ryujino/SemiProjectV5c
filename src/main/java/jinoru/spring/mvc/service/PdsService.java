package jinoru.spring.mvc.service;

import jinoru.spring.mvc.dao.PdsDAO;
import jinoru.spring.mvc.vo.PdsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * Created by jinoru on 2020-05-28.
 */
@Service("psrv")
public class PdsService {

    private PdsDAO pdao;

    @Autowired
    public PdsService(PdsDAO pdao) {
        this.pdao = pdao;
    }

    public String newPds(PdsVO pv) {
        String result = "데이터입력 실패";

        // 첨부파일 정보를 임시로 저장
        pv.setFname("asf2134.zip");
        pv.setFsize("123");
        pv.setFdown("1231");
        pv.setFtype("zip");

        if(pdao.insertPds(pv))
            result = "데이터 입력 성공";

        return result;
    }

    public ArrayList<PdsVO> showPds() {
        return (ArrayList<PdsVO>)pdao.selectPds();
    }

    public PdsVO showOnePds(String pno) {
        return pdao.selectOnePds(pno);
    }
}
