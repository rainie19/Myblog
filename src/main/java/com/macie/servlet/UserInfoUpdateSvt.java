package com.macie.servlet;

import com.macie.bean.vo.UserInfoVo;
import com.macie.common.CommonConstants;
import com.macie.helper.ImageUploadHelper;
import com.macie.helper.JsonResponseHelper;
import com.macie.helper.ResponseCode;
import com.macie.service.serviceImpl.UserInfoUpdateServiceImpl;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Macie
 * @date 2020/11/2 -20:35
 */
@WebServlet("/modifyUserInfo")
public class UserInfoUpdateSvt extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        /*
        ServletInputStream inputStream = req.getInputStream();
        StringBuffer stringBuffer = new StringBuffer();
        jsonResponseHelper jsonObject = new jsonResponseHelper();
        int len = 0;
        byte[] b = new byte[1024];
        while((len = inputStream.read(b)) != -1) {
            stringBuffer.append(new String(b, 0, len));
        }
        HashMap<String, StringBuffer> userInfoMap = jsonObject.convertString2Map(stringBuffer);
        String type = req.getParameter("type");
        StringBuffer userAvatar = userInfoMap.get("userAvatar");
        System.out.println("userAvatar:"+userAvatar);
        UserInfoVo userInfoVo = jsonObject.convertJson2Bean(userInfo, UserInfoVo.class);
        UserInfoUpdateImpl userInfoUpdate = new UserInfoUpdateImpl();
        userInfoUpdate.updateUserInfo(userInfoVo, type, userAvatar);
         */
        UserInfoUpdateServiceImpl userInfoUpdate = new UserInfoUpdateServiceImpl();
        InputStream inputStream = null;
        byte[] userAvatar = null;
        String imgType = null;
        String type = null;
        try {
            // 获取multipart/formdata方式上传的表单
            DiskFileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            List items = upload.parseRequest(req);
            Map<String, String> userInfoMap = new HashMap();
            for(Object object:items){
                FileItem fileItem = (FileItem) object;
                if (fileItem.isFormField()) {
                    if("type".equals(fileItem.getFieldName())) {
                        type = fileItem.getString();
                    }
                    else {
                        userInfoMap.put(fileItem.getFieldName(), fileItem.getString("utf-8"));
                    }
                }
                else {
                    if("userAvatar".equals(fileItem.getFieldName())) {
                        userAvatar = fileItem.get();
                        int index = fileItem.getName().lastIndexOf('.');
                        imgType = fileItem.getName().substring(index);
                    }
                }
            }
            JsonResponseHelper jsonObject = new JsonResponseHelper();
            String rootPath = req.getServletContext().getRealPath("/");
            JsonResponseHelper jsonResponse = new JsonResponseHelper();
            if("profile".equals(type)) {
                String oldUserName = userInfoMap.get("oldUserName");
                String newUserName = userInfoMap.get("userName");
                String avatarName = newUserName + imgType;


                String userAvatarPath = ImageUploadHelper.saveImage(userAvatar, CommonConstants.IMAGE_UPLOAD_PATH_AVATAR, avatarName);
                userInfoMap.put("userAvatarPath", userAvatarPath);
                jsonObject.setResponseData(null, userInfoMap);
                UserInfoVo userInfoVo = jsonObject.convertJson2Bean(jsonObject.toString(), UserInfoVo.class);
                userInfoUpdate.updateProfile(userInfoVo, oldUserName);
                if(oldUserName.equals(newUserName)) {
                    jsonResponse.setResponseOK();
                }
                else {
                    jsonResponse.setResponseCode(ResponseCode.CODE_CHANGED_ACCOUNT);
                }
            }
            else if("account".equals(type)) {
                String userName = userInfoMap.get("userName");
                String oldPassWd = userInfoMap.get("oldPassWd");
                String newPassWd = userInfoMap.get("newPassWd");
                if(userInfoUpdate.updatePassWord(userName,oldPassWd, newPassWd)) {
                    jsonResponse.setResponseCode(ResponseCode.CODE_CHANGED_ACCOUNT);
                }
                else {
                    jsonResponse.setResponseFailed("原密码错误，请重新输入！");
                }
            }
            out.println(jsonResponse);

        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
