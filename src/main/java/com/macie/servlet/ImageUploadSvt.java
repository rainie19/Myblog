package com.macie.servlet;

import com.macie.common.CommonConstants;
import com.macie.helper.ImageUploadHelper;
import com.macie.helper.JsonReponseHelper;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 上传图片
 *
 * @author Macie
 * @date 2020/11/8 -23:31
 */
@WebServlet("/uploadImg")
public class ImageUploadSvt extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        JsonReponseHelper jsonReponse = new JsonReponseHelper();
        try {
            // 获取multipart/formdata方式上传的表单
            DiskFileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            List items = upload.parseRequest(req);
            Map<String, String> userInfoMap = new HashMap();
            for (Object object : items) {
                FileItem fileItem = (FileItem) object;
                if (fileItem.isFormField()) {

                } else {
                    if ("image".equals(fileItem.getFieldName())) {
                        UUID randomUUID = UUID.randomUUID();
                        String itemName = fileItem.getName();
                        if (itemName == null) {
                            jsonReponse.setResponseFailed("图片上传错误");
                            return;
                        }
                        // 获取后缀名
                        int index = itemName.lastIndexOf('.');
                        String imgName = itemName.substring(0, index);
                        String imgType = itemName.substring(index);

                        Date currentTime = new Date();
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("_yyyy_MM_dd_");
                        String formatTime = simpleDateFormat.format(currentTime);

                        String filename = imgName + formatTime + currentTime.getTime() + imgType;
                        byte[] image = fileItem.get();

                        String imageUrl = ImageUploadHelper.saveImage(image, CommonConstants.IMAGE_UPLOAD_PATH_ARTICLE, filename);
                        jsonReponse.setResponseOK("imageUrl", imageUrl);

                    }
                    out.println(jsonReponse);
                }
            }

        } catch (FileUploadException e) {
            e.printStackTrace();
        }
    }
}
