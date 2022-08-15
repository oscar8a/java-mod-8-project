package com.booklistapp.api.service;

import com.booklistapp.api.models.ReadingList;
import com.booklistapp.api.repository.ReadingListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReadingListService {
    @Autowired
    private ReadingListRepository readingListRepository;

    public ReadingList createUserReadingList(ReadingList readingListData) {
        return readingListRepository.save(readingListData);
    }
}
