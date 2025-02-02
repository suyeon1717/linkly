package com.example.linkly.controller;

import com.example.linkly.common.dto.comment.CommentRequestDto;
import com.example.linkly.common.dto.comment.CommentResponseDto;
import com.example.linkly.service.comment.CommentService;
import com.example.linkly.service.heart.HeartService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
@Slf4j
public class CommentController {

    private final CommentService commentService;
    private final HeartService heartService;

    // 댓글 생성
    @PostMapping("/{feedId}")
    public ResponseEntity<CommentResponseDto> addComment(
            @PathVariable Long feedId,
            @Valid @RequestBody CommentRequestDto requestDto,
            HttpServletRequest request
            ){

        //log.info("feedid={} userid={} commnet={}",feedId,requestDto.getUserId(),requestDto.getContent());
        CommentResponseDto commentResponseDto = commentService.addComment(requestDto.getContent(),feedId,request);

        return new ResponseEntity<>(commentResponseDto, HttpStatus.CREATED);
    }

    // 댓글 특정 피드 리스트
    @GetMapping("/{feedId}")
    public ResponseEntity<CommentResponseDto> findCommentFeedById(@PathVariable Long feedId) {

        CommentResponseDto commentResponseDto = commentService.findCommentFeedById(feedId);

        return new ResponseEntity<>(commentResponseDto, HttpStatus.OK);
    }

    // 댓글 수정
    @PatchMapping("/{id}")
    public ResponseEntity<CommentResponseDto> update(@PathVariable Long id,@Valid @RequestBody CommentRequestDto dto) {
        log.info("id={} content{} userId{} ",id,dto.getContent(),dto.getUserId());
        CommentResponseDto updateComment = commentService.update(id, dto.getContent());

        return new ResponseEntity<>(updateComment, HttpStatus.OK);
    }

    // 댓글 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        commentService.delete(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<CommentResponseDto>> heartCountNumber() {

        List<CommentResponseDto> heartCountNumber = commentService.heartCountNumber();

        return new ResponseEntity<>(heartCountNumber, HttpStatus.OK);
    }
}
