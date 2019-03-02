package com.teammgh.cnboard;

public class ExamData {

    int target_grade;
    String exam_name;
    String exam_range;
    int exam_code;


    public ExamData(int target_grade, int exam_code,String exam_name,String exam_range){
        this.target_grade = target_grade;
        this.exam_name = exam_name;
        this.exam_range = exam_range;
        this.exam_code = exam_code;
    }
}
