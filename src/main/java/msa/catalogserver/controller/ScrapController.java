package msa.catalogserver.controller;

import lombok.RequiredArgsConstructor;
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
    public ResponseEntity<String> createScrap(@PathVariable String productName,@PathVariable String userId){
        if(scrapService.createScrap(productName,userId)) return ResponseEntity.status(HttpStatus.CREATED).body("성공");
        else return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body("실패");
    }
    // 스크랩 취소
}
