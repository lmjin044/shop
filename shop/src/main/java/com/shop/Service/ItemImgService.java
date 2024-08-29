package com.shop.Service;

import com.shop.Entity.ItemImg;
import com.shop.Repository.ItemImgRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

@Service
@RequiredArgsConstructor
@Transactional
public class ItemImgService {

    @Value("${itemImgPath}")
    private String imgPath;

    private final FileService fileService;
    private final ItemImgRepository itemImgRepository;

    public void saveItemImg(ItemImg itemImg, MultipartFile multipartFile) throws Exception {
        String originalName = multipartFile.getOriginalFilename();// 이미지원본이름
        String imgName="";
        String imgUrl="";
        // 파일 업로드
        if( !StringUtils.isEmpty( originalName )){// 업로드 한 원본 이미지이름 존재여부
            imgName = fileService.uploadFile(imgPath,
                    originalName, multipartFile.getBytes());
            imgUrl = "/images/"+imgName; // 웹에 사용할 이미지 경로
        }
        itemImg.setImgUrl( imgUrl );
        itemImg.setImgName( imgName );
        itemImg.setOriginalName( originalName );
        itemImgRepository.save(itemImg); // ItemImg 엔티티 객체를 저장
    }
}