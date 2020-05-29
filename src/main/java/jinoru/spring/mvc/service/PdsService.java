package jinoru.spring.mvc.service;

import jinoru.spring.mvc.dao.PdsDAO;
import jinoru.spring.mvc.vo.PdsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Map;

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

    public String newPds(PdsVO pv, Map<String, String> frmdata) {
        String result = "데이터입력 실패";

        procFormdata(pv, frmdata);

        if(pdao.insertPds(pv))
            result = "데이터 입력 성공";

        return result;
    }

    public ArrayList<PdsVO> showPds() {
        return (ArrayList<PdsVO>)pdao.selectPds();
    }

    public PdsVO showOnePds(String pno) {
        pdao.updateViewPds(pno);    // 조회수 증가
        return pdao.selectOnePds(pno);
    }

    // multipart 폼 데이터 처리
    private void procFormdata(PdsVO pv, Map<String, String> frmdata) {
        // multipart 폼 데이터 처리
        for(String key:frmdata.keySet()) {
            String val = frmdata.get(key);
            switch (key) {
                case "title":pv.setTitle(val); break;
                case "userid":pv.setUserid(val); break;
                case "contents":pv.setContents(val); break;

                case "file1":pv.setFname(val); break;
                case "file1size":pv.setFsize(val); break;
                case "file1type":pv.setFtype(val); break;
            }
        }
    }

    // 첨부파일 다운수 처리
    public void modifyDown(String pno) {
        pdao.updateDownPds(pno);
    }
}
