package com.ruoyi.web.controller.system;

import com.ruoyi.common.config.Global;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.SysMenu;
import com.ruoyi.system.domain.SysRole;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.service.ISysMenuService;
import com.ruoyi.zaihai.caiji.domain.CTaskChfz;
import com.ruoyi.zaihai.caiji.domain.CTaskSczj;
import com.ruoyi.zaihai.caiji.service.ICTaskChfzService;
import com.ruoyi.zaihai.caiji.service.ICTaskSczjService;
import com.ruoyi.zaihai.enums.ArabicToChineseNumerals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 * 首页 业务处理
 *
 * @author ruoyi
 */
@Controller
public class SysIndexController extends BaseController {
    @Autowired
    private ISysMenuService menuService;

    @Autowired
    private ICTaskSczjService cTaskSczjService;

    @Autowired
    private ICTaskChfzService cTaskChfzService;

    // 系统首页
    @GetMapping("/index")
    public String index(ModelMap mmap) {
        // 取身份信息
        SysUser user = ShiroUtils.getSysUser();
        // 根据用户id取出菜单
        List<SysMenu> menus = menuService.selectMenusByUser(user);

        List<SysRole> roles = ShiroUtils.getSysUser().getRoles();
        for (int i = 0; i < roles.size(); i++) {
            SysRole sysRole = roles.get(i);
            if (sysRole.getRoleName().equals("管理员")) {

            } else {
                //获取角色
                List<SysRole> role = ShiroUtils.getSysUser().getRoles();
                SysRole sysrole = role.get(0);
                String roleName = sysrole.getRoleName();
                mmap.put("roleName", roleName);
            }
        }
        mmap.put("menus", menus);
        mmap.put("user", user);
        mmap.put("copyrightYear", Global.getCopyrightYear());
        mmap.put("demoEnabled", Global.isDemoEnabled());
        return "index";
    }

    // 切换主题
    @GetMapping("/system/switchSkin")
    public String switchSkin(ModelMap mmap) {
        return "skin";
    }

    // 系统介绍
    @GetMapping("/system/main")
    public String main(CTaskSczj cTaskSczj, CTaskChfz cTaskChfz, ModelMap mmap) {
        /*鼠害首页统计表信息*/
        List<SysRole> roles = ShiroUtils.getSysUser().getRoles();
        for (int j = 0; j < roles.size(); j++) {
            SysRole sysRole = roles.get(j);
            String roleName = sysRole.getRoleName();
            publishRat(cTaskSczj, mmap, roleName);
        }
        /*虫害首页统计表信息*/
        for (int i = 0; i < roles.size(); i++) {
            SysRole sysRole = roles.get(i);
            String roleName = sysRole.getRoleName();
            publiciIsect(cTaskSczj, cTaskChfz, mmap, roleName);
        }

        return "main";
    }

    /*虫害首页计算方法*/
    private void publiciIsect(CTaskSczj cTaskSczj, CTaskChfz cTaskChfz, ModelMap mmap, String roleName) {
        //获取·到每条数据
        List<CTaskChfz> Originallist = cTaskChfzService.selectCTaskChfzList(cTaskChfz);
        //获取当前时间属于哪一周
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);//设置星期一为一周开始的第一天
        calendar.setMinimalDaysInFirstWeek(4);//可以不用设置
        int weekYear = calendar.get(Calendar.YEAR);//获得当前的年
        calendar.setTimeInMillis(System.currentTimeMillis());//时间戳
        int weekOfYear = calendar.get(Calendar.WEEK_OF_YEAR);//获得当前日期属于今年的第几周
        System.out.println("第几年：" + weekYear);
        System.out.println("第几周：" + weekOfYear);
        weekOfYear = weekOfYear - 1;
        calendar.setWeekDate(weekYear, weekOfYear, 2);//获得指定年的第几周的开始日期
        long starttime = calendar.getTime().getTime();//创建日期的时间该周的第一天，
        calendar.setWeekDate(weekYear, weekOfYear, 1);//获得指定年的第几周的结束日期
        long endtime = calendar.getTime().getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateStart = simpleDateFormat.format(starttime);//将时间戳格式化为指定格式
        String dateEnd = simpleDateFormat.format(endtime);
        System.out.println(dateStart);
        System.out.println(dateEnd);

        //拼接成第几周查询库中最新的一周
        String week = "";
        if (weekOfYear < 10) {
            week = ArabicToChineseNumerals.getNumber(String.valueOf(weekOfYear).substring(0, 1));
            week = "第" + week + "周";
        } else {
            String one = ArabicToChineseNumerals.getNumber(String.valueOf(weekOfYear).substring(0, 1));
            if (one.equals("一")) {
                one = "";
            }
            String two = ArabicToChineseNumerals.getNumber(String.valueOf(weekOfYear).substring(String.valueOf(weekOfYear).length() - 1));
            week = "第" + one + "十" + two + "周";
        }
        if (roleName.equals("省级")) {
            String deptName = ShiroUtils.getSysUser().getDept().getDeptName();
            if (deptName.equals("内蒙古自治区")) {
                deptName = deptName.substring(0, deptName.length() - 3);

            } else if (deptName.equals("西藏自治区")) {
                deptName = deptName.substring(0, deptName.length() - 3);

            } else if (deptName.equals("宁夏回族自治区")) {
                deptName = deptName.substring(0, deptName.length() - 5);

            } else if (deptName.equals("新疆维吾尔自治区")) {
                deptName = deptName.substring(0, deptName.length() - 6);
            } else if (deptName.equals("广西壮族自治区")) {
                deptName = deptName.substring(0, deptName.length() - 5);
            } else {
                //截取去除最后一个字符串
                deptName = deptName.substring(0, deptName.length() - 1);
            }

            System.out.println("-------------" + deptName + "++++++++++++++");
            double harmTotalareaWeek = 0.0;
            double engineeringhazardWeek = 0.0;
            double seriousAreaWeek = 0.0;
            double engineeringSeriousAreaWeek = 0.0;

            cTaskChfz.setProvince(deptName);


            for (int j = 0; j < Originallist.size(); j++) {
                CTaskChfz taskChfz = Originallist.get(j);

                String wk = taskChfz.getWeek();
                if (week.equals(wk)) {
                    if (taskChfz.getHarmTotalarea() != null) {
                        double a = taskChfz.getHarmTotalarea();
                        harmTotalareaWeek += a;
                    } else {
                        double a = 0.0;
                        harmTotalareaWeek += a;
                    }
                    if (taskChfz.getEngineeringhazard() != null) {
                        double b = taskChfz.getEngineeringhazard();
                        engineeringhazardWeek += b;
                    } else {
                        double b = 0.0;
                        engineeringhazardWeek += b;
                    }
                    if (taskChfz.getEngineeringSeriousArea() != null) {
                        double c = taskChfz.getEngineeringSeriousArea();
                        seriousAreaWeek += c;
                    } else {
                        double c = 0.0;
                        seriousAreaWeek += c;
                    }
                    if (taskChfz.getSeriousArea() != null) {
                        double d = taskChfz.getSeriousArea();
                        engineeringSeriousAreaWeek += d;
                    } else {
                        double d = 0.0;
                        engineeringSeriousAreaWeek += d;
                    }



                }
            }
            //创建存储数据总和的对象
            CTaskChfz cTaskSczWeek = new CTaskChfz();
            cTaskSczWeek.setHarmTotalarea(harmTotalareaWeek);
            cTaskSczWeek.setEngineeringSeriousArea(engineeringSeriousAreaWeek);
            cTaskSczWeek.setSeriousArea(seriousAreaWeek);
            cTaskSczWeek.setEngineeringhazard(engineeringhazardWeek);
            //cTaskSczWeek.setDefensiveArea(defensiveAreaWeek);
            mmap.put("cTaskchfzWeek", cTaskSczWeek);

            //根据数据库查询危害面积情况
            List<CTaskChfz> list = cTaskChfzService.selectCTaskChfzList(cTaskChfz);

            //定义统计数据合的变量
            double harmTotalarea = 0.0;
            double engineeringhazard = 0.0;
            double seriousArea = 0.0;
            double engineeringSeriousArea = 0.0;

            for (int z = 0; z < list.size(); z++) {
                CTaskChfz taskchfz = list.get(z);
                /** 危害总面积 */
                if (taskchfz.getHarmTotalarea() != null) {
                    double a = taskchfz.getHarmTotalarea();
                    harmTotalarea += a;
                } else {
                    double a = 0.0;
                    harmTotalarea += a;

                }
                /*double a = taskchfz.getHarmTotalarea();*/
                /** 工程区危害面积 */
                if (taskchfz.getEngineeringhazard() != null) {
                    double b = taskchfz.getEngineeringhazard();
                    engineeringhazard += b;
                } else {
                    double b = 0.0;
                    engineeringhazard += b;
                }

                /*double b = taskchfz.getEngineeringhazard();*/
                /** 严重危害总面积 */
                if (taskchfz.getSeriousArea() != null) {
                    double c = taskchfz.getSeriousArea();
                    seriousArea += c;
                } else {
                    double c = 0.0;
                    seriousArea += c;
                }
                /*double c = taskchfz.getSeriousArea();*/
                /** 工程区严重危害面积 */
                if (taskchfz.getEngineeringSeriousArea() != null) {
                    double d = taskchfz.getEngineeringSeriousArea();
                    engineeringSeriousArea += d;
                } else {
                    double d = 0.0;
                    engineeringSeriousArea += d;
                }
                /* double d = taskchfz.getEngineeringSeriousArea();*/


                /*engineeringhazard += b;*/
                /*seriousArea += c;*/
                /*engineeringSeriousArea += d;*/

            }
            //创建存储数据总和的对象
            CTaskChfz cTaskchfzJOb = new CTaskChfz();
            cTaskchfzJOb.setHarmTotalarea(harmTotalarea);
            cTaskchfzJOb.setEngineeringSeriousArea(engineeringSeriousArea);
            cTaskchfzJOb.setSeriousArea(seriousArea);
            cTaskchfzJOb.setEngineeringhazard(engineeringhazard);


            mmap.put("cTaskchfz", cTaskchfzJOb);
            //角色权限标识
            mmap.put("grade", "2");
            mmap.put("province", deptName);


        } else if (roleName.equals("市级")) {
            String deptName = ShiroUtils.getSysUser().getDept().getDeptName();
            double harmTotalareaWeek = 0.0;
            double engineeringhazardWeek = 0.0;
            double seriousAreaWeek = 0.0;
            double engineeringSeriousAreaWeek = 0.0;

            cTaskChfz.setCity(deptName);


            for (int j = 0; j < Originallist.size(); j++) {
                CTaskChfz taskChfz = Originallist.get(j);

                String wk = taskChfz.getWeek();
                if (week.equals(wk)) {
                    if (taskChfz.getHarmTotalarea() != null) {
                        double a = taskChfz.getHarmTotalarea();
                        harmTotalareaWeek += a;
                    } else {
                        double a = 0.0;
                        harmTotalareaWeek += a;
                    }
                    /** 工程区危害面积 */
                    if (taskChfz.getEngineeringhazard() != null) {
                        double b = taskChfz.getEngineeringhazard();
                        engineeringhazardWeek += b;
                    } else {
                        double b = 0.0;
                        engineeringhazardWeek += b;
                    }

                    /*double b = taskchfz.getEngineeringhazard();*/
                    /** 严重危害总面积 */
                    if (taskChfz.getSeriousArea() != null) {
                        double c = taskChfz.getSeriousArea();
                        seriousAreaWeek += c;
                    } else {
                        double c = 0.0;
                        seriousAreaWeek += c;
                    }
                    /*double c = taskchfz.getSeriousArea();*/
                    /** 工程区严重危害面积 */
                    if (taskChfz.getEngineeringSeriousArea() != null) {
                        double d = taskChfz.getEngineeringSeriousArea();
                        engineeringSeriousAreaWeek += d;
                    } else {
                        double d = 0.0;
                        engineeringSeriousAreaWeek += d;
                    }


                }
            }
            //创建存储数据总和的对象
            CTaskChfz cTaskSczWeek = new CTaskChfz();
            cTaskSczWeek.setHarmTotalarea(harmTotalareaWeek);
            cTaskSczWeek.setEngineeringSeriousArea(engineeringSeriousAreaWeek);
            cTaskSczWeek.setSeriousArea(seriousAreaWeek);
            cTaskSczWeek.setEngineeringhazard(engineeringhazardWeek);
            //cTaskSczWeek.setDefensiveArea(defensiveAreaWeek);
            mmap.put("cTaskchfzWeek", cTaskSczWeek);

            //根据数据库查询危害面积情况
            List<CTaskChfz> list = cTaskChfzService.selectCTaskChfzList(cTaskChfz);

            //定义统计数据合的变量
            double harmTotalarea = 0.0;
            double engineeringhazard = 0.0;
            double seriousArea = 0.0;
            double engineeringSeriousArea = 0.0;

            for (int z = 0; z < list.size(); z++) {
                CTaskChfz taskchfz = list.get(z);
                if (taskchfz.getHarmTotalarea() != null) {
                    double a = taskchfz.getHarmTotalarea();
                    harmTotalarea += a;
                } else {
                    double a = 0.0;
                    harmTotalarea += a;

                }
                /*double a = taskchfz.getHarmTotalarea();*/
                /** 工程区危害面积 */
                if (taskchfz.getEngineeringhazard() != null) {
                    double b = taskchfz.getEngineeringhazard();
                    engineeringhazard += b;
                } else {
                    double b = 0.0;
                    engineeringhazard += b;
                }

                /*double b = taskchfz.getEngineeringhazard();*/
                /** 严重危害总面积 */
                if (taskchfz.getSeriousArea() != null) {
                    double c = taskchfz.getSeriousArea();
                    seriousArea += c;
                } else {
                    double c = 0.0;
                    seriousArea += c;
                }
                /*double c = taskchfz.getSeriousArea();*/
                /** 工程区严重危害面积 */
                if (taskchfz.getEngineeringSeriousArea() != null) {
                    double d = taskchfz.getEngineeringSeriousArea();
                    engineeringSeriousArea += d;
                } else {
                    double d = 0.0;
                    engineeringSeriousArea += d;
                }


            }
            //创建存储数据总和的对象
            CTaskChfz cTaskchfzJOb = new CTaskChfz();
            cTaskchfzJOb.setHarmTotalarea(harmTotalarea);
            cTaskchfzJOb.setEngineeringSeriousArea(engineeringSeriousArea);
            cTaskchfzJOb.setSeriousArea(seriousArea);
            cTaskchfzJOb.setEngineeringhazard(engineeringhazard);


            mmap.put("cTaskchfz", cTaskchfzJOb);
            //角色权限标识
            mmap.put("grade", "3");

        } else if (roleName.equals("区级")) {

            String deptName = ShiroUtils.getSysUser().getDept().getDeptName();
            double harmTotalareaWeek = 0.0;
            double engineeringhazardWeek = 0.0;
            double seriousAreaWeek = 0.0;
            double engineeringSeriousAreaWeek = 0.0;

            cTaskChfz.setCounty(deptName);


            for (int j = 0; j < Originallist.size(); j++) {
                CTaskChfz taskChfz = Originallist.get(j);

                String wk = taskChfz.getWeek();
                if (week.equals(wk)) {
                    if (taskChfz.getHarmTotalarea() != null) {
                        double a = taskChfz.getHarmTotalarea();
                        harmTotalareaWeek += a;
                    } else {
                        double a = 0.0;
                        harmTotalareaWeek += a;
                    }
                    /** 工程区危害面积 */
                    if (taskChfz.getEngineeringhazard() != null) {
                        double b = taskChfz.getEngineeringhazard();
                        engineeringhazardWeek += b;
                    } else {
                        double b = 0.0;
                        engineeringhazardWeek += b;
                    }

                    /*double b = taskchfz.getEngineeringhazard();*/
                    /** 严重危害总面积 */
                    if (taskChfz.getSeriousArea() != null) {
                        double c = taskChfz.getSeriousArea();
                        seriousAreaWeek += c;
                    } else {
                        double c = 0.0;
                        seriousAreaWeek += c;
                    }
                    /*double c = taskchfz.getSeriousArea();*/
                    /** 工程区严重危害面积 */
                    if (taskChfz.getEngineeringSeriousArea() != null) {
                        double d = taskChfz.getEngineeringSeriousArea();
                        engineeringSeriousAreaWeek += d;
                    } else {
                        double d = 0.0;
                        engineeringSeriousAreaWeek += d;
                    }

                }
            }
            //创建存储数据总和的对象
            CTaskChfz cTaskSczWeek = new CTaskChfz();
            cTaskSczWeek.setHarmTotalarea(harmTotalareaWeek);
            cTaskSczWeek.setEngineeringSeriousArea(engineeringSeriousAreaWeek);
            cTaskSczWeek.setSeriousArea(seriousAreaWeek);
            cTaskSczWeek.setEngineeringhazard(engineeringhazardWeek);
            //cTaskSczWeek.setDefensiveArea(defensiveAreaWeek);
            mmap.put("cTaskchfzWeek", cTaskSczWeek);

            //根据数据库查询危害面积情况
            List<CTaskChfz> list = cTaskChfzService.selectCTaskChfzList(cTaskChfz);

            //定义统计数据合的变量
            double harmTotalarea = 0.0;
            double engineeringhazard = 0.0;
            double seriousArea = 0.0;
            double engineeringSeriousArea = 0.0;

            for (int z = 0; z < list.size(); z++) {
                CTaskChfz taskchfz = list.get(z);
                if (taskchfz.getHarmTotalarea() != null) {
                    double a = taskchfz.getHarmTotalarea();
                    harmTotalarea += a;
                } else {
                    double a = 0.0;
                    harmTotalarea += a;
                }
                /** 工程区危害面积 */
                if (taskchfz.getEngineeringhazard() != null) {
                    double b = taskchfz.getEngineeringhazard();
                    engineeringhazard += b;
                } else {
                    double b = 0.0;
                    engineeringhazard += b;
                }

                /*double b = taskchfz.getEngineeringhazard();*/
                /** 严重危害总面积 */
                if (taskchfz.getSeriousArea() != null) {
                    double c = taskchfz.getSeriousArea();
                    seriousArea += c;
                } else {
                    double c = 0.0;
                    seriousArea += c;
                }
                /*double c = taskchfz.getSeriousArea();*/
                /** 工程区严重危害面积 */
                if (taskchfz.getEngineeringSeriousArea() != null) {
                    double d = taskchfz.getEngineeringSeriousArea();
                    engineeringSeriousArea += d;
                } else {
                    double d = 0.0;
                    engineeringSeriousArea += d;
                }


            }
            //创建存储数据总和的对象
            CTaskChfz cTaskchfzJOb = new CTaskChfz();
            cTaskchfzJOb.setHarmTotalarea(harmTotalarea);
            cTaskchfzJOb.setEngineeringSeriousArea(engineeringSeriousArea);
            cTaskchfzJOb.setSeriousArea(seriousArea);
            cTaskchfzJOb.setEngineeringhazard(engineeringhazard);


            mmap.put("cTaskchfz", cTaskchfzJOb);
            //角色权限标识
            mmap.put("grade", "4");
        } else if (roleName.equals("国家级") || roleName.equals("管理员")) {
            //截取去除最后一个字符串
            double harmTotalareaWeek = 0.0;
            double engineeringhazardWeek = 0.0;
            double seriousAreaWeek = 0.0;
            double engineeringSeriousAreaWeek = 0.0;


            for (int j = 0; j < Originallist.size(); j++) {
                CTaskChfz taskChfz = Originallist.get(j);

                String wk = taskChfz.getWeek();
                if (week.equals(wk)) {

                    if (taskChfz.getHarmTotalarea() != null) {
                        double a = taskChfz.getHarmTotalarea();
                        harmTotalareaWeek += a;
                    } else {
                        double a = 0.0;
                        harmTotalareaWeek += a;
                    }
                    /** 工程区危害面积 */
                    if (taskChfz.getEngineeringhazard() != null) {
                        double b = taskChfz.getEngineeringhazard();
                        engineeringhazardWeek += b;
                    } else {
                        double b = 0.0;
                        engineeringhazardWeek += b;
                    }

                    /*double b = taskchfz.getEngineeringhazard();*/
                    /** 严重危害总面积 */
                    if (taskChfz.getSeriousArea() != null) {
                        double c = taskChfz.getSeriousArea();
                        seriousAreaWeek += c;
                    } else {
                        double c = 0.0;
                        seriousAreaWeek += c;
                    }
                    /*double c = taskchfz.getSeriousArea();*/
                    /** 工程区严重危害面积 */
                    if (taskChfz.getEngineeringSeriousArea() != null) {
                        double d = taskChfz.getEngineeringSeriousArea();
                        engineeringSeriousAreaWeek += d;
                    } else {
                        double d = 0.0;
                        engineeringSeriousAreaWeek += d;
                    }


                }
            }
            //创建存储数据总和的对象
            CTaskChfz cTaskSczWeek = new CTaskChfz();
            cTaskSczWeek.setHarmTotalarea(harmTotalareaWeek);
            cTaskSczWeek.setEngineeringSeriousArea(engineeringSeriousAreaWeek);
            cTaskSczWeek.setSeriousArea(seriousAreaWeek);
            cTaskSczWeek.setEngineeringhazard(engineeringhazardWeek);
            mmap.put("cTaskchfzWeek", cTaskSczWeek);

            //根据数据库查询危害面积情况
            List<CTaskChfz> list = cTaskChfzService.selectCTaskChfzList(cTaskChfz);

            //定义统计数据合的变量
            double harmTotalarea = 0.0;
            double engineeringhazard = 0.0;
            double seriousArea = 0.0;
            double engineeringSeriousArea = 0.0;

            for (int z = 0; z < list.size(); z++) {
                CTaskChfz taskchfz = list.get(z);
                if (taskchfz.getHarmTotalarea() != null) {
                    double a = taskchfz.getHarmTotalarea();
                    harmTotalarea += a;
                } else {
                    double a = 0.0;
                    harmTotalarea += a;
                }
                /** 工程区危害面积 */
                if (taskchfz.getEngineeringhazard() != null) {
                    double b = taskchfz.getEngineeringhazard();
                    engineeringhazard += b;
                } else {
                    double b = 0.0;
                    engineeringhazard += b;
                }

                /*double b = taskchfz.getEngineeringhazard();*/
                /** 严重危害总面积 */
                if (taskchfz.getSeriousArea() != null) {
                    double c = taskchfz.getSeriousArea();
                    seriousArea += c;
                } else {
                    double c = 0.0;
                    seriousArea += c;
                }
                /*double c = taskchfz.getSeriousArea();*/
                /** 工程区严重危害面积 */
                if (taskchfz.getEngineeringSeriousArea() != null) {
                    double d = taskchfz.getEngineeringSeriousArea();
                    engineeringSeriousArea += d;
                } else {
                    double d = 0.0;
                    engineeringSeriousArea += d;
                }

            }
            //创建存储数据总和的对象
            CTaskChfz cTaskchfzJOb = new CTaskChfz();
            cTaskchfzJOb.setHarmTotalarea(harmTotalarea);
            cTaskchfzJOb.setEngineeringSeriousArea(engineeringSeriousArea);
            cTaskchfzJOb.setSeriousArea(seriousArea);
            cTaskchfzJOb.setEngineeringhazard(engineeringhazard);


            mmap.put("cTaskchfz", cTaskchfzJOb);
            //角色权限标识
            mmap.put("grade", "1");
        }
    }

    /*鼠害首页计算方法*/
    private void publishRat(CTaskSczj cTaskSczj, ModelMap mmap, String roleName) {

        //获取当前时间属于哪一周
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);//设置星期一为一周开始的第一天
        calendar.setMinimalDaysInFirstWeek(4);//可以不用设置
        int weekYear = calendar.get(Calendar.YEAR);//获得当前的年
        calendar.setTimeInMillis(System.currentTimeMillis());//时间戳
        int weekOfYear = calendar.get(Calendar.WEEK_OF_YEAR);//获得当前日期属于今年的第几周
        weekOfYear = weekOfYear - 1;
        System.out.println("第几年：" + weekYear);
        System.out.println("第几周：" + weekOfYear);
        calendar.setWeekDate(weekYear, weekOfYear, 2);//获得指定年的第几周的开始日期
        long starttime = calendar.getTime().getTime();//创建日期的时间该周的第一天，
        calendar.setWeekDate(weekYear, weekOfYear, 1);//获得指定年的第几周的结束日期
        long endtime = calendar.getTime().getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateStart = simpleDateFormat.format(starttime);//将时间戳格式化为指定格式
        String dateEnd = simpleDateFormat.format(endtime);
        System.out.println(dateStart);
        System.out.println(dateEnd);

        //拼接成第几周查询库中最新的一周
        String week = "";
        if (weekOfYear < 10) {
            week = ArabicToChineseNumerals.getNumber(String.valueOf(weekOfYear).substring(0, 1));
            week = "第" + week + "周";
        } else {
            String one = ArabicToChineseNumerals.getNumber(String.valueOf(weekOfYear).substring(0, 1));
            if (one.equals("一")) {
                one = "";
            }
            String two = ArabicToChineseNumerals.getNumber(String.valueOf(weekOfYear).substring(String.valueOf(weekOfYear).length() - 1));
            week = "第" + one + "十" + two + "周";
        }

        if (roleName.equals("省级")) {
            String deptName = ShiroUtils.getSysUser().getDept().getDeptName();
            if (deptName.equals("内蒙古自治区")) {
                deptName = deptName.substring(0, deptName.length() - 3);

            } else if (deptName.equals("西藏自治区")) {
                deptName = deptName.substring(0, deptName.length() - 3);

            } else if (deptName.equals("宁夏回族自治区")) {
                deptName = deptName.substring(0, deptName.length() - 5);

            } else if (deptName.equals("新疆维吾尔自治区")) {
                deptName = deptName.substring(0, deptName.length() - 6);
            } else if (deptName.equals("广西壮族自治区")) {
                deptName = deptName.substring(0, deptName.length() - 5);
            } else {
                //截取去除最后一个字符串
                deptName = deptName.substring(0, deptName.length() - 1);
            }

            System.out.println("-------------" + deptName + "++++++++++++++");
            double harmTotalareaWeek = 0.0;
            double engineeringhazardWeek = 0.0;
            double seriousAreaWeek = 0.0;
            double engineeringSeriousAreaWeek = 0.0;
            double defensiveAreaWeek = 0.0;
            double controlareaWeek = 0.0;
            cTaskSczj.setProvince(deptName);
            //获取·到每条数据
            List<CTaskSczj> Originallist = cTaskSczjService.selectCTaskSczjList(cTaskSczj);

            for (int i = 0; i < Originallist.size(); i++) {
                CTaskSczj taskSczj = Originallist.get(i);

                String wk = taskSczj.getWeek();
                if (week.equals(wk)) {
                    double a = 0.0;
                    if (taskSczj.getHarmTotalarea() == null) {
                    } else {
                        a = taskSczj.getHarmTotalarea();
                    }
                    double b = 0.0;
                    if (taskSczj.getEngineeringhazard() == null) {
                    } else {
                        b = taskSczj.getEngineeringhazard();
                    }
                    double c = 0.0;
                    if (taskSczj.getEngineeringSeriousArea() == null) {
                    } else {
                        c = taskSczj.getEngineeringSeriousArea();
                    }
                    double d = 0.0;
                    if (taskSczj.getSeriousArea() == null) {
                    } else {
                        d = taskSczj.getSeriousArea();
                    }
                    /*当年防治面积*/
                   /* double e = 0.0;
                    if (taskSczj.getChemistryControl() == null) {
                    } else {
                        e = taskSczj.getChemistryControl();
                    }

                    double f = 0.0;
                    if (taskSczj.getcBotulinumtoxin() == null) {
                    } else {
                        f = taskSczj.getcBotulinumtoxin();
                    }

                    double g = 0.0;
                    if (taskSczj.getdBotulinumtoxin() == null) {
                    } else {
                        g = taskSczj.getdBotulinumtoxin();
                    }

                    double h = 0.0;
                    if (taskSczj.getTripterygium() == null) {
                    } else {
                        h = taskSczj.getTripterygium();
                    }

                    double m = 0.0;
                    if (taskSczj.getCurcumae() == null) {
                    } else {
                        m = taskSczj.getCurcumae();
                    }
                    double n = 0.0;
                    if (taskSczj.getHawkMouse() == null) {
                    } else {
                        n = taskSczj.getHawkMouse();
                    }
                    double o = 0.0;
                    if (taskSczj.getFoxRat() == null) {
                    } else {
                        o = taskSczj.getFoxRat();
                    }

                    double fz = e + f + g + h + m + n + o;*/
                    /*持续控制面积*/
                   /* double p = 0.0;
                    if (taskSczj.getHawkDeratization() == null) {
                    } else {
                        p = taskSczj.getHawkDeratization();
                    }
                    double q = 0.0;
                    if (taskSczj.getRodentControl() == null) {
                    } else {
                        q = taskSczj.getRodentControl();
                    }
                    double cx = p + q;
                    */
                    double cx = 0.0;
                    if (taskSczj.getControlarea() == null) {
                    } else {
                        cx= taskSczj.getControlarea();
                    }
                    double fz = 0.0;
                    if (taskSczj.getDefensiveArea() == null) {
                    } else {
                        fz= taskSczj.getDefensiveArea();
                    }


                    harmTotalareaWeek += a;
                    engineeringhazardWeek += b;
                    seriousAreaWeek += c;
                    engineeringSeriousAreaWeek += d;
                    defensiveAreaWeek += fz;
                    controlareaWeek += cx;
                }
            }
            //创建存储数据总和的对象
            CTaskSczj cTaskSczWeek = new CTaskSczj();
            cTaskSczWeek.setHarmTotalarea(harmTotalareaWeek);
            cTaskSczWeek.setEngineeringSeriousArea(engineeringSeriousAreaWeek);
            cTaskSczWeek.setSeriousArea(seriousAreaWeek);
            cTaskSczWeek.setEngineeringhazard(engineeringhazardWeek);
            cTaskSczWeek.setDefensiveArea(defensiveAreaWeek);
            cTaskSczWeek.setControlarea(controlareaWeek);

            mmap.put("cTaskSczWeek", cTaskSczWeek);

            //根据数据库查询危害面积情况
            List<CTaskSczj> list = cTaskSczjService.selectCTaskChfzList(cTaskSczj);
            /* List<CTaskSczj> list = cTaskSczjService.selectCTaskSczjParentList1(cTaskSczj);*/

            //定义统计数据合的变量
            double harmTotalarea = 0.0;
            double engineeringhazard = 0.0;
            double seriousArea = 0.0;
            double engineeringSeriousArea = 0.0;
            double defensiveArea = 0.0;
            double controlarea = 0.0;
            for (int i = 0; i < list.size(); i++) {
                CTaskSczj taskSczj = list.get(i);
                /** 危害总面积 */
                double a = 0.0;
                if (taskSczj.getHarmTotalarea() == null) {

                } else {
                    a = taskSczj.getHarmTotalarea();

                }
                /** 工程区危害面积 */
                double b = 0.0;
                if (taskSczj.getEngineeringhazard() == null) {

                } else {
                    b = taskSczj.getEngineeringhazard();
                }

                /** 严重危害总面积 */
                double c = 0.0;
                if (taskSczj.getSeriousArea() == null) {

                } else {
                    c = taskSczj.getSeriousArea();
                }

                /** 工程区严重危害面积 */
                double d = 0.0;
                if (taskSczj.getEngineeringSeriousArea() == null) {

                } else {
                    d = taskSczj.getEngineeringSeriousArea();
                }
                /** 防治面积 */
                double e = 0.0;
                if (taskSczj.getDefensiveArea() == null) {

                } else {
                    e = taskSczj.getDefensiveArea();
                }
                /** 持续控制面积 */
                double f = 0.0;
                if (taskSczj.getControlarea() == null) {

                } else {
                    f = taskSczj.getControlarea();
                }
                harmTotalarea += a;
                engineeringhazard += b;
                seriousArea += c;
                engineeringSeriousArea += d;
                defensiveArea += e;
                controlarea += f;
            }
            //创建存储数据总和的对象
            CTaskSczj cTaskSczjOb = new CTaskSczj();
            cTaskSczjOb.setHarmTotalarea(harmTotalarea);
            cTaskSczjOb.setEngineeringSeriousArea(engineeringSeriousArea);
            cTaskSczjOb.setSeriousArea(seriousArea);
            cTaskSczjOb.setEngineeringhazard(engineeringhazard);
            cTaskSczjOb.setDefensiveArea(defensiveArea);
            cTaskSczjOb.setControlarea(controlarea);

            mmap.put("cTaskSczj", cTaskSczjOb);
            //角色权限标识
            mmap.put("grade", "2");
            mmap.put("province", deptName);


        } else if (roleName.equals("市级")) {
            String deptName = ShiroUtils.getSysUser().getDept().getDeptName();
            //截取去除最后一个字符串
            double harmTotalareaWeek = 0.0;
            double engineeringhazardWeek = 0.0;
            double seriousAreaWeek = 0.0;
            double engineeringSeriousAreaWeek = 0.0;
            double defensiveAreaWeek = 0.0;
            double controlareaWeek = 0.0;
            cTaskSczj.setCity(deptName);
            //获取·到每条数据
            List<CTaskSczj> Originallist = cTaskSczjService.selectCTaskSczjList(cTaskSczj);

            for (int i = 0; i < Originallist.size(); i++) {
                CTaskSczj taskSczj = Originallist.get(i);
                String wk = taskSczj.getWeek();
                if (week.equals(wk)) {
                    double a = 0.0;
                    if (taskSczj.getHarmTotalarea() == null) {
                    } else {
                        a = taskSczj.getHarmTotalarea();
                    }
                    double b = 0.0;
                    if (taskSczj.getEngineeringhazard() == null) {
                    } else {
                        b = taskSczj.getEngineeringhazard();
                    }
                    double c = 0.0;
                    if (taskSczj.getEngineeringSeriousArea() == null) {
                    } else {
                        c = taskSczj.getEngineeringSeriousArea();
                    }
                    double d = 0.0;
                    if (taskSczj.getSeriousArea() == null) {
                    } else {
                        d = taskSczj.getSeriousArea();
                    }

                    /** 防治面积 */
                    double e = 0.0;
                    if (taskSczj.getDefensiveArea() == null) {

                    } else {
                        e = taskSczj.getDefensiveArea();
                    }
                    /** 持续控制面积 */
                    double f = 0.0;
                    if (taskSczj.getControlarea() == null) {

                    } else {
                        f = taskSczj.getControlarea();
                    }

                    harmTotalareaWeek += a;
                    engineeringhazardWeek += b;
                    seriousAreaWeek += c;
                    engineeringSeriousAreaWeek += d;
                    defensiveAreaWeek += e;
                    controlareaWeek += f;
                }
            }
            //创建存储数据总和的对象
            CTaskSczj cTaskSczWeek = new CTaskSczj();
            cTaskSczWeek.setHarmTotalarea(harmTotalareaWeek);
            cTaskSczWeek.setEngineeringSeriousArea(engineeringSeriousAreaWeek);
            cTaskSczWeek.setSeriousArea(seriousAreaWeek);
            cTaskSczWeek.setEngineeringhazard(engineeringhazardWeek);
            cTaskSczWeek.setDefensiveArea(defensiveAreaWeek);
            mmap.put("cTaskSczWeek", cTaskSczWeek);


            //根据数据库查询危害面积情况
            List<CTaskSczj> list = cTaskSczjService.selectCTaskChfzList1(cTaskSczj);

            //定义统计数据合的变量
            double harmTotalarea = 0.0;
            double engineeringhazard = 0.0;
            double seriousArea = 0.0;
            double engineeringSeriousArea = 0.0;
            double defensiveArea = 0.0;
            double controlarea = 0.0;
            for (int i = 0; i < list.size(); i++) {
                CTaskSczj taskSczj = list.get(i);
                /** 危害总面积 */
                double a = 0.0;
                if (taskSczj.getHarmTotalarea() == null) {

                } else {
                    a = taskSczj.getHarmTotalarea();

                }
                /** 工程区危害面积 */
                double b = 0.0;
                if (taskSczj.getEngineeringhazard() == null) {

                } else {
                    b = taskSczj.getEngineeringhazard();
                }

                /** 严重危害总面积 */
                double c = 0.0;
                if (taskSczj.getSeriousArea() == null) {

                } else {
                    c = taskSczj.getSeriousArea();
                }

                /** 工程区严重危害面积 */
                double d = 0.0;
                if (taskSczj.getEngineeringSeriousArea() == null) {

                } else {
                    d = taskSczj.getEngineeringSeriousArea();
                }
                /** 工程区严重危害面积 */
                double e = 0.0;
                if (taskSczj.getDefensiveArea() == null) {

                } else {
                    e = taskSczj.getDefensiveArea();
                }
                /** 工程区严重危害面积 */
                double f = 0.0;
                if (taskSczj.getControlarea() == null) {

                } else {
                    f = taskSczj.getControlarea();
                }
                harmTotalarea += a;
                engineeringhazard += b;
                seriousArea += c;
                engineeringSeriousArea += d;
                defensiveArea += e;
                controlarea += f;
            }
            //创建存储数据总和的对象
            CTaskSczj cTaskSczjOb = new CTaskSczj();
            cTaskSczjOb.setHarmTotalarea(harmTotalarea);
            cTaskSczjOb.setEngineeringSeriousArea(engineeringSeriousArea);
            cTaskSczjOb.setSeriousArea(seriousArea);
            cTaskSczjOb.setEngineeringhazard(engineeringhazard);
            cTaskSczjOb.setDefensiveArea(defensiveArea);
            cTaskSczjOb.setControlarea(controlarea);

            mmap.put("cTaskSczj", cTaskSczjOb);
            //角色权限标识
            mmap.put("grade", "3");
        } else if (roleName.equals("区级")) {
            String deptName = ShiroUtils.getSysUser().getDept().getDeptName();
            double harmTotalareaWeek = 0.0;
            double engineeringhazardWeek = 0.0;
            double seriousAreaWeek = 0.0;
            double engineeringSeriousAreaWeek = 0.0;
            double defensiveAreaWeek = 0.0;
            double controlareaWeek = 0.0;
            cTaskSczj.setCounty(deptName);
            //获取·到每条数据
            List<CTaskSczj> Originallist = cTaskSczjService.selectCTaskSczjList(cTaskSczj);
            for (int i = 0; i < Originallist.size(); i++) {
                CTaskSczj taskSczj = Originallist.get(i);
                String wk = taskSczj.getWeek();
                if (week.equals(wk)) {
                    double a = 0.0;
                    if (taskSczj.getHarmTotalarea() == null) {
                    } else {
                        a = taskSczj.getHarmTotalarea();
                    }
                    double b = 0.0;
                    if (taskSczj.getEngineeringhazard() == null) {
                    } else {
                        b = taskSczj.getEngineeringhazard();
                    }
                    double c = 0.0;
                    if (taskSczj.getEngineeringSeriousArea() == null) {
                    } else {
                        c = taskSczj.getEngineeringSeriousArea();
                    }
                    double d = 0.0;
                    if (taskSczj.getSeriousArea() == null) {
                    } else {
                        d = taskSczj.getSeriousArea();
                    }


                    /** 防治面积 */
                    double e = 0.0;
                    if (taskSczj.getDefensiveArea() == null) {

                    } else {
                        e = taskSczj.getDefensiveArea();
                    }
                    /** 持续控制面积 */
                    double f = 0.0;
                    if (taskSczj.getControlarea() == null) {

                    } else {
                        f = taskSczj.getControlarea();
                    }
                    harmTotalareaWeek += a;
                    engineeringhazardWeek += b;
                    seriousAreaWeek += c;
                    engineeringSeriousAreaWeek += d;
                    defensiveAreaWeek += e;
                    controlareaWeek += f;
                }
            }
            //创建存储数据总和的对象
            CTaskSczj cTaskSczWeek = new CTaskSczj();
            cTaskSczWeek.setHarmTotalarea(harmTotalareaWeek);
            cTaskSczWeek.setEngineeringSeriousArea(engineeringSeriousAreaWeek);
            cTaskSczWeek.setSeriousArea(seriousAreaWeek);
            cTaskSczWeek.setEngineeringhazard(engineeringhazardWeek);
            cTaskSczWeek.setDefensiveArea(defensiveAreaWeek);
            mmap.put("cTaskSczWeek", cTaskSczWeek);


            //根据数据库查询危害面积情况
            List<CTaskSczj> list = cTaskSczjService.selectCTaskChfzList2(cTaskSczj);

            //定义统计数据合的变量
            double harmTotalarea = 0.0;
            double engineeringhazard = 0.0;
            double seriousArea = 0.0;
            double engineeringSeriousArea = 0.0;
            double defensiveArea = 0.0;
            double controlarea = 0.0;
            for (int i = 0; i < list.size(); i++) {
                CTaskSczj taskSczj = list.get(i);
                /** 危害总面积 */
                double a = 0.0;
                if (taskSczj.getHarmTotalarea() == null) {

                } else {
                    a = taskSczj.getHarmTotalarea();

                }
                /** 工程区危害面积 */
                double b = 0.0;
                if (taskSczj.getEngineeringhazard() == null) {

                } else {
                    b = taskSczj.getEngineeringhazard();
                }

                /** 严重危害总面积 */
                double c = 0.0;
                if (taskSczj.getSeriousArea() == null) {

                } else {
                    c = taskSczj.getSeriousArea();
                }

                /** 工程区严重危害面积 */
                double d = 0.0;
                if (taskSczj.getEngineeringSeriousArea() == null) {

                } else {
                    d = taskSczj.getEngineeringSeriousArea();
                }
                /** 工程区严重危害面积 */
                double e = 0.0;
                if (taskSczj.getDefensiveArea() == null) {

                } else {
                    e = taskSczj.getDefensiveArea();
                }
                /** 工程区严重危害面积 */
                double f = 0.0;
                if (taskSczj.getControlarea() == null) {

                } else {
                    f = taskSczj.getControlarea();
                }
                harmTotalarea += a;
                engineeringhazard += b;
                seriousArea += c;
                engineeringSeriousArea += d;
                defensiveArea += e;
                controlarea += f;
            }
            //创建存储数据总和的对象
            CTaskSczj cTaskSczjOb = new CTaskSczj();
            cTaskSczjOb.setHarmTotalarea(harmTotalarea);
            cTaskSczjOb.setEngineeringSeriousArea(engineeringSeriousArea);
            cTaskSczjOb.setSeriousArea(seriousArea);
            cTaskSczjOb.setEngineeringhazard(engineeringhazard);
            cTaskSczjOb.setDefensiveArea(defensiveArea);
            cTaskSczjOb.setControlarea(controlarea);

            mmap.put("cTaskSczj", cTaskSczjOb);
            //角色权限标识
            mmap.put("grade", "4");

        } else if (roleName.equals("国家级") || roleName.equals("管理员")) {
            //截取去除最后一个字符串
            //定义每周更新的数据合的变量
            double harmTotalareaWeek = 0.0;
            double engineeringhazardWeek = 0.0;
            double seriousAreaWeek = 0.0;
            double engineeringSeriousAreaWeek = 0.0;
            double defensiveAreaWeek = 0.0;
            double controlareaWeek = 0.0;
            //获取·到每条数据
            List<CTaskSczj> Originallist = cTaskSczjService.selectCTaskSczjList(cTaskSczj);

            for (int i = 0; i < Originallist.size(); i++) {
                CTaskSczj taskSczj = Originallist.get(i);
                String wk = taskSczj.getWeek();
                if (week.equals(wk)) {
                    double a = 0.0;
                    if (taskSczj.getHarmTotalarea() == null) {
                    } else {
                        a = taskSczj.getHarmTotalarea();
                    }
                    double b = 0.0;
                    if (taskSczj.getEngineeringhazard() == null) {
                    } else {
                        b = taskSczj.getEngineeringhazard();
                    }
                    double c = 0.0;
                    if (taskSczj.getEngineeringSeriousArea() == null) {
                    } else {
                        c = taskSczj.getEngineeringSeriousArea();
                    }
                    double d = 0.0;
                    if (taskSczj.getSeriousArea() == null) {
                    } else {
                        d = taskSczj.getSeriousArea();
                    }
                    /** 防治面积 */
                    double e = 0.0;
                    if (taskSczj.getDefensiveArea() == null) {

                    } else {
                        e = taskSczj.getDefensiveArea();
                    }
                    /** 持续控制面积 */
                    double f = 0.0;
                    if (taskSczj.getControlarea() == null) {

                    } else {
                        f = taskSczj.getControlarea();
                    }

                    harmTotalareaWeek += a;
                    engineeringhazardWeek += b;
                    seriousAreaWeek += c;
                    engineeringSeriousAreaWeek += d;
                    defensiveAreaWeek += e;
                    controlareaWeek += f;
                }
            }
            //创建存储数据总和的对象
            CTaskSczj cTaskSczWeek = new CTaskSczj();
            cTaskSczWeek.setHarmTotalarea(harmTotalareaWeek);
            cTaskSczWeek.setEngineeringSeriousArea(engineeringSeriousAreaWeek);
            cTaskSczWeek.setSeriousArea(seriousAreaWeek);
            cTaskSczWeek.setEngineeringhazard(engineeringhazardWeek);
            cTaskSczWeek.setDefensiveArea(defensiveAreaWeek);
            cTaskSczWeek.setControlarea(controlareaWeek);

            mmap.put("cTaskSczWeek", cTaskSczWeek);


            //根据数据库查询危害面积情况
            List<CTaskSczj> list = cTaskSczjService.selectCTaskChfzList3(cTaskSczj);

            //定义统计数据合的变量
            double harmTotalarea = 0.0;
            double engineeringhazard = 0.0;
            double seriousArea = 0.0;
            double engineeringSeriousArea = 0.0;
            double defensiveArea = 0.0;
            double controlarea = 0.0;
            for (int i = 0; i < list.size(); i++) {
                CTaskSczj taskSczj = list.get(i);
                /** 危害总面积 */
                double a = 0.0;
                if (taskSczj.getHarmTotalarea() == null) {

                } else {
                    a = taskSczj.getHarmTotalarea();

                }
                /** 工程区危害面积 */
                double b = 0.0;
                if (taskSczj.getEngineeringhazard() == null) {

                } else {
                    b = taskSczj.getEngineeringhazard();
                }

                /** 严重危害总面积 */
                double c = 0.0;
                if (taskSczj.getSeriousArea() == null) {

                } else {
                    c = taskSczj.getSeriousArea();
                }

                /** 工程区严重危害面积 */
                double d = 0.0;
                if (taskSczj.getEngineeringSeriousArea() == null) {

                } else {
                    d = taskSczj.getEngineeringSeriousArea();
                }
                /** 工程区严重危害面积 */
                double e = 0.0;
                if (taskSczj.getDefensiveArea() == null) {

                } else {
                    e = taskSczj.getDefensiveArea();
                }
                /** 工程区严重危害面积 */
                double f = 0.0;
                if (taskSczj.getControlarea() == null) {

                } else {
                    f = taskSczj.getControlarea();
                }
                harmTotalarea += a;
                engineeringhazard += b;
                seriousArea += c;
                engineeringSeriousArea += d;
                defensiveArea += e;
                controlarea += f;
            }
            //创建存储数据总和的对象
            CTaskSczj cTaskSczjOb = new CTaskSczj();
            cTaskSczjOb.setHarmTotalarea(harmTotalarea);
            cTaskSczjOb.setEngineeringSeriousArea(engineeringSeriousArea);
            cTaskSczjOb.setSeriousArea(seriousArea);
            cTaskSczjOb.setEngineeringhazard(engineeringhazard);
            cTaskSczjOb.setDefensiveArea(defensiveArea);
            cTaskSczjOb.setControlarea(controlarea);

            mmap.put("cTaskSczj", cTaskSczjOb);
            //角色权限标识
            mmap.put("grade", "1");
        }
    }
}
