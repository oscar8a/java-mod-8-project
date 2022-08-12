package com.booklistapp.api.repository;

import com.booklistapp.api.models.ReadingList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReadingListRepository extends JpaRepository<ReadingList, Integer> {
}
