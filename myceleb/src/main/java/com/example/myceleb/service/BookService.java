package com.example.myceleb.service;

import com.example.myceleb.dto.request.CreateBookRequest;
import com.example.myceleb.dto.response.*;
import com.example.myceleb.entity.Book;
import com.example.myceleb.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;


    @Transactional(readOnly = true)
    public DefaultResponse<List<BookViewResponse>> findAll(){
        List<BookViewResponse> bookListDto = bookRepository.findBookListDto();
        return new DefaultResponse<>(StatusCode.OK,ResponseMessage.FIND_BOOKS_SUCCESS,bookListDto);
    }



    @Transactional
    public DefaultResponse<BookResponse> findById(Long id){
        Book bookById = bookRepository.findById(id).get();

        Book book = Book.builder()
                .author(bookById.getAuthor())
                .contents(bookById.getContents())
                .id(bookById.getId())
                .title(bookById.getTitle())
                .views(bookById.getViews() + 1)
                .build();

        bookRepository.save(book);

        DefaultResponse<BookResponse> bookSentences = getBookSentences(book.getId(), 0, 1000);
        return bookSentences;
    }


    @Transactional
    public DefaultResponse<Long> save(CreateBookRequest createBookRequest){

        Book book = Book.builder()
                .author(createBookRequest.getAuthor())
                .contents(createBookRequest.getContents())
                .title(createBookRequest.getTitle())
                .views(0L)
                .build();

        bookRepository.save(book);
        Long id = book.getId();
        return new DefaultResponse<>(StatusCode.OK, ResponseMessage.CREATE_BOOK_SUCCESS,id);

    }

    @Transactional(readOnly = true)
    public BookViewResponse findBookViewsById(Long id){
        BookViewResponse bookViewResponse = bookRepository.findBookViewDto(id);
        return bookViewResponse;
    }






   @Transactional(readOnly = true)
    public DefaultResponse<BookResponse> getBookSentences(Long id, int offset, int limit){


       Book book = bookRepository.findById(id).get();
       int lengthLimit=27;

       List<String> allSentences=splitString(book.getContents(),lengthLimit);
       List<String> sentences=new ArrayList<>();

       if(offset>= allSentences.size() || offset<0){        //offset이 범위를 벗어났을때
            return new DefaultResponse<>(StatusCode.BAD_REQUEST,ResponseMessage.OFFSET_ERROR,null);
       }

       if(limit<0){
           return new DefaultResponse<>(StatusCode.BAD_REQUEST,ResponseMessage.LIMIT_ERROR,null);
       }


       if(offset+limit>allSentences.size()){    //offset+limit이 문장 전체 수를 초과했을때 마지막문장까지반환
           for(int i=offset; i<allSentences.size(); i++){
               sentences.add(allSentences.get(i));
           }
           BookResponse bookResponse = BookResponse.builder()
                   .id(book.getId())
                   .author(book.getAuthor())
                   .title(book.getTitle())
                   .views(book.getViews())
                   .contents(sentences)
                   .count(allSentences.size()-offset)
                   .build();
           return new DefaultResponse<BookResponse>(StatusCode.OK,ResponseMessage.FIND_BOOK_INCOMPLETE_SUCCESS,bookResponse);
       }

       else {       //정상동작
           for (int i = offset; i < offset + limit; i++) {
               sentences.add(allSentences.get(i));
           }
           BookResponse bookResponse = BookResponse.builder()
                   .id(book.getId())
                   .author(book.getAuthor())
                   .title(book.getTitle())
                   .views(book.getViews())
                   .contents(sentences)
                   .count(limit)
                   .build();
           return new DefaultResponse<BookResponse>(StatusCode.OK,ResponseMessage.FIND_BOOK_COMPLETE_SUCCESS,bookResponse);
       }

   }



    public static List<String> splitString(String input, int limit){
        List<String> chunks=new ArrayList<>();

        int startIndex=0;
        while(startIndex<input.length()){
            int endIndex = getEndIndex(input, startIndex, limit);
            chunks.add(input.substring(startIndex, endIndex).trim());
            startIndex = endIndex;
        }
        return chunks;
    }

    public static int getEndIndex(String input, int startIndex, int limit){
        int endIndex=0,dotCount=0,doubleQuoteCount=0;
        StringBuilder sb=new StringBuilder();

        for(int i=startIndex; i<startIndex+limit; i++){

            if(i==input.length()) { return input.length();}

            char cur=input.charAt(i);
            sb.append(cur);

            if(cur=='.'){
                dotCount++;
            }
            else if(cur=='"'){
                doubleQuoteCount++;
            }
        }

        if(dotCount==0 && doubleQuoteCount==0){
            int i = sb.lastIndexOf(" ");
            endIndex=i;
        }

        else if(dotCount>0 && doubleQuoteCount==0){
            int i = sb.lastIndexOf(" ");
            endIndex=i;
        }

        else if(dotCount==0 && doubleQuoteCount>0){
            if(doubleQuoteCount%2==0){
                int i = sb.lastIndexOf("\"");
                endIndex=i+1;
            }
            else{
                int i = sb.lastIndexOf(" ");
                endIndex=i;
            }
        }

        else if(dotCount>0 && doubleQuoteCount>0){
            if(doubleQuoteCount%2==0){
                int i=sb.lastIndexOf("\"");
                endIndex=i+1;
            }
            else{
                if(dotCount==1){
                    int i = sb.lastIndexOf(" ");
                    endIndex=i+1;
                }
                else{
                    int dotLast=sb.lastIndexOf(".");
                    int doubleLast=sb.lastIndexOf("\"");
                    int i=Math.max(dotLast,doubleLast);
                    endIndex=i+1;
                }

            }

        }


        return startIndex+endIndex;
    }




}
