package com.coretek.pack.util;

/**
 * 排序算法类
 * @author Administrator
 *
 */
public class SuanFa{
	private int[] teacherExamOrderRecord;    //考试老师顺序的记录
	private int[] courseExamOrderRecord;      //考试科目顺序的记录
	private int[] dataExamOrderRecord;   //考试时间段顺序的记录
	private int[] classroomExamOrderRecord;//考试教室顺序的记录
	
	public ExamOrderRecord examOrderRecord;
	
	private int teacherNumberVar=0;	  //老师编号变量
	public int teacherNumber;         //老师的实际人数
	
	private int courseNumberVar=0;	  //科目编号变量
	public int courseNumber;          //考试科目的门数
	
	private int timeQuantumNumberVar=0;      //时间段编号变量
	public int timeQuantumNumber;            //考试时间段长度
	
	private int classroomNumberVar=0; //教室编号变量
	public int classroomNumber;       //教室的实际个数
	public SuanFa(){}
	/**
	 * 用于初始化需要记录排考的表的长度及数据
	 * @param inputTeacherNumber
	 * @param inputCourseNumber
	 * @param inputDataNumber
	 * @param inputClassroomNumber
	 */
	public SuanFa(int inputTeacherNumber,int inputCourseNumber,int inputDataNumber,int inputClassroomNumber){
		examOrderRecord=new ExamOrderRecord(inputCourseNumber);
		teacherNumber=inputTeacherNumber;
		courseNumber=inputCourseNumber;
		timeQuantumNumber=inputDataNumber;
		classroomNumber=inputClassroomNumber;
		teacherExamOrderRecord=new int[teacherNumber];
		courseExamOrderRecord=new int[courseNumber];
		dataExamOrderRecord=new int[timeQuantumNumber];		
		classroomExamOrderRecord=new int[classroomNumber];
		for(int i=0;i<classroomNumber;i++){
			classroomExamOrderRecord[i]=10000+i;
		}
		for(int i=0;i<teacherNumber;i++){
			teacherExamOrderRecord[i]=1000+i;
		}
		for(int i=0;i<courseNumber;i++){
			courseExamOrderRecord[i]=100+i;
		}
		for(int i=0;i<timeQuantumNumber;i++){
			dataExamOrderRecord[i]=10+i;
		}		
	}
	/**
	 * 用于测试算法是否正确，主要是打印输出
	 * @param inputTeacherNumber
	 * @param inputCourseNumber
	 * @param inputTimeQuantumNumber
	 * @param inputClassroomNumber
	 */
	public void testAlgorithmSuccessOrError(int inputTeacherNumber,int inputCourseNumber,int inputTimeQuantumNumber,int inputClassroomNumber){
		int i=0;
		String examTimePeriod=null;//考试时间段
//		System.out.println("��ʦ"+aa+"����Ŀ"+bb+",����"+dd+",ʱ��"+cc);
		i=inputTimeQuantumNumber/3+1;
		switch(inputTimeQuantumNumber%3){
		case 0:
			examTimePeriod="上午";
			break;
		case 1:
			examTimePeriod="下午";
			break;
		case 2:
			examTimePeriod="晚上";
			break;
		}
		System.out.println("在第"+i+"天"+examTimePeriod+
				":xxx"+inputTeacherNumber+"yyyy"+"zzz"+inputCourseNumber+"www"+inputClassroomNumber);
	}
	/**
	 * 排课的轮循算法（把相应编号存在相应的考试顺序记录里）
	 */
	public void suanTeacherKe(){
		examOrderRecord.teacherIndex=0;				
		examOrderRecord.courseIndex=0;
//		System.out.println("��ʦ"+teacherNumber+"�ˣ���Ŀ"+courseNumber+"��,����"+classroomNumber+"��,ʱ�䲻��");
		/*当老师人数大于等于科目的两倍时（一个考试科目需要两个监考老师）
		在这种情况下，只需把考试科目需要的老师满足就可以了*/
		if(teacherNumber>=2*courseNumber){
			for(int i=0;i<courseNumber;i++){
//				testAlgorithmSuccessOrError(teacherNumberVar+1,courseNumberVar+1,timeQuantumNumberVar,classroomNumberVar+1);
//				examOrderRecord.ad(teacherNumberVar+1,courseNumberVar+1,timeQuantumNumberVar,classroomNumberVar+1);
				examOrderRecord.inputCourseOrOtherRecordInformation(courseNumberVar,timeQuantumNumberVar,classroomNumberVar);
				examOrderRecord.inputTeacherRecordInformation(teacherNumberVar);
				examOrderRecord.teacherIndex++;				
				examOrderRecord.courseIndex++;
				/*当教室个数多余等于考试科目时*/
				if(classroomNumber>=courseNumber){					
					teacherNumberVar++;
					examOrderRecord.inputTeacherRecordInformation(teacherNumberVar);
					examOrderRecord.teacherIndex++;
					teacherNumberVar++;
					courseNumberVar++;
					classroomNumberVar++;
				}
				/*当教室个数少余考试科目时*/
				else{
					teacherNumberVar++;
					examOrderRecord.inputTeacherRecordInformation(teacherNumberVar);
					examOrderRecord.teacherIndex++;
					teacherNumberVar++;
					courseNumberVar++;
					classroomNumberVar++;
					/*当教室编号变量等于教室个数时，教室编号变量清零后又进行轮循*/
					if(classroomNumberVar==classroomNumber){
						classroomNumberVar=0;
						timeQuantumNumberVar++;
					}
				}				
				
			}
		/*当老师人数小于科目的两倍时（一个考试科目需要两个监考老师）
		在这种情况下，只需把老师轮循排序*/
		}else{
			for(int i=0;i<courseNumber;i++){	
//				testAlgorithmSuccessOrError(teacherNumberVar+1,courseNumberVar+1,timeQuantumNumberVar,classroomNumberVar+1);		
//				examOrderRecord.ad(teacherNumberVar+1,courseNumberVar+1,timeQuantumNumberVar,classroomNumberVar+1);
				examOrderRecord.inputCourseOrOtherRecordInformation(courseNumberVar,timeQuantumNumberVar,classroomNumberVar);
				examOrderRecord.inputTeacherRecordInformation(teacherNumberVar);
				examOrderRecord.teacherIndex++;
				examOrderRecord.courseIndex++;
				/*当教室的个数的两倍大于等于监考老师人数时，要把老师轮循排序*/
				if(2*classroomNumber>=teacherNumber){
//					examOrderRecord.inputTeacherRecordInformation(teacherNumberVar);
//					examOrderRecord.j1++;
					teacherNumberVar++;
					/*   当老师个数是奇数时   */
					/*if((teacherNumber%2==1)&&(teacherNumberVar==teacherNumber)){
						timeQuantumNumberVar++;
					}*/
					/*   当老师个数是偶数时   */
					/*if((teacherNumber%2==0)&&(teacherNumberVar==teacherNumber-1)){
						timeQuantumNumberVar++;
					}*/
					if(teacherNumberVar==teacherNumber){
						teacherNumberVar=0;
					}
					examOrderRecord.inputTeacherRecordInformation(teacherNumberVar);
					examOrderRecord.teacherIndex++;
					teacherNumberVar++;
					/*   当老师个数是奇数时   */
					if((teacherNumber%2==1)&&((i+1)*2%(teacherNumber-1)==0)){
						timeQuantumNumberVar++;
					}
					/*   当老师个数是偶数时   */
					if((teacherNumber%2==0)&&((i+1)*2%teacherNumber==0)){
						timeQuantumNumberVar++;
					}
					if(teacherNumberVar==teacherNumber){
						teacherNumberVar=0;
					}
					courseNumberVar++;
					classroomNumberVar++;
					/*  当教室编号变量等于教室个数时，直接清零   */
					if(classroomNumberVar==classroomNumber){
						classroomNumberVar=0;
					}							
				}
				/*当教室的个数的两倍小于监考老师人数时，要把教室编号轮循排序*/
				else{
//					examOrderRecord.inputTeacherRecordInformation(teacherNumberVar);
//					examOrderRecord.j1++;
					teacherNumberVar++;
					if(teacherNumberVar==teacherNumber){
						teacherNumberVar=0;
					}
					examOrderRecord.inputTeacherRecordInformation(teacherNumberVar);
					examOrderRecord.teacherIndex++;
					teacherNumberVar++;
					courseNumberVar++;
					classroomNumberVar++;
					if(classroomNumberVar==classroomNumber){
						classroomNumberVar=0;
						timeQuantumNumberVar++;
					}
					if(teacherNumberVar==teacherNumber){
						teacherNumberVar=0;
					}
				}				
			}
		}
	}	
}