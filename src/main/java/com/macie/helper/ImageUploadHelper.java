package com.macie.helper;

import com.macie.common.CommonConstants;
import com.macie.config.ProjectDeployConfig;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

/**
 * @author Macie
 * @date 2020/11/9 -0:47
 */
public class ImageUploadHelper {
    /**
     * 存储图片
     * @param image
     * @param path
     * @return 存储位置的相对路径
     */
    public static String saveImage(byte[] image, String path, String imageName) {
        String userAvatarPath = null;
        if(image == null) {
            return null;
        }
        try {
            String imageSaveDir = ProjectDeployConfig.IMAGE_PATH + path;
            File dir = new File(imageSaveDir);
            if (!dir.exists() && !dir.isDirectory()) {
                // 创建目录
                dir.mkdirs();
            }
            String avatarPath = imageSaveDir + "/" + imageName;
            BufferedOutputStream bufos = new BufferedOutputStream(new FileOutputStream(avatarPath));
            bufos.write(image);
            bufos.close();
            //返回相对路径
            userAvatarPath = CommonConstants.IMAGE_UPLOAD_PATH + path + "/" + imageName;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return userAvatarPath;
    }
}
