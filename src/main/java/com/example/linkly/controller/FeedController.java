package com.example.linkly.controller;


import com.example.linkly.common.dto.feed.CreateFeedRequestDto;
import com.example.linkly.common.dto.feed.FeedResponseDto;
import com.example.linkly.common.dto.feed.UpdateFeedRequestDto;
import com.example.linkly.entity.Feed;
import com.example.linkly.service.feed.FeedService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@Slf4j
@RestController
@RequestMapping("/feeds")
@RequiredArgsConstructor
public class FeedController {

    private final FeedService feedService;

    /**
     * 피드 생성
     *
     * @param requestDto
     * @return
     */
    @PostMapping
    public ResponseEntity<FeedResponseDto> feedSave(@Valid @RequestBody CreateFeedRequestDto requestDto, HttpServletRequest request) {
        FeedResponseDto feedResponseDto = feedService.feedSave(requestDto, request);
        return new ResponseEntity<>(feedResponseDto, HttpStatus.CREATED);
    }

    /**
     * 피드 단건 조회
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<FeedResponseDto> findById(@PathVariable Long id) {
        return new ResponseEntity<>(feedService.findById(id), HttpStatus.OK);

    }

    /**
     * 피드 수정
     *
     * @param id
     * @param requestDto
     * @return
     */
    @PatchMapping("/{id}")
    public ResponseEntity<FeedResponseDto> updateFeed(
            @PathVariable Long id,
            @Valid @RequestBody UpdateFeedRequestDto requestDto
    ) {
        return ResponseEntity.ok(feedService.updateFeed(id, requestDto));
    }

    /**
     * 피드 삭제
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFeed(@PathVariable Long id) {
        feedService.deleteFeed(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * 피드 페이징
     * 랜덤 피드
     *
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/getfeeds")
    public ResponseEntity<Page<Feed>> getFeeds(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<Feed> feedsPagination = feedService.getFeedsPagination(page - 1, size);
        return ResponseEntity.ok(feedsPagination);
    }

    /**
     * 베스트5 피드 조회
     *
     * @return
     */
    @GetMapping("/best")
    public ResponseEntity<List<Feed>> getBestFeeds() {
        List<Feed> bestFeeds = feedService.getBestFeeds();
        return new ResponseEntity<>(bestFeeds, HttpStatus.OK);
    }

    /**
     * 친구 피드보기
     * @param userId
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/friends")
    public Page<Feed> getFriendFeeds(
            @RequestParam UUID userId,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return feedService.getFriendFeeds(userId, page-1, size);
    }
}
