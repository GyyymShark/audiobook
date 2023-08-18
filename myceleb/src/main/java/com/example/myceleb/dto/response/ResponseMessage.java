package com.example.myceleb.dto.response;

public class ResponseMessage {
    public static final String CREATE_BOOK_SUCCESS="책 생성 성공";
    public static final String FIND_BOOKS_SUCCESS="전체 책 목록 조회 성공";
    public static final String FIND_BOOK_INCOMPLETE_SUCCESS="OFFSET+LIMIT이 전체 문장 개수 초과";
    public static final String FIND_BOOK_COMPLETE_SUCCESS="문장 조회 성공";
    public static final String OFFSET_ERROR="OFFSET 기입 오류";
    public static final String LIMIT_ERROR="LIMIT 기입 오류";
}
