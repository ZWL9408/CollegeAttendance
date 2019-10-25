package com.attendance.bean;

public class page {
    private int totalPage;
    private int currentPage;
    private int totalRecord;
    private int currentRecord;
    private int pageSize=8;
    //获得和设置当前页
    public int getCurrentPage(){
   	 return currentPage;
    }
    public void setCurrentPage(int currentRecord,int pageSize){
   	 if(currentRecord%pageSize==0){
   		 currentPage=currentRecord/pageSize;
   	 }
   	 else{
   		 currentPage=currentRecord/pageSize+1;
   	 }
    }
    //获得和设置当前记录
    public int getCurrentRecord(){
   	 return currentRecord;
    }
    public void setCurrentRecord(int currentRecord){
   	 this.currentRecord=currentRecord;
    }
    //获得和设置每页记录数量
    public int getPageSize(){
   	 return pageSize;
    }
    public void setPageSize(int pageSize){
   	 this.pageSize=pageSize;
    }
    //获得和设置总页数
    public int getTotalPage(){
   	 return totalPage;
    }
    public void setTotalPage(int totalRecord,int pageSize){
   	 if(totalRecord%pageSize==0){
   		 totalPage=totalRecord/pageSize;
   	 }
   	 else{
   		 totalPage=totalRecord/pageSize+1;
   	 }
    }
    //获得和设置总记录
    public int getTotalRecord(){
   	 return totalRecord;
    }
    public void setTotalRecord(int totalRecord){
   	 this.totalRecord=totalRecord;
    }

    
}
