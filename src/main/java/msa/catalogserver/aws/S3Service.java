package msa.catalogserver.aws;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

// 여기는 저장, 삭제 구현
public interface S3Service {
    // 저장 -> 주소값 반환
    String upload(MultipartFile file) throws IOException;

    // 삭제
    boolean delete(String url);


}
