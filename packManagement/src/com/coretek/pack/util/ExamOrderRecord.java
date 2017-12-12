package com.coretek.pack.util;

/**
 * 用于记录考试顺序记录
 * @author Administrator
 *
 */
public class ExamOrderRecord{
	public int[] teacherExamOrderRecord;
	public int[] courseExamOrderRecord;
	public int[] timeQuantumExamOrderRecord;
	public int[] classroomExamOrderRecord;
	public static int teacherIndex=0;//当前记录老师位置参数
	public static int courseIndex=0;//当前记录科目位置参数
	public ExamOrderRecord(){}
	/**
	 * 主要用于记录需要的个数,course代表考试科目个数，
	 * 一堂考试科目需要两个监考老师，一个班，一个教室
	 * @param course
	 */
	public ExamOrderRecord(int course){
		teacherExamOrderRecord=new int[2*course];
		courseExamOrderRecord=new int[course];
		timeQuantumExamOrderRecord=new int[course];
		classroomExamOrderRecord=new int[course];
	}
	/**
	 * 用于记录这一堂考试的教室，班级，科目编号是什么
	 * 输入参数代表编号
	 * @param y
	 * @param z
	 * @param w
	 */
	public void inputCourseOrOtherRecordInformation(int y,int z,int w){		
		courseExamOrderRecord[courseIndex]=y;
		timeQuantumExamOrderRecord[courseIndex]=z;
		classroomExamOrderRecord[courseIndex]=w;
	}
	/**
	 * 主要用于记录老师的编号
	 * @param x
	 */
	public void inputTeacherRecordInformation(int teacherNumberRecord){
		teacherExamOrderRecord[teacherIndex]=teacherNumberRecord;
	}
	/**
	 * 初始化老师的监考顺序
	 */
	public void initTeacherRecord(){
		for(int i=0;i<teacherExamOrderRecord.length;i++){
			teacherExamOrderRecord[i]=i;
		}
	}
}
