/**
 * Created on 2016-4-13
 */
package com.zyl.designpattern.builderpattern.change;

/**
 * 生成器作为内部类放到产品类里
 * @author iaskbear <a href="mailto:iaskbear@gmail.com">iaskbear</a>
 * @version $Id$
 */
public class CustomerInfo {
    private String name;
    private String sex;
    private int age;
    private String address;
    private String cardNo;
    private String idNum;
    
    private CustomerInfo(CustomerBuilder builder) {
        this.name = builder.getName();
        this.sex = builder.getSex();
        this.age = builder.getAge();
        this.address = builder.getAddress();
        this.cardNo = builder.getCardNo();
        this.idNum = builder.getIdNum();
    }
    
    public static class CustomerBuilder{
        private String name;
        private String sex;
        private int age;
        private String address;
        private String cardNo;
        private String idNum;
        
        public CustomerInfo builder() {
            //check the paramter avaliable
            return new CustomerInfo(this);
        }

        public String getName() {
            return name;
        }

        public CustomerBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public String getSex() {
            return sex;
        }

        public CustomerBuilder setSex(String sex) {
            this.sex = sex;
            return this;
        }

        public int getAge() {
            return age;
        }

        public CustomerBuilder setAge(int age) {
            this.age = age;
            return this;
        }

        public String getAddress() {
            return address;
        }

        public CustomerBuilder setAddress(String address) {
            this.address = address;
            return this;
        }

        public String getCardNo() {
            return cardNo;
        }

        public CustomerBuilder setCardNo(String cardNo) {
            this.cardNo = cardNo;
            return this;
        }

        public String getIdNum() {
            return idNum;
        }

        public CustomerBuilder setIdNum(String idNum) {
            this.idNum = idNum;
            return this;
        }
    }

    public String getName() {
        return name;
    }

    public String getSex() {
        return sex;
    }

    public int getAge() {
        return age;
    }

    public String getAddress() {
        return address;
    }

    public String getCardNo() {
        return cardNo;
    }

    public String getIdNum() {
        return idNum;
    }
}