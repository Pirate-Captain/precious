/**
 * Created on 2015-8-19
 */
package com.zyl.book.web.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

import com.codahale.metrics.MetricRegistry;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.zyl.book.model.BookModel;
import com.zyl.metrics.util.MetricsUtils;
import com.zyl.service.BookService;

/**
 * @author iaskbear <a href="mailto:iaskbear@gmail.com">iaskbear</a>
 * @version $Id$
 */
//定义返回 success 时重定向到 book Action
@Namespace("/user")
@Results(@Result(name="success" , type="redirectAction" , params = {"actionName" , "book"})) 
public class BookAction extends ActionSupport implements ModelDriven<Object> {
    private static final long serialVersionUID = -7817670704188234159L;
    private String id;
    private BookModel model = new BookModel();
    private List<BookModel> bookList = new ArrayList<BookModel>();
    private BookService service = new BookService();
    
    public HttpHeaders index() {
        MetricRegistry registry = MetricsUtils.getMetricRegistry();
        registry.counter(MetricRegistry.name(BookAction.class, "index", "counter")).inc();
        registry.meter(MetricRegistry.name(BookAction.class, "index", "meter")).mark();
        
        bookList = service.getAllBooks();
        return new DefaultHttpHeaders("index").disableCaching();
    }
    
    public HttpHeaders show() {
        model = service.getBookModelById(id);
        return new DefaultHttpHeaders("show"); 
    }
    
    public HttpHeaders edit() {
        model = service.getBookModelById(id);
        return new DefaultHttpHeaders("edit");
    }
    
    public HttpHeaders update() {
        BookModel bModel = service.getBookModelById(id);
        bModel.setAuthor(model.getAuthor());
        bModel.setName(model.getName());
        bModel.setPrice(model.getPrice());
        
        addActionMessage("更新成功");
        return new DefaultHttpHeaders("success");
    }
    
    public List<BookModel> getBookList() {
        return bookList;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setModel(BookModel model) {
        this.model = model;
    }

    @Override
    public Object getModel() {
        return bookList.size() == 0 ? model : bookList;
    }
}