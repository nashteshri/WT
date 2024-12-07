package com.gmail.samirshende420.LibrarayManagementSystemKitcoek.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class Student {

    Long prnNo;

    String name;

    String branch;

    String year;

    List<Book> assignedBooks = new ArrayList<>();
}