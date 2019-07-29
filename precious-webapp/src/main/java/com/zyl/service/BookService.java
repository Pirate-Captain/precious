/**
 * Created on 2015-8-19
 */
package com.zyl.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;

import com.zyl.book.model.BookModel;

/**
 * @author iaskbear <a href="mailto:iaskbear@gmail.com">iaskbear</a>
 * @version $Id$
 */
public class BookService {
    private static List<BookModel> bookModelList = new ArrayList<BookModel>();
    static {
        BookModel model1 = new BookModel();
        model1.setId(StringUtils.replace(UUID.randomUUID().toString(),"-",""));
        model1.setAuthor("张三");
        model1.setName("相对论的因式分解");
        model1.setPrice(188.8f);
        
        bookModelList.add(model1);
    }
    
    public BookModel getBookModelById(String id) {
        for ( BookModel model : bookModelList ) {
            if ( StringUtils.equals(id, model.getId()) ) {
                return model;
            }
        }
        return null;
    }
    
    public List<BookModel> getAllBooks() {
        return bookModelList;
    }
}
