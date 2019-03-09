package com.teammgh.cnboard;

public class NoticeData {
    public String notice_date;
    public String notice_title;
    public String notice_image;
    public int id;
    public int notice_kind;
    public int target_grade;

    public NoticeData (String notice_title, String notice_date, String notice_image, int id, int notice_kind, int target_grade) {
        this.notice_title = notice_title;
        this.notice_date = notice_date;
        this.notice_image = notice_image;
        this.id = id;
        this.notice_kind = notice_kind;
        this.target_grade = target_grade;
    }
}
