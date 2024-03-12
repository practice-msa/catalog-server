package msa.catalogserver.controller;

import lombok.RequiredArgsConstructor;
import msa.catalogserver.dto.response.ApiResponse;
import msa.catalogserver.dto.response.ErrorResponse;
import msa.catalogserver.service.ScrapService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ScrapController {
    private final ScrapService scrapService;
    // 스크랩 등록
    @PostMapping("/{productName}/scrap/{userId}")
    public ApiResponse<String> createScrap(@PathVariable String productName,@PathVariable String userId){
        if(scrapService.createScrap(productName,userId)) return new ApiResponse<>(true,"스크랩 성공",HttpStatus.CREATED,null);
        return new ApiResponse<>(true,null,HttpStatus.NOT_FOUND,new ErrorResponse("등록하지 못 했습니다."));
    }
    // 스크랩 취소
}
