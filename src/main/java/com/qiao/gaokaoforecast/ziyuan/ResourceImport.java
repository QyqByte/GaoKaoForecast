package com.qiao.gaokaoforecast.ziyuan;

import com.qiao.gaokaoforecast.dao.MajorDao;
import com.qiao.gaokaoforecast.dao.SchoolDao;
import com.qiao.gaokaoforecast.domain.Major;
import com.qiao.gaokaoforecast.domain.School;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;

/***
 * 进行学校资源的导入

 * @param
 * @version 0.1.0
 * @return
 * @author QYQ
 * @date 2021/7/28 20:02
 * @since 0.1.0
 */
@Component
public class ResourceImport {
    @Autowired
    private SchoolDao schoolDao;

    @Autowired
    private MajorDao majorDao;

    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = {RuntimeException.class})
    public void addAllSchools() throws IOException {
        File file = new File("D://河南省所有有效高校.txt");

        FileInputStream fileInputStream = new FileInputStream(file);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));

        String ss = new String();
        int num = 1;
        while ((ss=bufferedReader.readLine()) !=null){
            // 得到每一行学校的数据，将其加入到schools数据库中

            String[] str1 = ss.split(",");
            String[] rr = new String[2];
            int schoolid = 0;
            for (int i=0;i<str1.length;i++) {
                String[] s = str1[i].split("\"");
                if(i==str1.length-1){
                    String substring = s[2].substring(1, s[2].length() - 1);
                    schoolid = Integer.parseInt(substring.trim());
                    break;
                }
                rr[i] = s[3];
            }
            School school = new School();
            school.setSchool_name(rr[0]);
            school.setSchool_desc(rr[1]);
            school.setSchool_id(schoolid);
            int res = schoolDao.insertschool(school);
            if(res==1){
                System.out.println("第"+num+"个添加成功");
            }
            num+=1;
        }
    }
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = {Throwable.class})
    public void addAllMajors() throws IOException {
        File file = new File("D://2018年所有专业和对应学校编号.txt");
        FileInputStream fileInputStream = new FileInputStream(file);
        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        String string = new String();
        int num = 1;
        while ((string=bufferedReader.readLine()) != null) {
            String[] strs = string.split(",");
            Major major = new Major();
            String[] rr = new String[3];
            int[] kk = new int[5];
            for (int i = 0; i < strs.length; i++) {
                String[] split = strs[i].split("\"");
                if(i==6){
                    rr[2] = split[3].trim();
                    continue;
                }
                if(i==7){
                    kk[4] = Integer.parseInt(split[2].substring(2,6));
                    continue;
                }
                if(i<2){
                    rr[i] = split[3].trim();
                }else {
                    try{
                        kk[i-2] = Integer.parseInt(split[3].trim());
                    }catch (NumberFormatException e){
                        float temp = Float.parseFloat(split[3].trim());
                        kk[i-2] = (int)temp;
                    }
                }
            }
            major.setMajor_name(rr[0]);
            major.setMajor_batch(rr[1]);
            major.setMajor_average(rr[2]);
            major.setMajor_min_score(kk[0]);
            major.setMajor_min_section(kk[1]);
            major.setMajor_school_id(kk[2]);
            major.setMajor_province(kk[3]);
            major.setMajor_year(kk[4]);
            int res = majorDao.insertMajor(major);
            if(res==1){
                System.out.println("第"+num+"个记录添加成功！");
                num++;
            }
        }
    }

    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = {Throwable.class})
    public void testaddschool(School school1,School school2) throws IOException {

        int res1 = schoolDao.insertschool(school1);
        if(res1==1){
            System.out.println("sunccess111111");
            throw new IOException("文件未找到！");
        }
        int res2 = schoolDao.insertschool(school2);
        if(res2==1){
            System.out.println("success2222222");
        }
    }
}
