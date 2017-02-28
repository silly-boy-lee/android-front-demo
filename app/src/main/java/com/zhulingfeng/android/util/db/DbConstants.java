package com.zhulingfeng.android.util.db;

/**
 * @ClassName: DbConstants
 * @description: 数据库常量集
 * @author:  Mr.Lee
 */
@SuppressWarnings({"unused"})
public class DbConstants {
    /**
     * @FieldName: DB_NAME
     * @description: 数据库名
     */
    public static final String DB_NAME = "cmsg_android.db";
    /**
     * @FieldName: DB_VERSION
     * @description: 数据库版本号
     */
    public static final int DB_VERSION = 1;

    /**
     * @FieldName: TERMINATOR
     * @description: sql语句结束符
     */
    private static final String TERMINATOR = ";";

    /**
     * @FieldName: CREATE_DEPT_TABLE_SQL
     * @description: 部门信息表建表语句
     */
    public static final StringBuffer CREATE_DEPT_TABLE_SQL = new StringBuffer();


    static {
        CREATE_DEPT_TABLE_SQL.append("create table HR_B_DEPT(INNERID String PRIMARY KEY ,DEPTCODE text,DEPTNAME text,PARENTID text);");
    }
}
