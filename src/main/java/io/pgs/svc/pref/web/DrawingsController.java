package io.pgs.svc.pref.web;

import io.pgs.cmn.ResultMapper;
import io.pgs.cmn.ServiceStatus;
import io.pgs.svc.pref.dto.DrawingsDto;
import io.pgs.svc.pref.service.DrawingsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import static io.pgs.cmn.ResponseUtil.empty;
import static io.pgs.cmn.ResponseUtil.response;

@Slf4j
@Controller
@RequestMapping("/pref/drawings")
public class DrawingsController {

    @Value("${drawing.file.upload-dir}")
    private String uploadDir;


    @Resource
    private DrawingsService drawingsService;

    @PostMapping("/create")
    @ResponseBody
    public ModelAndView create(@RequestParam("image") MultipartFile file, @RequestParam("id") String id, @RequestParam("name") String name) {
        log.info("Let's start " + getClass().getName());
        Map<String, Object> result = new HashMap<>();

        log.debug("id >>>" + id);
        log.debug("name >>>" + name);
        log.debug("file >>>" + file);

        try {
            if (empty(id) || empty(name)) {
                return response(new ResultMapper(result, ServiceStatus.MSG_4001));
            }

            int duplicateCount = this.drawingsService.exists(id);
            if (duplicateCount > 0) { // 이미 등록된 데이터
                return response(new ResultMapper(result, ServiceStatus.MSG_3005));
            }

            String originalFilename = StringUtils.cleanPath(file.getOriginalFilename());
            String filenameExtension = StringUtils.getFilenameExtension(file.getOriginalFilename());

            log.debug("originalFilename: {}", originalFilename);
            log.debug("filenameExtension: {}", filenameExtension);

            LocalDateTime filenameDatetime = LocalDateTime.now();
            String newFilename = filenameDatetime.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS"));
            newFilename = newFilename + "." + filenameExtension;

            log.debug("newFilename: {}", newFilename);

            Path targetLocation = Paths.get(this.uploadDir + newFilename);
            long copyBytes = Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            log.debug("copyBytes: {}", copyBytes);

            if (copyBytes > 0) {

                // 저장 처리
                LocalDateTime now = LocalDateTime.now();

                DrawingsDto drawingsDto = new DrawingsDto();
                drawingsDto.setId(id);
                drawingsDto.setName(name);
                drawingsDto.setImg_path(newFilename); // 업로드 기본 디렉토리는 제외한다.
                drawingsDto.setImg_name(originalFilename);
                drawingsDto.setCreatedAt(Timestamp.valueOf(now));

                int successfulCount = this.drawingsService.save(id, drawingsDto);
                log.debug("successfulCount: {}", successfulCount);

                if (successfulCount == 0) {
                    return response(new ResultMapper(result, ServiceStatus.MSG_3006));
                }
            }

        } catch (Exception e) {
            return response(new ResultMapper(result, ServiceStatus.MSG_3006));
        }

        return response(new ResultMapper(result, ServiceStatus.Successful));
    }

    @PostMapping("/delete")
    @ResponseBody
    public ModelAndView delete(String id) {
        log.info("Let's start " + getClass().getName());
        Map<String, Object> result = new HashMap<>();
        if (empty(id)) {
            return response(new ResultMapper(result, ServiceStatus.MSG_4001));
        }

        int successfulCount = 0;

        try {
            successfulCount = this.drawingsService.delete(id);
            if (successfulCount == 0) {
                return response(new ResultMapper(result, ServiceStatus.MSG_3003));
            }
        } catch (Exception e) {
            return response(new ResultMapper(result, ServiceStatus.MSG_3003));
        }
        return response(new ResultMapper(result, ServiceStatus.Successful));
    }

    @GetMapping("/download")
    public ResponseEntity<org.springframework.core.io.Resource> download(String id, HttpServletRequest request) throws Exception {
        log.info("Let's start " + getClass().getName());

        DrawingsDto info = this.drawingsService.info(id);
        String newFileName = info.getImg_path();

        Path filePath = Paths.get(this.uploadDir + newFileName).toAbsolutePath().normalize();
        log.debug("filePath: {}", filePath);

        org.springframework.core.io.Resource resource = new UrlResource(filePath.toUri());
        log.debug("resource: {}", resource);

        String contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        log.debug("contentType: {}", contentType);
        // Fallback to the default content type if type could not be determined
        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);


    }


}
