package com.ruoyi.tool;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.file.*;
import java.util.*;
import java.util.regex.Pattern;

@Component
public final class UploadImage {

    private static final Pattern DATA_URI = Pattern.compile("^data:image/(\\w+);base64,");

    private static String uploadPath;

    @Value("${file.upload-path}")
    public void setUploadPath(String path) {
        uploadPath = path;
    }

    /**
     * 保存 base64 图片到 /dog 子目录
     * @param data 带 data URI 头或纯 base64
     * @return 可访问的相对路径，如 /dog/a7b9c4d8e9f10a1b2c3d4e5f6a7b8c9d0.png
     */
    public static String uploadPostImage_dog(String data) {
        try {
            String ext = parseExt(data);
            if (ext == null) {
                throw new IllegalArgumentException("无法识别图片类型");
            }

            String fileName = UUID.randomUUID().toString().replace("-", "") + "." + ext;

            String base64Image = data.contains(",") ? data.split(",")[1] : data;
            byte[] bytes = Base64.getDecoder().decode(base64Image);
            if (bytes.length > 10 * 1024 * 1024) {
                return "图片大小不能超过10MB";
            }

            // 拼接目录并自动创建
            Path dir = Paths.get(uploadPath, "dog");
            Files.createDirectories(dir);

            Path file = dir.resolve(fileName);
            Files.write(file, bytes);

            // 返回统一带斜杠的相对路径
            return "./img/dog/" + fileName;
        } catch (Exception e) {
            e.printStackTrace();
            return "保存失败：" + e.getMessage();
        }
    }

    /* =============== 以下功能不变 =============== */

    private static String parseExt(String base64) {
        // 1. 尝试从 data URI 头解析
        var m = DATA_URI.matcher(base64);
        if (m.find()) return m.group(1).toLowerCase();

        // 2. 无头时按魔数识别
        String pure = base64.contains(",") ? base64.split(",")[1] : base64;
        byte[] bytes = Base64.getDecoder().decode(pure);
        return guessExtFromMagic(bytes);
    }

    private static String guessExtFromMagic(byte[] bytes) {
        if (bytes.length < 4) return null;
        String hex = bytesToHex(Arrays.copyOfRange(bytes, 0, 4)).toUpperCase();
        return Map.of(
                        "FFD8FF", "jpg",
                        "89504E47", "png",
                        "47494638", "gif",
                        "52494646", "webp"
                ).entrySet().stream()
                .filter(e -> hex.startsWith(e.getKey()))
                .map(Map.Entry::getValue)
                .findFirst()
                .orElse(null);
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) sb.append(String.format("%02X", b));
        return sb.toString();
    }
}