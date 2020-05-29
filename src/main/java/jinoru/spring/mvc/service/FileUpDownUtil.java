package jinoru.spring.mvc.service;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.fileupload.RequestContext;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.servlet.ServletRequestContext;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by jinoru on 2020-05-29.
 */
public class FileUpDownUtil
{
    private String uploadPath="c:/JAVA/pdsupload/";
    // 파일 업로드시 저장된 경로 지정

    // 업로드 처리 메소드
    public Map<String, String> procUpload(HttpServletRequest req) {
        Map<String, String> frmdata = new HashMap<>();

        String fname="";

        // 요청한 폼데이터가 multipart 인지 확인
        RequestContext rctx = new ServletRequestContext(req);
        boolean isMultiPart = FileUpload.isMultipartContent(rctx);

        try {
            if (isMultiPart) {  // 클라이언트 요청이 multipart라면 view에서  enctype="multipart/form-data 확인
                DiskFileItemFactory df = new DiskFileItemFactory();
                ServletFileUpload upload = new ServletFileUpload(df);

                // 클라이언트의 요청정보를 리스트에 저장
                List items = upload.parseRequest(req);
                Iterator<FileItem> param = items.iterator();

                // 리스트에 저장된 요청정보를 하나씩 꺼내서
                // 폼 데이터의 유형에 따라 각각 처리
                while (param.hasNext()) {
                    try {
                        FileItem item = (FileItem)param.next();

                        if (item.isFormField()) {  // 텍스트 데이터라면
                            String name = item.getFieldName();          // 여기서 name은 view에서 type
                            String val = item.getString("utf-8");
                            frmdata.put(name, val);
                        } else {    // 파일 데이터라면
                            String ufname = item.getName(); // 파일 경로 추출

                            // 첨부 파일이 없는 경우 나머지 if문 이후 코드 실행 안함
                            if (ufname.equals("")||ufname==null)
                                continue;

                            // ex) c:/JAVA/jobs.txt
                            fname = ufname.substring(
                                    ufname.lastIndexOf("\\") + 1);   // 파일명 추출 jobs.txt 가 추출된다

                            // ex) fname => jobs.txt
                            // 겹치지 않는 파일명으 ㄹ위해 유니크한 의의 값 생성1
//                            UUID uuid = UUID.randomUUID();
                            // 겹치치 않는 파일명으 ㄹ위해 유니크한 임의의 값 생성2
                            String fmt = "yyyMMddHHmmss";
                            SimpleDateFormat sdf = new SimpleDateFormat(fmt);
                            String uuid = sdf.format(new Date());

                            String fnames[] = fname.split("[.]");
                            //fname = fnames[0] + uuid.toString() + "." + fnames[1];
                            fname = fnames[0] + uuid + "." + fnames[1];

                            // ex) fname => jobs123456789.txt
                            // ex) f => c:/JAVA/pdsupload/jobs123456789.txt
                            File f = new File(uploadPath + "/" + fname);

                            item.write(f);

                            String name = item.getFieldName();
                            frmdata.put(name, fname);

                            // 파일 기타정보 처리
                            long fsize = item.getSize() / 1024;
                            String ftype = fnames[1];

                            frmdata.put(name+"size", fsize+"");
                            frmdata.put(name+"type", ftype);

                            // 파일명 처리 결과 확인
                            System.out.println(ufname + "/" + fname);
                            System.out.println(fsize + "/" + ftype);
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }   // while

            }   // if

        } catch (Exception ex) {
            ex.printStackTrace();
        } // try

        return frmdata;
    }

    // 다운로드 처리 메소드

}
